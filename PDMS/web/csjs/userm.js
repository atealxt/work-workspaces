var data,recordDefine,sm,grid,gridForm,store,win,umForm,sel_form,sel_win,status_store,i_store;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : [
        {
            name: 'UId'
        },
        {
            name: 'ULoginId'
        },
        {
            name: 'UName'
        },
        {
            name: 'UIp'
        },
        {
            name: 'UIdentity'
        },
        {
            name: 'UGroup'
        },
        {
            name: 'URole'
        },
        {
            name: 'UStatus'
        },
        {
            name: 'UProj'
        }
        ]
    });

    var url = '../userm/A0500UserMAction.action';
    //定义数据集对象
    store = new Ext.data.Store({//配置数据集
        reader: reader,
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    //定义复选框选择模式变量
    sm = new Ext.grid.CheckboxSelectionModel({
        listeners: {
            rowselect: function(sm_temp, row, rec) {
                Ext.getCmp("userm-form").getForm().loadRecord(rec);
            }
        }
    });

    //创建Grid表格组件
    grid = new Ext.grid.GridPanel({
        width:'100%',
        autoHeight : true,
        autoScroll : true,
        frame:true,
        loadMask: true,
        sm : sm,//设置复选框选择模式
        store: store,
        tbar : new Ext.PagingToolbar({//分页工具栏 //bbar
            store : store,
            pageSize : 10,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
            emptyMsg : "没有记录"
        }),
        bbar:[
        {
            text : '修改会员',
            id:'b_chn',
            iconCls: 'icon_edit_u',
            handler : change
        },{
            text : '添加会员',
            id:'b_add',
            iconCls: 'icon_add_u',
            handler : addu
        },{
            text : '注销会员',
            id:'b_del',
            iconCls: 'icon_del_u',
            handler : delu
        }
        ],
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        columns: [//配置表格列
        sm,//复选框选择模式中的checkbox组件将会显示在该列
        {
            header: "登录ID",
            width: 120,
            dataIndex: 'ULoginId'
        },
        {
            header: "用户名",
            width: 120,
            dataIndex: 'UName'
        },
        {
            header: "IP地址",
            width: 110,
            dataIndex: 'UIp'
        },
        {
            header: "用户角色",
            width: 120,
            dataIndex: 'UIdentity'
        },
        {
            header: "用户组",
            width: 120,
            dataIndex: 'UGroup'
        },
        {
            header: "拥有权限",
            width: 120,
            dataIndex: 'URole'
        },
        {
            header: "状态",
            width: 50,
            dataIndex: 'UStatus'
        }
        ]
    });

    gridForm = new Ext.FormPanel({
        id: 'userm-form',
        applyTo :'grid-div',
        frame: true,
        labelAlign: 'left',
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        items: [{
            title: '用户信息',
            items : grid
        }],
        buttonAlign :'right'
    });

    i_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province']/*,
        data : [['职员'],['管理者']]*/
    });

    sel_form = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        autoWidth:true,
        autoHeight:true,
        defaultType: 'textfield',
        items: [
        new Ext.form.ComboBox({
            id:'sel_iden',
            name:'sel_iden',
            fieldLabel:'请选择',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : i_store,//设置数据源
            displayField:'province',//定义要显示的字段
            mode: 'remote',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            handleHeight : 10//下拉列表中拖动手柄的高度
        })]
    });

    store.load({
        params:{
            start:0,
            limit:10
        }
    });
});

