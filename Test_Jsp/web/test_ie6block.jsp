<%-- 
    Document   : test_ie6block
    Created on : Jan 31, 2010, 10:26:13 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="prototype.js" ></script>
        <script type="text/javascript">
            function getFile(){
                window.location.href = 'test_WordAndExcel.jsp';
            }
            function ajaxreqbytimer(){
                $('aaa').style.visibility = "hidden";
                setTimeout(function(){$("b1").click()}, 100);
            }

            function ajaxreq(asynchronous){
                new Ajax.Request('HelloServlet.jsp', {
                    method : 'post',
                    asynchronous : asynchronous,
                    onSuccess : function() {
                        getFile();
                    },
                    onFailure : function() {
                        alert('failure');
                    }
                });
            }
        </script>
    </head>
    <body onload="">
    <marquee id="aaa" style="">Hello World!</marquee>
    <input id="b1" type="button" value="no block" onclick="getFile()"/><br>
    <input type="button" value="no block2" onclick="ajaxreq(false)"/><br>
    <input type="button" value="has block" onclick="ajaxreq(true)"/><br>
    <input type="button" value="has block2" onclick="ajaxreqbytimer(true)"/><br>
</body>
</html>
