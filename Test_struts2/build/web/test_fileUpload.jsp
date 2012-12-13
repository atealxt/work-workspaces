<%-- 
    Document   : test8_fileUpload
    Created on : Jun 27, 2008, 5:03:32 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello World!</h2>
        
        <s:form action="Test8_fileUpload" method="post" enctype="multipart/form-data">            
            <input type="file" name="myDoc" value="Browse ..." />
            <input type="submit" />            
            
        </s:form>
        
    </body>
</html>
