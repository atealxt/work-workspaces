package consult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class TestTrap {

    String[] genericTrapNames = {
            "COLDSTART",
            "WARMSTART",
            "LINKDOWN",
            "LINKUP",
            "AUTHENTICATIONFAILURE",
            "EGPNEIGHBORLOSS",
            "ENTERPRISE_SPECIFIC", };

    private Connection conn = null;

    private String strSql = "select name from mib_info t where t.oid=?";
    private String strSqlst = "select TRAPTYPE from MIB_SPECTRAP t where t.ENTERPRISE=? and SPECIFICID=?";

    private void initDb() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@132.77.64.25:1522:ifmdb1";
        String username = "network3";
        String pwd = "telecoom";
        Class.forName(driver); // 隐式注册数据库驱动程序
        conn = DriverManager.getConnection(url, username, pwd);
    }

    private void closeDb() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String queryName(String oid) {
        PreparedStatement ps = null;
        String name = oid;
        try {
            initDb();
            ps = conn.prepareStatement(strSql);
            ps.setString(1, oid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
            }
            closeDb();
        }
        return name;
    }

    private String querySpecificName(String enterprise, String id) {
        String name = id;
        PreparedStatement psst = null;
        try {
            initDb();
            psst = conn.prepareStatement(strSqlst);
            psst.setString(1, enterprise);
            psst.setString(2, id);
            ResultSet rs = psst.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (psst != null)
                    psst.close();
            } catch (Exception e) {
            }
            closeDb();
        }
        return name;
    }

    @SuppressWarnings("unchecked")
    public void startListen() {
        try {
            // snmp4j通过transportmapping的监听端口接收SNMP信息,所以这里初始化一个
            // transportmapping,
            // 注明本机的IP地址及接收trap的端口.
            TransportMapping transport = new DefaultUdpTransportMapping(new UdpAddress("localhost/162"));
            // 创建一个处理消息的snmp实例
            Snmp snmp = new Snmp(transport);

            // CommandResponder是一个listener,用以处理获取的trap消息
            CommandResponder trapPrinter = new CommandResponder() {

                public synchronized void processPdu(CommandResponderEvent e) {
                    try {
                        PDU command = e.getPDU();
                        if (command != null) {
                            if (command.getClass().getName().equals("org.snmp4j.PDUv1")) {
                                PDUv1 v1 = (PDUv1) command;
                                String enterprise = queryName("." + v1.getEnterprise().toString());
                                System.out.println("enterprise =" + enterprise);
                                System.out.println("genericTrap = " + genericTrapNames[v1.getGenericTrap()]);
                                System.out.println("SpecificTrap = "
                                        + querySpecificName(enterprise, String.valueOf(v1.getSpecificTrap())));
                            }
                            // 这里示例输出trap的内容.具体的trap解析等工作在这里进行.
                            System.out.println(e.toString());
                            System.out.println("type:" + PDU.getTypeString(command.getType()));
                            UdpAddress address = (UdpAddress) e.getPeerAddress();
                            System.out.println("address : " + address.getInetAddress().getHostAddress());
                            System.out.println("port : " + address.getPort());
                            System.out.println("status : " + command.getErrorStatusText());
                            System.out.println(command.toString());

                            Vector vec = command.getVariableBindings();
                            VariableBinding variable = null;
                            for (int index = 0; index < vec.size(); index++) {
                                variable = (VariableBinding) vec.get(index);
                                variable.getOid();
                                System.out.println(queryName("." + variable.getOid().toString()) + "="
                                        + variable.getVariable());
                            }
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            };

            // 在snmp实例中添加CommandResponder listener
            snmp.addCommandResponder(trapPrinter);

            System.out.println("start listening!");
            // 开始启动trap监听.listen()方法内部启动了一个线程,这个线程监听发送到transport中定义的端口
            // 的消息.
            transport.listen();
            System.out.println(transport.isListening());// 测试监听是否正常

            // 等待一段测试时间,在这段时间可以发送trap信息测试.
            while (true)
                Thread.sleep(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestTrap trap = new TestTrap();
        trap.startListen();
    }
}
