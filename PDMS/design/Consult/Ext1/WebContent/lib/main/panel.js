/**
 * 定义命名空间
 */
Ext.namespace("Mis.Ext");

/*
*CRUD面板基类
*/
Mis.Ext.CrudPanel=Ext.extend(Ext.Panel,{
	closable: true,
  	autoScroll:true,
  	layout:"fit",
  	gridViewConfig:{},  
	//链接
  	linkRenderer:function(v)
  	{
  		if(!v)return "";
  		else return String.format("<a href='{0}' target='_blank'>{0}</a>",v);
  	},
	//时间
	dateRender:function(format)
    {
    	format=format||"Y-m-d h:i";
    	return Ext.util.Format.dateRenderer(format);
    },
	//查询
    search:function()
    {   
        var sname = this.name.getValue();//得到输入框的值
//        alert(sname);
    	this.store.load({params:{start:0,limit:30,name:sname}});
    },
	//刷新
    refresh:function()
    {
    	this.store.removeAll();
   		this.store.reload();
    },  
	//初始化窗口（用于新增，修改时）,继承后在createWin中调用该方法显示窗口
    initWin:function(width,height,title)
    {
    	var win=new Ext.Window({
			width:width,
			height:height,
			buttonAlign:"center",
			title:title,
			modal:true,
			shadow:true,
			closeAction:"hide",
			items:[this.fp],
			buttons:[{text:"保存",
					  handler:this.save,
					  scope:this},
					  {text:"清空",
					   handler:this.reset,
					   scope:this},
					  {text:"关闭",
					   handler:this.closeWin,
					   scope:this}
					   	]					  
		});
		return win;
    },
	//显示（新增/修改）窗口
    showWin:function()
	{	//createForm()需要在继承时提供，该方法作用是创建表单
		if(!this.win){
			if(!this.fp){
				this.fp=this.createForm();
			}
		this.win=this.createWin();
		this.win.on("close",function(){this.win=null;this.fp=null;this.store.reload();},this);
		}
		//窗口关闭时，数据重新加载
		this.win.show();
	},
	//创建（新增/修改）窗口
	create:function()
	{
		this.showWin();
		this.reset();
	},
	//数据保存[（新增/修改）窗口]
	save:function()
	{
		var id=this.fp.form.findField("id").getValue();		
		this.fp.form.submit({
				waitMsg:'正在保存。。。',
	            url:this.baseUrl+"?cmd="+(id?"Update":"Save"),
	            method:'POST',
	            success:function(form_instance_create, action){
				Ext.MessageBox.alert('友情提示', action.result.info);
	           	this.closeWin();
	           	this.store.reload();          	
	            },
				failure:function(form_instance_create, action){
//					Ext.MessageBox.alert("aa",action.result);
					Ext.MessageBox.show({
						title: '友情提示',
						msg: '检查表单是否填写完整!',
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.WARNING
					});
//				Ext.MessageBox.alert('友情提示', action.result.info);  	
	            },
	            scope:this
		});	
	},
	//（新增/修改）窗口上的清空
	reset:function()
	{
	if(this.win)this.fp.form.reset();
	},
	//（新增/修改）窗口上的关闭
	closeWin:function()
	{
		if(this.win)this.win.close();
		this.win=null;
		this.fp=null;
		this.store.reload();       
	},
	//修改，双击行，或选中一行点击修改，
	edit:function()
	{
		if(this.grid.selModel.hasSelection()){   
				var records = this.grid.selModel.getSelections();//得到被选择的行的数组
				var recordsLen = records.length;//得到行数组的长度
				if( recordsLen>1){
				Ext.Msg.alert("系统提示信息","请选择其中一项进行编辑！");}//一次只给编辑一行
				else{
                        var record=this.grid.getSelectionModel().getSelected();//获取选择的记录集
	                    var id=record.get("id");
	                    this.showWin();
	                    this.fp.form.loadRecord(record); //往表单（fp.form）加载数据
	                }
			    } else {   
                 Ext.Msg.alert("提示","请先选择要编辑的行!");
                }
	},	
	//删除,pid为主键值
	remove:function(pid)
	{
	          //var result;
	          var store=this.store;
	          var baseUrl=this.baseUrl;
              if(this.grid.selModel.hasSelection()){   
						var records = this.grid.selModel.getSelections();//得到被选择的行的数组
						var recordsLen = records.length;//得到行数组的长度
						var jsonStr = '';
						for(var i=0;i<recordsLen;i++){
							var id = records[i].get(pid);
							if(i!=0){
								jsonStr +=','+id ;
							}else{
								jsonStr += ''+id ;
							}
						}
						jsonStr += '' ; 
						//this.store.reload();这里能执行
						Ext.MessageBox.confirm('系统提示信息', '确定要删除所选的记录吗?', function(buttonobj){
					if(buttonobj=='yes'){
						var myCon = new Ext.data.Connection();
						Ext.MessageBox.wait('正在删除数据中, 请稍候……'); //出现一个等待条
						myCon.request({
							url:baseUrl+'?cmd=Remove',
							//url:'Plant.aspx?cmd=Remove',
							method:"GET",
							params:{'json':jsonStr},
							//callback : Function (Optional) options, success : Boolean ,response : Object 
							callback:function(a,b,c){
								Ext.MessageBox.hide();
								if(b==true){
									   Ext.Msg.alert("提示信息",c.responseText,function(){store.reload();},this);
								}else{
									    Ext.MessageBox.alert("系统提示信息","异步通讯失败,更新失败,请与管理员联系！");
								}
							}
						},this);
						}//if..yes
					},this);
			    } else {   
                         Ext.Msg.alert("提示","请先选择要删除的行!");
                }
	},
	//上传附件
	upload:function(code,no,PerCode)
	{	
	          //var result;
	          var store=this.store;
	          var baseUrl=this.baseUrl;
              if(this.grid.selModel.hasSelection()){     			  
						var records = this.grid.selModel.getSelections();//得到被选择的行的数组
						var recordsLen = records.length;//得到行数组的长度
						var strCode = '';
						var strNo='';
						var strPerson='';
						strCode=records[0].get(code);
						strNo=records[0].get(no);
						strPerson=records[0].get(PerCode);
						if(recordsLen>1){				
							Ext.Msg.alert("系统提示信息","请选择其中一项进行上传！");
						}else{
				           dialog = new Ext.ux.UploadDialog.Dialog({
				               url: 'FileUpload.do?cmd=Upload&strCode='+strCode+'&strNo='+strNo,
				               reset_on_hide: false,
				               allow_close_on_upload: true,
				               upload_autostart:false,
				               post_var_name:'upload',
					   			modal:true,
								shadow:true,				               
								closeAction:"hide"
				             });
				             dialog.show('show-button');
				         };
			    } else {   		
                         Ext.Msg.alert("提示","请先选择要上传附件的行!");
                }
	},	
	//查看附件
	view:function(code,no)
	{
	          var store=this.store;
	          var baseUrl=this.baseUrl;
              if(this.grid.selModel.hasSelection()){   
						var records = this.grid.selModel.getSelections();//得到被选择的行的数组
						var recordsLen = records.length;//得到行数组的长度
						var strCode = '';
						var strNo='';
						strCode=records[0].get(code);
						strNo=records[0].get(no);
						if(recordsLen>1){
							Ext.Msg.alert("系统提示信息","请选择其中一项进行下载！");
						}else{
						//this.store.reload();这里能执行
						//======================获得 数据源==========================								
						var annexStore=new Ext.data.JsonStore({
							id:"Id",
							url: 'FileUpload.do?cmd=View&strCode='+strCode+'&strNo='+strNo,
							root:'rows',
							totalProperty:"totalCount",
							remoteSort:true,
							fields:['instructions_no','annex_name','annex_address']
						});
						annexStore.load();						
									
						function showUrl(value){
							return "<a href='"+value+"' target='_blank'>查看</a>";
						};					
						var annexGrid=new Ext.grid.GridPanel({
							title:'查看附件',
				           height:300,
				           width:550, 	
				           columns:[
		                             {header:'项目编号',width:100,dataIndex:'instructions_no'},
		                             {header:'附件名称',width:200,dataIndex:'annex_name'},
		                             {header:'查看附件',width:200,dataIndex:'annex_address',renderer:showUrl}
								],
					        store: annexStore
						});							
						};

			           var win = new Ext.Window({
			                layout      : 'fit',
			                width       : 550,
			                height      : 300,
			                closeAction :'hide',
			                plain       : true,
				   			modal:true,
							shadow:true,				               
							closeAction:"hide",							
			                items       : annexGrid
			            });
			        win.show();
						
			    } else {   
                         Ext.Msg.alert("提示","请先选择要查看附件的行!");
                }
	},	
	sm:function()
	{
	      var csm= new Ext.grid.CheckboxSelectionModel();
	      return csm;
	},
	//初始化GRID面板
    initComponent : function(){  
       this.store=new Ext.data.JsonStore({
		id:"Id",
       	url: this.baseUrl+'?cmd=List',//默认的数据源地址，继承时需要提供
       	root: "rows",
  		totalProperty:"totalCount",
  		remoteSort:true,  		
  		fields:this.storeMapping});
      	this.cm.defaultSortable=true;   
      	this.sm= new Ext.grid.CheckboxSelectionModel();	  	
        Mis.Ext.CrudPanel.superclass.initComponent.call(this);
        var viewConfig=Ext.apply({forceFit:true},this.gridViewConfig);  
        this.grid=new Ext.grid.GridPanel({
        store: this.store,
        cm: this.cm,
        sm:this.sm,
        trackMouseOver:false,    
        loadMask: true,
        viewConfig:viewConfig,
        tbar: this.bar,
        bbar: new Ext.PagingToolbar({
            pageSize: 30,
            store: this.store,
            displayInfo: true,
            displayMsg: '显示第 {0} - {1} 条记录，共 {2}条记录',
            emptyMsg: "没有记录"
        })
   		});  
		
		//行的颜色
	
	this.grid.getView().getRowClass = function(record, index){
		if(record.data.state>=100){
			return 'green-row';
		}

		if(record.data.state==0){
			return 'yellow-row';
		}		
		if(record.data.state==1){
			return 'yellow-row';
		}	
		if(record.data.state==2){
			return 'yellow-row';
		}					
		if(record.data.state==-100){
			return 'red-row';
		}	
		
//====================================//
		if(record.data.action_id>=100){
			return 'green-row';
		}
			
		if(record.data.action_id==0){
			return 'yellow-row';
		}	
		if(record.data.action_id==1){
			return 'yellow-row';
		}		
		if(record.data.action_id==2){
			return 'yellow-row';
		}				
			
		if(record.data.action_id==-100){
			return 'red-row';
		}			
		return  '';
	};		

		//双击时执行修改
   		this.grid.on("celldblclick",this.edit,this);       
   		this.add(this.grid);
   		this.store.load({params:{start:0,limit:30}});
        }
});

