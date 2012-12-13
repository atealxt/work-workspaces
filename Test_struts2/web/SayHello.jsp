<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Say Hello</title>
    </head>
    <body>
        <h3>Say "Hello" to: </h3>
        <s:form action="HelloWorld">
            input "Atea" to go HelloWorld.jsp<br/>
            input "a" to go a.gif<br/>
            input else to go index.jsp<br/>
            
            <s:fielderror>
                <s:param>name</s:param>
            </s:fielderror>            
            <s:textfield name="name" label="Name" />
            <s:submit />
        </s:form>
    </body>
</html>