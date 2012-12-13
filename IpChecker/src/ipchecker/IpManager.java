package ipchecker;

import ipchecker.util.PropertiesUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class IpManager {

    public static void indeal(final String ip) {
        if (!dealing(ip)) {
            DEALING_IP.add(ip);
        }
    }

    public static void outdeal(final String ip) {
        if (dealing(ip)) {
            DEALING_IP.remove(ip);
        }
    }

    public static boolean dealing(final String ip) {
        if (DEALING_IP.contains(ip)) {
            return true;
        }
        return false;
    }

    public static String getIpInfo(final String ip) {
        return IPTABLE.get(ip);
    }

    public static void writeIpInfo(final String ip, final String now) {
        String ipInfo = IPTABLE.get(ip);
        if (ipInfo == null) {
            IPTABLE.put(ip, now);
            return;
        }
        String[] times = ipInfo.split(" ");
        if (times.length >= MAX_CONNECT) {
            ipInfo = ipInfo.substring(ipInfo.indexOf(" ") + 1);
        }
        IPTABLE.put(ip, ipInfo + " " + now);
    }

    private static Set<String> DEALING_IP = Collections.synchronizedSet(new LinkedHashSet<String>());

    /**
     * 项目启动时为空，随着访问的增加填充内容。
     * key:IP value:访问时间1 访问时间2 访问时间n
     */
    public static Map<String, String> IPTABLE = Collections.synchronizedMap(new HashMap<String, String>());

    private final static int MAX_CONNECT = Integer.parseInt(PropertiesUtils.getValue("ip.maxconnect"));
}
