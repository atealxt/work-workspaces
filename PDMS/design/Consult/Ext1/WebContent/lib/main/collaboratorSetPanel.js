//协作种类
CollaboratorSetPanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"collaboratorSetPanel",
	//标题
	title:"项目协作人",
	//数据源
	baseUrl:"CollaboratorSet.do",
	//表单
	createForm:function(){
		
	//======================项目名称数据源==========================
	var unitStore=new Ext.data.JsonStore({
		id:"Id",
		url: "BaseData.do"+'?cmd=List',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['unit_code','unit_name']
	});
	unitStore.load();				
		
	//======================人员数据源==========================
	var personsStore=new Ext.data.JsonStore({
		id:"Id",
		url: "BaseData.do"+'?cmdPerson=PersonData',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['personnel_code','personnel_name']
	});
	personsStore.load();			
		
		
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
       						new Ext.form.ComboBox({
    							store:unitStore,
    							hiddenName:'unit_code',
    							valueField:'unit_code',
//    							value:['A04'],
    							displayField:'unit_name',
    							fieldLabel:'协作部门',
    							typeAhead:true,
    							mode:'local',
    							triggerAction:'all',
    							selectOnFocus:true
    						})							
						]},{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:104},
                        items:[
       						new Ext.form.ComboBox({
    							store:personsStore,
    							hiddenName:'personnel_code',
    							valueField:'personnel_code',
    							displayField:'personnel_name',
    							fieldLabel:'协作人',
    							typeAhead:true,
    							mode:'local',
    							triggerAction:'all',
    							selectOnFocus:true
    						})]
                    }]
			}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"项目协作人");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('id');
    } ,
    storeMapping:["id","unit_name","personnel_name"],
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
    {header: "序号", sortable:true,width: 100, dataIndex:"id"},
	{header: "协作部门", sortable:true,width: 300, dataIndex:"unit_name"},
	{header: "协作人", sortable:true,width: 300, dataIndex:"personnel_name"}
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