<%-- 
    Document   : test3_IocServlet
    Created on : 2008/05/23, 9:03:44
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test3_IocServlet</title>
    </head>
    <body>
        <s:form action="Test3_IocServlet">
            
            <script>
                var aaa= "${requestScope.fromrequest}";
                //var aaa= "\${req}";
                //\${req} is not seen! can only use in struts tag?..
                //alert(aaa);
            </script>
            
            <!--
                1.in struts tag,cannot know whether the value is null..
                2.so,don't judge it in struts tag "s:if test='#req != null" 
                PS: when is null, out put nothing!
            -->            
            ${requestScope.fromrequest}<br/>
            ${sessionScope.fromsession}<br/> 
           
            <input type="submit" value="sumbit" />
        </s:form>
    </body>
</html>
