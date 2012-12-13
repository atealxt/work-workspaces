//用户管理
UserPanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"userPanel",
	//标题
	title:"用户管理",
	//数据源
	baseUrl:"user.php",
	//表单
	createForm:function(){
	var formPanel=new Ext.form.FormPanel({
		frame:true,
		labelWidth:70,
		labelAlign:'right',
		items:[{
			xtype:'fieldset',
			title:'基本信息',
			autoHeight:true,
			items:[{
				layout:'column',
                border:false,
                defaults:{border:false},
                items:[{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:120},
                        items:[{xtype:"hidden",name:"Id"},
						{fieldLabel:'用户名',name:'Name',width:120}
						]},{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:104},
                        items:[{
                        	inputType:'password',
                        	fieldLabel:'密&nbsp;&nbsp;码',
                        	name:'Password'
                        }]
                    }]
			},{
				layout:'form',
	            defaultType:'textfield',
				items:[{
	                	width:300,
	                 	name:"Email",
	                 	fieldLabel:'电子邮箱'
	      	  	}]
		}]
		},{
			xtype:'fieldset',
			title:'备注信息',
			autoHeight:true,
			items:[{
                	xtype:"textarea",
                	width:380,
                	height:80,
                 	name:"Remark",
                 	hideLabel:true
      	  	}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"用户管理");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('Id');
    } ,
    storeMapping:["Id","Name","Password","Email","Remark"],
    initComponent : function(){   
	var sm = new Ext.grid.CheckboxSelectionModel();
    this.cm=new Ext.grid.ColumnModel([
	new Ext.grid.RowNumberer(),//获得行号
    sm,
    {header: "编号", sortable:true,width: 100, dataIndex:"Id"},
	{header: "用户名", sortable:true,width: 300, dataIndex:"Name"},
	{header: "密码", sortable:true,width: 300, dataIndex:"Password"},
	{header: "电子邮箱", sortable:true,width: 300, dataIndex:"Email"},
	{header: "备注", sortable:true,width: 300, dataIndex:"Remark"}
        ]);
    UserPanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});