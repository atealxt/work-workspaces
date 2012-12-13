Ext.QuickTips.init();
Ext.BLANK_IMAGE_URL = 'plugins/extjs/ext-2.0/resources/images/default/s.gif';
var MainTabPanel
treePanel =  function() {
	  treePanel.superclass.constructor.call(this,{
        collapsible: true,
        margins : '0 5 5 0',
        xtype: 'treepanel',
        autoScroll: true,
        loader: new Ext.tree.TreeLoader(),
        root: new Ext.tree.AsyncTreeNode({
            expanded: true,
            children: [{
                text: '留言板',
                leaf: true,
                listeners : {
		        'click' : function() {
				  var panel = Ext.getCmp("guessbook");
					if (!panel) {
						panel =new guestbookTab({height:500});
					}
				MainTabPanel.openTab(panel);
			}
		}
                
            }, {
                text: 'Menu Option 2',
                leaf: true
            }, {
                text: 'Menu Option 3',
                leaf: true
            }]
        }),
        rootVisible: false,
        listeners: {
            click: function(n) {
                Ext.Msg.alert('Navigation Tree Click', 'You clicked: "' + n.attributes.text + '"');
            }
        }
    })
	};

Ext.extend(treePanel, Ext.tree.TreePanel);

var treeP = new treePanel();

accordionMenu =  function() {
	  accordionMenu.superclass.constructor.call(this,{
	  region: 'west',
	  title: 'Accordion Layout',
      layout:'accordion',
      width: 200,
      autoScroll: true,
      split: true,
      
      collapsible: true,
      enableTabScroll : true,
    //  defaults: {
        // applied to each contained panel
      //  bodyStyle: 'padding:15px'
    //},
    layoutConfig: {
        // layout-specific configs go here
        titleCollapse: true,
        animate: true,
        activeOnTop: true,
        activeItem:0
    },
    items: [{
        title: 'Panel 1',
        items:[treeP]
    },{
        title: 'Panel 2',
        html: '<p>Panel content!</p>'
    },{
        title: 'Panel 3',
        html: '<p>Panel content!</p>'
    }]
	
	});
};
Ext.extend(accordionMenu, Ext.Panel);


mainTab =  function() {
	this.openTab = function(panel, id){
				var o = (typeof panel == "string" ? panel: id || panel.id);
				var tab = this.getComponent(o);
				if(tab){
				this.setActiveTab(tab);
				}else if(typeof panel!="string"){
				 panel.id = o;
				 var p = this.add(panel);
				 this.setActiveTab(p);
				}
	  };
	  this.closeTab = function(panel ,id){
	   var o = (typeof panel == "string" ? panel : id || panel.id);
	    var tab = this.getComponent(o);
	    if(tab){
	     this.remove(tab);
	    }
	  };
	
	  mainTab.superclass.constructor.call(this, {
	       region:"center",
	       resizeTabs : true,
           activeItem:0,
           items:{
             title:"桌面",
             html:'这里是桌面,可以显示出来一些信息',
          	 tbar:[{text:"桌面"},{xtype: 'tbseparator'},{text:"后退"},{xtype: 'tbseparator'},{text:"前进"}]
           },
           
	  });
};
Ext.extend(mainTab, Ext.TabPanel);

