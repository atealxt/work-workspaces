<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>treeExtTLD 访问远程数据示例</title>
</head>
<body>
	<ext:body theme="black" msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="访问远程数据示例" width="270" height="200" id="win" layout="fit">
			<ext:tree.treePanel autoScroll="true">
				<ext:tree.treeLoader dataUrl="treeServer.jsp"/>
			</ext:tree.treePanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>