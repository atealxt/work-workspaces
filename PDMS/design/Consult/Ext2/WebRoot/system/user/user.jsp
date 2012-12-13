<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<style type="text/css">

    </style>
		<script>
		
		//转换系统用户状态
		Test.render3 = function(value, p, r){
				if(value=="1"){
		        	return "正常";
		        }else{
		        	return "注销";
		        }
		    };  
		
		Ext.onReady(function(){
	       	
	       	var conform_items = [{                 
	        	layout:'column',                 
	        	items:[
	        		{
	        			columnWidth:.5,
	        			layout: 'form',
	        			items: [{
			                xtype:'textfield',
		                    fieldLabel: '用户名',
		                    name: 'loginname',
		                    anchor:'95%'
			            }]                     
	        		},                     
	        		{
	        			columnWidth:.5,
	        			layout: 'form',
	        			items: [{
			                xtype:'textfield',
		                    fieldLabel: '手机',
		                    name: 'mobilephone',
		                    anchor:'95%'
			            }]
	        		}
	        	]             
	       	}];
	       	
	       	var store_fields = [
	        	{name: 'u_id'},
	        	{name: 'g_id'},
	        	{name: 'loginname'},
	        	{name: 'password'},
	        	{name: 'createtime'},
	        	{name: 'username'},
	        	{name: 'mobilephone'},
	        	{name: 'email'},
	        	{name: 'u_type'},
	        	{name: 'lastlogintime'},
	        	{name: 'status'}
	        ];
	        
	        var sm = new Ext.grid.CheckboxSelectionModel();
	        
	        var cm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),//自动行号
				sm,//checkbox
				{
		            id: 'u_id', 
		            header: "用户ID",
		            dataIndex: 'u_id',
		            width: 50,
		            hidden: true
		            //align: 'right',
		            //renderer: Renderers.renderTopic,	            	            
		        },{
		            header: "所属组织",
		            dataIndex: 'g_id',
		            width: 50,
		            hidden: true
		        },{
		            header: "用户名",
		            dataIndex: 'loginname',
		            width: 100
		        },{
		            header: "密码",
		            dataIndex: 'password',
		            width: 100,
		            hidden: true		            
		        },{
		            header: "创建时间",
		            dataIndex: 'createtime',
		            width: 100		            
		        },{
		            header: "用户名称",
		            dataIndex: 'username',
		            width: 100		            
		        },{
		            header: "手机号",
		            dataIndex: 'mobilephone',
		            width: 70            
		        },{
		            header: "邮箱",
		            dataIndex: 'email',
		            width: 150
	        	},{
		            header: "状态",
		            dataIndex: 'status',
		            renderer: Test.render3,
		            width: 100
		            
	        }]);
	        /*
	        //Ext2.2使用
	        var group_store = new Ext.data.JsonStore({   
		        fields: ['codeValue', 'codeText'], 
		        baseParams:{useAjax:'yes'},
		        proxy: new Ext.data.HttpProxy({
		            url: '<%=path%>/system/group_comboList.action'
		        })    
		    });
		    */
		    /*
		    var group_store = new Ext.data.Store({	        
		        proxy: new Ext.data.HttpProxy({
		            url: '<%=path%>/system/group_comboList.action'
		        }),
		        reader: new Ext.data.JsonReader({
		            fields: ['codeValue', 'codeText']
		        }) 
		    });
		    group_store.load();   // 加载数据
		    */
		    var group_store = new Ext.data.SimpleStore({
		        fields: ['codeValue', 'codeText'],
		        data : [['1', '公司一'],['2', '公司二'],['3', '公司三']]
		    });
		    
		    var otherBar = ['-','真实姓名: ',
	    		{
			    	xtype:'textfield',
			    	width:200,
			    	id:'username',
			    	name:'username'
		    	},'-',{
			    	text:'搜索',
			    	iconCls:'icon-srch',
			    	handler:function(){
				       userlist.store.lastOptions.params['start'] = 0;
				       userlist.store.reload();
			        }
		    	}    	
		    ]
			    
	        var form_items = [{
	        		inputType:'hidden',
	                name: 'u_id'
	           	},{
	           		xtype:"combo",
	           		store: group_store,
		            fieldLabel: "所属组织",
		            name: 'g_id',            
    				valueField :"codeValue",
                    displayField: "codeText",
                    mode: 'local',//'remote'
                    forceSelection: true,
                    allowBlank:false,
                    blankText:'请选择所属组织',
                    emptyText:'请选择所属组织',
                    hiddenName:"g_id",
                    editable: true,
                    triggerAction: 'all'
		        },{
	                fieldLabel: '用户名',
	                name: 'loginname',
	                allowBlank:false,
	                blankText:'请输入用户名'
	           	},{
	                inputType:'password',
	                fieldLabel: '密码',
	                name: 'password',
	                allowBlank:false,
	                blankText:'请输入密码'
	           	},{
	           		xtype:"xdatetime",
		            fieldLabel: "创建时间",
		            name: 'createtime',
	                dateFormat:'Y-m-d',
	                dateConfig: {
	                     altFormats:'Y-m-d|Y-n-d',
	                     allowBlank:false,
	                     blankText:'请输入日期'   
	                },
	                timeFormat:'H:i:s',
	                timeConfig: {
	                     altFormats:'H:i:s',
	                     allowBlank:false,
	                     blankText:'请输入时间'  
	                }      
		        },{
		            fieldLabel: "用户名称",
		            name: 'username',
		            allowBlank:false,
	                blankText:'请输入用户名称'		            
		        },{
	                fieldLabel: '手机号',
	                name: 'mobilephone',
	                allowBlank:false,
	                blankText:'请输入手机号'
	           	},{
	                fieldLabel: '邮箱',
	                name: 'email',
	                vtype:'email',
	                allowBlank:false,
	                blankText:'请输入邮箱'
	           	},{
		            fieldLabel: "状态",
		            name: 'status',
		            allowBlank:false,
	                blankText:'请输入状态'
	        }];
	        
			var config = {
				//----------查询条件Form配置-----------------------
				needConform:false,//是否需要查询条件
				//conform_height:110,//查询面板高度
				//conform_items:conform_items,//查询条件Form属性
				//----------主键ID--------------------------------
				idProperty:'u_id',
				//----------表格数据配置----------------------------
				store_fields:store_fields,
				store_url:'<%=path%>/system/user!list.action',
				limit:10,
				//-----------表格组件配置---------------------------
				gridTitle:'用户列表',
				sm:sm,
				cm:cm,
				autoExpandColumn:'loginname',
				needCurdBar:true,
				otherBar:otherBar,
				search:[{name:'username',mapping:'username'}],
				//-----------对话框配置-----------------------------
				dlgTitle:'用户信息',
				dlgWidth:400,
				dlgHeight:300,
				//-----------详细Form配置---------------------------
				form_items:form_items,	
				create_url:'<%=path%>/system/user!create.action',
				update_url:'<%=path%>/system/user!update.action',
				delete_url:'<%=path%>/system/user!delete.action',
				//delete_otherParams:[],
				//-----------显示配置-------------------------------
				tabId:'<%=request.getParameter("tabId")%>'
			};
			
			var userlist = new Test.CURD(config);					    
			userlist.init();
			
			//解决FF3下日期控件过长的问题
			/*
				方法1：修改CSS
				.x-date-middle {   
				    padding-top:2px;padding-bottom:2px;   
				    width:130px;  
				}  
			*/
			//方法2：
			Ext.override(Ext.menu.DateMenu,{      
			    render : function(){      
			        Ext.menu.DateMenu.superclass.render.call(this);      
			        if(Ext.isGecko){      
			            this.picker.el.dom.childNodes[0].style.width = '178px';      
			            this.picker.el.dom.style.width = '178px';      
			        }      
			    }      
			}); 
			
		}); 
		
		
		   
	</script>
	</head>

	<body>

	</body>
</html>
