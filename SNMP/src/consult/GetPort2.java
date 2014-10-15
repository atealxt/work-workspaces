package consult;

import java.io.IOException;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class GetPort2 {

    private String ipAddress;
    private String community;

    public static String[] oids = { ".1.3.6.1.2.1.2.2.1.2", // "ifDescr",
            ".1.3.6.1.2.1.2.2.1.3", // "ifType",
            ".1.3.6.1.2.1.2.2.1.5", // "ifSpeed",
            ".1.3.6.1.2.1.2.2.1.6", // "ifPhysAddress",
            ".1.3.6.1.2.1.2.2.1.8", // "ifOperStatus",
            ".1.3.6.1.2.1.4.20.1.2", // "ipAdEntIfIndex",
            ".1.3.6.1.2.1.31.1.1.1.18", // "ifAlias",
            ".1.3.6.1.2.1.31.1.1.1.1", // "ifName"

    };
    int[] type = { 1, // "ifDescr",
            2, // "ifType",
            2, // "ifSpeed",
            3, // "ifPhysAddress",
            2, // "ifOperStatus",
            4, // "ipAdEntIfIndex",
            1, // "ifAlias",
            1 // "ifName"
    };

    public GetPort2(String ipAddress, String community) {
        this.ipAddress = ipAddress;
        this.community = community;
    }

    @SuppressWarnings("unchecked")
    private String getPortInfo(int id) {
        String str = "";
        try {
            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();
            PDUFactory pf = new DefaultPDUFactory(PDU.GET);
            TableUtils tu = new TableUtils(snmp, pf);

            OID[] columns = new OID[1];
            String oid = oids[id];
            Address targetAddress = GenericAddress.parse(ipAddress + "/161");
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);
            target.setVersion(SnmpConstants.version2c);
            target.setTimeout(1000);
            target.setRetries(1);

            columns[0] = new VariableBinding(new OID(oid)).getOid();

            List list = tu.getTable(target, columns, null, null);

            for (int i = 0; i < list.size(); i++) {
                TableEvent te = (TableEvent) list.get(i);
                VariableBinding[] vb = te.getColumns();
                for (int j = 0; j < vb.length; j++) {
                    str += vb[j].getOid().toString().substring(oid.length()) + "___" + vb[j].getVariable() + "====";
                }
            }
            snmp.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return str;
    }

    public String getDeviceInfo() {
        String str = "";
        for (int index = 0; index < oids.length; index++) {
            str += "********";
            str += getPortInfo(index);
        }

        return str;
    }

    public static void main(String[] args) {
        GetPort2 gp = new GetPort2("10.13.50.129", "public");
        String str = gp.getDeviceInfo();
        System.out.println(str);
    }
}