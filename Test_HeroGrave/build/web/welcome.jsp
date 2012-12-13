<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>welcome</title>
    </head>

<!--    
	<frameset rows="100,*"  frameborder="0" border="0" framespacing="0">
	    <frame name="head" src="page/head.html" noresize marginheight=0 marginwidth=0 scrolling="no">
	    <frame name="main" src="page/main.html" noresize marginheight=0 marginwidth=0>
	</frameset>
-->	
<body class="">
    <s:form action="Welcome" onsubmit="this.submit.disabled='true';">
        
    <script type="text/javascript"> 
        document.forms[0].submit();
    </script>
    
    </s:form>
</body>
</html>

