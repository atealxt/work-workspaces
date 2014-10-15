package ipchecker;

import ipchecker.util.PropertiesUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public final class IpChecker {

    public static boolean valid(final String ip) {
        if (notIpv4(ip)) {
            System.out.println("not a valid IPv4 address: " + ip);
            return false;
        }

        if (IpManager.dealing(ip)) {
            System.out.println(ip + "正在处理中。频繁攻击性访问直接返回false");
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = "";
        Date currentTime = new Date();
        now = format.format(currentTime);

        try {
            IpManager.indeal(ip);

            String ipInfo = IpManager.getIpInfo(ip);// 字符串格式：访问时间1 访问时间2 访问时间n
            if (ipInfo == null) {
                return true;
            }

            String[] times = ipInfo.split(" ");
            if (times.length < MAX_CONNECT) {
                return true;
            }

            try {
                int during = (int) (currentTime.getTime() - format.parse(times[0]).getTime()) / 1000;
                return (during > MONITOR_TIME);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }

        } finally {
            IpManager.writeIpInfo(ip, now);
            IpManager.outdeal(ip);
        }
    }

    private static boolean notIpv4(final String ip) {
        return !PATTERN_IPV4_DEMO.matcher(ip).matches();
    }

    private static Pattern PATTERN_IPV4_DEMO = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
    private final static int MAX_CONNECT = Integer.parseInt(PropertiesUtils.getValue("ip.maxconnect"));
    private final static int MONITOR_TIME = Integer.parseInt(PropertiesUtils.getValue("ip.monitor.time"));

}
