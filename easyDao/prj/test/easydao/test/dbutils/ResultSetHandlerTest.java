package easydao.test.dbutils;

import java.util.List;
import java.util.Map;

import easydao.dbutils.DBUtils;
import easydao.test.TestBase;

public class ResultSetHandlerTest extends TestBase {

    @Override
    public void execute() throws Exception {
        List<Map<String, Object>> result = new DBUtils().query("select * from child");
        System.out.println(result);
    }
}
