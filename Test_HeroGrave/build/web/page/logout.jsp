<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        <s:form action="Logout.action" onsubmit="this.submit.disabled='true';">
            <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>
            <input type="hidden" id="page" name="page" value="${param.page}"/> 
            <input type="hidden" id="title" name="title" value="${param.title}"/>
            <input type="hidden" id="station" name="station" value="${param.station}"/>
            <input type="hidden" id="urlLocation" name="urlLocation" />
            
            <script type="text/javascript"> 
                document.forms[0].submit();            
            </script>            
            
        </s:form>
        
    </body>
</html>
