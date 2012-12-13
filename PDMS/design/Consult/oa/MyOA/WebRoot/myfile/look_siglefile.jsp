<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<title><bean:message key="look_siglefile.page.title" /></title>
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
<td width="815" class="td" valign="top">
<TABLE width="809" class="position">
	<TR>
		<TD><bean:message key="look_siglefile.page.position" /></TD>
		<TD align="right"><a href="file.do?method=back"><bean:message
			key="look_siglefile.page.back" /></a></TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
<table width=809 border=1 cellpadding="0" cellspacing="0" bordercolor=gray bordercolorlight=gray bordercolordark=white>
<tr>
<td align="left" valign="top" width="809" >
  <br>
  <br>
  <logic:present name="upfileFormBean">
	<html:hidden property="id" name="upfileFormBean"/>
  </logic:present>
  <logic:notPresent name="upfileFormBean">
	<input type="hidden" name="id">
  </logic:notPresent>
      <table width="809" height="224"  border="1" cellpadding="0" cellspacing="-10" bordercolor="balck" bordercolorlight="black" bordercolordark="white">
        <tr valign="left">
            <table width="809"  border="1" cellspacing="0" cellpadding="0" bordercolor=black bordercolorlight=black bordercolordark=white>
             <logic:present name="upfileFormBean">
               <tr valign="middle">
                <td height="30" align="right" valign="middle" bgcolor="#DFE5F5"><bean:message key="look_siglefile.page.filename" />:&nbsp;</td>
                <td height="30">&nbsp;<html:text name="upfileFormBean" property="filename" style="border:0;" readonly="true"/></td>
              </tr>
              <tr valign="middle">
                <td height="30" align="right" valign="middle" bgcolor="#DFE5F5"><bean:message key="look_siglefile.page.fileuper" />:&nbsp;</td>
                <td height="30" align="left">&nbsp;<html:text name="upfileFormBean" property="fileuper" style="border:0" readonly="true"/></td>
              </tr>
              <tr valign="middle">
                <td height="30" align="right" valign="middle" bgcolor="#DFE5F5"><bean:message key="look_siglefile.page.filesize" />:&nbsp;</td>
                <td height="30" align="left">&nbsp;<html:text name="upfileFormBean" property="filesize" style="border:0" readonly="true"/></td>
              </tr>
              <tr valign="middle">
                <td height="30" align="right" valign="middle" bgcolor="#DFE5F5"><bean:message key="look_siglefile.page.fileuptime" />:&nbsp;</td>
                <td height="30" align="left">&nbsp;<html:text name="upfileFormBean" property="fileuptime" style="border:0" readonly="true"/></td>
              </tr>
              <tr valign="middle">
                <td height="73" align="right" valign="middle" bgcolor="#DFE5F5"><bean:message key="look_siglefile.page.fileinfo" />:&nbsp;</td>
                <td height="73" align="left">&nbsp;<html:text name="upfileFormBean" property="fileinfo" style="border:0" readonly="true"/></td>
              </tr>
            </logic:present>
          </table>
        </tr>
      </table>
      </td>
</tr>
</table>
</td>
</tr>
</TABLE>
<%} %>
</body>
</html>
