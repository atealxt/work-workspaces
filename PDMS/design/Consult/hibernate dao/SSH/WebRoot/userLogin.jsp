<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'userLogin.jsp' starting page</title>


	</head>

	<body>

		<h1>
			�û���¼
		</h1>
		<form action="<%=request.getContextPath()%>/user/userManager.do?mehtod=login" method="post">
			�û�����
			<input name="uname" type="text">
			<br />
			���룺
			<input name="pwd" type="password"><br/>
			<input type="submit" value="�ύ">
		</form>

	</body>
</html>
