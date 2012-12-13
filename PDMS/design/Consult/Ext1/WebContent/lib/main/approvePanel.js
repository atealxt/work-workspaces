//用户管理
ApprovePanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"approvePanel",
	//标题
	title:"协作审批",
	//数据源
	baseUrl:"ApproveCheck.do",
	//表单
	createForm:function(){
		
	//======================项目状态 数据源==========================
	var stateStore=new Ext.data.JsonStore({
		id:"Id",
		url: "StateSet.do"+'?cmd=List&stateId=100,-100',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['action_id','action_name']
	});
	stateStore.load();				
		
	var formPanel=new Ext.form.FormPanel({
		frame:true,
		labelWidth:70,
		labelAlign:'right',
		items:[{
			xtype:'fieldset',
			title:'审批信息',
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
						{fieldLabel:'项目编号',name:'instructions_no',width:120}
						]},{
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
							selectOnFocus:true
						})]
                    }]
			}]
		},{
			xtype:'fieldset',
			title:'审批意见',
			autoHeight:true,
			items:[{
                	xtype:"textarea",
                	width:380,
                	height:80,
                 	name:"confirmation_reason",
                 	hideLabel:true
      	  	}]
		}]
	});	
	return formPanel;
    },
	//创建窗口
    createWin:function()
    {
    	return this.initWin(438,300,"协作审批");
    },
    //查看附件
    viewUpload:function(){
    	this.view('instructions_no','');
    },	
    storeMapping:["id","action_name","state","instructions_no","category_name","request_title","unit_name","require_completion_time","confirmation_time","confirmation_reason","annex"],
    initComponent : function(){ 
   this.name=new Ext.form.TextField({
				name: 'name',
				anchor:'95%',
				maxLength:25
				}); 	  
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	function check(value){
		if(value=='null'){
			value="";
		}
		return value;
	}
	
    this.cm=new Ext.grid.ColumnModel([
	new Ext.grid.RowNumberer(),//获得行号
    sm,
//    {header: "序号", sortable:true,width: 100, dataIndex:"id"},
	{header: "审批状态", sortable:true,width: 300, dataIndex:"action_name"},
	{header: "项目编号", sortable:true,width: 300, dataIndex:"instructions_no"},
	{header: "项目类型", sortable:true,width: 300, dataIndex:"category_name"},
	{header: "项目标题", sortable:true,width: 300, dataIndex:"request_title"},
	{header: "申请部门", sortable:true,width: 300, dataIndex:"unit_name"},
	{header: "申请时间", sortable:true,width: 300, dataIndex:"require_completion_time"},
	{header: "审批时间", sortable:true,width: 300, dataIndex:"confirmation_time",renderer:check},
	{header: "审批意见", sortable:true,width: 300, dataIndex:"confirmation_reason",renderer:check},
	{header: "附件", sortable:true,width: 300, dataIndex:"annex",renderer:check}
        ]);
		
	
	this.bar=([		//'-'给工具栏按钮之间添加'|'
					{
						id:'editButton',
						text:'审批',
						iconCls:'approveIconCss',
						tooltip:'审批协作请求',
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
	      
    ApprovePanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});