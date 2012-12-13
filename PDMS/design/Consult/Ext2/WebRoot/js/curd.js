Ext.namespace('Test');//创建名字域
Ext.QuickTips.init(); 
/*
var config = {
	//----------查询条件Form配置-----------------------
	needConform:false,//是否需要查询条件
	//conform_height:110,//查询面板高度
	//conform_items:conform_items,//查询条件Form属性
	//----------主键ID--------------------------------
	idProperty:'roleid',
	//----------表格数据配置----------------------------
	store_fields:store_fields,
	store_url:'<%=path%>/system/role!list.action',
	limit:10,
	//-----------表格组件配置---------------------------
	gridTitle:'角色列表',
	sm:sm,
	cm:cm,
	autoExpandColumn:'rolename',
	needCurdBar:false,
	otherBar:otherBar,
	search:[{name:'search_rolename',mapping:'rolename'}],
	//-----------对话框配置-----------------------------
	dlgTitle:'用户信息',
	dlgWidth:400,
	dlgHeight:300,
	//-----------详细Form配置---------------------------
	form_layout:'column',//使用多列布局
	fileUpload: true,//是否为文件上传
	form_labelWidth:100,//文字宽度
	need_form_defaults_width:'no',//是否需要默认宽度，不需要时需要
	form_items:form_items,	
	create_url:'<%=path%>/system/user_create.action',
	update_url:'<%=path%>/system/user_update.action',
	delete_url:'<%=path%>/system/user_delete.action',
	delete_otherParams:[],
	//-----------显示配置-------------------------------
	tabId:'<%=request.getParameter("tabId")%>'
	//tabId:'<s:property value="tabId" escape="false" />'
};
*/
Test.CURD = function(config){

	Ext.apply(this, config);
	
	this.conform = new Ext.FormPanel({
		//layout:'fit',
		region:'north',
		title:'查询条件',
		height:this.conform_height,
        labelAlign: 'right',
        labelSeparator: ':   ',
        bodyStyle:'padding:5px 5px 0',
        frame:true,
        defaults: {msgTarget: 'side'},
        items: this.conform_items,
       	buttons: [{
	       text: '查询',
	       handler:function(){
		       this.store.lastOptions.params['start'] = 0;
		       this.store.reload();
	       },
	       scope:this
	   	},{
	       text: '重置',
	       handler:function(){this.con_form_reset()},
	       scope:this
	   	}]
        
  	});
  	
  	this.con_form_reset = function(){
		if(typeof this.otherCon_form_reset == 'function'){
	    	this.otherCon_form_reset(this);
    	}else{
			this.conform.form.reset();	
    	}
	};

    this.store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({
            url: this.store_url
        }),
        reader: new Ext.data.JsonReader({
            root: 'roots',
            totalProperty: 'totalCount',
            id: this.idProperty,
            fields: this.store_fields
        }),

        remoteSort: false
    });
    
    this.curd_items = [{
            text:'新增',
            tooltip:'新增',
            iconCls:'icon-add',
            handler:function(){this.doAdd();},
            scope:this
        }, '-', {
            text:'编辑',
            tooltip:'选中一行或双击一行修改',
            iconCls:'icon-edit',
            handler:function(){this.doUpdate();},
            scope:this
        },'-',{
            text:'删除',
            tooltip:'选中一行或多行删除',
            iconCls:'icon-del',
            handler:function(){this.doDelete();},
            scope:this
    	},'-',{
            text:'刷新',
            tooltip:'刷新记录',
            iconCls:'icon-ref',
            handler:function(){this.store.reload();},
            scope:this
	}];
	
	var tbar_items=[];
	if(this.needCurdBar){
		tbar_items = this.curd_items;
	}
    if(Ext.isArray(this.otherBar)){
    	for(var oi=0;oi<this.otherBar.length;oi++){
    		tbar_items.push(this.otherBar[oi]);
    	}
    }
    if(tbar_items.length>0){
	    this.tbar = new Ext.Toolbar({
	    	items:tbar_items
	    });  
    }else{
    	this.tbar = null;
    }

    this.pagingBar = new Ext.PagingToolbar({
        pageSize: this.limit||10,
        store: this.store,
        displayInfo: true,
        displayMsg: '共有{2}条记录.当前显示{0}-{1}条记录.',
        emptyMsg: "没有记录"
    });
    
	this.grid = new Ext.grid.GridPanel({
		layout:'fit',
        region:'center',
        title:this.gridTitle,
        //bodyStyle:'width:100%',
        //autoWidth:true,    
        autoScroll:true,
        store: this.store,
        sm: this.sm,
        cm: this.cm,
        autoExpandColumn: this.autoExpandColumn,        
        trackMouseOver:false,
        loadMask:true,
        viewConfig: {
        	forceFit:true
        },
        tbar:this.tbar,
        bbar: this.pagingBar
    });
    
    if(this.form_layout=='column'){
    	this.form = new Ext.FormPanel({
	        labelWidth: this.form_labelWidth||60,
	        labelAlign: 'right',
	        labelSeparator: ':   ',
	        bodyStyle:'padding:17px 17px 0',
	        frame:true,
	        defaults: {msgTarget: 'side'},
	        fileUpload: this.fileUpload||false,
	        items: this.form_items,
	       	keys: [{
	            key: [10,13],
	            fn: function(){
	                
	            }
	        }]
	        
	  	});
    }else{
    	var form_defaults;
	    if(this.need_form_defaults_width=='no'){
	    	form_defaults = {msgTarget: 'side'};
	    }else{
	    	form_defaults = {width:270,msgTarget: 'side'};
	    }
    	this.form = new Ext.FormPanel({
	        labelWidth: this.form_labelWidth||60,
	        labelAlign: 'right',
	        labelSeparator: ':   ',
	        bodyStyle:'padding:17px 17px 0',
	        frame:true,
	        defaults: form_defaults,
	        defaultType: 'textfield',
	        fileUpload: this.fileUpload||false,
	        items: this.form_items,
	       	keys: [{
	            key: [10,13],
	            fn: function(){
	                
	            }
	        }]
	  	});
    }
    
    this.dlg = new Ext.Window({
	  	autoCreate : true,
	  	title:this.dlgTitle||'详细信息',
	  	layout: 'fit',
      	resizable:false,
      	constrain:true,
      	constrainHeader:true,
      	minimizable : false,
      	maximizable : false,
      	stateful: false,
      	modal: true,
      	shim:true,
      	buttonAlign:"center",
      	width:this.dlgWidth||400,
      	height:this.dlgHeight||300,
      	minHeight: 80,
      	border:false,
      	plain:true,
      	footer:true,
      	closable:true,
      	closeAction:'hide',
      	items: this.form,
	  	buttons: [{
	       text: '提交',
	       type: 'submit',
	       handler:function(){this.doSave();},
	       scope:this
	   	},{
	       text: '关闭',
	       handler:function(){this.dlg.hide();},
	       scope:this
	   	}]
    });

    this.rowdblclick = function(grid, rowIndex, e){
    	if(typeof this.otherRowdblclick == 'function'){
    		this.otherRowdblclick(grid, rowIndex, e,this);
    	}else{
			var record = grid.getStore().getAt(rowIndex);   //Get the Record
		    //var data = record.get('loginname');
		    //Ext.MessageBox.alert('show','当前选中的数据是'+data);						
	        this.dlg.show();
	        this.form.getForm().loadRecord(record);
        }
	};
    
    // 保存修改信息
	this.doSave = function(){
		if(typeof this.otherDoSave == 'function'){
    		this.otherDoSave(this);
    	}else{
			var id = eval("this.form.form.findField('"+this.idProperty+"').getValue();");
			var save_url;
			if(id==''){
				save_url = this.create_url;
			}else{
				save_url = this.update_url;
			}
			if (this.form.form.isValid()){
				this.form.form.submit({
					waitMsg : '正在处理...',
					url : save_url,    
	                method : 'post',       
	                params : {useAjax:'yes'},    
					failure : function(form, action) {
						Ext.MessageBox.alert('提示信息', '保存失败，可能网络超时，请重新登录');
					},
					success : function(form, action) {
						//if(action.result.success=='1'){
							Ext.MessageBox.alert('提示信息', '数据保存成功'); 
							this.dlg.hide();
							this.store.reload();
						//}else{
							//Ext.MessageBox.alert('提示信息', '数据保存失败'); 
						//}
				   },
				   scope:this
				});
			} else {
				Ext.MessageBox.alert('提示信息', '请修正页面提示的错误后提交。');
			}
		}
	};
	
	this.doAdd = function(){
		if(typeof this.otherDoAdd == 'function'){
	    		this.otherDoAdd(this);
    	}else{
			this.form.form.reset();
	    	this.dlg.show();	
    	}
	};
	
	this.doUpdate = function(){
		if(this.grid.selModel.hasSelection()){   
  				var records = this.grid.selModel.getSelections();//得到被选择的行的数组
    		if(records.length>1){
    			Ext.Msg.alert("系统提示信息","请选择其中一项进行编辑！");}//一次只给编辑一行
    		else{
                var record = this.grid.getSelectionModel().getSelected();//获取选择的记录集
                this.dlg.show();
                this.form.form.loadRecord(record); //往表单（fp.form）加载数据
            }
       	}else{   
            Ext.Msg.alert("提示","请先选择要编辑的行!");
        }
				
	};
	
	this.doDelete = function(){
		if(this.grid.selModel.hasSelection()){
			if(typeof this.otherDoDelete == 'function'){
	    		this.otherDoDelete(this);
	    	}else{
		    	var records = this.grid.selModel.getSelections();//得到被选择的行的数组
		      	var aa = [];
		      	if(Ext.isArray(this.delete_otherParams)){
		      		this.delete_otherParams.push(this.idProperty);
		      	}else{
		      		this.delete_otherParams = [this.idProperty];
		      	}
		      	for(var i=0;i<records.length;i++){
			    	var o = {};
			    	for(var j=0;j<this.delete_otherParams.length;j++){
			    		eval("o."+this.delete_otherParams[j]+"="+records[i].get(this.delete_otherParams[j]));
			    	}
			    	aa.push(o);
		      	}
		      	//var jsonData = JSON.stringify(aa);
		      	var jsonData = Ext.encode(aa);
		     	Ext.MessageBox.confirm('系统提示信息', '确定要删除所选的记录吗?', function(buttonobj){
		      		if(buttonobj=='yes'){
			      		var myCon = new Ext.data.Connection();
			      		Ext.MessageBox.wait('正在删除数据中, 请稍候……'); //出现一个等待条
			      		myCon.request({
							url:this.delete_url,
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
			       			scope:this
			      		});
		      		}
		     	},this);
	    	}
	    }else{   
	        Ext.Msg.alert("提示","请先选择要删除的行!");
	    }
	};
	
	this.init = function(){
			
		this.cm.defaultSortable = true;
		
		this.store.load({params:{start:0, limit:this.limit||10}});
    	this.store.on('beforeload',function(){
    		var cons = {};
    		if(this.needConform){
	    		this.conform.form.items.each(function(f){
	               if(f.isFormField){
	                   if(f.getValue()==''){
	                   		eval("cons['"+f.getName()+"']=null;");
	                   }else{
	                   		eval("cons['"+f.getName()+"']='"+f.getValue()+"';");
	                   }
	               }
	            });
	        }
	        if(Ext.isArray(this.search)){
        		for(var sl=0;sl<this.search.length;sl++){
        			if(Ext.get(this.search[sl].name).getValue()==''){
		        		eval("cons['"+this.search[sl].mapping+"']=null;");
		        	}else{
		        		eval("cons['"+this.search[sl].mapping+"']='"+Ext.get(this.search[sl].name).getValue()+"';");
		        	}
        		}
	        }
	        Ext.apply(      
	        	this.store.baseParams,cons
	        );
    	},this); 
    	
    	this.grid.on('rowdblclick', this.rowdblclick, this);
    	
    	if(this.needConform){ 
	        Ext.getCmp(this.tabId).add(new Ext.Panel({
	            layout  :'border',
	            border  :false,
	            items:  [this.conform,this.grid]
	        }));
        }else{
        	Ext.getCmp(this.tabId).add(this.grid);
        }
		Ext.getCmp('viewPort').doLayout();
	};
	
};

Test.render1 = function(value, p, record){
        return String.format(
            '<a href="http://localhost:9001/AutoTest/*.action?id={0}" target="_blank">查看</a>',
            value, record.data.u_id);
    };
    
Test.render2 = function(value, p, r){
		if(value!=""){
        	return String.format('{0}', value.substring(0,19));
        }else{
        	return String.format('{0}', value);
        }
    };

