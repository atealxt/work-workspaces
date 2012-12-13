<%-- 
    Document   : test2_ajax
    Created on : 2008/02/02, 16:07:37
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test2_ajax</title>
        <script type="text/javascript" src="AjaxTemplate.js"></script>
        <script type="text/javascript">
            function search(){
                vApp = document.getElementById("t1");
                send_request("test2_ajaxTest.do",5000,vApp);
            }
        </script>
    </head>
    <body>
        <h2>test2: Ajax</h2>
        
        <html:form method="get" action="/test2_ajaxTest.do">        
            <input type="text" id="t1" size="20" disabled="true"/><br/>
            <input type="button" id="b1" value="ajax_test" onclick="search()"/>
        </html:form>          
        
        <hr/><a href="index.htm">index page</a><br/>
    </body>
</html>
