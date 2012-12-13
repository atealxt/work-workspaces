<%-- 
    Document   : test1_Tag
    Created on : 2008/04/21, 16:28:57
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
        
        <s:form action="Test1_sif">
                       
            <s:set name="age" value="29"/>
            <s:if test="#age > 60">
            老年人<br/>
            </s:if>
            <s:elseif test="#age > 35">  
            中年人<br/>  
            </s:elseif>  
            <s:elseif test="#age > 15" id="wawa">  
            青年人<br/>  
            </s:elseif>  
            <s:else>  
            少年<br/>  
            </s:else> 
            
            <s:set name="value2" value="15"/>
            <s:if test="#age > #value2">
            dayu<br/>
            </s:if> 
            <s:else>  
            xiaoyu<br/>  
            </s:else>             
            
            <s:submit value="submit"/>
            
            <s:hidden name="paraTest" value="あああ"/>
            <input type="hidden" name="paraTest2" value="いいい"/>
            ${param.paraTest}
            ${param.paraTest2}<br/>
            <s:if test="%{#parameters.paraTest != null}">
                aaaaa
            </s:if>
            <hr/>
            
            <s:if test="%{#request.intTest != null}">
                <s:set name="forTest" value="#request.intTest"/>
                <s:if test="%{#forTest > 0 }">
                    <s:property value="#forTest" />    <br/>                
                </s:if> 
            </s:if>
            
            <s:property value="charTest" escape="false" />    <br/>
            
            
        </s:form>

    </body>
</html>
