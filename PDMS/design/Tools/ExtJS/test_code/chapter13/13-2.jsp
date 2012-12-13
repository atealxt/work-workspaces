<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags/ext" prefix="ext" %>
<html>
<head>
<title>formExtTLD 简单字段示例</title>
</head>
<body>
	<ext:body msgTarget="side" smProvider="Ext.state.Provider()" locale="zh_CN">
		<ext:window title="formExtTLD 简单字段示例" width="340" height="420" id="win" layout="fit">
			<ext:form.formPanel bodyStyle="padding:10" labelSeparator ="：" id="testform">
				<ext:form.textField
					fieldLabel="文本框字段"
					width="150"
					name="userName"
					value="文本"/>
				<ext:form.dateField
					fieldLabel="日期字段"
					width="150"
					name="userDate"
					format="Y年m月d日"
					value="2008-01-01"/>
				<ext:form.numberField
					fieldLabel="数组字段"
					name="userNum"
					width="150"
					value="100"/>
				<ext:form.fieldset title ="字段集" width="300" labelSeparator ="：">
					<ext:form.checkbox
						fieldLabel="复选框1"
						name="checkbox1"
						checked = "true"/>
					<ext:form.checkbox
						fieldLabel="复选框2"
						name="checkbox2"/>
					<ext:form.radio
						fieldLabel="单选框1"
						name='radiobox'/>
					<ext:form.radio
						fieldLabel="单选框2"
						name='radiobox'
						checked = "true"/>
				</ext:form.fieldset>
				<ext:form.textArea
					fieldLabel="文本区字段"
					name="userMemo"
					width="150"
					value="可以输入多行内容"/>
				<ext:form.timeField
					name="userTime"
					fieldLabel="时间选择框"
					allowBlank="false"
					value="12:00"
					width="150"/>
				<ext:button type="submit" onClick="submit()">提交表单</ext:button>
			</ext:form.formPanel>
		</ext:window>
	</ext:body>
	<ext:onReady>
		win.show();
	</ext:onReady>
	<script type="text/javascript">
		function submit(){//提交表单
			testform.form.submit({
				clientValidation:true,//进行客户端验证
				waitMsg : '正在提交表单数据请稍后',//提示信息
				waitTitle : '提示',//标题
				url : 'formExtTLDServer.jsp',//请求的url地址
				method:'POST',//请求方式
				success:function(form,action){//加载成功的处理函数
					Ext.Msg.alert('提示','表单数据提交成功');
				},
				failure:function(form,action){//加载失败的处理函数
					Ext.Msg.alert('提示','表单数据提交失败，原因：'+action.failureType);
				}
			});
		}
	</script>
</body>
</html>