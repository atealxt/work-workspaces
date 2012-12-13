package snmp;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

public abstract class Snmpter implements CommandResponder {

    protected MultiThreadedMessageDispatcher dispatcher;
    protected Snmp snmp;
    protected Address listenAddress;
    protected ThreadPool threadPool;
    protected TransportMapping transport;

    public void start() {
        try {
            init();
            run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(new StringBuilder("\"").append(threadPool.getName()).append("\"")
                .append(" Listener started"));
    }

    protected abstract void init() throws RuntimeException, IOException;

    private void run() throws RuntimeException, IOException {
        // 对TCP与UDP协议进行处理
        if (listenAddress instanceof UdpAddress) {
            transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
        } else {
            transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
        }
        snmp = new Snmp(dispatcher, transport);
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        snmp.listen();
        snmp.addCommandResponder(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public/* synchronized(need not yet) */void processPdu(CommandResponderEvent respEvnt) {
        // 解析Response
        if (respEvnt != null && respEvnt.getPDU() != null) {

            for (VariableBinding obj : (Vector<VariableBinding>) respEvnt.getPDU().getVariableBindings()) {
                System.out.println(new StringBuilder(this.getClass().getSimpleName()).append("\t")
                        .append(PDU.getTypeString(respEvnt.getPDU().getType())).append(":\t").append(obj.getOid())
                        .append(" - ").append(obj.getVariable()));
            }

            UdpAddress address = (UdpAddress) respEvnt.getPeerAddress();
            System.out.print("address : " + address.getInetAddress().getHostAddress());
            System.out.print("\tport : " + address.getPort());
            System.out.println("\tstatus : " + respEvnt.getPDU().getErrorStatusText());
        }
    }

    public Address getAddress() {
        return listenAddress;
    }
}
