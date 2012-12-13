<%-- 
    Document   : test_sessionLive
    Created on : 2008/05/20, 10:24:03
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h5>close the page and open this page</h5>
        <% 
            
            String str = (String)session.getAttribute("sessionlive"),strCookie;

            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies){
                if("sessionid".equals(cookie.getName())){
                    strCookie = cookie.getValue();
                    if(strCookie!=null){
                        out.println(strCookie+"<br/>"); 
                    }
                }
            }

            out.print(str); 
            
        %>
    </body>
</html>
