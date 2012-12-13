var data,recordDefine,sm,grid,gridForm,store,mSubmit,win,text_search;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    Ext.override(Ext.ToolTip, {
        onTargetOver : function(e){
            if(this.disabled || e.within(this.target.dom, true)){
                return;
            }
            var t = e.getTarget(this.delegate);
            if (t) {
                this.triggerElement = t;
                this.clearTimer('hide');
                this.targetXY = e.getXY();
                this.delayShow();
            }
        },
        onMouseMove : function(e){
            var t = e.getTarget(this.delegate);
            if (t) {
                this.targetXY = e.getXY();
                if (t === this.triggerElement) {
                    if(!this.hidden && this.trackMouse){
                        this.setPagePosition(this.getTargetXY());
                    }
                } else {
                    this.hide();
                    this.lastActive = new Date(0);
                    this.onTargetOver(e);
                }
            } else if (!this.closable && this.isVisible()) {
                this.hide();
            }
        },
        hide: function(){
            this.clearTimer('dismiss');
            this.lastActive = new Date();
            delete this.triggerElement;
            Ext.ToolTip.superclass.hide.call(this);
        }
    });

    var fields = [
    {
        name: 'MId'
    },
    {
        name: 'MName'
    },
    {
        name: 'MConfirm'
    },
    {
        name: 'MComtime',
        type: 'date'
    },
    {
        name: 'MDc'
    },
    {
        name: 'MCs'
    },
    {
        name: 'MIs'
    },
    {
        name: 'MPrj'
    },
    {
        name: 'MDist'
    }
    ];

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : fields
    });

    var url = '../mymisson/A1000MyMissionAction.action';
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

    //详细搜索
    text_search = new Ext.form.TextField({
        name : 'textSearch',
        width : 200,
        emptyText : '请输入搜索条件(任务内容)',
        listeners : {
            'specialkey' : function(field, e) {
                if (e.getKey() == Ext.EventObject.ENTER) {
                    searchContent();
                }
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
        bbar : new Ext.PagingToolbar({//分页工具栏 //bbar
            store : store,
            pageSize : 20,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
            emptyMsg : "没有记录"
        }),
        tbar:[
        {
            text : '受取任务',
            id:'b_rec',
            iconCls: 'icon_down',
            handler : receive
        },{
            text : '提交任务',
            id:'b_sub',
            iconCls: 'icon_up_m',
            handler : submit
        },'  ',text_search,
        {
            text : '查询',
            id:'b_m_ser',
            iconCls: 'icon_tree_search',
            handler:searchContent
        }
        ],
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        plugins: new Ext.ux.plugins.grid.CellToolTips([
        {
            field: 'MName',
            tpl: '<b>任务: ID {MId}</b><br/>{MName}'
        }
        ]),
        columns: [//配置表格列
        sm,//复选框选择模式中的checkbox组件将会显示在该列
        {
            header: "任务内容",
            width: 350,
            dataIndex: 'MName'
        },
        {
            header: "所剩确认时间",
            width: 90,
            //sortable: true,
            dataIndex: 'MConfirm'
        },
        {
            header: "完成日限",
            width: 100,
            renderer: Ext.util.Format.dateRenderer('Y/m/d'),
            //sortable: true,
            dataIndex: 'MComtime'
        },
        {
            header: "受取状态",
            width: 70,
            //sortable: true,
            renderer: filter,
            dataIndex: 'MDc'
        },
        {
            header: "完成状态",
            width: 70,
            //sortable: true,
            renderer: filter,
            dataIndex: 'MCs'
        },
        {
            header: "审核状态",
            width: 70,
            //sortable: true,
            renderer: filter,
            dataIndex: 'MIs'
        },
        {
            header: "所属项目",
            width: 120,
            //sortable: true,
            dataIndex: 'MPrj'
        },
        {
            header: "分配人",
            width: 80,
            dataIndex: 'MDist'
        }
        ]
    });

    gridForm = new Ext.FormPanel({
        applyTo :'grid-div',
        frame: true,
        labelAlign: 'left',
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        items: [{
            title: '任务信息',
            items : grid
        }]
    });

    store.load({
        params:{
            start:0,
            limit:20
        }
    });
});

/** 接受任务(按钮事件) */
function receive(){

    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择任务!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var valid=true;
    var msgshow = "所选任务不能接收!";
    grid.getSelectionModel().each(function(rec){
        if(rec.get('MDc')!="否"){//接收状态
            valid = false;
        }
        else if(rec.get('MCs')!="否"){//完成状态
            valid = false;
        }
        else if(rec.get('MConfirm')=="-" || rec.get('MConfirm')=="超时未接收"){//所剩确认时间
            msgshow = "超时未接收,请及时联系任务派遣人!";
            valid = false;
        }
        if(!valid)return;
    });
    if(!valid){
        Ext.MessageBox.show({
            title: '提示',
            msg: msgshow,
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '接收所选任务？';
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                receiveit();
            }
        }
    });
}

