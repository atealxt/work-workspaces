package snmp;

import java.io.IOException;

import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

/**
 * TODO 实际来说Agent可能是硬件商提供好的，可以不用手写？待调查
 */
public class Agent extends Snmpter {

    @Override
    protected void init() throws RuntimeException, IOException {
        threadPool = ThreadPool.create("SNMP代理程序服务器", ConfigConstant.AGENT_THREAD_POOL_SIZE);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool, new MessageDispatcherImpl());
        listenAddress = GenericAddress.parse(System.getProperty("snmp4j.listenAddress", SnmpUtils
                .makeSnmpUrl(ConfigConstant.UDP, ConfigConstant.AGENT_IP, ConfigConstant.AGENT_PORT)));
    }

}
