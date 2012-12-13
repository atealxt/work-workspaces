<%-- 
    Document   : test6_date
    Created on : 2008/06/23, 10:26:06
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test6_date</title>
    </head>
    <body>
        <s:form action="Test6_date"> 
            <s:set name="dateTest" value="#request.dateTest"/>
            <s:date name="dateTest" format="yyyy-MM-dd"/>
            <!--or you can just fall back on a predefined format with key 'struts.date.format' in your properties file.-->
            
            <br/>
            <input type="submit" value="submit" />            


            
        </s:form>
    </body>
</html>
