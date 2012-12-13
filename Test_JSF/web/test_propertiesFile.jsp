<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_propertiesFile</title>
    </head>
    <body>
        <h3>Document   : test_propertiesFile</h3>
        <h3>Created on : Aug 7, 2008, 9:55:56 AM</h3>
        <h3>Author     : Administrator</h3> 
        <f:view>
                           
        <f:loadBundle basename="jsftest" var="varProperties"/>
        <h:outputText value="#{varProperties.firsttest}" /><br/>
        <h:outputText value="#{varProperties.encodetest}" /><br/>
        <h:outputFormat value="#{varProperties.paramtest}">
            <f:param value="Ashurei"/>
            <f:param value="Luis"/>
        </h:outputFormat><br/>        
        
        
        </f:view>
    </body>
</html>
