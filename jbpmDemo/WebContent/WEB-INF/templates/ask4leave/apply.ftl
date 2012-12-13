<DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假流程Demo</title>
</head>
<body>
<body>
  <fieldset>
    <legend><#if reject?exists>重新申请(被${reject}驳回)<#else>申请</#if></legend>
    <form action="apply?action=apply" method="post">
    <table>
	    <tr><td>申请人：</td><td><input type="text" name="applyer" value="${USERNAME}" readonly/></td></tr>
	    <tr><td>请假时间(天数)：</td><td><input type="text" name="day" value="3"/></td></tr>
	    <tr><td>请假原因：</td><td><textarea name="reason"></textarea></td></tr>
	    <input type="hidden" name="taskId" value="${RequestParameters.id}">
    </table>
    <input type="submit"/>
    </form>
  </fieldset>

</body>
</html>