<%-- 
    Document   : firsttest
    Created on : 2007/12/20, 11:16:13
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="FirstTestTagBean" class="com.mytest.firsttest.firsttest"/><!--means create javabeans obj,and set it into request-->
<c:set target="${FirstTestTagBean}" property="stringFirstTest" value="this is first test."/>
<c:set target="${FirstTestTagBean}" property="name" value="this is the name."/>
<c:set var="iteratortest" value="${FirstTestTagBean.iteratorTest}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/JavaScript">
            
        </script>
    </head>
    <body>
        
    <html:form method="get" action="/firsttest.do">        
    <!-- 
        <form method="get" action="./firsttest.do"/>
    -->

        <h4>Test 1:</h4> 
        <html:text property="t1" size="20" value="${msg}" disabled="true"/><br/>
        <html:submit property="s" value="submit1"/>
        
        <h4>Test 2:</h4>
        <table border="1">
            <tr>
                <td>bean:message</td>
                <td><bean:message key="firsttest.beanmessage"/></td>                
            </tr>
            <tr>
                <td>bean:write</td>
                <td><bean:write name="FirstTestTagBean" property="stringFirstTest" /></td>                
            </tr>
            <tr>
                <td>bean:define</td>
                <td>
                    <bean:define id="DefineFirstTest" type="java.lang.String" property="stringFirstTest" value="abc"/>
                    <bean:write name="DefineFirstTest" />
                </td>                
            </tr>  
            <tr>
                <td>logic:present<br/>logic:iterate</td>
                <td>
                <logic:present name="FirstTestTagBean">
                    <logic:iterate id="test" name="iteratortest">
                        <bean:write name="test"/><br/>
                    </logic:iterate>
                </logic:present>
                </td>                
            </tr>            
        </table>
             
    </html:form>
    
    <hr/><a href="index.htm">index page</a><br/>
    
    </body>
</html>
