<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
	<script type="text/javascript" src="js/curd.js"></script>
	<script type="text/javascript" src="js/datetimeField.js"></script>
	<script type="text/javascript">
		// ext实例展示的js文件
		Ext.BLANK_IMAGE_URL = 'images/public/s.gif"';
		Ext.QuickTips.init();//加载快速提示框	
		Ext.namespace('ExtExample');//创建名字域
		Ext.Ajax.on("requestcomplete",function(_conn,_response,_options){
			try{
				var result = Ext.decode(_response.responseText);
				if(result.timeout=='yes'){
					Ext.MessageBox.alert("系统提示","网络超时，请重新登录！",function(){   
						top.location.href = '<%=path%>/login.jsp';	
					});       			            
				}
			}catch(e) {
                // ignore
            }  
		});	
				
		//应用程序主页面
		ExtExample.app = function(){};
		
		Ext.extend(ExtExample.app,Ext.util.Observable,{
			//顶端
			header:new Ext.BoxComponent({
				region:'north',
				el:'north',
				height:30,
				margins:'5'
			}),	
			//低端
			footer: new Ext.BoxComponent({
				region:'south',
				el:'south',
				height:25
			}),		
			//实例菜单树形
			menuTree:new Ext.tree.TreePanel({
				title:'功能菜单',
				region:'west',
				id:'extExample-tree',
				autoScroll:true, 
				enableDD:false,//是否支持拖拽效果
				containerScroll: true,//是否支持滚动条
				split:true,
		        width: 180,
		        minSize: 175,
		        maxSize: 300,
				rootVisible:true,//是否显示跟节点
				collapseMode:'mini',//在分割线处出现按钮
		        collapsible: true,
		        margins:'0 0 5 5',
				loader:new Ext.tree.TreeLoader({ 
				   	dataUrl:'<%=path%>/system/resource_getTree.action',
				   	baseParams:{useAjax:'yes'}  
		        }),
				tools:[{
						id:'refresh',
						handler:function(){
							var tree = Ext.getCmp('extExample-tree');
							tree.root.reload();
						}
					}]
			}),
			//菜单根节点
			menuRoot: new Ext.tree.AsyncTreeNode({
				text:'功能菜单',
				draggable:false,
				id:'0'
			}),
			
			//主要显示区
			main:new Ext.TabPanel({   
				region:'center',
				id:'mainPanel',   
				enableTabScroll:true,   
				activeTab:0, 
				margins:'0 5 5 0',
				items:[
				   	new Ext.Panel({   
						id:"tab_index",
						title:"首页",
						border:false ,
						autoLoad:{url:'welcome.jsp', scripts:true}
	               	})
				]   
			}),  
			
			//初始化构造函数
			init:function(){
				
				this.menuTree.setRootNode(this.menuRoot);
				this.menuRoot.expand();
				
				//给树形菜单添加事件
				this.menuTree.on('click',this.menuClickAction,this);
				
				//给main的tab页面添加tabchange事件
				this.main.on('tabchange', this.changeTab, this);
		
				var myView = new Ext.Viewport({
					layout:'border',
					border:false,
					items:[this.header,this.menuTree,this.main,this.footer]
				});
		
			},
			//属性菜单的点击事件
			menuClickAction:function(node){
				if(!node.isLeaf()){   
		            return false;   
		        }
		
				var tabId = 'tab-' + node.id;
				var tabTitle = node.text;
				var tablink = '<%=path%>'+node.attributes.link;
				var tab = this.main.getComponent(tabId);//得到tab组建
				
				if(!tab){
		            tab = this.main.add({
				            id:tabId,
							title: tabTitle,
							layout:'fit',        
				            closable:true,
				            loadMask:true,
				            //html:'<iframe id="iframe_'+tabId+'" scrolling="auto" frameborder="0" width="100%" height="100%" src="'+tablink+'"></iframe>'
			            	autoLoad:{url:tablink, scripts:true, params: 'tabId='+tabId}
			         });	
		        }else{  
			        //tab.getUpdater().update(tablink);
		        	//tab.getUpdater().update(tab.autoLoad);
		        	//tab.getUpdater().refresh();
		        	//Ext.get('iframe_'+tabId).dom.src = tablink;
		        }
		        this.main.setActiveTab(tab);
			},
		
			//change的实现
			changeTab:function(tab,newtab){
				this.main.doLayout();
				//如果存在相应树节点，就选中,否则就清空选择状态
		        var nodeId = newtab.id.replace('tab-','');
		        var node = this.menuTree.getNodeById(nodeId);
		        if(node){
		            this.menuTree.getSelectionModel().select(node);
		        }else{
		            this.menuTree.getSelectionModel().clearSelections();
		        }  
			}
		
		});
		
		Ext.onReady(function(){
		    var extExample = new ExtExample.app();
			
			extExample.init();
		});
	</script>
  </head>
  
  <body>
    <div id="north"><span class="api-title">Ext2.0.2(2.1以后license问题) + Strtus2 + Spring2 + Ibatis2 + Hsqldb 自动生成项目</span></div>
	<div id="south">
	    <div class="power" id="power">
	    	Power By: <a href="http://www.blogjava.net/ilovezmh" target="_blank">ZHU</a>&nbsp;
		</div>
	    <div class="bq" id="banquan">
	    	版权所有：<a href="http://www.blogjava.net/ilovezmh" target="_blank">ZHU</a>
	    </div>
	</div>
	<div id="hiddenDiv" style="display:none;"></div>
  </body>
</html>