//主panel
MainPanel = function() {
	this.openTab = function(panel, id) {
		var o = (typeof panel == "string" ? panel : id || panel.id);
		var tab = this.getComponent(o);		
		if (tab) {
			this.setActiveTab(tab);
		} else if(typeof panel!="string"){
			panel.id = o;
			var p = this.add(panel);
			this.setActiveTab(p);
		}
	};
	this.closeTab = function(panel, id) {
		var o = (typeof panel == "string" ? panel : id || panel.id);
		var tab = this.getComponent(o);
		if (tab) {
			this.remove(tab);
		}
	};
	MainPanel.superclass.constructor.call(this, {
		id : 'main',
		region : 'center',
		margins : '0 5 5 0',
		resizeTabs : true,
		minTabWidth : 135,
		tabWidth : 135,
		enableTabScroll : true,
		activeTab : 0,
		items : [{
			id : 'homePage',
			title : '主页',
			closable : false,
			autoLoad : {
				url : 'lib/report/about.html',
				callback:function(a,b,c){
						var jsStr="";
						Ext.Ajax.request({
							method:'POST',
							url:"lib/report/about.js",
							scope:this,
							success:function(response){
								jsStr+=response.responseText;
								var model=eval(jsStr);
							}
						});
					}				
			},
			autoScroll : true
		}]
	});
	
};
Ext.extend(MainPanel, Ext.TabPanel);