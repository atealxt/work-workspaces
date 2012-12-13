<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>gridExtTLD 可编辑表格示例</title>
</head>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="gridExtTLD 可编辑表格示例" width="370" height="150" id="win" layout="fit">
			<ext:grid.editorGridPanel>
				<ext:grid.columnModel>
					<ext:grid.column header="姓名" width="100" fieldsType="string">
						<ext:form.textField/>
					</ext:grid.column>
					<ext:grid.column header="性别" width="100" fieldsType="string">
						<ext:form.comboBox displayField="text">
							<ext:form.option value="男">男</ext:form.option>
							<ext:form.option value="女">女</ext:form.option>
						</ext:form.comboBox>
					</ext:grid.column>
					<ext:grid.column header="出生日期" width="100" fieldsType="date"
						renderer="Ext.util.Format.dateRenderer('Y年m月d日')">
						<ext:form.dateField/>
					</ext:grid.column>
				</ext:grid.columnModel>
				<ext:grid.row>
					<ext:grid.rowCell>tom</ext:grid.rowCell>
					<ext:grid.rowCell>男</ext:grid.rowCell>
					<ext:grid.rowCell>01/10/2008</ext:grid.rowCell>
				</ext:grid.row>
			</ext:grid.editorGridPanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>