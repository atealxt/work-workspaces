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
		<td align="right">��ѡ���ϴ����ļ���</td>
		<td><html:file property="fileContent"/></td>
	  </tr>
	  <tr>
		<td align="right">�ļ�ע�ͣ�</td>
		<td><html:textarea cols="30" property="remark"/></td>
	  </tr>
	  <tr>
		<td colspan="2" align="center"><html:submit value="�ύ"/></td>
	  </tr>
	</table>
 </html:form>

</body>
</html>
