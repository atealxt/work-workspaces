package pdms.platform.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.platform.core.EasyAction;
import pdms.platform.service.A0000TestService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0000TestAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0000TestAction.class);
    private A0000TestService testService;

    public String test1() throws Exception {
        testService.showmsg();

        logger.info(getSession());
        logger.info(getRequest());
        logger.info(getResponse());

        return SUCCESS;
    }

    public String test2() throws Exception {
        return SUCCESS;
    }

//    public A0000TestService getTestService() {
//        return testService;
//    }

    public void setTestService(A0000TestService testService) {
        this.testService = testService;
    }
}
