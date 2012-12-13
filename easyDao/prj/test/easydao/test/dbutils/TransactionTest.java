package easydao.test.dbutils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import easydao.dbutils.DBUtils;
import easydao.test.TestBase;

public class TransactionTest extends TestBase {

    @Override
    public void execute() throws Exception {

        DBUtils dBUtils = new DBUtils(false);

        List<Map<String, Object>> result = Collections.emptyList();
        try {
            dBUtils.beginTransaction();

            result = dBUtils.query("select * from child where 1=?", 1);

            dBUtils.commitTransaction();
        } catch (Exception e) {
            dBUtils.rollbackTransaction();
            e.printStackTrace();
        } finally {
            dBUtils.closeConnection();
        }

        Assert.assertTrue(result.size() > 0);
    }
}
