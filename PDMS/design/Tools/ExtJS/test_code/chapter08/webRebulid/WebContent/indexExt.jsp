<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简易书籍管理系统EXT版</title>
<link rel="stylesheet" type="text/css" href="pagesExt/extjs2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="pagesExt/css/style.css" />
<script type="text/javascript" src="pagesExt/extjs2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="pagesExt/extjs2.0/ext-all.js"></script>
<script type="text/javascript" src="pagesExt/extjs2.0/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="pagesExt/extjs2.0/ext/themeChange.js"></script>
</head>
<script type="text/javascript">
	Ext.onReady(function(){
		Ext.BLANK_IMAGE_URL = 'pagesExt/extjs2.0/resources/images/default/s.gif';
		var root = new Ext.tree.TreeNode({
			text : '系统说明',
			url : 'pagesExt/about.jsp',
			expanded : true//默认展开根节点
		});
		var node1 = new Ext.tree.TreeNode({
			text : '书籍类型维护',
			url : 'bookext.do?method=showBookTypeList'
		});
		var node2 = new Ext.tree.TreeNode({
			text : '书籍维护',
			url : 'bookext.do?method=showBookList'
		});
		root.appendChild(node1);
		root.appendChild(node2);
		var menu = new Ext.tree.TreePanel({
			border : false,
			root : root,
			hrefTarget : 'mainContent',
			listeners : {
				click : function(node,e){
					mainPanel.load({
						url:node.attributes.url,
						callback : function(){
							mainPanel.setTitle(node.text);
						},
						scripts: true
					});
				}
			}
			,
			tbar : [
				'皮肤选择：',
				{
					xtype : 'themeChange',
					width : 80,
					listWidth : 80
				},
				'->'
			]
		});
		new Ext.Viewport({
			title : 'Ext.Viewport示例',
			layout:'border',//表格布局
			items: [{
				title : '简易书籍管理系统ExtJs版',
				collapsible: true,
				html : '<br><center><font size = 6>简易书籍管理系统</font></center>',
				region: 'north',//指定子面板所在区域为north
				height: 100
			},{
				title : '功能菜单',
				items : menu,
				split:true,
				collapsible: true,
				region:'west',//指定子面板所在区域为west
				width: 150
			},{
				title: '系统说明',
				contentEl : 'aboutDiv',
				collapsible: true,
				id : 'mainContent',
				region:'center'//指定子面板所在区域为center
			}]
		});
		var mainPanel = Ext.getCmp('mainContent');
	});
</script>
<body>
<div id='aboutDiv' style='height:96%;width:100%'>
<table style="width: 100%; height: 100%" border=0>
	<tr>
		<td align=center>
		<div style="width: 300px; height: 200px; overflow: auto;" align=left>
		<p>简易图书管理系统实现了书籍类型及图书的新增、修改和删除，包含了书籍类型列表及书籍列表，
		它不具备一个真的图书管理系统的全部功能，创建它的目的在于演示常见WEB框架到Ext JS的转化。</p>
		</div>
		</td>
	</tr>
</table>
</div>
</body>
</html>