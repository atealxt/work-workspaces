<%@ page contentType="text/html; charset=UTF-8"%>
<%@page errorPage="uperr.jsp"%>
<%@ page import="com.jspsmart.upload.*" %>
<body>
<%
	SmartUpload mySmartUpload=new SmartUpload();
	mySmartUpload.initialize(pageContext);
	mySmartUpload.upload();
	mySmartUpload.save("d:/");
%>
