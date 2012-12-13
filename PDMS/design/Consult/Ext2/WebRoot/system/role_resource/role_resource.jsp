<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色资源权限管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<style type="text/css">

    </style>
	<script>
		Ext.onReady(function(){
	       	
	       	var store_fields = [
	        	{name: 'r_id'},
	        	{name: 'r_name'},
	        	{name: 'r_desc'},
	        	{name: 'createtime'},
	        	{name: 'status'}
	        ];
	        
	        //var sm = new Ext.grid.CheckboxSelectionModel();
	        var sm = new Ext.grid.RowSelectionModel({singleSelect:true});        
	        
	        var cm = new Ext.grid.ColumnModel([
				//new Ext.grid.RowNumberer(),//自动行号
				//sm,
				{ 
		            header: "角色ID",
		            dataIndex: 'r_id',
		            //hidden : true,
		            //hideable : false,
		            width: 100            	            
		        },{
		            header: "角色名称",
		            dataIndex: 'r_name',
		            width: 100
		        }]);
		        
		    var role_source = new Ext.tree.TreePanel({
		    	id:'role_source',
		    	height:250,
			    autoScroll:true,
			    animate:false,
			    enableDD:false,
			    border: false,
			    containerScroll: true, 
			    rootVisible: true,
			    root: new Ext.tree.AsyncTreeNode({
			        text: '资源树',
			        draggable: false,
			        expanded: true,
			        //uiProvider:Ext.ux.TreeCheckNodeUI,   
			        id:'0'   
			    }),
			    checkModel: "cascade",
			    loader:new Ext.tree.TreeLoader({
					dataUrl:'<%=path%>/system/roleresource!getTree.action',
					baseAttrs: {uiProvider: Ext.ux.TreeCheckNodeUI} //添加 uiProvider 属性
				})
		    });
		    
	        var form_items = [{
	        		inputType:'hidden',
	                name: 'r_id'
	           	},{
	                fieldLabel: '角色名称',
	                name: 'r_name',
	                width:270,
	                readOnly: true
	           	},role_source
	        ];
		    
		    var otherBar = ['角色名称: ',{
				    	xtype:'textfield',
				    	width:200,
				    	id:'search_r_name',
				    	name:'search_r_name'
			    	},'-',{
				    	text:'搜索',
				    	iconCls:'icon-srch',
				    	handler:function(){
					       userlist.store.lastOptions.params['start'] = 0;
					       userlist.store.reload();
				        }
			    	}    	
			    ]
		    
			var config = {
				//----------查询条件Form配置-----------------------
				needConform:false,//是否需要查询条件
				//conform_height:110,//查询面板高度
				//conform_items:conform_items,//查询条件Form属性
				//----------主键ID--------------------------------
				idProperty:'roleid',
				//----------表格数据配置----------------------------
				store_fields:store_fields,
				store_url:'<%=path%>/system/role!list.action',
				limit:10,
				//-----------表格组件配置---------------------------
				gridTitle:'角色列表',
				sm:sm,
				cm:cm,
				autoExpandColumn:'rolename',
				needCurdBar:false,
				otherBar:otherBar,
				search:[{name:'search_r_name',mapping:'r_name'}],
				//-----------对话框配置-----------------------------
				dlgTitle:'角色权限',
				dlgWidth:400,
				dlgHeight:400,
				//-----------详细Form配置---------------------------
				need_form_defaults_width:'no',
				form_items:form_items,	
				//create_url:'<%=path%>/system/user_create.action',
				//update_url:'<%=path%>/system/user_update.action',
				//delete_url:'<%=path%>/system/user_delete.action',
				//-----------显示配置-------------------------------
				tabId:'<%=request.getParameter("tabId")%>'
			};
			
			var userlist = new Test.CURD(config);					    
			userlist.init();
			
			userlist.otherDoSave = function(obj){
				var checkedNodes = Ext.getCmp('role_source').getChecked();//tree必须事先创建好. 
			    var checkedIds = []; 
			    for(var i=0;i<checkedNodes.length;i++){
			        checkedIds.push(checkedNodes[i].id); 
			    } 
			    Ext.Ajax.request({
				    url: '<%=path%>/system/roleresource!update.action',
				    params: {
				    	r_id:obj.form.form.findField('r_id').getValue(),
				    	checkedIds:checkedIds.join(',')
				    },
				    method : 'post',
				    failure: function(form, action){
				        Ext.MessageBox.alert('提示信息', '网络连接异常');
				    },
				    success: function(response, options){
				        var result = Ext.util.JSON.decode(response.responseText);
				        if (result.success == '1'){
				        	obj.dlg.hide();
				            Ext.Msg.alert('提示信息', '数据保存成功');
				        }
				        else{
				            Ext.Msg.alert('提示信息', '数据保存失败');
				        }
				    }
				}); 
			};
			
			userlist.otherRowdblclick = function(grid, rowIndex, e, obj){
				var record = grid.getStore().getAt(rowIndex);   //Get the Record
			    //var data = record.get('loginname');
			    //Ext.MessageBox.alert('show','当前选中的数据是'+data);
			    role_source.getLoader().on('beforeload',function(obj,node,callback){
				   obj.baseParams={r_id:record.get('r_id')};
				});
			    obj.dlg.show();
		        obj.form.getForm().loadRecord(record);
		        role_source.root.reload();
		        role_source.expandAll();
			};
			
		});    
	</script>
  </head>
    
  <body>

  </body>
 </html>