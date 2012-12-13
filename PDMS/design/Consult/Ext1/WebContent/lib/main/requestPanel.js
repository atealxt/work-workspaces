Ext.apply(Ext.form.VTypes, {
    daterange : function(val, field) {
        var date = field.parseDate(val);

        if(!date){
            return;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = Ext.getCmp(field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        } 
        else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = Ext.getCmp(field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }

        return true;
    }
});


//协作种类
RequestPanel=Ext.extend(Mis.Ext.CrudPanel,{
	//id，需唯一
	id:"requestPanel",
	//标题
	title:"协作申请",
	//数据源
	baseUrl:"RequestInfo.do",
	//表单
	createForm:function(){
	
	
	//======================项目编号 数据源==========================
	var ProjectCode=new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url:"GetSequences.do"+'?cmd=ProjectCode'
		}),
		reader:new Ext.data.JsonReader({
			remoteSort:true,
			fields:['instructions_no']
		})
	});
	ProjectCode.load();
	
var  codeCombo=new Ext.form.ComboBox({
			store:ProjectCode,
			name:'instructions_no',
			hiddenName:'instructions_no',
			valueField:'instructions_no',
			displayField:'instructions_no',
			fieldLabel:'项目编号',
			typeAhead:true,
			mode:'local',
			triggerAction:'all',
			allowBlank:false,
			selectOnFocus:true                     	
        });

	
	//======================项目类型 数据源==========================
	var groupDS=new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url:"BaseData.do"+'?cmd=getGroupsBySort'
		}),
		reader:new Ext.data.JsonReader({
			root:'rows',
			totalProperty:'totalCount',
			remoteSort:true,
			fields:['category_code','category_name']
		})
	});
	groupDS.load();	
	
	var groupCombo=	new Ext.form.ComboBox({
						store:groupDS,
						emptyText:'请选择大类',
						allowBlank:false,
						name:'category_name_G',
						hiddenName:'category_code_G',
						valueField:'category_code',
						displayField:'category_name',
						fieldLabel:'项目类型',
						typeAhead:true,
						mode:'local',
						triggerAction:'all',
						selectOnFocus:true
					});
	//-----------------------------------------------------------------//
	
	var typeDs=new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url: "BaseData.do"+'?cmd=getTypeBySort'
		}),
		reader:new Ext.data.JsonReader({
			root:'rows',
			totalProperty:'totalCount',
			remoteSort:true,
			fields:['category_code','category_name']			
		})
	});

	var typeCombo=new Ext.form.ComboBox({
					fieldLabel:'类型子类',
					store:typeDs,
					emptyText:'请选择子类',
					allowBlank:false,
					name:'category_name',
					hiddenName:'category_code',
					valueField:'category_code',
					displayField:'category_name',
					typeAhead:true,
					mode:'local',
					triggerAction:'all',
					selectOnFocus:true
				});
				
	groupCombo.on('select',function(){
		typeCombo.reset();
		typeDs.proxy=new Ext.data.HttpProxy({
		url:'BaseData.do?cmd=getTypeBySort&&category_code='+groupCombo.getValue()});
		typeDs.load();
	});		
			
	  //Form面板
	var formPanel=new Ext.form.FormPanel({
		frame:true,
		labelWidth:80,
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
                        defaults:{width:120},
                        items:[{xtype:"hidden",name:"id"},
							codeCombo
						]}]
			},{
				layout:'column',
                border:false,
                defaults:{border:false},
                items:[{
	                        columnWidth:.5,
							layout:'form',
							defaultType:'textfield',
	                        defaults:{width:120},
	                        items:[
							groupCombo]
	                    },
						{
	                        columnWidth:.5,
							layout:'form',
							defaultType:'textfield',
	                        defaults:{width:104},
	                        items:[
							typeCombo]
	                    }]
			},{
				layout:'form',
	            defaultType:'textfield',
				items:[{
	                	width:300,
	                 	name:"category_explain",
						allowBlank:false,
	                 	fieldLabel:'类型说明'
	      	  	}]
		},{
				layout:'column',
                border:false,
                defaults:{border:false},
                items:[{
                           	columnWidth:.5,
                            layout:'form',
                            defaultType:'datefield',
                            defaults:{width:120},
                            format:'Y-m-d',
                            items:[{
								fieldLabel:'企划完成时间',
								id: 'date1',
								name:'require_completion_time',
								vtype:'daterange',
								allowBlank:false,
								endDateField: 'date2'
								}]},{
	                    	columnWidth:.5,
	                        layout:'form',
							defaultType:'datefield',
	                        defaults:{width:104},
							format:'Y-m-d',
	                        items:[{
								fieldLabel:'业主提报时间',
								id: 'date2',
								name:'developer_confirm_time',
								vtype:'daterange',
								allowBlank:false,
								endDateField: 'date3'
								}]
	                    }]
			},
			{
				layout:'column',
                border:false,
                defaults:{border:false},
                items:[	{
                           	columnWidth:.5,
                            layout:'form',
                            defaultType:'datefield',
                            defaults:{width:120},
                            format:'Y-m-d',
                            items:[{
								fieldLabel:'发包时间',
								id: 'date3',
								name:'contract_time',
								vtype:'daterange',
								allowBlank:false,
								endDateField: 'date4'}]
								},{
                           	columnWidth:.5,
                            layout:'form',
                            defaultType:'datefield',
                            defaults:{width:104},
                            format:'Y-m-d',
                            items:[{
								fieldLabel:'发布时间',
								id: 'date4',
								name:'issued_date',
								vtype:'daterange',
								allowBlank:false,
								startDateField: 'date3'
								}]
							}]
			}]
		},{
			xtype:'fieldset',
			title:'项目内容',
			autoHeight:true,
			items:[{
                	xtype:"textarea",
                	width:380,
                	height:80,
                 	name:"request_context",
					allowBlank:false,
                 	hideLabel:true
      	  	}]
		}]
	});	
	return formPanel;
		
    },
    
	//创建窗口
    createWin:function()
    {
    	return this.initWin(460,373,"协作申请");
    },
	//删除
	removeData:function(){
	//alert(this.baseUrl);
    this.remove('id');
    } ,
    //上传附件
    uploadFile:function(){
    	this.upload('instructions_no','','require_personnel');
    },
    //查看附件
    viewUpload:function(){
    	this.view('instructions_no','');
    },
    storeMapping:["id","instructions_no","unit_name","category_name","category_explain","request_title","request_context","developer_confirm_time","issued_date","require_completion_time","contract_time","require_personnel","action_name","state","remark","annex"],
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
	{header: "项目编号", sortable:true,width: 100, dataIndex:"instructions_no"},
	{header: "部门名称", sortable:true,width: 100, dataIndex:"unit_name"},
	{header: "项目类型", sortable:true,width: 100, dataIndex:"category_name"},
	{header: "类型说明", sortable:true,width: 100, dataIndex:"category_explain"},
	{header: "项目标题", sortable:true,width: 100, dataIndex:"request_title"},
	{header: "项目内容", sortable:true,width: 100, dataIndex:"request_context"},
	{header: "企划完成时间", sortable:true,width: 100, dataIndex:"require_completion_time"},
	{header: "业主提报时间", sortable:true,width: 100, dataIndex:"developer_confirm_time"},	
	{header: "发包时间", sortable:true,width: 100, dataIndex:"contract_time"},
	{header: "发布时间", sortable:true,width: 100, dataIndex:"issued_date"},
	{header: "审批状态", sortable:true, dataIndex:"action_name"},
	{header: "附件", sortable:true, dataIndex:"annex"}
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
		
    RequestPanel.superclass.initComponent.call(this);   //调用基类的方法
    }
	});