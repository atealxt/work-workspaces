
//var simpleForm = new Ext.FormPanel({    
//   labelAlign: 'left',    
//   renderTo:'m',
//   title: '表单例子',    
//   bodyStyle:'width:100%',     
//   frame:true,    
//   labelWidth:80,    
//   height:441,
//   items: [{    
//       layout:'column',    
//       border:false,    
//       items:[{    
//           columnWidth:.5,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'textfield',    
//               fieldLabel: '姓名',    
//               name: 'name',    
//               anchor:'90%'    
//           }]    
//       },{    
//           columnWidth:.25,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               style:'margin-top:7px',    
//               xtype:'radio',    
//               fieldLabel: '性别',    
//               boxLabel:' 男',    
//               name: 'sex',    
//               checked:true,    
//               inputValue:'男',    
//               anchor:'95%'    
//           }]    
//       },{    
//           columnWidth:.25,    
//           layout: 'form',    
//           labelWidth:0,    
//           hideLabels:true,    
//           border:false,    
//           items: [{    
//               style:'margin-top:7px',    
//               xtype:'radio',    
//               fieldLabel: '',    
//               boxLabel:'女',    
//               name: 'sex',    
//               inputValue:'女',    
//               anchor:'95%'    
//           }]    
//       },{    
//           columnWidth:.5,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'datefield',    
//               fieldLabel: '出生日期',    
//               name: 'birthday',    
//               anchor:'90%'    
//           }]    
//     },{    
//           columnWidth:.5,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'combo',    
//               store: new Ext.data.SimpleStore({    
//            fields: ["retrunValue", "displayText"],    
//                data: [[1,'小学'],[2,'初中'],[3,'高中'],[4,'中专'],[5,'大专'],[6,'大学']]    
//               }),    
//               valueField :"retrunValue",    
//               displayField: "displayText",    
//               mode: 'local',    
//               forceSelection: true,    
//               blankText:'请选择学历',    
//               emptyText:'选择学历',    
//               hiddenName:'education',    
//               editable: false,    
//               triggerAction: 'all',    
//               //allowBlank:false,    
//            fieldLabel: '学历',    
//               name: 'education',    
//               anchor:'90%'    
//           }]    
//     },{    
//           columnWidth:.4,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'checkbox',    
//               style:'margin-top:7px',
//               fieldLabel: '权限组',    
//               boxLabel:' 系统管理员',    
//               name: 'popedom',    
//               inputValue:'1',    
//               anchor:'90%'    
//           }]    
//     },{    
//           columnWidth:.2,    
//           layout: 'form',    
//           labelWidth:0,    
//           hideLabels:true,    
//           border:false,    
//           items: [{    
//               xtype:'checkbox',    
//               style:'margin-top:7px',
//               fieldLabel: '',    
//               boxLabel:'管理员',    
//               name: 'pepedom',    
//               inputValue:'2',    
//               anchor:'90%'    
//           }]    
//       },{    
//           columnWidth:.2,    
//           layout: 'form',    
//           labelWidth:0,    
//           hideLabels:true,    
//           border:false,    
//           items: [{    
//               xtype:'checkbox',    
//               style:'margin-top:7px',
//               fieldLabel: '',    
//               boxLabel:'普通用户',    
//               name: 'pepedom',    
//               inputValue:'3',    
//               anchor:'90%'    
//           }]    
//       },{    
//           columnWidth:.2,    
//           layout: 'form',    
//           labelWidth:0,    
//           hideLabels:true,    
//           border:false,    
//           items: [{    
//               xtype:'checkbox',    
//               style:'margin-top:7px',
//               fieldLabel: '',    
//               boxLabel:'访客',    
//               name: 'pepedom',    
//               inputValue:'4',    
//               anchor:'90%'    
//           }]    
//       },{    
//           columnWidth:.5,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'textfield',    
//               fieldLabel: '电子邮件',    
//               name: 'email',    
//               vtype:'email',    
//               allowBlank:false,    
//               anchor:'90%'    
//           }]    
//       },{    
//           columnWidth:.5,    
//           layout: 'form',    
//           border:false,    
//           items: [{    
//               xtype:'textfield',    
//               fieldLabel: '个人主页',    
//               name: 'url',    
//               vtype:'url',    
//               anchor:'90%'    
//           }]    
//       }]    
//   },{    
//       xtype:'tabpanel',    
//       plain:true,    
//       activeTab: 0,    
//       height:255,    
//       bodyStyle:'padding:15px 5px 0',
//       defaults:{},    
//       items:[{    
//           xtype:'fieldset',
//           title: '测试练习',
//           collapsible: true,
//           autoHeight:true,
//           defaults: {width: 210},
//           defaultType: 'textfield',
//           style:'margin-top:10px',
//           items :[{
//                   fieldLabel: '电话',
//                   name: 'home',
//                   value: '(888) 555-1212'
//               },{
//                   fieldLabel: '地址',
//                   name: 'business'
//               },{
//                   fieldLabel: '手机',
//                   name: 'mobile'
//               },{
//                   fieldLabel: '简介',
//                   name: 'fax'
//               }]
//       },{    
//           title:'数字时间字母',    
//           layout:'form',    
//           defaults: {width: 230},    
//           defaultType: 'textfield',    
//           items: [{    
//               xtype:'numberfield',    
//               fieldLabel: '数字',    
//               name: 'number'    
//           },{    
//               xtype:'timefield',    
//               fieldLabel: '时间',    
//               name: 'time'    
//           },{    
//               fieldLabel: '纯字母',    
//               name: 'char',    
//               vtype:'alpha'    
//           }]    
//       },{    
//           title:'文本区域',    
//           layout:'fit',    
//           items: {    
//               xtype:'textarea',    
//               id:'area',    
//               fieldLabel:''    
//           }    
//       }]    
//   }],    
//   buttons: [{    
//    text: '保存',    
//    handler:function(){    
//        if(simpleForm.form.isValid()){    
//         this.disabled=true;    
//         simpleForm.form.doAction('submit',{     
//          url:'test.jsp',    
//          method:'post',    
//          params:'',    
//          success:function(form,action){    
//                 Ext.Msg.alert('操作','保存成功！');    
//                 this.disabled=false;    
//          },    
//          failure:function(){    
//              Ext.Msg.alert('操作','保存失败！');    
//              this.disabled=false;    
//          }    
//         });    
//     }    
//       }                
//   },{    
//       text: '取消',    
//       handler:function(){simpleForm.form.reset();}    
//   }]    
//});    
//}); 

