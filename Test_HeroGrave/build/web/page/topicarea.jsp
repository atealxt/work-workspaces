<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                   
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">         
        <script src="../common/common.js" type="text/javascript"></script>
        <script type="text/javascript">  
            
            function headSubmit(){
                document.getElementById("head").submit();
            }            
            
        </script>        
    </head>
    <body>        

        <s:form action="Topicarea.action" onsubmit="this.submit.disabled='true';">
            
        <!--<input type="hidden" id="urlLocation" name="urlLocation"/> -->
        <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>        
        <input type="hidden" id="page" name="page" value="${param.page}"/>
        
        <script type="text/javascript"> 
            //saveLocationUrl();
            document.forms[0].submit();            
        </script>        

        </s:form>
    	
        
    </body>
</html>