/** 修改用户信息 */
function change(){
    if(!isOneSel()){
        return;
    }

    var uid,uname,ip,status,url,iden,grop,role;
    grid.getSelectionModel().each(function(rec){
        //t_name.setValue(rec.get('RTitle'));
        uid = rec.get('ULoginId');
        uname = rec.get('UName');
        ip = rec.get('UIp');
        status = rec.get('UStatus');
        iden = rec.get('UIdentity');
        grop = rec.get('UGroup');
        role = rec.get('URole');
        url = '../userm/A0501EditAction.action?id=' + rec.get('UId');
    });

    status_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        data : [['在职','1'],['离职','2'],['其他','3']]
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        autoWidth:true,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '登录ID',
            id:'uid',
            name: 'uid',
            allowBlank: false,
            value:uid,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户名',
            id:'uname',
            name: 'uname',
            allowBlank: false,
            value:uname,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: 'IP地址',
            id:'ip',
            name: 'ip',
            allowBlank: false,
            value:ip,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户角色',
            id:'iden',
            name: 'iden',
            value:iden,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户组',
            id:'grop',
            name: 'grop',
            value:grop,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '拥有权限',
            id:'role',
            name: 'role',
            value:role,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },
        new Ext.form.ComboBox({
            id:'status',
            name:'status',
            fieldLabel:'状态',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : status_store,//设置数据源
            displayField:'province',//定义要显示的字段
            valueField:'post',//定义值字段
            mode: 'local',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            value:status,
            anchor: '90%',  // anchor width by percentage
            handleHeight : 10//下拉列表中拖动手柄的高度
        })]
    });

    win = new Ext.Window({
        title: '修改用户信息',
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
        tbar:[
        {
            text: '清空用户角色',
            type:'d',
            id:'f_iden_d',
            iconCls: 'icon_clear',
            handler: f_iden
        },
        {
            text: '添加用户角色',
            type:'i',
            id:'f_iden_i',
            iconCls: 'icon_add',
            handler: f_iden
        },
        {
            text: '清空用户组',
            type:'d',
            id:'f_grop_d',
            iconCls: 'icon_clear',
            handler: f_grop
        },
        {
            text: '添加用户组',
            type:'i',
            id:'f_grop_i',
            iconCls: 'icon_add',
            handler: f_grop
        },
        {
            text: '清空用户权限',
            type:'d',
            id:'f_role_d',
            iconCls: 'icon_clear',
            handler: f_role
        },
        {
            text: '添加用户权限',
            type:'i',
            id:'f_role_i',
            iconCls: 'icon_add',
            handler: f_role
        }
        ],
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

/** 修改用户角色 */
function f_iden(param){
    if(param.type=='d'){
        Ext.getCmp("iden").setValue("");
        return;
    }
    createSelWin('../userm/A0504IdenAction.action','iden');
}

/** 修改用户组 */
function f_grop(param){
    if(param.type=='d'){
        Ext.getCmp("grop").setValue("");
        return;
    }
    createSelWin('../userm/A0506GropAction.action','grop');
}

/** 修改用户权限 */
function f_role(param){
    if(param.type=='d'){
        Ext.getCmp("role").setValue("");
        return;
    }
    createSelWin('../userm/A0505RoleAction.action','role');
}

/** 添加会员 */
function addu(){

    var status="",url="../userm/A0502AddUsrAction.action",iden="",grop="",role="";
    grid.getSelectionModel().each(function(rec){
        status = rec.get('UStatus');
        iden = rec.get('UIdentity');
        grop = rec.get('UGroup');
        role = rec.get('URole');
    });
    if(status == ""){
        status = "在职";
    }

    status_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        data : [['在职','1'],['离职','2'],['其他','3']]
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        autoWidth:true,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '登录ID',
            id:'uid',
            name: 'uid',
            allowBlank: false,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户名',
            id:'uname',
            name: 'uname',
            allowBlank: false,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: 'IP地址',
            id:'ip',
            name: 'ip',
            allowBlank: false,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户角色',
            id:'iden',
            name: 'iden',
            value:iden,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '用户组',
            id:'grop',
            name: 'grop',
            value:grop,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '拥有权限',
            id:'role',
            name: 'role',
            value:role,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },
        new Ext.form.ComboBox({
            id:'status',
            name:'status',
            fieldLabel:'状态',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : status_store,//设置数据源
            displayField:'province',//定义要显示的字段
            valueField:'post',//定义值字段
            mode: 'local',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            value:status,
            anchor: '90%',  // anchor width by percentage
            handleHeight : 10//下拉列表中拖动手柄的高度
        })]
    });

    win = new Ext.Window({
        title: '新建用户',
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
        tbar:[
        {
            text: '清空用户角色',
            type:'d',
            id:'f_iden_d',
            iconCls: 'icon_clear',
            handler: f_iden
        },
        {
            text: '添加用户角色',
            type:'i',
            id:'f_iden_i',
            iconCls: 'icon_add',
            handler: f_iden
        },
        {
            text: '清空用户组',
            type:'d',
            id:'f_grop_d',
            iconCls: 'icon_clear',
            handler: f_grop
        },
        {
            text: '添加用户组',
            type:'i',
            id:'f_grop_i',
            iconCls: 'icon_add',
            handler: f_grop
        },
        {
            text: '清空用户权限',
            type:'d',
            id:'f_role_d',
            iconCls: 'icon_clear',
            handler: f_role
        },
        {
            text: '添加用户权限',
            type:'i',
            id:'f_role_i',
            iconCls: 'icon_add',
            handler: f_role
        }
        ],
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

/** 注销会员(按钮事件) */
function delu(){
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择会员!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '注销会员： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('ULoginId') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                u_nuke();
            }
        }
    });
}

