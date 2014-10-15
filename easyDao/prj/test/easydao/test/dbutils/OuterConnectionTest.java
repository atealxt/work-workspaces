package easydao.test.dbutils;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import easydao.connector.impl.ConnectorDriverManager;
import easydao.dbutils.DBUtils;
import easydao.test.TestBase;

public class OuterConnectionTest extends TestBase {

    @Override
    public void execute() throws Exception {
        Connection conn = null;
        try {
            conn = ConnectorDriverManager.getInstance().getConnection();
            DBUtils dBUtils = new DBUtils(conn);
            List<Map<String, Object>> result = dBUtils.query("select * from child");
            System.out.println(result);
        } catch (Exception e) {
            if(conn!=null){
                conn.close();
            }
        }
    }
}
