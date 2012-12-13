package main;

import java.io.IOException;
import java.util.Arrays;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

import snmp.Agent;
import snmp.Manager;
import snmp.SnmpManager;

public class Main {

    private static Manager manager = new Manager();
    private static Agent agent = new Agent();

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws Exception {
        // 加载Agent和Manager监听

        manager.start();
        agent.start();

        // test
        sendTrap();
        Thread.sleep(1000);
        sendGet();

        System.exit(0);
    }

    private static void sendTrap() {
        try {
            SnmpManager.Trap(manager.getAddress(), Arrays
                    .asList(new VariableBinding(new OID("1.3.6.1.2.3377.10.1.1.1.1"), new OctetString("a snmp trap")),
                            new VariableBinding(new OID("1.2.3.4.5"), new OctetString("a MIB address like this"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendGet() throws IOException {
        System.out.println(SnmpManager.Get(agent.getAddress(), "public", "1.2.3.4.5"));
    }

}
