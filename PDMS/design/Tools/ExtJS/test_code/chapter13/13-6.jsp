<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>gridExtTLD 分组表格示例</title>
</head>
<script type="text/javascript">
	var dataArray = [
			[1,'张三','男',29],[2,'李四','女',30],
			[3,'王五','男',27],[4,'赵六','女',31]
		];
</script>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="gridExtTLD 分组表格示例" width="370" height="250" id="win" layout="fit">
			<ext:grid.gridPanel>
				<ext:grid.columnModel>
					<ext:grid.column header="id" width="100" hidden="true" dataIndex="id"/>
					<ext:grid.column header="姓名" width="100" dataIndex="name"/>
					<ext:grid.column header="性别" width="100" dataIndex="sex"/>
					<ext:grid.column header="年龄" width="100" dataIndex="age"/>
				</ext:grid.columnModel>

				<ext:data.groupingStore groupField="sex" autoLoad="true" data="dataArray"
				sortInfo="{field: 'id', direction: 'ASC'}">

					<ext:data.arrayReader>
						<ext:data.fields>
							<ext:data.field name="id"/>
							<ext:data.field name="name"/>
							<ext:data.field name="sex"/>
							<ext:data.field name="age"/>
						</ext:data.fields>
					</ext:data.arrayReader>
				</ext:data.groupingStore>

				<ext:grid.groupingView forceFit="true"/>

			</ext:grid.gridPanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>