<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_jstl</title>
    </head>
    <body>
        <h3>Document   : test_jstl</h3>
        <h3>Created on : Aug 5, 2008, 9:38:29 AM</h3>
        <h3>Author     : Administrator</h3>        
                
        <c:forEach var="i" begin="1" end="10">
            <c:out value="${i}"/>
        </c:forEach>   
        <br/>
        
        <c:forTokens var="color"
        items="(red (orange) yellow)(green)((blue) violet)"
        delims="()">
            <c:out value="${color}"/>
        </c:forTokens>        
        
        
    </body>
</html>
