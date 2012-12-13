package pdms.platform.configeration;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.context.ApplicationContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DIManager {

    private static Log logger = LogFactory.getLog(DIManager.class);
    private static AbstractApplicationContext applicationContext;//for client
    private static WebApplicationContext ctx;//for web

    private DIManager() {
    }


//    static {
//        if (applicationContext == null) {
//            logger.info("ClassPathXmlApplicationContext init");
//            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//            applicationContext.registerShutdownHook();
//        }
//    }
//    /**
//     * get ApplicationContext
//     */
//    public static ApplicationContext getContext() {
//        return applicationContext;
//    }
    /**
     * get DI object
     */
    public static Object getBean(String name) {

        if (ctx != null) {
            return ctx.getBean(name);
        } else if (applicationContext != null) {
            return applicationContext.getBean(name);
        } else {
            ServletContext sc = ServletActionContext.getServletContext();
            if (sc != null) {
                logger.info("WebApplicationContext init");
                ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
                if (ctx != null) {
                    return ctx.getBean(name);
                }
            }

            logger.info("ClassPathXmlApplicationContext init");
            applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            applicationContext.registerShutdownHook();
            return applicationContext.getBean(name);
        }
    }
}
