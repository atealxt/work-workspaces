<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>formExtTLD 复杂字段示例</title>
</head>
<script type="text/javascript">
	var dataArray=[[1,"项目一"],[2,"项目二"],[3,"项目三"]];
</script>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="formExtTLD 复杂字段示例" width="270" height="230" id="win" layout="fit">
			<ext:form.formPanel bodyStyle="padding:10" labelSeparator ="：" id="testform" labelWidth="100">

				<ext:form.fieldset title="下拉框（读取本地数据）"  labelSeparator ="：">
					<ext:form.comboBox
						fieldLabel="下拉框一"
						width="100"
						name="combo1"
						value="1">
						<ext:form.option value="1">项目一</ext:form.option>
						<ext:form.option value="2">项目二</ext:form.option>
						<ext:form.option value="3">项目三</ext:form.option>
					</ext:form.comboBox>

					<ext:form.comboBox
						fieldLabel="下拉框二"
						mode="local"
						displayField="text"
						valueField="value"
						triggerAction="all"
						width="100"
						name="combo2"
						value="2">
						<ext:data.simpleStore autoLoad="true" data="dataArray">
							<ext:data.field name="value"/>
							<ext:data.field name="text"/>
						</ext:data.simpleStore>
					</ext:form.comboBox>
				</ext:form.fieldset>

				<ext:form.fieldset title="下拉框（读取远程数据）"  labelSeparator ="：">
					<ext:form.comboBox
						fieldLabel="下拉框三"
						mode="remote"
						displayField="personName"
						valueField="id"
						triggerAction="all"
						width="100"
						name="combo3">
						<ext:data.store autoLoad="true">
							<ext:data.httpProxy url="jsonServer.jsp"/>
							<ext:data.jsonReader/>
						</ext:data.store>
					</ext:form.comboBox>
				</ext:form.fieldset>

			</ext:form.formPanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
</body>
</html>