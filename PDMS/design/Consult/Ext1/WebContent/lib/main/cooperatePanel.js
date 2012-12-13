//用户管理
CooperatePanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"cooperatePanel",
	//标题
	title:"协作过程",
	//数据源
	baseUrl:"CooperateSet.do",
	//表单
	createForm:function(){
	
	//======================项目编号 数据源==========================
	var coopProject=new Ext.data.JsonStore({
		id:"Id",
		url: "BaseData.do"+'?cmd=Cooperate&statecode=100',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['instructions_no','request_title']
	});
	coopProject.load();	
	
	//======================项目状态 数据源==========================
	var stateStore=new Ext.data.JsonStore({
		id:"Id",
		url: "StateSet.do"+'?cmd=List',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['action_id','action_name']
	});
	stateStore.load();		
	
	
	//Form表单
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
                        layout:'form',
                        defaultType:'textfield',
                        items:[{xtype:"hidden",name:"id"},
						new Ext.form.ComboBox({
							store:coopProject,
							hiddenName:'instructions_no',
							valueField:'instructions_no',
							displayField:'request_title',
							fieldLabel:'协作项目',
							typeAhead:true,
							mode:'local',
							triggerAction:'all',
							selectOnFocus:true,
							allowBlank:false,
							blankText:"协作项目不允许为空！",							
							width:310
							})]}]
			},{
				layout:'column',
                border:false,
                defaults:{border:false},
                items:[{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'textfield',
                        defaults:{width:104},
                        items:[
                           new Ext.form.ComboBox({
							store:stateStore,
							hiddenName:'action_id',
							valueField:'action_id',
							displayField:'action_name',
							fieldLabel:'项目状态',
							typeAhead:true,
							mode:'local',
							triggerAction:'all',
							allowBlank:false,
							blankText:"项目状态不允许为空！",
							selectOnFocus:true
						})]
                    },{
                    	columnWidth:.5,
                        layout:'form',
                        defaultType:'datefield',
                        defaults:{width:120},
                        format:'yyyy-MM-dd',
                        items:[{
							fieldLabel:'协作时间',
							name:'operating_time',
							width:120,
							allowBlank:false,
							blankText:"协作时间不允许为空！"
					}]
				}]
			}]
		},{
			xtype:'fieldset',
			title:'协作说明',
			autoHeight:true,
			items:[{
                	xtype:"textarea",
                	width:380,
                	height:80,
                 	name:"operating_description",
					allowBlank:false,
					blankText:"协作说明不允许为空！",
                 	hideLabel:true
      	  	}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"协作过程");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('id');
    } ,
	//上传附件
	uploadFile:function(){
	//alert(this.baseUrl);
    this.upload('instructions_no','implementation_no');
    } ,  
    //查看附件
    viewUpload:function(){
    	this.view('instructions_no','implementation_no');
    },    
    storeMapping:["id","implementation_no","instructions_no","request_title","personnel_name","action_name","action_id","operating_time","operating_description","annex"],
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
	{header: "项目编号", sortable:true,width: 300, dataIndex:"instructions_no"},
	{header: "执行序号", sortable:true,width: 100, dataIndex:"implementation_no"},
	{header: "项目标题", sortable:true,width: 300, dataIndex:"request_title"},
	{header: "协作人", sortable:true,width: 300, dataIndex:"personnel_name"},
	{header: "项目状态", sortable:true,width: 300, dataIndex:"action_name"},
	{header: "协作时间", sortable:true,width: 300, dataIndex:"operating_time"},
	{header: "协作说明", sortable:true,width: 300, dataIndex:"operating_description"},
	{header: "附件", sortable:true,width: 300, dataIndex:"annex"}
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
						text:'刷新',
						iconCls:'refreshIcon',
						tooltip:'刷新纪录',          
	                    handler: this.refresh,
	                   scope:this
					},'-',
					{
						text:'上传',
						iconCls:'uploadIconCss',
						tooltip:'上传附件',          
	                    handler: this.uploadFile,
	                    scope:this
					},'-',
					{
						text:'下载',
						iconCls:'viewIconCss',
						tooltip:'下载附件',          
	                    handler: this.viewUpload,
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
	
    CooperatePanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});