<%-- 
    Document   : test1
    Created on : 2008/01/10, 16:54:22
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test1_Dispatch</title>
    </head>
    <body>
    <h2>test 1: Dispatch</h2>
        
    <html:form method="get" action="/test1_Dispatch.do">        
        <html:text property="t1" size="20" value="${test1_Dispatch}" disabled="true"/><br/>
        <html:submit property="action">
            <bean:message key="test1.submit1"/>
        </html:submit><br/>
        <html:submit property="action">
            <bean:message key="test1.submit2"/>
        </html:submit><br/>
    </html:form>        
        
    <hr/><a href="index.htm">index page</a><br/>
    </body>
</html>
