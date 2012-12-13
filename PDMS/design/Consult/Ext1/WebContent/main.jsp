<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.soa.userbean.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>富阳项目协作管理系统</title>
    <link href="default.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="favicon.ico" />
    <link rel="Bookmark" href="favicon.ico" />
    <link href="lib/extjs/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
    <link href="lib/extjs/resources/css/xtheme-slate.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--加载等待标签-->
<div id="loadingTab">
    <div class="loading-indicator">
		<img src="Images/loading.gif" alt="loading" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>
        <a href="Default.html">富阳项目协作管理系统</a><br />
		<span id="loading-msg">加载样式表和图片...</span>
  </div>
</div>
<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '正在加载UI组建...';</script>
<script language="javascript" type="text/javascript" src="lib/extjs/adapter/ext/ext-base.js"></script>
<script language="javascript" type="text/javascript" src="lib/extjs/ext-all.js"></script>
<script language="javascript" type='text/javascript' src='lib/upload/Ext.ux.UploadDialog.packed.js'></script>
<script type="text/javascript" src="lib/report/menu/EditableItem.js"></script>
<script type="text/javascript" src="lib/report/menu/RangeMenu.js"></script>
<script type="text/javascript" src="lib/report/grid/GridFilters.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/Filter.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/StringFilter.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/DateFilter.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/ListFilter.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/NumericFilter.js"></script>
<script type="text/javascript" src="lib/report/grid/filter/BooleanFilter.js"></script>

<!--验证用户是否登陆-->
	<script language="javascript" type="text/javascript">
		var store=new Ext.data.JsonStore({
			url:'isLogin.do?cmd=isLogin',
			remoteSort:true,
			fields:['msg']
		});
		store.load();
		
		function isLogin(){
			if (store.getAt(0) != null) {
				if (store.getAt(0).get('msg') != 'OK') {
					Ext.MessageBox.show({
						title: '错误',
						msg: '未登录或登录超时!',
						buttons: {
							"ok": "登录"
						},
						icon: Ext.MessageBox.ERROR,
						fn: function(){
							document.location = 'index.html'
						}
					});
				}
			}
		}
		Ext.onReady(isLogin);
	
	</script>


<!--加载ext-lang-zh_CN.js是加在本地化文件，用来显示中文所使用的-->
<script language="javascript" type="text/javascript" src="lib/main/ext-lang-zh_CN.js"></script>
<%
	UserInfo info=(UserInfo)session.getAttribute("session");
	int part_id=0;
	String unit_code="";
	if(info!=null){
		part_id=info.getPart_id();
		unit_code=info.getUnit_code();
		if((part_id==2)||(part_id==1054)){
			out.print("<script language='javascript' type='text/javascript' src='lib/main/menu.js'></script>");		
		}
		if(part_id==363||unit_code.equals("B3")){
			out.print("<script language='javascript' type='text/javascript' src='lib/main/menu_QH.js'></script>");		
		}
		if((part_id==31)||(part_id==30)){
			out.print("<script language='javascript' type='text/javascript' src='lib/main/menu_YW.js'></script>");		
		}		
	}else{
		response.sendRedirect("index.html");
	}
	
%>


<script language="javascript" type="text/javascript" src="lib/main/panel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/eastPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/userPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/collaborationCategoryPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/stateSetPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/collaboratorSetPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/requestPanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/cooperatePanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/approvePanel.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/manage.js"></script>
<script language="javascript" type="text/javascript" src="lib/main/TreeCheckNodeUI.js"></script>
<script language="javascript" type="text/javascript">document.getElementById('loading-msg').innerHTML = '正在初始化组建...';</script>
<script language="javascript" type="text/javascript">
		Ext.get('loadingTab').fadeOut({remove: true});//让加载标签消失
</script>

<div id="north" style="float:left;margin:5px;font:normal 25px tahoma, arial, sans-serif, 宋体;">
<br>
&nbsp;&nbsp;<b>富阳项目协作管理系统</b>
</div>

</body>
</html>