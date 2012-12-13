var removeTopicCategory,removeTopic;
var topicCategoryLoader=Global.topicCategoryLoader;
/**
 * 日志目录
 */
TopicCategoryManage=function()
{
this.storeMapping=["id","name","intro",{name:"op",mapping:"id"}	];
this.operationRender=function(obj){
	return !obj||obj=="-1"?"":"<a href='javascript:removeTopicCategory("+obj+")'>删除</a>";
	};
this.store=new Ext.data.JsonStore({		
  		id:"id",
  		url:"topicCategory.ejf?cmd=getCategory&pageSize=-1",  		
  		root:"result",
  		totalProperty:"rowCount",
  		remoteSort:true,  		
    	fields:this.storeMapping
  		});
this.cm=new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer({header:"序号",width:40,sortable:true}),
		{header: "分类名称",width:120,dataIndex:"name",editor: new Ext.form.TextField({				   
	               allowBlank: false
	           })},
		{header: "简介",dataIndex:"intro",editor:new Ext.form.TextField()},	
		{header: "操作", sortable:false,width: 80, dataIndex:"op",renderer:this.operationRender}
		]);	
this.store.paramNames.sort="orderBy";//改变排序参数名称
this.store.paramNames.dir="orderType";//改变排序类型参数名称
this.cm.defaultSortable=true;
this.grid=new Ext.grid.EditorGridPanel({
			id:"topicCategoryGrid",	
 			store:this.store,
  			cm:this.cm, 
        	loadMask: true,
        	clicksToEdit:1,
        	autoExpandColumn:2,
        	frame:true,
 			region:"center"
		});
this.grid.on("afteredit",this.afterEdit,this);
this.tree=new  Ext.tree.TreePanel({title:"日志分类",
 			region:"west",
 			width:150, 			
 			root:new Ext.tree.AsyncTreeNode({
 				id:"root",
   				text:"日志分类",   	
   				expanded:true,
   				loader:topicCategoryLoader   		
   				})
 			});
this.tree.on("click",function(node,eventObject){
	var id=(node.id!='root'?node.id:"");
	this.store.baseParams.id=id;
	this.store.removeAll();
	this.store.load();
},this);			
TopicCategoryManage.superclass.constructor.call(this, {
		id:"topicCategoryPanel",
		title:"日志分类管理",
		closable: true,
  		autoScroll:true,
  		layout:"border",
  		tbar: [
				'操作: ',' ',{                 
                   text:"新增分类",
                   handler:this.addCategory,
                   scope:this                 
				}, {                 
                   text:"删除分类",
                   handler:this.removeCategory,
                   scope:this      
				},' ',new Ext.Toolbar.Fill(),"查询"
            ],
 		items:[this.tree, 			
 			this.grid
 		]           
   	 });
this.store.load();
}; 
Ext.extend(TopicCategoryManage, Ext.Panel,{
	addCategory:function(){
	    var create=Ext.data.Record.create(this.storeMapping);
	  	var obj=new create({id:'-1',name:'',intro:''});
		this.grid.stopEditing();
		this.store.insert(this.store.getCount(),obj);	
		this.grid.startEditing(this.store.getCount()-1, 0);
   	},
    removeCategory:function(id){
    	var tree=this.tree;
    	var store=this.store;
	   	var m=Ext.MessageBox.confirm("删除提示","是否真的要删除数据？",function(ret){
			if(ret=="yes"){
				topicCategoryService.delTopicCategory(id,function(ret){				
					store.remove(store.getById(id));	
					var node=tree.getNodeById(id);
					var parent=node.parentNode;				
					parent.removeChild(node);				
					if(!parent.childNodes){
					 node.parentNode.getUI().removeClass('x-tree-node-expanded');
	              	 node.parentNode.getUI().addClass('x-tree-node-leaf');
					}			
				});				
			}
		}
		);
   	},
   	afterEdit:function(obj){
	   	var r=obj.record;
		var id=r.get("id");
		var name=r.get("name");
		var c=this.record2obj(r);
		var tree=this.tree;
		var node=tree.getSelectionModel().getSelectedNode();	
		if(node && node.id!="root")c.parentId=node.id;
		if(id=="-1" && name!=""){					
			topicCategoryService.addTopicCategory(c,function(id){				
				if(id)r.set("id",id);
				if(!node)node=tree.root;
				node.appendChild(new Ext.tree.TreeNode({
                    id:id,
                    text:c.name,
                    leaf:true
                }));
                node.getUI().removeClass('x-tree-node-leaf');
                node.getUI().addClass('x-tree-node-expanded');
                node.expand();
			});
		}
		else if(name!="")
		{	
			topicCategoryService.updateTopicCategory(r.get("id"),c,function(ret){	
				if(ret)tree.getNodeById(r.get("id")).setText(c.name);
			});
		}
   	},
   	record2obj:function(r)
	{
		return {name:r.get("name"),
		intro:r.get("intro")		
		};
	}
});


