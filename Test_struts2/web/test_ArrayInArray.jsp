<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_ArrayInArray</title>
    </head>
    <body>
        <h3>Document   : test_ArrayInArray</h3>
        <h3>Created on : Jul 21, 2008, 1:12:30 PM</h3>
        <h3>Author     : Administrator</h3>  
        
        <s:form action="Test_ArrayInArray">
        <s:submit /><hr/>
        
        <s:iterator value="listTest">
            <s:iterator>
                <s:property/>
            </s:iterator>
            <s:property/><br/>
        </s:iterator>        
        
        <script type="text/javascript">
            
            var arrTest = new Array(); 
            var arrTem;
            <s:iterator value="listTest">
                arrTem = new Array();                
                <s:iterator>
                    arrTem.push(<s:property/>);                        
                </s:iterator> 
                arrTest.push(arrTem);    
            </s:iterator>
                
            function showArr(){
                alert(arrTest[0]);
            }    
                    
        </script>   
        <br/><input type="button" value="box" onclick="showArr()"/>
        
        
        
        
        </s:form>
    </body>
</html>
