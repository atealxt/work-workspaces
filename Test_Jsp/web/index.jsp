<%-- 
    Document   : index
    Created on : 2008/05/20, 10:23:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        
<form method="POST">
        
        <% 
            
            session.setAttribute("sessionlive", "Session living");
            
            ///*
            Random random = new Random(); 
            int i = random.nextInt();
            System.out.println("set cookie: " + i);
            Cookie cookie = new Cookie("sessionid", new Integer(i).toString());
            //response.addCookie(cookie);
            //*/
        %>
        <a href="test_sessionLive.jsp">test_sessionLive.jsp</a> <br/>
        <a href="HelloServlet.jsp">HelloServlet.jsp</a> <br/>
        <a href="test_SelfDefineTag.jsp">test_SelfDefineTag.jsp</a> <br/>
        <a href="test_WordAndExcel.jsp">test_WordAndExcel.jsp</a> <br/>
        <a href="test_jstl.jsp">test_jstl.jsp</a> <br/>
        <a href="test_ie6block.jsp">test_ie6block.jsp</a> <br/>
     

</form>
        
    </body>
</html>
