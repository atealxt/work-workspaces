var data,t_form,sm,grid,store,toolbar,recordDefine,reader,t_reply,win,level_store,title_store,role_store;

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
            name: 'RId'
        },
        {
            name: 'RTitle'
        },
        {
            name: 'RContent'
        },
        {
            name: 'RUsr'
        },
        {
            name: 'RTime',
            type: 'date'
        },
        {
            name: 'isTopic'
        }
        ]
    });

    var url = '../topic/A0400TopicAction.action?id=' + Ext.get('t_id').dom.innerHTML;
    url = url + '&mode=' + Ext.get('t_mode').dom.innerHTML;
    store = new Ext.data.Store({//配置数据集
        reader: reader,
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    toolbar =new Ext.PagingToolbar({//分页工具栏 //bbar
        store : store,
        //pageSize : 10,
        pageSize : 9,
        displayInfo : true,
        displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
        emptyMsg : "没有记录"
    });

    //定义复选框选择模式变量
    sm = new Ext.grid.CheckboxSelectionModel({
        listeners: {
            rowselect: function(sm_temp, row, rec) {
                Ext.getCmp("prj-form").getForm().loadRecord(rec);
            }
        }
    });

    //创建Grid表格组件
    grid = new Ext.grid.GridPanel({
        //width:790,
        width:'100%',
        //height:400,
        autoHeight : true,
        //autoWidth:true,
        autoScroll : true,
        //maxHeight : 300,
        store: store,
        sm : sm,//设置复选框选择模式
        viewConfig:{//表格视图配置对象
            autoFill : true,
            forceFit : true,
            enableRowBody : true,//使用行体
            getRowClass : function(record,index,rowParams,st){
                //指定行体内容
                rowParams.body = '<div style="margin:10 10 10 10;">'+
                record.get('RContent')+'</div>';
                //指定行体跨列范围
                rowParams.cols = 14;
                if(index==0){
                    //指定行体样式
                    rowParams.bodyStyle  = 'background-color : #FFFFDC;';
                }else{
                    rowParams.bodyStyle  = 'background-color : #FFFFEC;';
                }
            }
        },
        tbar : toolbar,
        bbar:[
        {
            text : '引用',
            iconCls: 'icon_quot',
            handler : quot
        },{
            text : '编辑',
            iconCls: 'icon_change2',
            handler : change
        },{
            text : '回复',
            iconCls: 'icon_new',
            handler : reply
        }
        ],
        columns: [//配置表格列
        sm,
        new Ext.grid.RowNumberer({
            header : "◎"/*,
            width :10*/
        }),
        {
            header: "内容",
            width: 800,
            dataIndex: 'RTitle'
        },
        {
            header: "发表人",
            width: 80,
            dataIndex: 'RUsr'
        },
        {
            header: "发表时间",
            width: 140,
            dataIndex: 'RTime',
            renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')
        }
        ]
    })

    t_form = new Ext.FormPanel({
        id: 'prj-form',
        applyTo :'t_body',
        frame: true,
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        //width: 810,
        //width: '98%',
        //height : '100%',
        items:[
        {
            title: '帖子信息',
            items : grid
        }],
        buttonAlign :'right'
    });

    store.load({
        params:{
            start:0,
            limit:9//limit:10
        }
    });

});

