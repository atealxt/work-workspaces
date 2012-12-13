var data,recordDefine,sm,grid,gridForm,store,win,umForm,sel_form,sel_win,status_store,i_store;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    var fields =  [
    {
        name: 'GId'
    },
    {
        name: 'GName'
    },
    {
        name: 'GUsr'
    },
    {
        name: 'GRole'
    },
    {
        name: 'GIden'
    }
    ];

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : fields
    });

    var url = '../groupm/A1200GroupMAction.action';
    //定义数据集对象
    store = new Ext.data.Store({//配置数据集
        reader: reader,
        //data: data
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    //定义复选框选择模式变量
    sm = new Ext.grid.CheckboxSelectionModel({});

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
            text : '修改用户组',
            id:'b_chn',
            iconCls: 'icon_edit_u',
            handler : change
        },{
            text : '新建用户组',
            id:'b_add',
            iconCls: 'icon_add_u',
            handler : addg
        },{
            text : '删除用户组',
            id:'b_del',
            iconCls: 'icon_del_u',
            handler : delg
        }
        ],
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        columns: [//配置表格列
        sm,//复选框选择模式中的checkbox组件将会显示在该列        
        {
            header: "用户组名称",
            width: 80,
            dataIndex: 'GName'
        },
        {
            header: "组内成员",
            width: 160,
            dataIndex: 'GUsr'
        },
        {
            header: "拥有权限",
            width: 120,
            dataIndex: 'GRole'
        },
        {
            header: "拥有身份",
            width: 120,
            dataIndex: 'GIden'
        }
        ]
    });

    gridForm = new Ext.FormPanel({
        id: 'groupm-form',
        applyTo :'grid-div',
        frame: true,
        labelAlign: 'left',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        items: [{
            title: '用户组信息',
            items : grid
        }],
        buttonAlign :'right'
    });

    store.load({
        params:{
            start:0,
            limit:10
        }
    });
});

/** 修改拥有身份 */
function f_iden(param){
    if(param.type=='d'){
        Ext.getCmp("giden").setValue("");
        return;
    }
    createSelWin('../userm/A0504IdenAction.action','giden');
}

/** 修改拥有权限 */
function f_role(param){
    if(param.type=='d'){
        Ext.getCmp("grole").setValue("");
        return;
    }
    createSelWin('../userm/A0505RoleAction.action','grole');
}

/** 修改组内成员 */
function f_user(param){
    if(param.type=='d'){
        Ext.getCmp("gusr").setValue("");
        return;
    }
    createSelWin('../userm/A0500UserMAction.action','gusr');
}

/** 修改用户组 */
function change(){
    if(!isOneSel()){
        return;
    }
    changeOrAdd("../groupm/A1201EditAction.action?id=","修改用户组信息");
}

/** 新建用户组 */
function addg(){
    changeOrAdd("../groupm/A1202AddGrpAction.action?cpId=","新建用户组");
}

/** 显示新建或修改用户组的画面 */
function changeOrAdd(url,title){

    var gname,gusr,grole,giden;
    grid.getSelectionModel().each(function(rec){
        gname = rec.get('GName');
        gusr = rec.get('GUsr');
        grole = rec.get('GRole');
        giden = rec.get('GIden');
        url = url + rec.get('GId');
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
            fieldLabel: '用户组名称',
            id:'gname',
            name: 'gname',
            allowBlank: false,
            value:gname,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '组内成员',
            id:'gusr',
            name: 'gusr',
            value:gusr,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '拥有权限',
            id:'grole',
            name: 'grole',
            value:grole,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '拥有身份',
            id:'giden',
            name: 'giden',
            value:giden,
            readOnly:true,
            anchor: '90%'  // anchor width by percentage
        }]
    });

    win = new Ext.Window({
        title: title,
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
            text: '清空组内成员',
            type:'d',
            id:'f_gusr_d',
            iconCls: 'icon_clear',
            handler: f_user
        },
        {
            text: '添加组内成员',
            type:'i',
            id:'f_gusr_i',
            iconCls: 'icon_add',
            handler: f_user
        },
        {
            text: '清空拥有权限',
            type:'d',
            id:'f_grole_d',
            iconCls: 'icon_clear',
            handler: f_role
        },
        {
            text: '添加拥有权限',
            type:'i',
            id:'f_grole_i',
            iconCls: 'icon_add',
            handler: f_role
        },
        {
            text: '清空拥有身份',
            type:'d',
            id:'f_giden_d',
            iconCls: 'icon_clear',
            handler: f_iden
        },
        {
            text: '添加拥有身份',
            type:'i',
            id:'f_giden_i',
            iconCls: 'icon_add',
            handler: f_iden
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

/** 删除用户组(按钮事件) */
function delg(){
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择用户组!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '删除用户组： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('GName') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                g_nuke();
            }
        }
    });
}

/** 删除用户组 */
function g_nuke(){
    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        title:'提示',
        progressText: 'waiting...',
        msg:'正在处理请稍后......'
    });
    Ext.Ajax.request({
        url : '../groupm/A1203DelGrpAction.action',
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
                Ext.Msg.alert('提示','处理成功！');
            }else{
                Ext.Msg.alert('提示','处理失败！');
            }
        },
        failure : function(response,options){
            msgTip.hide();
            Ext.Msg.alert('提示','处理过程中发生错误！');
        }
    });
}

/* 刷新Grid数据 */
function reloadGrid(){
    grid.store.reload();
}

/** 取得所选ID */
function getIdList(){
    var recs = grid.getSelectionModel().getSelections();
    var list = [];
    for(var i = 0 ; i < recs.length ; i++){
        var rec = recs[i];
        list.push(rec.get('GId'))
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

/** 关闭 */
function win_hide(){
    Ext.form.Field.prototype.msgTarget = 'qtip';
    win.close();
}

/** 生成并显示权限等选择框 */
function createSelWin(url,control){

    if(control == "gusr"){
        i_store = new Ext.data.Store({//配置数据集
            reader: new Ext.data.JsonReader({
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
            })
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
                displayField:'ULoginId',//定义要显示的字段
                mode: 'remote',//本地模式
                forceSelection : true,//要求输入值必须在列表中存在
                resizable : true,//允许改变下拉列表的大小
                typeAhead : true,//允许自动选择匹配的剩余部分文本
                editable:false,
                pageSize : 10,//分页大小
                handleHeight : 10//下拉列表中拖动手柄的高度
            })]
        });
    }else{
        i_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
            fields: ['province']
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
    }
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
                    sel_win.close();
                }
            }
        },{
            text: '取消',
            iconCls: 'icon_close',
            handler : function(){
                sel_win.close();
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