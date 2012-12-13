var loader,root,tree,tabPanel,bottomPanel,username;
var pageTheme = '../extjs/resources/css/xtheme-indigo.css';
Ext.util.CSS.swapStyleSheet('theme', pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';//统一指定错误信息提示方式

    //导航条
    //Tree加载地址
    loader=new Ext.tree.TreeLoader({
        url:"A0204LinkAction.action"
    });

    //创建根节点
    root = new Ext.tree.AsyncTreeNode({
        id:'root',
        text: '导航条',
        draggable:false,
        expanded : true,
        loader:loader
    });

    //创建Tree面板组件
    tree = new Ext.tree.TreePanel({
        //title : '树形菜单',
        //width : 150,
        autoWidth:true,//unuse
        //height : 150,
        //autoHeight : true,
        height:'100%',
        //enableDD : true,//允许拖动树节点
        baseCls :'treebg',
        applyTo : 'link',
        root : root
    });
    tree.on("click",addShow);

    //主显示区
    tabPanel = new Ext.TabPanel({
        //height : '100%',
        //width : '85%',
        autoWidth : true,
        //autoHeight : true,//cannnot use this
        //autoScroll : true,
        //activeTab : 0,//默认激活第一个tab页
        animScroll : true,//使用动画滚动效果
        enableTabScroll : true,//tab标签超宽时自动出现滚动按钮
        applyTo : 'dmain',
        resizeTabs : true,
        deferredRender : false,
        autoTabs : true,
        tabMargin : 50,
        tabWidth : 100,
        listeners:{
            //自动刷新
            activate:function(tab){
                alert(tab);
                tab.getUpdater().refresh();
            }
        },
        plugins: new Ext.ux.TabMenu()
    });
    tabPanel.add({
        title: '主页',
        tabTip: '主页',
        frame: true,
        loadMask : true,
        closable : false,
        autoScroll : true,
        defaultHeight:'100%',
        contentEl : 'dmainShow'
    });
    tabPanel.setActiveTab(0);

    //帖子信息
    new Ext.Panel({
        //title:'帖子信息',
        layout : 'fit',
        //frame:true,//渲染面板
        //height : 200,
        autoHeight : true,
        //width : tree.getInnerWidth(),
        autoWidth : true,
        //bodyStyle:'padding: 5 5 5 5;',
        applyTo :'topicAnalysis',
        defaults : {//设置默认属性
            //columnWidth:.33,//指定列宽
            //height : 230,
            autoHeight : true,
            //collapsible: true,
            //draggable:true,
            bodyStyle:'font-size: 12px;',
            frame : true
        },
        items: [
        {
            xtype:'portal',
            region:'center',
            margins:'35 5 5 0',
            items:[
            {
                columnWidth:.28,
                style:'padding:10px 0 10px 10px',
                items:[{
                    id : 'l_t',
                    title:'最新主题',
                    contentEl : 'l_t',
                    buttons:[
                    new Ext.Button({
                        id : 'b_l_t',
                        text : '更多',
                        iconCls: 'icon_more',
                        handler : more
                    })]
                }]
            },
            {
                columnWidth:.28,
                style:'padding:10px 0 10px 10px',
                items:[{
                    id : 'l_r',
                    title:'最新回复',
                    contentEl : 'l_r',
                    buttons:[
                    new Ext.Button({
                        id : 'b_l_r',
                        text : '更多',
                        iconCls: 'icon_more',
                        handler : more
                    })]
                }]
            },
            {
                columnWidth:.28,
                style:'padding:10px 0 10px 10px',
                items:[{
                    id : 'l_u',
                    title:'未完结主题',
                    contentEl : 'l_u',
                    buttons:[
                    new Ext.Button({
                        id : 'b_l_u',
                        text : '更多',
                        iconCls: 'icon_more',
                        handler : more
                    })]
                }]
            }
            ]
        }
        ]
    });

    //页脚
    bottomPanel = new Ext.Panel({
        renderTo:'newtab',
        frame:true,//渲染面板
        bodyStyle:'background-color:#FFFFFF',//设置面板体的背景色
        items:[{
            xtype:'iframepanel',
            defaultHeight:'80',
            autoHeight : true,
            defaultSrc: '../pages/main_bottom.html'
        }]
    });

    //关闭初始化窗口
    Ext.get('loading').remove();
    Ext.get('loading-mask').fadeOut({
        remove:true
    });

    //网站整体布局
    new Ext.Viewport({
        title : '页眉',
        layout:'border',
        frame:true,
        header:false,
        items: [
        {
            title: 'PDMS',
            collapsible: true,
            contentEl : 'page_head',
            region: 'north'
        },{
            title: '导航',
            //contentEl : 'link',
            collapsible: true,
            layout:'fit',
            items : tree,
            region:'west',
            width:150,
            tbar : [
            '<font size=1>皮肤选择：</font>',
            {
                xtype : 'themeChange',
                width : 80,
                listWidth : 80
            }
            ]
        },{
            title: '页脚',
            collapsible: true,
            //contentEl : 'page_foot',
            items : bottomPanel,
            region:'south',
            height: 80
        },{
            //title: '主页',
            //contentEl : 'dmain',
            items:tabPanel,
            region:'center',
            layout:'fit',
            autoScroll:true
        }]
    });

    username = Ext.get('username').dom.innerHTML;
});

/** 添加显示tab页面 */
function addTab(url,title){

    for(var i=0;i<tabPanel.items.length;i++){
        if(tabPanel.items.get(i).title == title){
            //已经存在此tab
            tabPanel.setActiveTab(tabPanel.items.get(i));
            //刷新一下页面
            tabPanel.doLayout(true);
            return;
        }
    }

    var frame = tabPanel.add({
        name: title,
        title: title,
        tabTip: title,
        xtype: 'iframepanel',//a ManagedIframePanel
        frame: true,
        loadMask : true,
        closable : true,
        defaultHeight:'100%',
        //autoHeight : true,
        defaultSrc : url
    });
    tabPanel.doLayout();
    tabPanel.setActiveTab(frame);//设置当前tab页

}

/** Tree导航条单击事件 */
function addShow(param1,param2){
    //if(param1.childNodes.length==0 && param1.isRoot != true){
    if(param1.leaf && !param1.isRoot){
        var reqUrl = param1.attributes.myurl;
        addTab(reqUrl,param1.text);
    }
}

/** '更多'按钮单击事件 */
function more(param1){
    //alert(param1.id);
    var url;

    switch (param1.id){
        case "b_l_t":
        case "b_l_u":
            url = "s11s21s31c";
            break;
        case "b_l_r":
            url = "s11s22s31c";
            break;
        case "b_m_t":
            url = "s11s21s32c" + Ext.get('usrN').dom.innerHTML;
            break;
        case "b_m_r":
            url = "s11s22s32c" + Ext.get('usrN').dom.innerHTML;
            break;
    }
    addTab("../pages/search.jsp?cc=" + url, "搜索");

}
