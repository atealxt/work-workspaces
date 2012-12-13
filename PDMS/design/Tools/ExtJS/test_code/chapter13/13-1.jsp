<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>ExtTLD test</title>
</head>
<body>
	<ext:body>
		<ext:window title="ExtTLD示例" width="300" height="200" id="win">
			第一个ExtTLD示例。
			<ext:button onClick="alert('hello world')">请单击我</ext:button>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>