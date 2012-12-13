<%-- 
    Document   : test7_dispatch
    Created on : 2008/06/23, 10:56:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test7_dispatch</title>
    </head>
    <body>
        ${request.act}<br/>
        
        <s:form action="Test7_dispatch!act1">
            <input type="submit" value="submit1"/>
        </s:form>
        <s:form action="Test7_dispatch!act2">
            <input type="submit" value="submit2"/>
        </s:form>        
    </body>
</html>
