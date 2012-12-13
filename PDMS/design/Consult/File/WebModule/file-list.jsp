<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<title>file-download</title>
</head>
<body bgcolor="#ffffff">
<ol>
  <logic:iterate id="item" name="fileList" scope="request">
    <li><a href='fileAction.do?method=download&fileId=<bean:write name="item" property="fileId"/>'> <bean:write name="item" property="fileName"/></a> </li>
  </logic:iterate>
</ol>
</body>
</html>
