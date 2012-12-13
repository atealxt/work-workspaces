//协作种类
StateSetPanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"stateSetPanel",
	//标题
	title:"状态设置",
	//数据源
	baseUrl:"StateSet.do",
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
                        items:[{xtype:"hidden",name:"id"},
						{fieldLabel:'状态编号',name:'action_id',width:120}
						]},{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:104},
                        items:[{
                        	fieldLabel:'状态名称',
                        	name:'action_name'
                        }]
                    }]
			}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"状态设置");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('id');
    } ,
    storeMapping:["id","action_id","action_name"],
    initComponent : function(){   
   this.name=new Ext.form.TextField({
				name: 'name',
				anchor:'95%',
				maxLength:25
				}); 		
	var sm = new Ext.grid.CheckboxSelectionModel();
    this.cm=new Ext.grid.ColumnModel([
	new Ext.grid.RowNumberer(),//获得行号
    sm,
	{header: "状态编号", sortable:true,width: 300, dataIndex:"action_id"},
	{header: "状态名称", sortable:true,width: 300, dataIndex:"action_name"}
        ]);
		
	this.bar=([{
						id:'addButton',
						text: '新增',
						iconCls:'addIconCss',
						tooltip:'添加新纪录',
						handler: this.create,
	                    scope:this
					},'-',//'-'给工具栏按钮之间添加'|'
					{
						id:'editButton',
						text:'编辑',
						iconCls:'editIconCss',
						tooltip:'修改记录',
						handler: this.edit,
	                    scope:this
					},'-',
					{
						text:'删除',
						iconCls:'deleteIconCss',
						tooltip:'删除所选中的信息',          
	                    handler: this.removeData,
	                    scope:this
					},'-',
					{
						text:'刷新',
						iconCls:'refreshIcon',
						tooltip:'刷新纪录',          
	                    handler: this.refresh,
	                   scope:this
					},'->',//'->'代表让工具栏按钮到右边去
			{    
	            text: '查询',   
	            pressed: true,  
	            iconCls:'selectIconCss',         
	            handler: this.search,
	            scope:this
	        },this.name,'   '
	    ]);		
		
    StateSetPanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});