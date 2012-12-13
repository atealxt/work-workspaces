<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ page import="com.oa.struts.util.Constants"%>
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

<title><bean:message key="look_file.page.title" /></title>

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
		<TD><bean:message key="look_file.page.position" /></TD>
		<TD align="right"><a href="file_up.do?method=up"><bean:message key="look_file.page.add" /></a></TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
<b><html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" /></b>
<TABLE border="0" width="809">
	<TR class="tableheader">
		<TD><bean:message key="look_file.page.filename" /></TD>
		<TD><bean:message key="look_file.page.fileuper" /></TD>
		<TD><bean:message key="look_file.page.filesize" /></TD>
		<TD><bean:message key="look_file.page.fileuptime" /></TD>
		<TD><bean:message key="button.operation" /></TD>
	</TR>
	<logic:present name="upfileList">
	<logic:iterate id="upfile" name="upfileList" scope="request">
	<TR>
		<TD><bean:write name="upfile" property="filename" scope="page"/></TD>
		<TD><bean:write name="upfile" property="fileuper" scope="page"/></TD>
		<TD><bean:write name="upfile" property="filesize" scope="page"/></TD>
		<TD><bean:write name="upfile" property="fileuptime" scope="page"/></TD>
		<TD><a href='file_view.do?method=view&id=<bean:write name="upfile" property="id" scope="page"/>'><bean:message key="button.view" /></a>
		<a href="file_download.do?method=download&id=<bean:write name="upfile" property="id" scope="page"/>&filename=<bean:write name="upfile" property="filename" scope="page"/>"><bean:message key="button.download" /></a>
		<%if (!session.getAttribute(Constants.USERID_KEY).equals(Constants.NORMALUSER)) {%>
		<a href="file.do?method=delete&id=<bean:write name="upfile" property="id" scope="page"/>&filename=<bean:write name="upfile" property="filename" scope="page"/>"><bean:message key="button.delete" /></a>
		<%} %>
		</TD>
	</TR>
	</logic:iterate>
	</logic:present>
</TABLE>
<logic:present name="pager">
<form name="form1" action="file.do?method=list" method="post">
<TABLE border="0" width="809" class="pager">
	<tr><td colspan="4" width="809" height="2" align="center" bgcolor="#1D93DD"></td></tr>
	<TR>
		<TD align="left"><bean:message key="pager.pageSize" />
			<html:select name="pager" property="pageSize" onchange="document.all.pageNo.value='1';document.all.form1.submit();">
				<logic:notEmpty name="pager" scope="request">
					<html:options name="pager" property="pageSizeIndexs" />
				</logic:notEmpty>
			</html:select>
		</TD>
		<TD align="center">
		<bean:message key="pager.rowCount" /><bean:write name="pager" property="rowCount" />
		</TD>
		<TD align="right">
			<a href="javascript:document.all.pageNo.value='<bean:write name="pager" property="firstPageNo" />';document.all.form1.submit();"><bean:message key="pager.firstPageNo" /></a>
			<a href="javascript:document.all.pageNo.value='<bean:write name="pager" property="prePageNo" />';document.all.form1.submit();"><bean:message key="pager.prePageNo" /></a>
			<a href="javascript:document.all.pageNo.value='<bean:write name="pager" property="nextPageNo" />';document.all.form1.submit();"><bean:message key="pager.nextPageNo" /></a>
			<a href="javascript:document.all.pageNo.value='<bean:write name="pager" property="lastPageNo" />';document.all.form1.submit();"><bean:message key="pager.lastPageNo" /></a>
			<html:select name="pager" property="pageNo" onchange="document.all.form1.submit();">
				<logic:notEmpty name="pager" scope="request">
					<html:options name="pager" property="pageNoIndexs" />
				</logic:notEmpty>
			</html:select>
		</TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
</form>
</logic:present>
</td>
</tr>
</TABLE>
<%} %>
</body>
</html>
