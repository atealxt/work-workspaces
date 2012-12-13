package snmp;

/**
 * TODO 放入XML动态加载、设置系统默认加载模板类etc...
 */
public class ConfigConstant {

    public static String UDP = "UDP";
    public static String TCP = "TCP";
    
    public static String MANAGER_IP = "localhost";
    public static String AGENT_IP = "localhost";

    public static String MANAGER_PORT = "162";
    public static String AGENT_PORT = "161";

    public static int MANAGER_THREAD_POOL_SIZE = 50;
    public static int AGENT_THREAD_POOL_SIZE = 10;
}
