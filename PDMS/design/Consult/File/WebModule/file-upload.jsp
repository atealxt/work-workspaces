<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<title>
file-update
</title>
</head>
<body bgcolor="#ffffff">
  <html:form action="/fileAction.do?method=upload" method="post" enctype="multipart/form-data">
	<table width="100%" border="0">
	  <tr>
		<td align="right">请选择上传的文件：</td>
		<td><html:file property="fileContent"/></td>
	  </tr>
	  <tr>
		<td align="right">文件注释：</td>
		<td><html:textarea cols="30" property="remark"/></td>
	  </tr>
	  <tr>
		<td colspan="2" align="center"><html:submit value="提交"/></td>
	  </tr>
	</table>
 </html:form>

</body>
</html>
