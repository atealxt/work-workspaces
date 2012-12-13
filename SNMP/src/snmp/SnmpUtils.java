package snmp;

public class SnmpUtils {

    public static String makeSnmpUrl(String protocol, String ipAddress, String port) {
        StringBuffer address = new StringBuffer();
        address.append(protocol);
        address.append(":");
        address.append(ipAddress);
        address.append("/");
        address.append(port);
        return address.toString();
    }
}
