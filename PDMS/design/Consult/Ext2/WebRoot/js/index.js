Ext.BLANK_IMAGE_URL = 'images/public/s.gif"';
var path,main, menu, header, bottom;
Ext.QuickTips.init();

/**
*	左功能区
*/
MenuPanel = function(){
    MenuPanel.superclass.constructor.call(this, {
        id: 'menu',
        region: 'west',
        title: "菜单",
        split: true,
        collapseMode: 'mini',
        width: 200,
        minSize: 125,
        maxSize: 300,
        collapsible: true,
        margins: '0 0 -1 1',
        layout: "accordion",
        defaults: {
            autoScroll: true,
            border: false
        },
        items: [{
            title: "系统菜单",
            iconCls: "icon-sys",
            items: [new Ext.tree.TreePanel({
            	id:'system_menu',
			    useArrows:false,//使用箭头形式
			    autoScroll:true,
			    animate:true,
			    enableDD:false,
			    border: false,
			    containerScroll: true,
			    rootVisible: false,
			    root: new Ext.tree.AsyncTreeNode({
			        text: '系统菜单',
			        draggable: false,
			        expanded: true,
			        id:'0'    
			    }),
			    loader:new Ext.tree.TreeLoader({
					dataUrl:path+'/system/resource!getTree.action'
				}),
				listeners: {
		            "click": function(node){
		            	if(node.attributes.leaf){
		                	main.openUrl(path+node.attributes.link, 'tab-'+node.id, node.text);
		                }
		            }
		        }		
			})],
			tools:[{
				id:'refresh',
				qtip:'刷新',
				handler:function(){
					Ext.getCmp('system_menu').root.reload();
				}
			}]
        }, {
            title: "功能菜单",
            iconCls: "icon-sys"
        }, {
            title: "帮助",
            iconCls: "icon-sys"
        }]
    });
    
};
Ext.extend(MenuPanel, Ext.Panel);
/**
*	主功能区
*/
MainPanel = function(){

    this.homePage = path+'/welcome.jsp';
    
    this.openUrl = function(tabUrl, tabId, tabTitle){
        var tab = this.getComponent(tabId); 
        if(!tab){
        	tab = this.add({
        		id:tabId,
				title: tabTitle,
				layout:'fit',        
	            closable:true,
	            loadMask:true,
            	autoLoad:{url:tabUrl, scripts:true ,params: 'tabId='+tabId }
			});
        }else{

        }
        if(this.items.getCount() > 10){ 
            this.remove(1);
        }
        this.setActiveTab(tab);
    };
    
    this.openTab = function(panel){
        var tab = this.add(panel);
        panel.doLayout();
        if (this.items.getCount() > 10){ 
            this.remove(1);
        }
        this.setActiveTab(tab);
    };
    
    this.closeTab = function(panel, id){
        var tabId = (typeof panel == "string" ? panel : id || panel.id);
        var tab = this.getComponent(tabId);
        if (tab){
            this.remove(tab);
        }
    };
    
    this.closeAll = function(excep){
        this.items.each(function(p){
            if (p.closable && p != excep) 
                this.closeTab(p);
        }, this);
    };
    
    this.menu = new Ext.menu.Menu({
        items: [{
            text: "关闭所有Tab",
            handler: this.closeAll,
            scope: this
        }, {
            text: "关闭其它Tab",
            handler: function(){
                this.closeAll(this.getActiveTab());
            },
            scope: this
        }]
    });
    
    MainPanel.superclass.constructor.call(this, {
        id: 'main',
        region: 'center',
        resizeTabs: true,
        minTabWidth: 65,
        tabWidth: 120,
        enableTabScroll: true,
        activeTab: 0,
        items: [{
            id: 'homePage',
            title: '首 页',
            closable: false,
            //html: "<iframe width='100%' height='99%' frameborder='0' scrolling='auto' src='" + this.homePage + "'></iframe>",
            loadMask:true,
            autoLoad:{url:this.homePage, scripts:true},
            autoScroll: true
        }]
    });
    
    this.on("contextmenu", function(tabPanel, tab, e){
        this.menu.showAt(e.getPoint());
    }, this);
    
    this.on("tabchange", function(tab, newtab){
        this.doLayout();
    }, this);
    
};
Ext.extend(MainPanel, Ext.TabPanel);


