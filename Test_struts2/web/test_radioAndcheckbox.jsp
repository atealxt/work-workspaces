<%-- 
    Document   : test5_radioAndcheckbox
    Created on : 2008/06/23, 9:54:59
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>   

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test5_radioAndcheckbox</title>
    </head>
    <body>
        <s:form action="Test5_radioAndcheckbox">
            <s:radio name="radiotest"
            list="#{'5':'pass' , '2':'fail'}"
            value='2'
            /><br/>
            
            <s:checkboxlist
            list="{'Red', 'Blue', 'Green'}"
            name="favoriteColor"/><br/>   
            
            <s:checkboxlist name="skills1"
            list="{ 'Java', '.Net', 'RoR', 'PHP' }"
            value="{ 'Java', '.Net' }" /><br/>
            
            <s:checkboxlist name="skills2"
            label="Skills 2"
            list="#{ 1:'Java', 2: '.Net', 3: 'RoR', 4: 'PHP' }"
            value="{ 1, 2, 3 }"/>            
            
        </s:form>
    </body>
</html>
