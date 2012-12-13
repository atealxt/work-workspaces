package pdms.platform.configeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目启动时的设置
 * @author LUSuo(atealxt@gmail.com)
 */
public class StartUpConfig extends HttpServlet implements ServletContextListener {

    /**
     * 取得ServletContext的RealPath
     */
    public static String REALPATH;
    private static Log logger = LogFactory.getLog(StartUpConfig.class);

    @Override
    public final void init() throws ServletException {
        logger.info("StartUpConfig init");
        REALPATH = getServletContext().getRealPath("/");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("StartUpConfig contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("StartUpConfig contextDestroyed");
    }
}