/** 接受任务 */
function receiveit(){

    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        progressText: 'waiting...',
        title : '提示',
        msg:'正在处理请稍后......'
    });
    Ext.Ajax.request({
        url : '../mymisson/A1002RevAction.action',
        params : {
            ids : ids
        },
        method : 'POST',
        timeout : 10000,
        success : function(response,options){
            msgTip.hide();
            var result = Ext.util.JSON.decode(response.responseText);
            if(result.success){
                Ext.Msg.alert('提示','成功受取任务，请及时完成！');
            }else{
                Ext.Msg.alert('提示',result.errors);
            }
            //如有部分失败,也需要刷新
            grid.store.reload();
            gridForm.form.reset();
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
        list.push(rec.get('MId'))
    }
    return list;
}

/** 提交任务 */
function submit(){
    if(!isOneSel()){
        return;
    }

    var valid = true,mId,url;
    grid.getSelectionModel().each(function(rec){
        if(rec.get('MDc') !="是" || rec.get('MCs') =="超时" || rec.get('MIs') =="是"){
            valid = false;
            return;
        }
        mId = rec.get('MId');
    });
    if(!valid){
        Ext.MessageBox.show({
            title: '提示',
            msg: '所选任务不能提交!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var fields = [
    {
        name: 'FId'
    },
    {
        name: 'FName'
    },
    {
        name: 'FType'
    },
    {
        name: 'FAddr'
    },
    {
        name: 'FSize'
    },
    {
        name: 'FDate',
        type: 'date'
    }
    ];

    var storefile = new Ext.data.Store({//配置数据集
        reader: new Ext.data.JsonReader({
            totalProperty: 'results',
            results: "results",
            root : "items",
            fields : fields
        }),
        proxy : new Ext.data.HttpProxy({
            url : '../file/A0900FileAction.action'
        })
    });

    url = "../mymisson/A1001SubmAction.action?id=" + mId;
    mSubmit = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        minWidth: 700,
        minHeight: 300,
        autoWidth:true,
        autoHeight:true,
        url:url,
        //fileUpload:true,
        defaultType: 'textfield',
        items: [
        {
            xtype: 'htmleditor',
            fieldLabel: '内容',
            id: 'content',
            name: 'content'
        },new Ext.form.ComboBox({
            id:'file_sel',
            name:'file_sel',
            fieldLabel:'附件',
            store : storefile,//设置数据源
            triggerAction: 'all',//单击触发按钮显示全部数据
            listWidth : 300,//指定列表宽度
            editable : false,//禁止编辑
            width:300,
            loadingText : '正在加载文件信息',//加载数据时显示的提示信息
            displayField:'FName',//定义要显示的字段
            mode: 'remote',//远程模式
            pageSize : 10//分页大小
        }),new Ext.form.Hidden({//隐藏域
            id:'file_id',
            name:'file_id'
        })]
    });

    win = new Ext.Window({
        title: '提交任务',
        width:700,
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
        items: mSubmit,
        buttons: [{
            text: '提交',
            iconCls: 'icon_ok',
            handler: function(){
                var selected = storefile.getAt(Ext.getCmp("file_sel").selectedIndex);
                if(selected!=null){
                    Ext.getCmp("file_id").setValue(selected.data.FId);
                }else{
                    Ext.getCmp("file_id").setValue("");
                }
                win_submit();
            }
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

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

/** 列显示样式过滤 */
function filter(val){
    if(val == "是"){
        return '<span style="color:green;">' + val + '</span>';
    }else if(val == "否"){
        return val;
    }else{
        return '<span style="color:red;">' + val + '</span>';
    }
}

/** 打开提交窗口 */
function showWin(){
    Ext.form.Field.prototype.msgTarget = 'side';
    mSubmit.form.reset();
    mSubmit.isAdd = true;
    win.show();
}

/** 关闭 */
function win_hide(){
    win.close();
}

/** 提交 */
function win_submit(){

    if(mSubmit.form.isValid()){
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
        mSubmit.getForm().submit({
            success: function(){
                grid.store.reload();
                gridForm.form.reset();
                win_hide();
                Ext.Msg.alert('提示','提交成功！');
            },
            failure: function(form,action){
                if(action.failureType =="server"){
                    Ext.Msg.alert('提示',action.result.errors);
                }else{
                    Ext.Msg.alert('提示','提交失败，原因：'+action.failureType);
                }
                mSubmit.form.reset();
            }
        })
    }
}

/** 搜索 */
function searchContent(){
    store.baseParams.conditions = text_search.getValue();
    store.load({
        params : {
            start : 0,
            limit : 20
        }
    });
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}