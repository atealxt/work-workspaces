MenuPanel = function() {
	MenuPanel.superclass.constructor.call(this, {
		id : 'menu',
		region : 'west',
		title : "系统菜单",
		split : true,
		width : 200,
		minSize : 175,
		maxSize : 500,
		collapsible : true,
		margins : '0 0 5 5',
		cmargins : '0 0 0 0',		
		layout : "accordion",
		layoutConfig : {
				titleCollapse : true,
				animate : true
			},
			items : [ {
				title : "协作请求",
				items : [new RequestMenuPanel()]
			},
			{
				title : "协作管理",
				items : [new ManageMenuPanel()]
			}/*,
			{
				title : "用户管理",
				items : [new UserMenuPanel()]
			}*/]
		});
};
Ext.extend(MenuPanel, Ext.Panel);

//--------------------------------- 协作请求菜单---------------------------------//
RequestMenuPanel=function()
{	//调用基类构造函数
	RequestMenuPanel.superclass.constructor.call(this, {
        autoScroll:true,
        animate:true,
        border:false,
        rootVisible:false,
        root:new Ext.tree.TreeNode({
        text: '协作申请',
        draggable:false,
       	expanded:true
   	 })        
    }); 
   this.root.appendChild(new Ext.tree.TreeNode({
   		text:"协作申请",
   		listeners:{'click':function(){
   			var panel=Ext.getCmp("requestPanel");
   			if(!panel){
   				panel=new RequestPanel();
   			}
   			main.openTab(panel);
   			}}	
   		}));  
   this.root.appendChild(new Ext.tree.TreeNode({
  		text:"协作过程",
  		listeners:{'click':function(){
  			var panel=Ext.getCmp("cooperatePanel");
  			if(!panel){
  				panel=new CooperatePanel();
  			}
  			main.openTab(panel);
  			}}	
  		}));     
  }
Ext.extend(RequestMenuPanel, Ext.tree.TreePanel);


//--------------------------------- 协作管理菜单---------------------------------//
ManageMenuPanel=function()
{	//调用基类构造函数
	ManageMenuPanel.superclass.constructor.call(this, {
        autoScroll:true,
        animate:true,
        border:false,
        rootVisible:false,
        root:new Ext.tree.TreeNode({
        text: '协作管理',
        draggable:false,
       	expanded:true
   	 })        
    }); 

/*		
   this.root.appendChild(new Ext.tree.TreeNode({
   		text:"协作查询",
//		href:"groupView.html",
//		hrefTarget:'_blank',
   		listeners:{'click':function(){
   			var panel=Ext.getCmp("requestGroupPanel");
   			if(!panel){
   				panel=new RequestGroupPanel();
   			}
   			main.openTab(panel);

   			}}	
   		}));  		
		
*/	

	var treeNode=new Ext.tree.TreeNode({
   		text:"协作查询"
   		});
		
	treeNode.on('click',function(node,event){
            var tabSearch = new Ext.Panel({
				id:101,
				title:"协作查询",
				autoScroll:true,
				layout: 'fit',
				border:false,
				closable:true
				});

            main.openTab(tabSearch);		
			tabSearch.load({
				url:"lib/report/grid-filter.html",
				callback:function(a,b,c){
					var jsStr="";
					Ext.Ajax.request({
						method:'POST',
						url:"lib/report/grid-filter.js",
						scope:this,
						success:function(response){
							jsStr+=response.responseText;
							this[node.id]=eval(jsStr);
							var model=new this[node.id];
						}
					});
				}
				
			});
   			});
   this.root.appendChild(treeNode);  		
  }
Ext.extend(ManageMenuPanel, Ext.tree.TreePanel);

//--------------------------------- 用户管理菜单---------------------------------//
UserMenuPanel=function()
{	//调用基类构造函数
	UserMenuPanel.superclass.constructor.call(this, {
        autoScroll:true,
        animate:true,
        border:false,
        rootVisible:false,
        root:new Ext.tree.TreeNode({
        text: '用户管理',
        draggable:false,
       	expanded:true
   	 })        
    }); 
   this.root.appendChild(new Ext.tree.TreeNode({
   		text:"用户管理",
   		listeners:{'click':function(){
   			var panel=Ext.getCmp("userPanel");
   			if(!panel){
   				panel=new UserPanel();
   			}
   			main.openTab(panel);
   			}}	
   		}));  
  }
Ext.extend(UserMenuPanel, Ext.tree.TreePanel);

//----------------------------系统设置菜单----------------------------------------//
SystemMenuPanel=function(){
	SystemMenuPanel.superclass.constructor.call(this,{
		autoScroll:true,
		animate:true,
		border:false,
		rootVisible:false,
		root:new Ext.tree.TreeNode({
			text:'协作种类',
			draggable:false,
			expanded:true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode({
		text:"协作种类",
		listeners:{'click':function(){
			var panel=Ext.getCmp("collaborationCategoryPanel");
			if(!panel){
				panel=new CollaborationCategoryPanel();
			}
			main.openTab(panel);
		}}		
	}));
	
	this.root.appendChild(new Ext.tree.TreeNode({
		text:"状态设置",
		listeners:{'click':function(){
			var panel=Ext.getCmp("stateSetPanel");
			if(!panel){
				panel=new StateSetPanel();
			}
			main.openTab(panel);
		}}		
	}));	
	
	this.root.appendChild(new Ext.tree.TreeNode({
		text:"项目协作人",
		listeners:{'click':function(){
			var panel=Ext.getCmp("collaboratorSetPanel");
			if(!panel){
				panel=new CollaboratorSetPanel();
			}
			main.openTab(panel);
		}}		
	}));		
}
Ext.extend(SystemMenuPanel,Ext.tree.TreePanel);

//---------------------------其他菜单----------------------------------------//
OtherMenuPanel = function() {
	OtherMenuPanel.superclass.constructor.call(this, {
		autoScroll : true,
		animate : true,
		border : false,
		rootVisible : false,
		root:new Ext.tree.TreeNode( {
			text : '基本信息配置',
			draggable : false,
			expanded : true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode({
		text:'基本信息配置'
	}))
}
Ext.extend(OtherMenuPanel, Ext.tree.TreePanel);

