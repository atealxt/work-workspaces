package snmp;

import java.io.IOException;

import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

public class Manager extends Snmpter {

    @Override
    protected void init() throws RuntimeException, IOException {
        threadPool = ThreadPool.create("SNMP管理程序服务器", ConfigConstant.MANAGER_THREAD_POOL_SIZE);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
        listenAddress = GenericAddress.parse(System.getProperty("snmp4j.listenAddress", SnmpUtils
                .makeSnmpUrl(ConfigConstant.UDP, ConfigConstant.MANAGER_IP, ConfigConstant.MANAGER_PORT)));
    }

}