//   draggable:{ insertProxy: false,
////      Called for each mousemove event while dragging the DD object.
//        onDrag : function(e){
////          Record the x,y position of the drag proxy so that we can
////          position the Panel at end of drag.
//            var pel = this.proxy.getEl();
//            this.x = pel.getLeft(true);
//            this.y = pel.getTop(true);

////          Keep the Shadow aligned if there is one.
//            var s = this.panel.getEl().shadow;
//            if (s) {
//                s.realign(this.x, this.y, pel.getWidth(), pel.getHeight());
//            }
//        },

////      Called on the mouseup event.
//        endDrag : function(e){
//            this.panel.setPosition(this.x, this.y);
//        }
//}, 


Ext.onReady(function(){
Ext.QuickTips.init();    
Ext.form.Field.prototype.msgTarget = 'side';   
var simpleForm = new Ext.FormPanel({    
   labelAlign: 'left',    
   renderTo:'m',
   title: '事件录入',    
   bodyStyle:'width:700;',
  // autoWidth:true,     
  bodyBorder:true,
   frame:true,  
   hideBorders:true,  
   //iconCls:'my-icon',  //header的背景图标
   //itemCls:'my-icon',
   overCls:'fontset',
   labelWidth:80,
   labelAlign:'right',    
   autoHeight:true,
    buttons:[{text:'保存',type:'submit',scope:this,handler:function(){alert("aaa")}},{text:'cancelData',handler:function(){alert('a')}}],
   //defaults:{disabled:false},
   items: [{
       layout:'column',    
       border:true,    
       items:[{
            columnWidth:.5,
            layout:'form',
            items:[{
                xtype:'textfield',
                fieldLabel:'事件编号：',
                name:'txtMBBSM',
                anchor:'90%',
                labelSeparator:'', //标签和文本之间的分隔符
               // labelStyle: 'font-weight:bold;color:red' ,//标签样式
               // vtype:'email',                  //验证类型
                msgTarget:'side',//'qtip',      //错误消息显示位置
               // overCls:'hover',
               // regex:new RegExp('\w+'),
               // regexText:"不符合正则",
               // msgFx:'fx',
              //  validator:my,
              //  invalidText:'值有误',
                emptyText:'请输入值',        //初始为空默认显示文本
                allowBlank:false,           //是否允许为空
              //  vtypeText:'email不正确',    //vtype验证的错误消息
             //   fieldClass:'borderset',     //设置文本框本身的样式
               // cls:'borderset',          ////added to this component's Element 
               disabled:false,               //禁用这个文本框
           //    height:20,                   //设置控件高度
           //    hideLabel:false,              //会隐藏  fieldLabel:'事件编号：',
               //inputType:'file',        //文本框里面输入密码 或者  文件上传 file
            //   maxLength:'50',              //输入最大长度
           //    maxLengthText:'最大文本不能超过50',//超过最大长度显示文本
               blankText:'事件编号不能为空！'  //非空显示文本
              }]
       },{
            columnWidth:.5,
            layout:'form',
            items:[{
                    xtype:'combo',
                    labelSeparator:'',
                    fieldLabel:'事件类型：',
                    name:'txtFPECANCYTYPE',
                    anchor:'90%'
            }] 
        },{
            columnWidth:.5,
            layout:'form',
            border:false,
            items:[{
                xtype:'datefield',
                labelSeparator:'',
                fieldLabel:'事发时间：',
                name:'txtFSJDATE',
                anchor:'90%'
            }]
       },{    
           columnWidth:.5,    
           layout: 'form',    
           border:false,    
           items: [{    
               xtype:'combo',    
               labelSeparator:'',
               fieldLabel: '报案方式：',    
               name: 'txtFREPORTMETHOD',    
               anchor:'90%',
               store:new Ext.data.SimpleStore({
                    data: [[1,'电话举报'],[2,'写信举报']]    ,//data:[[1,'电话举报'],[2,'写信举报']],
                    fields:["returnValue1","displayText1"]
               }),

               displayField:'displayText1',
               valueField:'returnValue1',
               mode: 'local',       //代表数据是不是在本地
               //forceSelection: true,    
               blankText:'请选择举报方式',    
               emptyText:'选择举报方式',    
               hiddenName:'txtFREPORTMETHOD',  //store the field's data value   
               editable: false,    
               triggerAction: 'all'  
                   
           }]    
     },{
            columnWidth:.5,
            border:false,
            layout:'form',
            items:[{
                    xtype:'textfield',
                    labelSeparator:'',
                    fieldLabel:'报案人：',
                    name:'txtFREPORTER',
                    anchor:'90%'
            }] 
        },{
            columnWidth:.5,
            layout:'form',
            border:false,
            items:[{
                xtype:'textfield',
                labelSeparator:'',
                fieldLabel:'联系电话：',
                name:'txtfreporterphone',
                anchor:'90%'
            }]
       },{
            columnWidth:1,
            border:false,
            layout:'form',
            items:[{
                    xtype:'textfield',
                    labelSeparator:'',
                    fieldLabel:'报案人：',
                    name:'txtFREPORTER',
                    anchor:'95%'
            }] 
        },{
            columnWidth:1,
            layout:'form',
            border:false,
            items:[{
                xtype:'textfield',
                labelSeparator:'',
                fieldLabel:'联系电话：',
                name:'txtfreporterphone',
                anchor:'95%'
            }]
       },{
            columnWidth:1,
            layout:'form',
            border:false,
            items:[{
                xtype:'textfield',
                labelSeparator:'',
                fieldLabel:'事件描述：',
                name:'txtFCONTENT',
                anchor:'95%'
            }]
       }]    
   },
    {
       xtype:'tabpanel',    
       plain:true,    
       activeTab: 0,    
       height:175,    
       bodyStyle:'padding:15px 5px 0',
       defaults:{},    
       items:[{    
           xtype:'fieldset',
           title: '空间信息',
           collapsible: true,
           autoHeight:true,
           defaults: {width: 210},
           defaultType: 'textfield',
           style:'margin-top:10px',
           items :[{
                   fieldLabel: '经度坐标：',
                   labelSeparator:'',
                   name: 'home',
                   //value: '(888) 555-1212'
               },{
                   fieldLabel: '纬度坐标：',
                   labelSeparator:'',
                   name: 'business'
               },{
                   fieldLabel: '事件半径：',
                   labelSeparator:'',
                   name: 'mobile'
               }],
        buttons:[{minWidth:'80',xtype:'button',icon:'../../ico/拾取.gif',cls:'x-btn-text-icon'}]   
               
       }]
//    xtype:"panel",layout:"column",items:[
//            {xtype:"panel",layout:'form', border:false,columnWidth:.5,items:[{xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'}]},
//            {xtype:"panel",layout:"column",items:[
//                    {xtype:"panel",layout:"form",columnWidth:.5,
//                            items:[{xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'},
//                                    {xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'},{xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'}]   }]},
//            {xtype:"panel",columnWidth:.2,items:{xtype:"button",text:"拾取"}}]
           
//            xtype:"panel",html:"空间信息",border:false,columnWidth:.2,height:80,},
//            {xtype:"panel",layout:"column",items:[
//                    {xtype:"panel",layout:"form",columnWidth:.5,
//                            items:[{xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'},
//                                    {xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'},{xtype:"textfield",fieldLabel:"经度坐标",anchor:'50%'}]   }]},
//            {xtype:"panel",columnWidth:.2,items:{xtype:"button",text:"拾取"}
    
}//结束
 ]
 
});//这个是simpleForm的


// simpleForm.on({
//                    //提交之前
//                    beforeaction: function(form, action){
//                        regButton.disable();
//                    },
//                    //提交完成后
//                    actioncomplete: function(form, action){            
//                        if(action.type == 'submit'){
//                            regButton.enable();
//                          }        
//                        if(action.result.success){
//                        Cookies.set('Allcard_userName', Ext.get('CustomerName').dom.value);
//                        Ext.Msg.show({
//                           title:'成功',
//                           msg: '恭喜,您已注册成功!',
//                           modal : false,
//                           fn: showResult,
//                           buttons: Ext.Msg.OK
//                        }).getDialog().moveTo(200, 100);    
//                        }
//                        else{
//                        Ext.Msg.show({
//                           title:'错误',
//                           msg: '抱歉！您注册失败，请联系管理员!',
//                           modal : false,
//                           buttons: Ext.Msg.OK
//                        }).getDialog().moveTo(200, 100);
//                        }            
//                    },
//                    //提交失败后
//                    actionfailed: function(form, action){
//                        regButton.enable();
//                    }
//         })




}); 

