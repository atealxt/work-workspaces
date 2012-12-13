<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<html:html locale = "true"/> 
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><bean:message key="sms_view.page.position" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
<%
  if(session.getAttribute("username")==null){
	  response.sendRedirect(path+"/error.jsp");
  }
  else{
%>
<TABLE width="815" valign="top">
<tr>
<td width="809" class="td" valign="top">
<TABLE width="809" class="position">
	<TR>
		<TD><bean:message key="sms_view.page.position" /></TD>
		<TD align="right"><a href="sms.do?method=back"><bean:message
			key="sms_view.page.back" /></a></TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
<TABLE border="0" width="809">
	<TR>
		<TD><bean:message key="sms.page.username" /></TD>
		<TD><logic:present name="smsFormBean">
			<html:text property="username" name="smsFormBean" maxlength="50"/>
		</logic:present> <logic:notPresent name="smsFormBean">
			<input type="text" name="username" maxlength="50">
		</logic:notPresent> <html:errors property="username" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="sms.page.smstitle" /></TD>
		<TD><logic:present name="smsFormBean">
			<html:text property="smstitle" name="smsFormBean" maxlength="50"/>
		</logic:present> <logic:notPresent name="smsFormBean">
			<input type="text" name="smstitle" maxlength="50">
		</logic:notPresent> <html:errors property="smstitle" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="sms.page.message" /></TD>
		<TD><textarea name="message" cols="60" rows="15">
		<logic:present name="smsFormBean">
		<bean:write property="message" name="smsFormBean" />
		</logic:present></textarea>  
		<html:errors property="message" /></TD>
	</TR>
</TABLE>
</td>
</tr>
</TABLE>
<%} %>
</body>
</html>
