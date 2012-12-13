<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>gridExtTLD 简单表格示例</title>
</head>
<script type="text/javascript">
	var dataArray = [
		[1,'张三','男',new Date(1979,09,13),'zhang@abc.com'],
		[2,'李四','女',new Date(1978,10,01),'li@abc.com'],
		[3,'王五','男',new Date(1981,01,01),'wang@abc.com']
	];
</script>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="gridExtTLD 简单表格示例" width="370" height="150" id="win" layout="fit">
			<ext:grid.gridPanel>
				<ext:grid.columnModel>
					<ext:grid.rowNumberer/>
					<ext:grid.column header="Id" dataIndex="id" hidden="true"/>
					<ext:grid.column header="姓名" dataIndex="name" width="50"/>
					<ext:grid.column header="性别" dataIndex="sex" width="50"/>
					<ext:grid.column header="出生日期" dataIndex="birthday" width="100"
						renderer="Ext.util.Format.dateRenderer('Y年m月d日')"/>
					<ext:grid.column header="电子邮件" dataIndex="email" width="100"/>
				</ext:grid.columnModel>
				<ext:data.simpleStore autoLoad="true" data="dataArray">
					<ext:data.field name="id"/>
					<ext:data.field name="name"/>
					<ext:data.field name="sex"/>
					<ext:data.field name="birthday"/>
					<ext:data.field name="email"/>
				</ext:data.simpleStore>
			</ext:grid.gridPanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>