//放在外面
function saveData()
{
    alert("aaa");
//if (simpleForm.isValid()) {
//          Ext.Msg.show({
//           title:'再确认一下',
//           modal : false,
//           msg: '您确定资料正确吗?',
//           buttons: Ext.Msg.OKCANCEL,
//           fn:  function(btn, text){
//                if (btn == 'ok'){
//                        simpleForm.submit({
//                        url: 'shijianluruSave.aspx',
//                        params: {
//                        oper: 'submit'
//                        },
//                        msgethod: 'POST',
//                        waitMsg:'正在提交,请稍等'
//                        });
//                } 
//            },
//            animEl: 'regbutton'
//         }).getDialog().moveTo(200, 100);
//     }
//     else
//     {
//          Ext.Msg.show({
//                           title:'信息',
//                           msg: '请填写完整后再提交!',
//                           modal : false,
//                           buttons: Ext.Msg.OK
//                            }).getDialog().moveTo(200, 100);
//     }
     
}

function cancelData()
{
    alert("bbb");
}







//空间信息部分的内容直接使用Column布局就可以了。
//{xtype:"panel",layout:"column",items:[
//            {xtype:"panel",html:"空间信息",border:false,columnWidth:.2},
//            {xtype:"panel",layout:"column",items:[
//                    {xtype:"panel",layout:"form",columnWidth:.5,items:[{xtype:"textfield",fieldLabel:"经度坐标"},{xtype:"textfield"},{xtype:"textfield}]},
//            {xtype:"panel",columnWidth:.5,items:{xtype:"button",text:"拾取"}}
//  ]}]}





