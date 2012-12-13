<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>treeExtTLD 简单tree示例</title>
</head>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="简单tree示例" width="270" height="200" id="win" layout="fit">
			<ext:tree.treePanel autoScroll="true">
				<ext:tree.treeNode text="一级节点1"/>
				<ext:tree.treeNode text="一级节点2">
					<ext:tree.treeNode text="二级节点1"/>
					<ext:tree.treeNode text="二级节点2">
						<ext:tree.treeNode text="三级节点1"/>
						<ext:tree.treeNode text="三级节点2"/>
					</ext:tree.treeNode>
				</ext:tree.treeNode>
				<ext:tree.treeNode text="一级节点3">
					<ext:tree.treeNode text="二级节点1"/>
					<ext:tree.treeNode text="二级节点2"/>
				</ext:tree.treeNode>
			</ext:tree.treePanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>