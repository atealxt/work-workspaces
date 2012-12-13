<!--notice 
    1.h:selectBooleanCheckbox: only this form's value can be auto save,other form's values will be missed!
    2.h:commandButton run sequence: click->actionListener->action
    3.immediate: false->can auto set fields. true:cannnot.
-->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>firsttest</title>
    </head>
    <body>
        <h3>Document   : firsttest</h3>
        <h3>Created on : Jul 24, 2008, 1:55:25 PM</h3>
        <h3>Author     : Administrator</h3>  
        
        <f:view>
            <c:if test="${!empty aaa}">
            ${aaa}<br/>            
                <h:outputText value="#{Firsttest.firstName}" /><br/>
                <h:outputText value="#{Firsttest.lastName}" /><br/>             
                <h:outputText value="#{Firsttest.items[0]}" /><br/>
                
            </c:if>        
            
            <h:form>
                First name: <h:inputText value="#{Firsttest.firstName}"/><BR>
                Last name: <h:inputText value="#{Firsttest.lastName}"/><BR>
                <h:commandButton
                    value="Sign Me Up!"
                    action="#{Firsttest.execute}"/>
                <h:commandLink
                    value="Sign Me Up!"
                    action="#{Firsttest.execute}"/><br/>                    
                
                <hr/>
                <h:selectBooleanCheckbox 
                    valueChangeListener="#{Firsttest.boolChangeTest}"
                    onclick="submit()"
                    immediate="true"/>            
                <h:selectOneMenu value="green">
                    <f:selectItems value="#{Firsttest.color}"/>
                </h:selectOneMenu>
                <h:commandButton
                    value="Listener test"
                    image="pic/a.gif"
                    actionListener="#{Firsttest.listenerTest}"
                    immediate="true"/><br/>                
                
            </h:form>        
            
        </f:view>        
        
    </body>
</html>
