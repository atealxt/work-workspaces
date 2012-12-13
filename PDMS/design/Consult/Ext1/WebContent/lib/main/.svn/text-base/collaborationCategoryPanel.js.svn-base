//协作种类
CollaborationCategoryPanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"collaborationCategoryPanel",
	//标题
	title:"协作种类",
	//数据源
	baseUrl:"CollaborationCategory.do",
	//表单
	createForm:function(){
	
	//======================combox数据源==========================
	var dataStore=new Ext.data.JsonStore({
		url: this.baseUrl+'?cmd=List',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['category_code','category_name']
	});
	dataStore.load();
	
	//====================Form窗口==================================
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
                       {fieldLabel:'名称',name:'category_name',width:120}
						]},{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:104},
                        items:[
   						new Ext.form.ComboBox({
							store:dataStore,
							hiddenName:'superiors_code',
							valueField:'category_code',
							displayField:'category_name',
							fieldLabel:'上级',
							typeAhead:true,
							mode:'local',
							triggerAction:'all',
							selectOnFocus:true
						})	
						]}]
			}]
		},{
			xtype:'fieldset',
			title:'备注信息',
			autoHeight:true,
			items:[{
                	xtype:"textarea",
                	width:380,
                	height:80,
                 	name:"category_description",
                 	hideLabel:true
      	  	}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"协作种类");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('category_code');
    } ,
    storeMapping:["category_code","category_name","superiors","category_description"],
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
//	{header: "编号", sortable:true,width: 300, dataIndex:"category_code"},
	{header: "名称", sortable:true,width: 300, dataIndex:"category_name"},
	{header: "上级", sortable:true,width: 300, dataIndex:"superiors"},
	{header: "备注", sortable:true,width: 300, dataIndex:"category_description"}
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
    CollaborationCategoryPanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});