/**
日志菜单
*/
TopicMenuPanel=function()
{	
	TopicMenuPanel.superclass.constructor.call(this, {
        autoScroll:true,
        animate:true,
        border:false,
        rootVisible:false,
        root:new Ext.tree.TreeNode({
        text: '日志管理菜单',
        draggable:false,
       	expanded:true
   	 })        
    });
   this.root.appendChild(new Ext.tree.TreeNode({
   		text:"写新日志",
   		listeners:{'click':function(){
   			var panel=Ext.getCmp("writeTopicPanel");
   			if(!panel){
   				panel=new WriteTopicPanel();
   			}
   			main.openTab(panel);
   			}}	
   		})); 
   this.root.appendChild(new Ext.tree.TreeNode({
   		text:"分类管理",
   		listeners:{'click':function(){
   			var panel=Ext.getCmp("topicCategoryPanel");
   			if(!panel){
   				panel=new TopicCategoryManage();
   				removeTopicCategory=function(id){panel.removeCategory(id);};
   			}
   			main.openTab(panel);
   			}
   		}	
   		}));
 
   var categoryNode=new Ext.tree.AsyncTreeNode({
   		id:"root",
   		text:"日志内容管理",   	
   		loader:topicCategoryLoader
   		});
   this.root.appendChild(categoryNode);
   this.on("click",function(node,eventObject)
   {
   	if(node==categoryNode||categoryNode.contains(node)){
   		var panel=Ext.getCmp("topicListPanel");
   		if(!panel){
   				panel=new TopicListManage();
   				removeTopic=function(id){
   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
   					panel.removeData();};
   				editTopic=function(id){
   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
   					panel.edit();
   					};
   			}
   		main.openTab(panel);
   		panel.store.baseParams.categoryId=(node.id!='root'?node.id:"");
   		panel.store.removeAll();
   		panel.store.reload();
   	}
   });
}
Ext.extend(TopicMenuPanel, Ext.tree.TreePanel,{});

/**
 * 书写新日志或添加日志
 */
