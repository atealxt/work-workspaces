package easydao.test.connector;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;
import easydao.dbutils.DBUtils;
import easydao.test.TestBase;

public class ConnectorDbcpTest extends TestBase {

    public static AtomicInteger COUNT = new AtomicInteger(100);

    @Override
    public void execute() {

        for (int i = 0; i < 100; i++) {
            try {
                new TT().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (COUNT.get() == 0) {
                break;
            }
        }
    }
}

class TT extends Thread {

    @Override
    public void run() {

        DBUtils dBUtils = new DBUtils(false);

        List<Map<String, Object>> result = Collections.emptyList();
        try {
            dBUtils.beginTransaction();
            Thread.sleep(100);
            result = dBUtils.query("select * from child where 1=1");
            dBUtils.commitTransaction();
        } catch (Exception e) {
            dBUtils.rollbackTransaction();
            e.printStackTrace();
        } finally {
            dBUtils.closeConnection();
        }
        Assert.assertTrue(result.size() > 0);

        ConnectorDbcpTest.COUNT.addAndGet(-1);
    }
}
