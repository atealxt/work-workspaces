<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_Interceptor</title>
    </head>
    <body>
        <h3>Document   : test_Interceptor</h3>
        <h3>Created on : Jul 30, 2008, 9:10:00 AM</h3>
        <h3>Author     : Administrator</h3>  
        
        <s:form action="Test_InterceptorAction">
            
            ${sessionScope.aaa}<br/>
            
            <s:textfield name="t1"/>
            
            <s:submit/>
            
        </s:form>
        
    </body>
</html>
