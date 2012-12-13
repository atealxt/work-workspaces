/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class Test_RunSequence_Servlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {   
        System.out.println("Test_RunSequence_Servlet!");
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }     
    
}
