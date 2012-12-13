var p_form,data,recordDefine,store,sm,grid,view,reader,p_topic,win;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){
    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    //创建记录类型，mapping值为字段值对应数组中数据的索引位置
    var fields = [
    {
        name: 'TId'
    },
    {
        name: 'TLevel'
    },
    {
        name: 'TName'
    },
    {
        name: 'TUser'
    },
    {
        name: 'TRCnt'
    },
    {
        name: 'TDate',
        type: 'date'
    },
    {
        name: 'TStatus'
    }
    ];

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : fields
    });

    //定义数据集对象
    store = new Ext.data.GroupingStore({//配置分组数据集
        reader: reader,
        sortInfo:{
            field: 'TDate',
            direction: "DESC"
        },
        groupField:'TLevel',//指定分组字段
        proxy : new Ext.data.HttpProxy({
            url : '../project/A0301LTAction.action?id=' + Ext.get('p_id').dom.innerHTML
        })
    });

    //设置表格分组视图
    view = new Ext.grid.GroupingView({
        groupTextTpl : '{text}',//定义分组行模板
        groupByText : '使用当前字段进行分组',//表头菜单提示信息
        showGroupsText : '表格分组',//表头菜单提示信息
        showGroupName : true,//显示分组字段名称
        startCollapsed : false,//展开分组
        hideGroupedColumn : true//隐藏分组列
    });

    //创建Grid表格组件
    grid = new Ext.grid.GridPanel({
        //width : 790,
        width:'100%',
        //height : 550,
        autoHeight : true,
        autoScroll : true,
        frame:true,
        loadMask: true,
        store: store,
        view: view,
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        tbar : new Ext.PagingToolbar({//分页工具栏 //bbar
            store : store,
            pageSize : 20,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
            emptyMsg : "没有记录"
        }),
        bbar:[
        {
            text : '发表帖子',
            iconCls: 'icon_new',
            handler : topic_new
        },{
            text : '关闭帖子',
            iconCls: 'icon_del',
            handler : topic_close
        }],
        columns: [//配置表格列
        //sm,//复选框选择模式中的checkbox组件将会显示在该列
        new Ext.grid.RowNumberer(),
        {
            header: "帖子等级",
            width: 10,
            dataIndex: 'TLevel'
        },{
            header: "主题名",
            width: 640,
            dataIndex: 'TName'
        },
        {
            header: "发表时间",
            width: 150,
            renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s'),
            dataIndex: 'TDate'
        },
        {
            header: "发表人",
            width: 80,
            dataIndex: 'TUser'
        },
        {
            header: "回复人数",
            width: 80,
            dataIndex: 'TRCnt'
        },
        {
            header: "状态",
            width: 60,
            dataIndex: 'TStatus'
        }
        ]
    });

    p_form = new Ext.FormPanel({
        id: 'prj-form',
        applyTo :'p_body',
        frame: true,
        labelAlign: 'left',
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        //width: 810,
        //width:'80%',
        items:[
        {
            title: Ext.get('p_name').dom.innerHTML,
            collapsible : true,
            contentEl : 'p_an',
            bodyStyle:'padding:10px; font-size:14px;'
        },{
            title: '帖子信息',
            items : grid
        }]
    });

    var level_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        data : [['Position 1','1'],['Position 2','2'],]
    });
    var title_store = new Ext.data.SimpleStore({
        fields: ['province', 'post'],
        data : [['讨论','1'],['求助','2'],['资源','3'],['其他','3']]
    });
    var role_store = new Ext.data.SimpleStore({
        fields: ['province', 'post'],
        data : [['Private','1'],['Public','0']]
    });
    p_topic = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        minWidth: 700,
        minHeight: 300,
        autoWidth:true,
        autoHeight:true,
        url:'../topic/A0403AddTPAction.action?pId=' + Ext.get('p_id').dom.innerHTML,
        fileUpload:true,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '主题名',
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
            value:'0',//默认选择
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
            value:'2',//默认选择
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
        /*{
            xtype: 'textfield',
            fieldLabel: '附件',
            name: 'UF',
            inputType: 'file',
            anchor: '100%'  // anchor width by percentage
        },*/{
            xtype: 'htmleditor',
            fieldLabel: '内容',
            id: 't_content',
            name: 't_content'
        }]
    });

    win = new Ext.Window({
        title: '发表新帖',
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
        items: p_topic,
        buttons: [{
            text: '提交',
            iconCls: 'icon_ok',
            handler: function() {

                if(p_topic.form.isValid()){
                    if(Ext.getCmp("t_content").getValue().length < 10){
                        Ext.MessageBox.show({
                            title:'错误',
                            msg:'主题内容长度太少',
                            icon:Ext.Msg.ERROR,
                            buttons:Ext.Msg.OK
                        })
                        return;
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
                    p_topic.getForm().submit({
                        success: function(){
                            grid.store.reload();
                            Ext.Msg.alert('提示','提交成功');
                            win.hide();
                        },
                        failure: function(form,action){
                            if(action.failureType =="server"){
                                Ext.Msg.alert('提示',action.result.errors);
                            }else{
                                Ext.Msg.alert('提示','提交失败，原因：'+action.failureType);
                            }
                            p_topic.form.reset();
                        }
                    })
                }
            }
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler:function(){
                Ext.form.Field.prototype.msgTarget = 'qtip';
                win.hide();
            }
        }]
    });

    store.load({
        params:{
            start:0,
            limit:20
        }
    });
});

/* 刷新Grid数据 */
function reloadGrid(){
    grid.store.reload();
}

/** 发表主题 */
function topic_new(){
    Ext.form.Field.prototype.msgTarget = 'side';
    p_topic.form.reset();
    p_topic.isAdd = true;
    win.show();
}

/** 锁定主题(事件) */
function topic_close(){
    
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择主题!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '关闭主题： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('TName') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                closeT();
            }
        }
    });
}
/** 锁定主题 */
function closeT(){
    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        title: '请等待',
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        progressText: 'waiting...',
        msg:'正在处理请稍后......'
    });
    Ext.Ajax.request({
        url : '../topic/A0404CloseAction.action',
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
                p_form.form.reset();
                Ext.Msg.alert('提示','处理成功！');
            }else{
                Ext.Msg.alert('提示',result.errors);
            }
        },
        failure : function(response,options){
            msgTip.hide();
            Ext.Msg.alert('提示','处理过程中发生错误！');
        }
    });
}

/** 取得所选ID */
function getIdList(){
    var recs = grid.getSelectionModel().getSelections();
    var list = [];
    for(var i = 0 ; i < recs.length ; i++){
        var rec = recs[i];
        list.push(rec.get('TId'))
    }
    return list;
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}