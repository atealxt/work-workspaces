<%-- 
    Document   : error
    Created on : 2008/06/24, 16:35:28
    Author     : Administrator
    
        a${param.errCode}<br/>
        b<s:property value="errCode" /><br/>        
        c<s:property value="#attr.errCode"/><br/>

        <s:set name="err" value="#attr.errCode"/>
        d<s:property value="#err"/><br/>
        
        <s:set name="err2" value=" 'errCode' + #attr.errCode "/>
        e<s:property value="#err2"/><br/>

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5;URL=${requestScope.errRedirect}">
            
        <title>error</title>
    </head>
    <body class="">
        <table width="90%">
                        <tr><td align=center>
                            <s:property value="%{getText('errCode' + #attr.errCode)}"/>
                        </td></tr> 
                        <tr><td align=center>
                            <s:text name="errJmp1"/>
                            <span id="time"> 6 </span>
                            <s:text name="errJmp2"/>
                        </td></tr> 
        </table>
        
        <script type="text/javascript">

        function timer() {
                var time = document.getElementById("time");
                var i = time.innerHTML; 
                if(i>0)i--;
                time.innerHTML  = " " + i + " ";

                window.setTimeout("timer()", 1000);    
        }

        timer ();

        </script>        

    </body>
</html>