WriteTopicPanel=Ext.extend(Ext.Panel,{
	id:"writeTopicPanel",
	title:"书写日志",
	closable: true,
  	autoScroll:true,
  	layout:"fit",  			
	saveTopic:function()
	{	
		var id=this.fp.form.findField("id").getValue();
		this.fp.form.submit({
				waitMsg:'正在保存。。。',
	            url:"topic.ejf?cmd="+(id?"update":"save"),
	            method:'POST',
	            success:function(){
	            var main=Ext.getCmp("main");
	            Ext.Msg.alert("提示信息","数据保存成功!",function(){
	           	main.closeTab(this);
	           	var panel=Ext.getCmp("topicListPanel");
	           	if(!panel)panel=new TopicListManage();
	           	main.openTab(panel);
	           	panel.refresh();},this);
	            },
	            scope:this
		});
	},
	createFormPanel:function(){
		return  new Ext.form.FormPanel({		
		buttonAlign:"center",
		labelAlign:"right",
		bodyStyle:'padding:25px',
		defaults:{width:650},
		autoWidth:true, 
		frame:false,
		bodyBorder:false,
		border:true,
		labelWidth:60,
		items:[	
			{xtype:"hidden",name:"id"},	   
		    {xtype:"textfield",
			 name:"title",
			 fieldLabel:"主题"},
			{xtype:"treecombo",
			 fieldLabel:"所属栏目",
			 name:"category",
			 hiddenName:"category",
			 tree:new Ext.tree.TreePanel({
 				root:new Ext.tree.AsyncTreeNode({
 				id:"root",
   				text:"日志分类",   	
   				expanded:true,
   				loader:Global.topicCategoryLoader
   				})
 			})},
		  	{
		  	xtype:"textarea",
		  	name:"intro",
		  	fieldLabel:"摘要"},
  			{
  			xtype:"htmleditor",
  			height:330,
	  		name:"content",
	  		fieldLabel:"日志内容"
	  		}],
  		buttons:[{text:"提交",
  				  handler:this.saveTopic,
  				  scope:this},
  				  {text:"清空",
  				   handler:function(){this.fp.form.reset();},
  				   scope:this  				   
  				  },
  				  {text:"取消",
  				   handler:function(){Ext.getCmp("main").closeTab(this);},
  				   scope:this  				   
  				  }]
   	 });
   	 },
	initComponent : function(){
	WriteTopicPanel.superclass.initComponent.call(this);
	this.fp=this.createFormPanel();
	this.add(this.fp);	
	}
});
/**
 * 日志列表
 */
TopicListManage=Ext.extend(EasyJF.Ext.CrudPanel,{
	id:"topicListPanel",
	title:"日志内容管理",
	baseUrl:"topic.ejf",
	gridViewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>摘要：'+record.data.intro+'</p><br/>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },
    create:function()
	{
		var main=Ext.getCmp("main");
		var panel=Ext.getCmp("writeTopicPanel");
   			if(!panel){
   				panel=new WriteTopicPanel();
   			}
   		main.openTab(panel);
	},
	edit:function()
	{
		var record=this.grid.getSelectionModel().getSelected();
		if(!record){
			Ext.Msg.alert("提示","请先选择要编辑的行!");
			return;
		}
	    var id=record.get("id");
	   	var main=Ext.getCmp("main");
	   	var panelId="writeTopicPanel"+id;
	  	var writePanel=Ext.getCmp(panelId);
	   	if(!writePanel)writePanel=new WriteTopicPanel({id:panelId,title:"编辑日志:"+record.data.title});
	   	main.openTab(writePanel);
	   	writePanel.fp.form.loadRecord(record);
	},
	topicRender:function(value, p, record){    
    	return String.format('<b><a href="portal.ejf?cmd=topicShow&id={0}" target="_blank">{1}</a></b><br/>',record.id,value)
    },    
    operationRender:function(obj){
	return !obj||obj=="-1"?"":"<a href='javascript:editTopic("+obj+")'>编辑</a>　<a href='javascript:removeTopic("+obj+")'>删除</a>";
	}, 
	categoryRender:function(value,p,record)
	{
		if(!value)return "";
		else return value.name;
	},
	commentsRender:function(value, p, record){   
    	return value?value.length:0;
    },   
	storeMapping:["id","title","intro","readTimes","category","inputTime","comments","content",{name:"op",mapping:"id"}
	],	
    toggleDetails:function(btn, pressed){
        var view = this.grid.getView();
        view.showPreview = pressed;
        view.refresh();
    },
    initComponent : function(){
  	this.cm=new Ext.grid.ColumnModel([{        
           header: "主题",
           dataIndex: 'title',
           width:250,
           renderer:this.topicRender
        },{
           header: "所属栏目",
           dataIndex: 'category',
           width: 100,
           renderer:this.categoryRender
        }
        ,{
           header: "点击数",
           dataIndex: 'readTimes',
           width: 70,
           align: 'center'
        },{
           header: "评论数",
           dataIndex: 'comments',
           width: 70 ,
           renderer:this.commentsRender         
        },{          
           header: "发表时间",
           dataIndex: 'inputTime',
           width: 150
        },
        {header: "操作", sortable:false,width: 80, dataIndex:"op",renderer:this.operationRender}]);  
		TopicListManage.superclass.initComponent.call(this);
	}
});
