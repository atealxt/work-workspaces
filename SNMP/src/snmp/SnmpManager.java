package snmp;

import java.io.IOException;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * TODO close all connection??
 * */
public final class SnmpManager {

    public static void Trap(Address targetAddress, List<VariableBinding> data) throws IOException {
        // 设置 target
        CommunityTarget target = new CommunityTarget();
        target.setAddress(targetAddress);

        // 通信不成功时的重试次数
        target.setRetries(2);
        // 超时时间
        target.setTimeout(1500);
        // snmp版本
        target.setVersion(SnmpConstants.version2c);

        // 创建 PDU
        PDU pdu = new PDU();
        for (VariableBinding d : data) {
            pdu.add(d);
        }
        pdu.setType(PDU.TRAP);

        TransportMapping transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);

        // // 向Agent发送PDU，并接收Response
        // ResponseEvent respEvnt = snmp.send(pdu, target);
        // // 解析Response
        // if (respEvnt != null && respEvnt.getResponse() != null) {
        // for (VariableBinding obj : (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings()) {
        // System.out.println("Agent: " + obj.getOid() + " : " + obj.getVariable());
        // }
        // }

        snmp.send(pdu, target);
        snmp.close();
    }

    @SuppressWarnings("unchecked")
    public static List<VariableBinding> Get(Address targetAddress, String community, String oid) throws IOException {

        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);

        // 创建共同体对象CommunityTarget
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        // 通信不成功时的重试次数
        target.setRetries(2);
        // 超时时间
        target.setTimeout(1500);
        // snmp版本
        target.setVersion(SnmpConstants.version2c);

        TransportMapping transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        // 向Agent发送PDU，并接收Response
        ResponseEvent respEvnt = snmp.send(pdu, target);
        snmp.close();

        // 解析Response
        if (respEvnt != null && respEvnt.getResponse() != null) {
            return respEvnt.getResponse().getVariableBindings();
        }
        return null;
    }

    // TODO
    // SET
}
