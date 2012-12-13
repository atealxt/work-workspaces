<%-- 
    Document   : test
    Created on : 2008/06/17, 11:05:06
    Author     : Administrator
    

fall to use s:if
    <s:set name="RequestGetData" value="requestScope.getData" scope="request"/>
    <s:if test="%{#RequestGetData != 0}">                
        <input type="text" value="${RequestGetData}" /><br/>                 
    </s:if>           
    <s:else>
        <input type="text" value="null.." /><br/>                 
    </s:else>

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" contect="atea">
            
        <title>test</title>
        
        <script src="common/common.js" type="text/javascript"></script>
        <script type="text/javascript">            
            var submittingMSG = "<s:text name='submitting'/>";
            

            
        </script>
        
    </head>
    <body onload="init()">
        <s:form name="formStrutsTest" action="StrutsTest.action" onsubmit="this.submit.disabled='true';">
            
            <s:text name="welcomeMSG"/><br/>
                                   
            <s:if test="%{#request.test != null}">
                Test seccess!<br/>
                
                <s:iterator value="list">
                  <s:property value="id"/><br/>
                  <s:property value="name"/><hr>                  
                </s:iterator>                
            
            </s:if>
                        
            <input type="text" id="text1" name="text1" />
            <s:submit id="submit1" key="submit" onclick="beforSubmit(this,submittingMSG)" />
            
            <input type="hidden" id="urlLocation" name="urlLocation"/>
        </s:form>
    </body>
</html>




