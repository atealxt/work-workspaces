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

<title><bean:message key="up_file.page.title" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
		<TD><bean:message key="up_file.page.position" /></TD>
		<TD align="right"><a href="file.do?method=back"><bean:message
			key="up_file.page.back" /></a></TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
<form name="form1" action="file_up.do?method=upload" enctype="multipart/form-data" method="post">
<b><html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" /></b>
<TABLE border="0" width="809">
	<TR>
		<TD><bean:message key="up_file.page.filename" /></TD>
		<TD><logic:present name="upfileFormBean">
			<html:file property="formfile" name="upfileFormBean" size="66" style="border:1 solid"/>
		</logic:present> <logic:notPresent name="upfileFormBean">
			<input type="file" name="filename" maxlength="50">
		</logic:notPresent>
		 <html:errors property="notfile" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="up_file.page.fileinfo" /></TD>
		<TD><logic:present name="upfileFormBean">
			<html:text property="fileinfo" name="upfileFormBean" maxlength="10"/>
		</logic:present> <logic:notPresent name="upfileFormBean">
			<input type="text" name="fileinfo" maxlength="10">
		</logic:notPresent></TD>
	</TR>
	<TR>
		<TD colspan="1"><input type="submit"
			value="<bean:message key='button.upload' />">
		</TD>
		<TD colspan="1"><input type="reset"
			value="<bean:message key='button.reset' />">
		</TD>
	</TR>
</TABLE>
</form>
</td>
</tr>
</TABLE>
<%} %>
</body>
</html>