/** 引用按钮点击事件 */
function quot(){
    if(!isOneSel()){
        return;
    }

    t_reply = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        minWidth: 700,
        minHeight: 300,
        autoWidth:true,
        autoHeight:true,
        url:'../topic/A0401AddRepAction.action?id=' + Ext.get('t_id').dom.innerHTML,
        fileUpload:true,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '主题名',
            id:'t_name',
            name: 't_name',
            allowBlank: false,
            blankText: '主题名不能为空.',
            anchor: '90%'  // anchor width by percentage
        },
        {
            xtype: 'htmleditor',
            fieldLabel: '内容',
            id: 't_content',
            name: 't_content'
        }]
    });

    win = new Ext.Window({
        title: '引用',
        width:700,
        height:450,
        enableDD : true,
        layout: 'fit',
        plain:true,
        closable:false,
        //collapsible: true,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: t_reply,
        buttons: [{
            text: '提交',
            type:1,
            iconCls: 'icon_ok',
            handler: win_submit
        },{
            text: '取消',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    var t_name = Ext.getCmp('t_name');
    var t_content = Ext.getCmp('t_content');
    var quot_mode;
    grid.getSelectionModel().each(function(rec){
        t_name.setValue(rec.get('RTitle'));

        var vTemp = rec.get('RContent');
        vTemp = vTemp.replace(/<\/?(?!br)[^>]*>/g,'');
        vTemp = vTemp.replace('<br>','\n').substr(0, 100);
        vTemp = vTemp.replace('\n','<br>');
        quot_mode =  "\[quot\]引用 ";
        quot_mode += rec.get('RUsr');
        quot_mode += ": <br>";
        quot_mode += vTemp;
        quot_mode += "\[\/quot\]";
    });

    t_name.readOnly = true;
    t_content.setValue(quot_mode);

    showWin();
}

/** 回复按钮点击事件 */
function reply(){

    t_reply = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        minWidth: 700,
        minHeight: 300,
        autoWidth:true,
        autoHeight:true,
        url:'../topic/A0401AddRepAction.action?id=' + Ext.get('t_id').dom.innerHTML,
        fileUpload:true,
        defaultType: 'textfield',
        items: [
        {
            xtype: 'htmleditor',
            fieldLabel: '回复内容',
            id: 't_content',
            name: 't_content'
        }]
    });

    win = new Ext.Window({
        title: '回复',
        width:700,
        height:400,
        enableDD : true,
        layout: 'fit',
        plain:true,
        closable:false,
        //collapsible: true,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: t_reply,
        buttons: [{
            text: '提交',
            type:2,
            iconCls: 'icon_ok',
            handler: win_submit
        },{
            text: '取消',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 修改按钮点击事件 */
function change(){
    if(!isOneSel()){
        return;
    }

    //权限判断
    var istopic;
    var userOk = true;
    grid.getSelectionModel().each(function(rec){
        istopic = rec.get('isTopic');
        if(parent.username != rec.get('RUsr')){
            Ext.MessageBox.show({
                title: '错误',
                msg: '您不能修改别人的帖子!',
                buttons: Ext.MessageBox.OK,
                icon: Ext.Msg.WARNING
            });
            userOk = false;
        }
    });
    if(!userOk){
        return;
    }

    level_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        data : [['Position 1','1'],['Position 2','2'],]
    });
    title_store = new Ext.data.SimpleStore({
        fields: ['province', 'post'],
        data : [['讨论','1'],['求助','2'],['资源','3'],['其他','3']]
    });
    role_store = new Ext.data.SimpleStore({
        fields: ['province', 'post'],
        data : [['Private','1'],['Public','0']]
    });

    var url = '../topic/A0402EditAction.action?id=' + Ext.get('t_id').dom.innerHTML;
    if(!istopic){
        grid.getSelectionModel().each(function(rec){
            url = '../topic/A0402EditAction.action?rId=' + rec.get('RId');
        });
    }

    t_reply = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        minWidth: 700,
        minHeight: 300,
        autoWidth:true,
        autoHeight:true,
        url : url,
        fileUpload:true,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '主题名',
            id:'t_name',
            name: 't_name',
            allowBlank: false,
            blankText: '主题名不能为空.',
            anchor: '90%'  // anchor width by percentage
        },
        new Ext.form.ComboBox({
            id:'t_role',
            name:'t_role',
            fieldLabel:'帖子权限',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : role_store,//设置数据源
            displayField:'province',//定义要显示的字段
            valueField:'post',//定义值字段
            mode: 'local',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            handleHeight : 10//下拉列表中拖动手柄的高度
        }),
        new Ext.form.ComboBox({
            id:'t_level',
            name:'t_level',
            fieldLabel:'帖子等级',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : level_store,//设置数据源
            displayField:'province',//定义要显示的字段
            valueField:'post',//定义值字段
            mode: 'local',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            handleHeight : 10//下拉列表中拖动手柄的高度
        }),
        new Ext.form.ComboBox({
            id:'t_title',
            name:'t_title',
            fieldLabel:'帖子分类',
            triggerAction: 'all',//单击触发按钮显示全部数据
            store : title_store,//设置数据源
            displayField:'province',//定义要显示的字段
            valueField:'post',//定义值字段
            mode: 'local',//本地模式
            forceSelection : true,//要求输入值必须在列表中存在
            resizable : true,//允许改变下拉列表的大小
            typeAhead : true,//允许自动选择匹配的剩余部分文本
            editable:false,
            handleHeight : 10//下拉列表中拖动手柄的高度
        }),
        {
            xtype: 'htmleditor',
            fieldLabel: '内容',
            id: 't_content',
            name: 't_content'
        }]
    });

    win = new Ext.Window({
        title: '编辑',
        width:700,
        height:450,
        enableDD : true,
        layout: 'fit',
        plain:true,
        closable:false,
        //collapsible: true,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: t_reply,
        buttons: [{
            text: '提交',
            type:3,
            istopic:istopic,
            iconCls: 'icon_ok',
            handler: win_submit
        },{
            text: '取消',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    var t_name = Ext.getCmp('t_name');
    var t_content = Ext.getCmp('t_content');
    grid.getSelectionModel().each(function(rec){
        istopic = rec.get('isTopic');
        t_name.setValue(rec.get('RTitle'));
        //t_content.setValue(rec.get('RContent'));
        var vTemp = rec.get('RContent');
        vTemp = vTemp.replace('<div class=\"quotecontent\">','\[quot\]');
        vTemp = vTemp.replace('</div>','\[\/quot\]');
        t_content.setValue(vTemp);
    });
    if(!istopic){
        t_reply.items.removeAt(0);
        t_reply.items.removeAt(0);
        t_reply.items.removeAt(0);
        t_reply.items.removeAt(0);
    }

    showWin();
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

/** 打开回复窗口 */
function showWin(){
    Ext.form.Field.prototype.msgTarget = 'side';
    t_reply.form.reset();
    t_reply.isAdd = true;
    win.show();
}

/**
 * 提交
 * @param param.type 1:引用 2:回复 3:修改
 * @param param.istopic 是否是修改的主题
 */
function win_submit(param){
    if(t_reply.form.isValid()){
        if(Ext.getCmp("t_content").getValue().length < 10){
            Ext.MessageBox.show({
                title:'错误',
                msg:'主题内容长度太少',
                icon:Ext.Msg.ERROR,
                buttons:Ext.Msg.OK
            })
            return;
        }

        //if(param.type == 1){
        if(param.type == 1 || param.type == 3){
            var t_content = Ext.getCmp('t_content');
            var vTemp = t_content.getValue();
            vTemp = vTemp.replace('\[quot\]','<div class=\"quotecontent\">');
            vTemp = vTemp.replace('\[\/quot\]','</div>');
            t_content.setValue(vTemp);
        }

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
        t_reply.getForm().submit({
            success: function(){

                //t_mode只使用一次
                Ext.get('t_mode').dom.innerHTML = "";

                if(param.type == 3){
                    grid.store.reload();

                    if(param.istopic){

                        grid.getSelectionModel().selectRow(0);//选择第一行

                        //刷新tab
                        grid.getSelectionModel().each(function(rec){
                            parent.tabPanel.doLayout(true);
                        });
                    }
                }else{
                    /*
                    store.load({
                        params:{
                            start:-1,
                            limit:10
                        }
                    });*/
                    grid.store.reload();
                }

                Ext.Msg.alert('提示','提交成功');
                //win.hide();
                win.close();
            },
            failure: function(form,action){
                if(action.failureType =="server"){
                    Ext.Msg.alert('提示',action.result.errors);
                }else{
                    Ext.Msg.alert('提示','提交失败，原因：'+action.failureType);
                }
                t_reply.form.reset();
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
    //win.hide();
    win.close();
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}