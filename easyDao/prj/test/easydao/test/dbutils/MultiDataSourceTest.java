package easydao.test.dbutils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import easydao.dbutils.DBUtils;
import easydao.test.TestBase;

public class MultiDataSourceTest extends TestBase {

    private static final String TEST_DS_NAME = "ds1";

    @Override
    public void execute() throws Exception {
        DBUtils dBUtils = new DBUtils(false, TEST_DS_NAME);

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
