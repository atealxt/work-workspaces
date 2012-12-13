<%-- 
    Document   : test4_iterator
    Created on : 2008/06/23, 9:19:16
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>   

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test4_iterator</title>
        <style type="text/css">
        .classone { color: rgb(000,255,000); }
        .classtwo { color: #FF0000; }
        </style>        
    </head>
    <body>
        <s:form action="Test4_iterator">
            <s:iterator value="#request.inOutAccountList" id="data" status="listStat">
                <s:if test='#listStat.odd == true '>
                    <div class="classone">
                        <s:property value="#listStat.index+1"  />
                    </div>
                </s:if>
                <s:else>
                    <div class="classtwo">
                        <s:property value="#listStat.index+1"  />
                    </div>
                </s:else>                
            </s:iterator> 
            
            <input type="submit" value="submit" />
            
        </s:form>
    </body>
</html>
