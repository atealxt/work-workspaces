<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test10_Loop</title>
    </head>
    <body>
        <h3>Document   : test10_Loop</h3>
        <h3>Created on : Jul 9, 2008, 1:13:38 PM</h3>
        <h3>Author     : Administrator</h3>   
        
        <s:set name="loopNum" value="5" />
        <s:bean name="org.apache.struts2.util.Counter">           
           <s:param name="last" value="#loopNum" />
           <s:iterator>
             counter:<s:property/>
           </s:iterator>
        </s:bean>        
        
    </body>
</html>
