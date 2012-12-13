<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色管理</title>
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
	        	{name: 'createtime'},
	        	{name: 'r_desc'},
	        	{name: 'status'},
	        	{name: 'userCount'}
	        ];
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        //var sm = new Ext.grid.RowSelectionModel({singleSelect:true});        
	        
	        var cm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),//自动行号
				sm,
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
		        },{
		            header: "角色描述",
		            dataIndex: 'r_desc',
		            width: 100
		        },{
		            header: "用户数量",
		            dataIndex: 'userCount',
		            hidden: true,
		            hideable: false,
		            width: 100
		        }]);
		        
		    
	        var form_items = [{
	        		inputType:'hidden',
	                name: 'r_id'
	           	},{
	                fieldLabel: '角色名称',
	                name: 'r_name',
	                allowBlank:false,
	                width:270
	           	},{
	           		xtype:'textarea',
	                fieldLabel: '角色描述',
	                name: 'r_desc',
	                width:270
	           	}
	        ];
		    
		    var otherBar = ['-','角色名称: ',{
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
				idProperty:'r_id',
				//----------表格数据配置----------------------------
				store_fields:store_fields,
				store_url:'<%=path%>/system/role!list.action',
				limit:10,
				//-----------表格组件配置---------------------------
				gridTitle:'角色列表',
				sm:sm,
				cm:cm,
				autoExpandColumn:'r_name',
				needCurdBar:true,
				otherBar:otherBar,
				search:[{name:'search_r_name',mapping:'r_name'}],
				//-----------对话框配置-----------------------------
				dlgTitle:'角色信息', 
				dlgWidth:400,
				dlgHeight:220,
				//-----------详细Form配置---------------------------
				form_items:form_items,	
				create_url:'<%=path%>/system/role!create.action',
				update_url:'<%=path%>/system/role!update.action',
				delete_url:'<%=path%>/system/role!delete.action',
				//-----------显示配置-------------------------------
				tabId:'<%=request.getParameter("tabId")%>'
			};
			
			var userlist = new Test.CURD(config);					    
			userlist.init();
			
			userlist.otherDoDelete = function(obj){
				var records = obj.grid.selModel.getSelections();//得到被选择的行的数组
		      	var aa = [];
		      	var bb = '';
		      	if(typeof obj.delete_otherParams =='object' && obj.delete_otherParams.constructor==Array){
		      		obj.delete_otherParams.push(obj.idProperty);
		      	}else{
		      		obj.delete_otherParams = [obj.idProperty];
		      	}
		      	for(var i=0;i<records.length;i++){
			    	var o = {};
			    	if(records[i].get('userCount')>0){
			    		bb+='角色ID为：'+records[i].get('roleid')+' 的角色存在用户，不可删除<br/>';
			    	}else{
				    	for(var j=0;j<obj.delete_otherParams.length;j++){
				    		eval("o."+obj.delete_otherParams[j]+"="+records[i].get(obj.delete_otherParams[j]));
				    	}
				    	aa.push(o);
			    	}
		      	} 
		      	//var jsonData = JSON.stringify(aa);
		      	var jsonData = Ext.encode(aa);
		      	var del_message = '确定要删除所选的记录吗?';
		      	if(bb.length>0){
		      		del_message = bb+'点“是”删除其它所选角色，或“否”修改用户角色后再删除。';
		      	}
		     	Ext.MessageBox.confirm('提示信息', del_message, function(buttonobj){
		      		if(buttonobj=='yes'){
			      		var myCon = new Ext.data.Connection();
			      		Ext.MessageBox.wait('正在删除数据中, 请稍候……'); //出现一个等待条
			      		myCon.request({
							url:obj.delete_url,
			       			method:"POST",
			       			params:{useAjax:'yes',jsonData:jsonData},
					       	callback:function(a,b,c){
					        	Ext.MessageBox.hide();
						        if(b==true){
						            Ext.Msg.alert("提示信息","成功删除数据!",function(){this.store.reload();},this);
						        }else{
						            Ext.MessageBox.alert("系统提示信息","异步通讯失败,更新失败,请与管理员联系！");
						        }
			       			},
			       			scope:obj
			      		});
		      		}
		     	},obj);
			}
			
		});    
	</script>
  </head>
    
  <body>

  </body>
 </html>