//Ext.onReady(function(){
//Ext.QuickTips.init();    
//Ext.form.Field.prototype.msgTarget = 'side';   
//var simpleForm = new Ext.FormPanel({    
//   labelAlign: 'left',    
//   renderTo:'m',
//   title: '事件录入',    
//   bodyStyle:'width:700;',
//  // autoWidth:true,     
//  bodyBorder:true,
//   frame:true,  
//   hideBorders:true,  
//   //iconCls:'my-icon',  //header的背景图标
//   //itemCls:'my-icon',
//   overCls:'fontset',
//   labelWidth:80,
//   labelAlign:'right',    
//   autoHeight:true,
//  
//   //defaults:{disabled:false},
//   items: [{
//       layout:'column',    
//       border:true,    
//       items:[{
//            columnWidth:.5,
//            layout:'form',
//            items:[{
//                xtype:'textfield',
//                fieldLabel:'事件编号：',
//                name:'txtMBBSM',
//                anchor:'90%',
//                labelSeparator:'', //标签和文本之间的分隔符
//               // labelStyle: 'font-weight:bold;color:red' ,//标签样式
//               // vtype:'email',                  //验证类型
//                msgTarget:'side',//'qtip',      //错误消息显示位置
//               // overCls:'hover',
//               // regex:new RegExp('\w+'),
//               // regexText:"不符合正则",
//               // msgFx:'fx',
//              //  validator:my,
//              //  invalidText:'值有误',
//                emptyText:'请输入值',        //初始为空默认显示文本
//                allowBlank:false,           //是否允许为空
//              //  vtypeText:'email不正确',    //vtype验证的错误消息
//             //   fieldClass:'borderset',     //设置文本框本身的样式
//               // cls:'borderset',          ////added to this component's Element 
//               disabled:false,               //禁用这个文本框
//           //    height:20,                   //设置控件高度
//           //    hideLabel:false,              //会隐藏  fieldLabel:'事件编号：',
//               //inputType:'file',        //文本框里面输入密码 或者  文件上传 file
//            //   maxLength:'50',              //输入最大长度
//           //    maxLengthText:'最大文本不能超过50',//超过最大长度显示文本
//               blankText:'事件编号不能为空！'  //非空显示文本
//              }]
//       },{
//            columnWidth:.5,
//            layout:'form',
//            items:[{
//                    xtype:'combo',
//                    labelSeparator:'',
//                    fieldLabel:'事件类型：',
//                    name:'txtFPECANCYTYPE',
//                    anchor:'90%'
//            }] 
//        }, {
//            columnWidth:.5,
//            layout:'form',
//            border:false,
//            items:[{
//                xtype:'textfield',
//                labelSeparator:'',
//                fieldLabel:'空间信息1：',
//                //name:'txtFCONTENT',
//                anchor:'95%'
//            }]
//       },{
//            columnWidth:.5,
//            layout:'form',
//            border:false,
//            items:[{
//                xtype:'textfield',
//                labelSeparator:'',
//                fieldLabel:'空间信息2：',
//                name:'txtFCONTENT',
//                anchor:'95%'
//            },{
//                xtype:'textfield',
//                labelSeparator:'',
//                fieldLabel:'空间信息3：',
//                name:'txtFCONTENT',
//                anchor:'95%'
//            }]
//       },  {
//    layout:'table',
//    defaults: {
//        // applied to each contained panel
//        bodyStyle:'padding:20px'
//    },
//    layoutConfig: {
//        // The total column count must be specified here
//        columns: 2
//    },
//    items:[{
//            html: '<p>空间信息：</p>',
//            rowspan: 3
//          },{
//            html: '<input type="text" name="aa">'
//          },{
//            html: '<p>Cell c content</p>'
//          },
//            {
//            html: '<p>Cell D content</p>'
//          }]    
//   }//,
//  ]
//}]
// 
//});//这个是simpleForm的

//}); 

