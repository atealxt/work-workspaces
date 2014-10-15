package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        String json = "[{ \"attr\" : { \"id\" : " + (Integer.parseInt(id) * 2) + " }, \"data\" : \"child From " + id
                + "\" ,\"state\" : \"closed\" } ]";
        System.out.println("json: " + json);
        response.getOutputStream().print(json);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
