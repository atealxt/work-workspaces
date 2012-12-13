Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    var loginForm = new Ext.form.FormPanel({
        title:'登录框',
        labelWidth : 60,//标签宽度
        width : 230,
        autoHeight: 500,
        frame : true,
        labelSeparator :'：',//分隔符
        iconCls: 'icon_form',
        //buttonAlign : 'right',
        labelAlign : 'left',
        applyTo :'form',
        items:[
        {
            //html : '<center><img src="../images/logo/pdms.gif"></center><br>'
        },
        new Ext.form.TextField({
            fieldLabel:'用户ID',
            id:'userName',
            name : 'userName',
            //width : 120,
            vtype:'alphanum',
            allowBlank : false
        }),
        new Ext.form.TextField({
            fieldLabel:'密码',
            id:'password',
            name : 'password',
            //width : 120,
            inputType : 'password',
            vtype:'alphanum',
            allowBlank : false
        })
        ],
        buttons:[
        new Ext.Button({
            id : 'bLogin',
            text : '登陆',
            iconCls: 'icon_login',
            handler : login
        }),
        new Ext.Button({
            text : '重置',
            iconCls: 'icon_reset',
            handler : reset
        }),
        new Ext.Button({
            text : '随便看看',
            iconCls: 'icon_guest',
            handler : guesting
        })
        ]
    });

    //登录
    function login(){

        if(loginForm.form.isValid()){
            loginForm.form.submit({
                clientValidation:true,//进行客户端验证
                waitMsg : '正在登陆系统请稍后',//提示信息
                waitTitle : '提示',//标题
                timeout : 10000,
                url : '../login/A0101LoginAction.action',//请求的url地址
                method:'GET',//请求方式
                failure:function(form,action){//加载失败的处理函数
                    if(action.failureType =="server"){
                        Ext.Msg.alert('提示','系统登陆失败，原因：'+action.result.errors);
                    }else{
                        Ext.Msg.alert('提示','系统登陆失败，原因：'+action.failureType);
                    }
                    loginForm.form.reset();
                },
                success:function(form,action){//加载成功的处理函数
                    //Ext.Msg.alert('提示','系统登陆成功',callback);
                    callback();
                }
            });

        }else{
            Ext.MessageBox.show({
                title:'提示',
                msg:'请输入登录信息',
                icon:Ext.Msg.INFO,
                buttons:Ext.Msg.OK
            })
        }
    }

    //重置
    function reset(){
        loginForm.form.reset();
    }

    //访客
    function guesting(){
        var userName = Ext.getCmp('userName');
        var password = Ext.getCmp('password');
        userName.setValue("guest");
        password.setValue("guest");
        login();
    }

    function callback(param){
        Ext.getCmp("bLogin").disabled = true;
		
        Ext.MessageBox.show({
            title:'提示',
            msg: '页面跳转中，请稍等...',
            progressText: 'Waiting...',
            width:300,
            wait:true,
            waitConfig: {
                interval:100
            }
        });
        setTimeout(function(){
            Ext.MessageBox.hide();
        }, 10000);        

        //跳转
        //document.getElementById("loginform").submit();
        window.location.href = '../main/A0200MainAction.action';
    }

});

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}