/** 注销会员 */
function u_nuke(){
    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        title: '请等待',
        progressText: 'waiting...',
        msg:'正在处理请稍后......'
    });
    Ext.Ajax.request({
        url : '../userm/A0503DelUsrAction.action',
        params : {
            ids : ids
        },
        method : 'POST',
        timeout : 10000,
        success : function(response,options){
            msgTip.hide();
            var result = Ext.util.JSON.decode(response.responseText);
            if(result.success){
                grid.store.reload();
                gridForm.form.reset();
                Ext.Msg.alert('提示','注销成功！');
            }else{
                Ext.Msg.alert('提示','注销失败！');
            }
        },
        failure : function(response,options){
            msgTip.hide();
            Ext.Msg.alert('提示','注销过程中发生错误！');
        }
    });

}

/** 取得所选ID */
function getIdList(){
    var recs = grid.getSelectionModel().getSelections();
    var list = [];
    for(var i = 0 ; i < recs.length ; i++){
        var rec = recs[i];
        list.push(rec.get('UId'))
    }
    return list;
}

/** 判断是否只选择了1行 */
function isOneSel(){
    var len = grid.getSelectionModel().selections.length;
    if(len == 0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择对象!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return false;
    }
    if(len > 1){
        Ext.MessageBox.show({
            title: '提示',
            msg: '只能选择1个对象!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return false;
    }
    return true;
}

/** 打开窗口 */
function showWin(){
    Ext.form.Field.prototype.msgTarget = 'side';
    umForm.form.reset();
    umForm.isAdd = true;
    win.show();
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
                Ext.Msg.alert('提示','提交成功',reloadGrid);
                win_hide();
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

/* 刷新Grid数据 */
function reloadGrid(){
    grid.store.reload();
}

/** 关闭 */
function win_hide(){
    Ext.form.Field.prototype.msgTarget = 'qtip';
    win.close();
}

/** 生成并显示权限等选择框 */
function createSelWin(url,control){
    i_store.proxy = new Ext.data.HttpProxy({//读取远程数据的代理
        url : url//远程地址
    });
    i_store.reload();

    sel_win = new Ext.Window({
        title: '选择',
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
        items: sel_form,
        buttons: [
        {
            text: '添加',
            iconCls: 'icon_ok',
            handler: function(){
                if(Ext.getCmp("sel_iden").lastSelectionText!=undefined){
                    //判断是否已经重复
                    if( Ext.getCmp(control).getValue().indexOf(Ext.getCmp("sel_iden").lastSelectionText)!=-1){
                        Ext.Msg.alert('提示','已包含此项!');
                        return;
                    }

                    var vTemp = Ext.getCmp(control).getValue()==""?Ext.getCmp(control).getValue():Ext.getCmp(control).getValue()+",";
                    Ext.getCmp(control).setValue(vTemp + Ext.getCmp("sel_iden").lastSelectionText);
                    sel_win.hide();
                }
            }
        },{
            text: '取消',
            iconCls: 'icon_close',
            handler : function(){
                sel_win.hide();
            }
        }]
    });

    sel_form.form.reset();
    sel_form.isAdd = true;
    sel_win.show();
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}