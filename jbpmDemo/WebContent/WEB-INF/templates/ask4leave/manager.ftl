<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假流程Demo</title>
</head>
<body>
  <fieldset>
    <legend><#if RequestParameters.type == 'manager'>经理</#if><#if RequestParameters.type == 'boss'>Boss</#if>审核</legend>
    <form action="audit?action=audit" method="post">
    <input type="hidden" name="taskId" value="${RequestParameters.id}">
    <table>
	    <tr><td>申请人：</td><td>${applyer}</td></tr>
	    <tr><td>请假时间(天数)：</td><td>${day}</td></tr>
	    <tr><td>请假原因：</td><td>${reason}</td></tr>
	    <input type="hidden" name="taskId" value="${RequestParameters.id}">
    </table>
    <input name="result" type="submit" value="批准"/>
    <input name="result" type="submit" value="驳回"/>
    </form>
  </fieldset>

</body>
</html>