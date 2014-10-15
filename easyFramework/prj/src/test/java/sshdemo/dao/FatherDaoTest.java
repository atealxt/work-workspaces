package sshdemo.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sshdemo.TestBase;
import sshdemo.entity.Child;
import sshdemo.entity.Father;

public class FatherDaoTest extends TestBase {

    @Autowired
    @Qualifier("FatherDao")
    protected FatherDao fatherDao;

    @Test
    public void testCount() {
        Assert.assertTrue(fatherDao.count() > 0);
    }

    @Test
    public void testFind() {
        Father f = fatherDao.findByName("tom");
        Assert.assertNotNull(f);
        Assert.assertNotNull(fatherDao.findById(f.getId()));
    }

    @Override
    public void execute() throws Exception {
        Child c = new Child();
        c.setName("aaa");

        Father f = fatherDao.findByName("tom");
        logger.debug(f.getChildren());
        f.getChildren().add(c);
        fatherDao.save(f);

        Father ff = fatherDao.findById(f.getId());
        logger.debug(ff.getChildren());
    }
}