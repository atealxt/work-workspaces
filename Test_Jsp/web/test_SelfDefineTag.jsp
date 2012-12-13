<%-- 
    Document   : test_Tag
    Created on : 2008/05/22, 14:56:39
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="atea" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_Tag</title>
    </head>
    <body>

<form method="POST">
    
    <h5>test1</h5>

    <input type="text" value="test!!" name="t1" id="t1" /><br/>
    <textarea name="address" rows="3" cols="30">
        <c:if test="${pageContext.request.method == 'POST' && !empty param.t1}">
            <c:out value="${param.t1}"/>
        </c:if>        
    </textarea>
    <br><input type="submit" value="click me!"/>

    <h5>test2</h5>
    <atea:test_tag_testarea name="hahaha" id="id!"/>


</form>        
        
    </body>
</html>
