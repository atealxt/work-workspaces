<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="js/ext/resources/css/ext-all.css" rel="stylesheet" type="text/css">
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="js/ext/ext-all.js"></script>
	<script type="text/javascript" src="js/ext/source/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/sessionCheck.js"></script>
	<script type="text/javascript" src="js/ext-ux/TreeCheckNodeUI-min.js"></script>
	<script type="text/javascript" src="js/ext-ux/ComboBoxTree-min.js"></script>
	<script type="text/javascript" src="js/ext-ux/ComboBoxCheckTree.js"></script>
	<script type="text/javascript" src="js/ext-ux/datetimeField.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/curd.js"></script>
	<script type="text/javascript"> 
		Ext.onReady(function(){
			path = '<%=path%>';
		    menu = new MenuPanel();
		    main = new MainPanel();
		    header = new Ext.Panel({
		        border: true,
		        region: 'north',
		        layout: 'anchor',
		        height: 90,
		        border: false,
		        items: [{
		            xtype: "box",
		            border: true,
		            el: "header",
		            anchor: 'none -24'
		        }, new Ext.Toolbar({
		            items: ["->", {
		                text: "更改密码",
		                cls: "x-btn-text- icon",
		                icon: "images/key.gif",
		                handler: function(){}
		            }, "-", {
		                text: "注销用户",
		                cls: "x-btn-text- icon",
		                icon: "images/stop.gif",
		                handler: function(){}
		            }, '&nbsp;']
		        })]
		    });
		
		    bottom = new Ext.Toolbar({
		        id: "bottom",
		        border: false,
		        frame: true,
		        region: "south",
		        height: 25,
		        items: ["当前登陆用户：", 'zmh', '->', "©2007-2008 <a href='http://www.blogjava.net/ilovezmh'><font color=blue>祝</font></a>　版权所有　　　　"]
		    });
		
		    var viewport = new Ext.Viewport({
		    	id:'viewPort',
		        layout: 'fit',
		        items: [{
		            id: "desktop",
		            layout: "border",
		            items: [header, menu, main,bottom]
		        }]
		    });
		
		});
	</script>
  </head>
  
  <body>
    <div id="header" style="background: url(images/header/headerBg.gif); font: 25px bold '黑体'; padding-top: 8px">
		<div id="logo" onclick="javascript:window.location.href=window.location.href" onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'"
			 style="background: url(images/header/logo.gif); width: 175px; height: 65px; line-height: 69px; display: inline; position: absolute; left: 0; top: 0;">
		</div>
		<div id="c"
			 style="text-align: right; margin: 0px; float: right; display: inline; position: absolute; right: 0; top: 0;">
			<a href="/" target="_blank"><img src="images/header/001.jpg" 
			   onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'" border="0" /></a>
			<a href="/news/" target="_blank"><img src="images/header/002.jpg" 
			   onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'" border="0" /></a>
			<a href="/person/" target="_blank"><img src="images/header/003.jpg" 
			   onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='normal'" border="0" /></a>
		</div>
	</div>
  </body>
</html>
