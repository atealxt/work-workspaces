var panel,t_panel,u_panel,umForm,url,win,isChangeBaseInfo;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    Ext.apply(Ext.form.VTypes, {
        password: function(val, field) {
            if (field.initialPassField) {
                var pwd = Ext.getCmp(field.initialPassField);
                return (val == pwd.getValue());
            }
            return true;
        },
        passwordText: '密码确认错误'
    });

    //帖子信息
    t_panel = new Ext.Panel({
        layout : 'column',
        frame:true,//渲染面板
        autoHeight : true,
        //width : '70%',
        applyTo :'topicAnalysis',
        defaults : {//设置默认属性
            bodyStyle:'padding:3px 3px 3px 3px;font-size:12px;',//background-color:#FFFFFF
            collapsible : true,//允许展开和收缩
            frame : true
        },
        items: [
        {
            id : 'l_t',
            title:'我的主题',
            contentEl : 'l_t',
            columnWidth:.5,//指定列宽为容器宽度的30%
            height : 230,
            buttons:[
            new Ext.Button({
                id : 'b_m_t',
                text : '更多',
                iconCls: 'icon_more',
                handler : parent.more
            })]
        },
        {
            id : 'l_r',
            title:'我的回复',
            contentEl : 'l_r',
            columnWidth:.5,//指定列宽为容器宽度的30%
            height : 230,
            buttons:[
            new Ext.Button({
                id : 'b_m_r',
                text : '更多',
                iconCls: 'icon_more',
                handler : parent.more
            })]
        }
        ]
    });

    //用户信息
    u_panel = new Ext.form.FormPanel({
        title:'用户信息',
        frame:true,//渲染面板
        autoHeight : true,
        autoWidth : true,
        collapsible : true,
        defaults : {//设置默认属性
            bodyStyle:'padding:3px 3px 3px 3px;font-size:12px;',//background-color:#FFFFFF
            collapsible : true,//允许展开和收缩
            frame : true,
            width:300
        },
        bbar:[{
            id:'bbar_cb',
            text : '修改基本信息',
            iconCls: 'icon_change1',
            handler : changeBase
        },{
            id:'bbar_cp',
            text : '修改密码',
            iconCls: 'icon_change2',
            handler : changePsw
        }],
        defaultType: 'textfield',
        items: [
        {
            id : 'loginId_show',
            name: 'loginId_show',
            fieldLabel: '用户ID',
            readOnly : true,
            //disabled : true,
            value : Ext.get('loginId').dom.innerHTML
        },{
            id : 'name_show',
            name: 'name_show',
            fieldLabel: '用户名',
            readOnly : true,
            value : Ext.get('name').dom.innerHTML
        },{
            id : 'identity',
            name: 'identity',
            fieldLabel: '用户身份',
            readOnly : true,
            value : Ext.get('identity').dom.innerHTML
        },{
            id : 'fprj',
            name: 'fprj',
            fieldLabel: '所属项目',
            //autoWidth:true,
            readOnly : true,
            value : Ext.get('fprj').dom.innerHTML
        }
        ]
    });

    panel = new Ext.Panel({
        //header : true,
        frame:true,//渲染面板
        autoHeight : true,
        //autoWidth : true,
        width : '80%',
        applyTo :'panel',
        items: [
        u_panel,t_panel
        ]
    })
});

/** 修改基本信息 */
function changeBase(){

    isChangeBaseInfo = true;

    url = '../user/A0801EditAction.action';
    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        autoWidth:true,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        items: [
        {
            id : 'loginId',
            name: 'loginId',
            fieldLabel: '用户ID',
            allowBlank: false,
            value : Ext.getCmp("loginId_show").value
        },{
            id : 'name',
            name: 'name',
            fieldLabel: '用户名',
            allowBlank: false,
            value : Ext.getCmp("name_show").value
        }]
    });

    win = new Ext.Window({
        title: '修改用户基本信息',
        autoWidth:true,
        autoHeight:true,
        enableDD : true,
        layout: 'fit',
        plain:true,
        closable:false,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: umForm,
        buttons: [
        {
            text: '提交',
            iconCls: 'icon_ok',
            handler: win_submit
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 修改密码 */
function changePsw(){

    isChangeBaseInfo = false;

    url = '../user/A0801ChgPswAction.action';
    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        autoWidth:true,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        defaults: {
            inputType: 'password'
        },
        items: [
        {
            id : 'psw_o',
            name: 'psw_o',
            allowBlank: false,
            fieldLabel: '原密码'
        },{
            id : 'psw_n',
            name: 'psw_n',
            allowBlank: false,
            fieldLabel: '新密码'
        },{
            id : 'psw_r',
            name: 'psw_r',
            allowBlank: false,
            fieldLabel: '密码确认',
            vtype: 'password',
            initialPassField: 'psw_n'
        }]
    });

    win = new Ext.Window({
        title: '修改密码',
        autoWidth:true,
        autoHeight:true,
        enableDD : true,
        layout: 'fit',
        plain:true,
        closable:false,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: umForm,
        buttons: [
        {
            text: '提交',
            iconCls: 'icon_ok',
            handler: win_submit
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 提交 */
function win_submit(){

    if(umForm.form.isValid()){
        Ext.MessageBox.show({
            title: '请等待',
            msg: '提交...',
            progressText: 'submiting',
            width:300,
            waitConfig: {
                interval:200
            },
            wait:true,
            closable:false
        });
        umForm.getForm().submit({
            success: function(){
                Ext.Msg.alert('提示','提交成功');
                win_hide();

                if(isChangeBaseInfo){
                    Ext.getCmp("loginId_show").setValue(umForm.items.get(0).getValue());
                    Ext.getCmp("name_show").setValue(umForm.items.get(1).getValue());
                }
            },
            failure: function(form,action){
                if(action.failureType =="server"){
                    Ext.Msg.alert('提示',action.result.errors);
                }else{
                    Ext.Msg.alert('提示','提交失败，原因：'+action.failureType);
                }
                umForm.form.reset();
            }
        })
    }
}

/** 关闭 */
function win_hide(){
    win.close();
}

/** 打开窗口 */
function showWin(){
    umForm.form.reset();
    umForm.isAdd = true;
    win.show();
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}