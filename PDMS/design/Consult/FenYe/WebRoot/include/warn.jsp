<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<logic:messagesPresent>
	<script language="javascript" type="text/javascript">
 	var msg = "皇家金典提示您：\n";
	<html:messages id="error">
		msg += "<bean:write name="error"/>\r\n";
	</html:messages>
	alert(msg);
 	</script>
</logic:messagesPresent>
