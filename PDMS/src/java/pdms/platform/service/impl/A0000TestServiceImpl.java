package pdms.platform.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.ExampleDao;
import pdms.components.pojo.Example;
import pdms.platform.service.A0000TestService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0000TestServiceImpl implements A0000TestService {

    private static Log logger = LogFactory.getLog(A0000TestServiceImpl.class);
    private ExampleDao testDao;

    @Override
    public void showmsg() {
        logger.info("showmsgshowmsgshowmsgshowmsg");
        Example e = testDao.findByName("firstname");
        logger.info(e);
    }

    public ExampleDao getTestDao() {
        return testDao;
    }

    public void setTestDao(ExampleDao testDao) {
        this.testDao = testDao;
    }
}
