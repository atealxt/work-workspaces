package htmlunitdemo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class IndexServlet extends HttpServlet {

    protected Logger logger = Logger.getLogger(getClass());

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {

        logger.debug(req.getParameter("text1"));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
