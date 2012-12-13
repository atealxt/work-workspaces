Ext.onReady(function() {
   Ext.QuickTips.init();

 var myFormWin = function() {
       newFormWin =   new Ext.Window({
               title : '新闻内容修改',
               renderTo : Ext.getBody(),
               layout : 'fit',
               width : 800,
               height : 200,
               // closeAction : 'hide',
               plain : false,
               modal : true,
               maximizable : true,
               minimizable : true, 
               closable:true,
               items : new Ext.TabPanel({
                   // el : 'editfck',
                   autoTabs : true,
                   activeTab : 0,
                   deferredRender : false,
                   border : false,
                   html : 'asdfasdfsdf'
               })

           });
           this.newFormWin.on("close",function() {
                   alert('abc')},this)
            newFormWin.show();
       
               };
            myFormWin();   
       
    });