<%-- 
    Document   : test3_session
    Created on : 2008/03/19, 11:09:38
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <html:form method="get" action="/test3_session.do">
            
            <html:text property="t1" size="20" value="${SessionValue}" disabled="true"/><br/>
            <html:submit property="s" value="ShowSessionValue"/>    
            
        </html:form>
        
        <hr/><a href="index.htm">index page</a><br/>
    </body>
</html>
