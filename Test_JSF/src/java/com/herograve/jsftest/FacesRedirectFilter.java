/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.jsftest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Filter that redirects all requests sent to blah.jsp to blah.faces.
 *  Fixes the very annoying error messages you get if you access
 *  JSF pages with .jsp extensions. However, this filter assumes three things:
 *  <UL>
 *    <LI>The extension is .faces. You can change this in web.xml, but this
 *        code will not pick up the change automatically.
 *        (TODO: can the extension be determined automatically?)
 *    <LI>You have no non-JSF JSP pages that you want to access with .jsp extensions.
 *    <LI>The welcome-file name is index.jsp. If the URL ends with / and the system
 *        forwards to a .jsp page, the filter is invoked but is not told
 *        the file name.
 *  </UL>
 *  See <A HREF="http://www.coreservlets.com/JSF-Tutorial">
 *  the coreservlets.com JSF tutorial</A>.
 */

public class FacesRedirectFilter implements Filter {
  private final static String EXTENSION = "jsf";


    public void init(FilterConfig arg0) throws ServletException {        
    }

    public void destroy() {
    }  
  
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)
                            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String uri = request.getRequestURI();

        System.out.println(uri);

        if (uri.endsWith(".jsp")) {
            int length = uri.length();
            String newAddress = uri.substring(0, length-3) + EXTENSION;
            response.sendRedirect(newAddress);
        }
        else{
            response.sendRedirect("welcomeJSF.jsf");
        }
    }
    

}
