package sshdemo.client;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sshdemo.TestBase;
import sshdemo.dao.ChildDao;
import sshdemo.dao.FatherDao;
import sshdemo.service.FatherAndChildService;
import sshdemo.service.ServiceA;

public class AutoRollBack extends TestBase {

    @Autowired
    @Qualifier("FatherAndChildService")
    protected FatherAndChildService fatherAndChildService;
    @Autowired
    @Qualifier("ServiceA")
    protected ServiceA serviceA;
    @Autowired
    @Qualifier("ChildDao")
    protected ChildDao childDao;
    @Autowired
    @Qualifier("FatherDao")
    protected FatherDao fatherDao;

    @Test
    public void testAutoRollback1() {
        try {
            fatherAndChildService.testTransaction();
            assertNull(childDao.findByName("ff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAutoRollback2() {
        try {
            serviceA.testTransaction();
            assertNull(childDao.findByName("ff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws Exception {}

}