guestbookTab =  function() {
	var pageSize=10;
	var MessageRecord = Ext.data.Record.create([
	　　{name: 'id', type: 'int', mapping: 'id'},
        {name: 'title', type: 'string', mapping: 'title'},
        {name: 'author', type: 'string', mapping: 'author'},
        {name: 'ip', type: 'string', mapping: 'ip'},
        {name: 'image', type: 'string', mapping: 'image'},
        {name: "inputTime",type:"date",mapping:"inputTime",dateFormat: 'Y-m-dTH:i:s'},
        {name :"content",type:"string",mapping:"content"}
	 ]);
 
	 var cm = new Ext.grid.ColumnModel([
     {  id: 'id', header: '编号', dataIndex: 'id', type: 'int',hidden: true },
     {  id: 'title',  header: '标题',　 dataIndex: 'title',　type: 'string'},
    　{　id: 'author',
        header: '作者',
        dataIndex: 'author',
        type: 'string'
    },{
        id: 'inputTime',
        header: '留言时间',
        dataIndex: 'inputTime',
        type: 'date',
        renderer: Ext.util.Format.dateRenderer('Y年m月d日 H时i分s秒')
    }]);
    cm.defaultSortable = true;
    var proxy = new Ext.data.HttpProxy({url:'guessbook.ejf?cmd=list&pageSize='+10});
    var reader = new Ext.data.JsonReader({
         id:"id",
         root:"result",
  		 totalProperty:"rowCount",
    }, MessageRecord);
	 
    var store = new Ext.data.Store({
   	 proxy:proxy,
     reader:reader,
     baseParams:{limit:10}
    });
     store.load({params:{start: 0}});
    
     var pagingToolbar2 = new Ext.PagingToolbar({     
        pageSize: 10,
        store: store,
        displayInfo: true,
        displayMsg: '显示第 <em>{0}</em> 条到 <em>{1}</em> 条记录，一共 <em>{2}</em> 条',
        emptyMsg: "没有记录",
    });
	   guestbookTab.superclass.constructor.call(this, {
	  	   id:"guessbook",
	  	   title:"留言本",
	  	   clicksToEdit:1,
           bbar : pagingToolbar2,
           tbar:[{text:"添加",handler: postMessage},{xtype:'tbseparator'},{text:"删除"},{xtype: 'tbseparator'},{text:"详细"}],
      	   height:300, 
      	   store: store,
      	   cm: cm,
     	   loadMask: true,
     	   viewConfig: {
     	    forceFit: true
     	   }
	  });
};
Ext.extend(guestbookTab, Ext.grid.EditorGridPanel);

var win ,formpanel;
 
function postMessage(btn){
if(!formpanel){
 formpanel = new Ext.form.FormPanel({
    url:"guessbook.ejf?cmd=save",
    labelAlign: 'right',
    labelWidth:50,
    frame:true,
    
    items:[ {layout: 'column',items:[{
          columnWidth:.5,
          layout:"form",
          defaultType:"textfield",
          items: [{fieldLabel: '标题',
                            name: 'title',
                            anchor: '100%',
                            allowBlank: false
                        },{
                            fieldLabel: '作者',
                            name: 'author',
                            anchor: '100%',
                            allowBlank: false
                        }]                         
                        },{
                         columnWidth:.5,
			            layout:"form",
			             defaultType:"textfield",
			             items:[{
			              fieldLabel: '图像',
                            name: 'image',
                            anchor: '100%',
                            allowBlank: false
             }]
                        
                        }]},
                        {
                    fieldLabel: '内容',
                    name: 'content',
                    xtype: 'htmleditor',
                    anchor: '100%',
                    allowBlank: false
                }],
                 buttons: [{
                    text: '发布',
                    handler: save
                    
                },{
                    text: '取消',
                    handler:function(){win.hide()} 
                }]
 });
}

     if(!win){
            win = new Ext.Window({
                layout: 'fit',
                width: 600,
                height: 400,
                resizable: false,
                closeAction: 'hide',
                title: '发布新留言',
                items: [formpanel]
            });
        }
        formpanel.getForm().reset();
        win.show(btn.el);
        
        function save(){
            if(!formpanel.getForm().isValid()){
             return;
            }
              formpanel.getForm().submit({
	            success:function(form , action){
	               if(action.result.success){
 	               	Ext.Msg.confirm("信息","发布成功:"+action.result.msg+".是否继续添加",function(btn){
	               	  if(btn=="yes"){
	               	  formpanel.getForm().reset();
	               	  }else{win.hide}
	               	});
	               }else{
	               	$msg
	               Ext.Msg.alert("错误","发布失败"+action.result.msg);
	               }
	            },
	            failure:function(){
	            Ext.Msg.alert("错误","发布失败");
	            }
            });
        }
        
        function cancelForm() {
        win.hide();
    }

}
 

 MainTabPanel =new mainTab(); 
  Ext.onReady(function(){
    new Ext.Viewport({
      layout:"border",
      items:[{
        region: 'north',
        html:'<h1 class="x-panel-header">Page Title</h1>',
        border: false,
        margins:'0 0 5 0',
        autoScroll: true
      },
       new accordionMenu() ,
       MainTabPanel ,
        {
          region:"south",
          title:"information",
          collapsible: true,
          html:"information goes here",
          split:true,
          height:100,
          minHeight:100
        }
        ]
    } );
    
});