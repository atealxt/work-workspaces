Ext.onReady(function() {

   Ext.QuickTips.init();

   var simple = new Ext.FormPanel({
         // label settings here cascade unless overridden
       url : '/wf/deviceAdd.do',
       frame : true,
       title : '录入设备3',
       bodyStyle : 'padding:5px 5px 0',
       width : 650,
       height:200,
       defaults : {
           width : 400
       },
       // defaultType : 'textfield',
       // labelAlign : 'top',
       items : [{
           xtype : "fieldset",
           height:200,
           width:600,
           defaultType : 'textfield',
           
           // labelAlign : 'top',
           items : [{
                
               layout:"column",
               items : [{
                    columnWidth :.5,
                   xtype : "textfield",
                    fieldLabel : "字段１"
                    
                    },{
                    columnWidth :.5,
                   xtype : "textfield",
                    fieldLabel : "字段１"
                    
                    }]
           }]
       }],

       buttons : [{
           text : '保存并提交',
           handler : function() {
               if (simple.form.isValid()) {// 验证通过
                   simple.form.doAction('submit', {
                       url : '/wf/deviceAdd.do',
                       method : 'post',
                       params : '',
                       waitMsg : '正在添加设备,请稍等...',
                       success : function(form, action) {
                           Ext.Msg.alert('添加设备', "成功添加了设备!");
                       },
                       failure : function(form, action) {
                           Ext.Msg.alert('添加设备', "添加设备失败");
                       }
                   });

               } else {//
                   Ext.Msg.alert('', "请正确填写设备信息!");
               }
           }
       }, {
           text : 'Cancel'
       }]
   });

   simple.render(document.body);

}); 