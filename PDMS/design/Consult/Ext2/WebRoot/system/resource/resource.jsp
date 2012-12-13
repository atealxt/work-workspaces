<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资源管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<style type="text/css">

    </style>
	<script>
		Ext.onReady(function(){
			var SourceRecord = Ext.data.Record.create([    
			    {name: 'r_id', mapping: 'r_id'},    
			    {name: 'p_rid'}, 
			    {name: 'r_name'},   
			    {name: 'r_desc'},    
			    {name: 'r_type'},    
			    {name: 'linkaddress'},    
			    {name: 'r_order'},
			    {name: 'status'}
			]);  

			var selectedNode;//选中的节点
			//定义右键菜单
		    var rightClick = new Ext.menu.Menu({
		        id :'rightClickCont',
		        items : [{
		            id:'rMenu1',
		            text : '添加子节点',
		            iconCls:'icon-add',
		            //增加菜单点击事件
		            handler:function(){
                      	var record = new SourceRecord({    
						    p_rid : selectedNode.id,  
						    r_id : '', 
						    r_name :'',  
						    r_desc : '',    
						    r_type : '',    
						    linkaddress : '',    
						    status : '',
						    r_order : selectedNode.childNodes.length  
						});
						tree_form.form.loadRecord(record);
                    },
                    scope : this
		        }, {
		            id:'rMenu2',
		            text : '修改',
		            iconCls:'icon-edit',
		            handler:function(){
		            	var record = new SourceRecord({    
						    p_rid : selectedNode.parentNode.id,  
						    r_id : selectedNode.id, 
						    r_name : selectedNode.text,    
						    r_desc : selectedNode.attributes.r_desc,    
						    r_type : selectedNode.attributes.r_type,    
						    linkaddress : selectedNode.attributes.link    
						});
						tree_form.form.loadRecord(record);
		            }
		        }, {
		            id:'rMenu3',
		            text : '删除',
		            iconCls:'icon-del',
		            handler:function(){doDelete();} 
		        }]
		     });
		     
			var tree = new Ext.tree.TreePanel({
				title: "资源树",
				columnWidth:.3,
			    autoScroll:true,
			    animate:true,
			    enableDD:true,
			    border: false,
			    containerScroll: true, 
			    rootVisible: true,
			    root: new Ext.tree.AsyncTreeNode({
			        text: '资源树',
			        draggable: false,
			        expanded: true,
			        id:'0'   
			    }),
			    loader:new Ext.tree.TreeLoader({
					dataUrl:'<%=path%>/system/resource!getTree.action'
				}),
				tools:[{
						id:'refresh',
						qtip:'刷新资源树',
						handler:function(){
							tree.root.reload();
						}
				}],
				listeners: {
		            "click": function(node){
		            	if(node!=tree.root){
			            	var record = new SourceRecord({    
							    p_rid : node.parentNode.id,  
							    r_id : node.id,
							    r_name : node.text,    
							    r_desc : node.attributes.r_desc,    
							    r_type : node.attributes.r_type,    
							    linkaddress : node.attributes.link   
							});
							tree_form.form.loadRecord(record);
							selectedNode = node;
						}
		            },
			        "contextmenu": function(node,event){//声明菜单类型
			        	if(node==tree.root){//根节点不可修改删除
			        		rightClick.items.item(1).disable();
			        		rightClick.items.item(2).disable();
			        	}else{
			        		rightClick.items.item(1).enable();
			        		rightClick.items.item(2).enable();
			        	}
			        	if(node.isLeaf()){//叶子节点不可新增子节点
			        		rightClick.items.item(0).disable();
			        	}else{
			        		rightClick.items.item(0).enable();
			        	}
			          	event.preventDefault();//阻止浏览器默认行为处理事件
			          	node.select(); //节点选中
			          	rightClick.showAt(event.getXY());//取得鼠标点击坐标，展示菜单
			          	//selectedNode = tree.getSelectionModel().getSelectedNode();//得到选中的节点
						selectedNode = node;
			     	},
			     	"movenode": function(tree,node,oldParent,newParent,index){//声明菜单类型
			          	var params = [];
			          	for(var i=0;i<oldParent.childNodes.length;i++){
			          		params.push({ 
							    r_id : oldParent.childNodes[i].id,
							    r_order : i
			          		});
			          	}
			          	if(newParent!=oldParent){
				          	for(var i=0;i<newParent.childNodes.length;i++){
				          		params.push({
				          			p_rid : newParent.id, 
								    r_id : newParent.childNodes[i].id,
								    r_order : i
				          		});
				          	}
			          	}
			          	//alert(Ext.util.JSON.encode(params));
			          	Ext.Ajax.request({
						    url: '<%=path%>/system/resource!updateOrderBy.action',
						    params: {updateJson:Ext.util.JSON.encode(params)},
						    method : 'post',
						    failure: function(form, action){
						        Ext.MessageBox.alert('提示信息', '网络连接异常');
						    },
						    success: function(response, options){
						        var result = Ext.util.JSON.decode(response.responseText);
						        if (result.success == '1'){
						            Ext.Msg.alert('提示信息', '数据修改成功');
						        }
						        else{
						            Ext.Msg.alert('提示信息', '数据修改失败');
						        }
						    }
						});
			     	}
			    }
            });
            
             // simple array store
		    var sourcetype_store = new Ext.data.SimpleStore({
		        fields: ['codeValue', 'codeText'],
		        data : [['1', '目录'],['2', '菜单']]
		    });
    		
            var tree_form = new Ext.FormPanel({
		        labelWidth: 75, // label settings here cascade unless overridden
		        url:'save-form.php',
		        border:false,
		        title: '资源信息',
		        columnWidth:.7,
		        bodyStyle:'padding:5px 5px 0',
		        defaults: {width: 300,msgTarget: 'side'},
		        defaultType: 'textfield',
		        items: [{
		                fieldLabel: '父资源ID',
		                name: 'p_rid',
		                allowBlank:false,
		                readOnly:true
		            },{
		                fieldLabel: '本资源ID',
		                name: 'r_id',
		                readOnly:true
		            },{
		                fieldLabel: '资源名称',
		                name: 'r_name',
		                allowBlank:false
		            },{
		                fieldLabel: '资源描述',
		                name: 'r_desc'
		            },{
		           		xtype:"combo",
		           		store: sourcetype_store,
			            fieldLabel: "资源类型",
			            name: 'r_type',            
	    				valueField :"codeValue",
	                    displayField: "codeText",
	                    mode: 'local',//'remote'
	                    forceSelection: true,
	                    allowBlank:false,
	                    blankText:'请选择资源类型',
	                    emptyText:'请选择资源类型',
	                    hiddenName:"r_type",
	                    editable: true,
	                    triggerAction: 'all'
		        	},{
		                fieldLabel: '资源链接',
		                name: 'linkaddress'
		            },{
		            	xtype: "hidden", 
		                name: 'r_order'
		            }
		        ],
		
		        buttons: [{
		            text: '保存',
		            handler:function(){doSave();}
		        },{
		            text: '重置',
		            handler:function(){tree_form.form.reset();}
		        }]
		    });
		    
		    var doSave = function(){
				var id = tree_form.form.findField('r_id').getValue();
				var save_url;
				if(id==''){
					save_url = '<%=path%>/system/resource!create.action';
				}else{
					save_url = '<%=path%>/system/resource!update.action';
				}
				if (tree_form.form.isValid()){
					tree_form.form.submit({
						waitMsg : '正在处理...',
						url : save_url,    
		                method : 'post',       
		                params : {useAjax:'yes'},    
						failure : function(form, action) {
							Ext.MessageBox.alert('提示信息', '网络连接异常');
						},
					    success : function(form, action) {
					   		if(action.result.success=='1'){
								Ext.MessageBox.alert('提示信息', '数据保存成功'); 
								if(id==''){//新增
									selectedNode.reload();
									//selectedNode.expand();
								}else{//修改
									selectedNode.parentNode.reload();
									//electedNode.parentNode.expand();
								}
								form.reset();
							}else{
								Ext.MessageBox.alert('提示信息', '数据保存失败'); 
							}
					   },
					   scope:this
					});
				} else {
					Ext.MessageBox.alert('提示信息', '请修正页面提示的错误后提交。');
				}
			};
			
			var doDelete = function(){
				var mes;
				if(selectedNode.isLeaf()){//叶子节点
					mes = "确定删除该节点?";
				}else{
					mes = "该节点存在子节点，删除该节点将同时删除所有子节点，确定删除？";
				}
	        	Ext.MessageBox.confirm("提示信息",mes,function(button,text){
					if(button=="yes"){
			        	Ext.Ajax.request({
						    url: '<%=path%>/system/source!delete.action',
						    params: {r_id : selectedNode.id},
						    method : 'post',
						    failure: function(form, action){
						        Ext.MessageBox.alert('提示信息', '网络连接异常');
						    },
						    success: function(response, options){
						        var result = Ext.util.JSON.decode(response.responseText);
						        if (result.success == '1'){
						            Ext.Msg.alert('提示信息', '数据删除成功');
						            selectedNode.parentNode.reload();
						            //selectedNode.parentNode.expand();
						        }
						        else{
						            Ext.Msg.alert('提示信息', '数据删除失败');
						        }
						    }
						});
					}else{
						return;
					}		
				});
					
			};
		    
		    var source_panel = new Ext.Panel({
		    	layout:'column',
		        collapsible:true,
		        border:false,
		        items:[tree,tree_form]
		    });
		    
		    Ext.getCmp('<%=request.getParameter("tabId")%>').add(source_panel);
		    Ext.getCmp('viewPort').doLayout();
			//source_panel.render();
			
		});    
	</script>
  </head>
    
  <body>

  </body>
 </html>