<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'userLogin.jsp' starting page</title>


	</head>

	<body>

		<h1>
			用户注册
		</h1>
		<form action="<%=request.getContextPath()%>/user/userManager.do?mehtod=reg" method="post">
			用户名：
			<input name="uname" type="text">
			<br />
			age：
			<input name="age" type="text"><br/>
			sex：
			<input name="sex" type="text"><br/>
			<input type="submit" value="提交">
		</form>

	</body>
</html>
