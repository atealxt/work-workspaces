var data,recordDefine,sm,grid,gridForm,store,win,i_store,sel_form,status_store,umForm;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    //自定义VType类型，验证日期选择范围
    Ext.apply(Ext.form.VTypes, {
        //验证方法
        dateRange : function(val, field) {

            field.disabledDays = [0,6];
            field.disabledDaysText = '禁止选择该日期';

            if(!field.boxReady){
                return true;
            }
            if(field.dateRange){

                var beginId = field.dateRange.begin;
                this.beginField = Ext.getCmp(beginId);

                var endId = field.dateRange.end;
                this.endField = Ext.getCmp(endId);

                var beginDate = this.beginField.getValue();
                var endDate = this.endField.getValue();
            }

            //            if(!field.boxReady){
            //                return true;
            //            }
            if(beginDate != "" && beginDate < endDate){
                return true;
            }else if(beginDate != "" && endDate==""){
                this.dateRangeText = '如填写开始日期，则结束日期也必须填写';
                return false;
            }else{
                return false;
            }
        },
        //验证提示信息
        dateRangeText : '开始日期必须小于结束日期'
    });

    var fields =  [
    {
        name: 'PId'
    },
    {
        name: 'PName'
    },
    {
        name: 'PMan'
    },
    {
        name: 'PUsrs'
    },
    {
        name: 'PSd',
        type: 'date'
    },
    {
        name: 'PEd',
        type: 'date'
    },
    {
        name: 'PStatus'
    }
    ];

    //创建记录类型，mapping值为字段值对应数组中数据的索引位置
    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : fields
    });

    var url = '../projectm/A0600ProjectMAction.action';
    store = new Ext.data.Store({//配置数据集
        reader: reader,
        //data: data
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    //定义复选框选择模式变量
    sm = new Ext.grid.CheckboxSelectionModel({
        listeners: {
            rowselect: function(sm_temp, row, rec) {
                Ext.getCmp("projm-form").getForm().loadRecord(rec);
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
            pageSize : 10,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
            emptyMsg : "没有记录"
        }),
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        columns: [//配置表格列
        sm,//复选框选择模式中的checkbox组件将会显示在该列
        {
            header: "项目名",
            width: 120,
            dataIndex: 'PName'
        },
        {
            header: "项目负责人",
            width: 220,
            dataIndex: 'PMan'
        },
        {
            header: "项目成员",
            width: 220,
            dataIndex: 'PUsrs'
        },
        {
            header: "开发开始日",
            width: 120,
            renderer: Ext.util.Format.dateRenderer('Y/m/d'),
            dataIndex: 'PSd'
        },
        {
            header: "开发结束日",
            width: 120,
            renderer: Ext.util.Format.dateRenderer('Y/m/d'),
            dataIndex: 'PEd'
        },
        {
            header: "状态",
            width: 50,
            dataIndex: 'PStatus'
        }
        ]
    });

    gridForm = new Ext.FormPanel({
        id: 'projm-form',
        applyTo :'grid-div',
        frame: true,
        labelAlign: 'left',
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        items: [{
            title: '项目信息',
            items : grid
        },{
            xtype: 'fieldset',
            labelWidth: 90,
            title:'<br>详细信息',
            defaults: {
                width: 500,
                readOnly : true
            },
            defaultType: 'textfield',
            autoHeight: true,
            //border: false,
            items: [{
                id : 'PId',
                name: 'PId',
                fieldLabel: '项目ID'
            },{
                id : 'PName',
                name: 'PName',
                fieldLabel: '项目名'
            },{
                id : 'PMan',
                name: 'PMan',
                fieldLabel: '项目负责人'
            },{
                id : 'PUsrs',
                name: 'PUsrs',
                fieldLabel: '项目成员'
            },{
                id : 'PSd',
                name: 'PSd',
                fieldLabel: '开发开始日',
                xtype: 'datefield',
                hideTrigger : true
            },{
                id : 'PEd',
                name: 'PEd',
                fieldLabel: '开发结束日',
                xtype: 'datefield',
                hideTrigger : true
            },{
                id : 'PStatus',
                name: 'PStatus',
                fieldLabel: '状态'
            }]
        }],
        buttonAlign :'right',
        buttons:[
        {
            text : '新建项目',
            id:'b_add',
            iconCls: 'icon_new',
            handler : addp
        },{
            text : '修改项目',
            id:'b_chn',
            iconCls: 'icon_change2',
            handler : change
        },{
            text : '关闭项目',
            id:'b_del',
            iconCls: 'icon_del',
            handler : delp
        }
        ]
    });

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

    store.load({
        params:{
            start:0,
            limit:10
        }
    });

});

/** 修改项目信息 */
function change(){
    if(!isOneSel()){
        return;
    }

    var pName,pMan,status,url,pSd,pEd,pUsrs;
    grid.getSelectionModel().each(function(rec){
        pName = rec.get('PName');
        pMan = rec.get('PMan');
        pSd = rec.get('PSd');
        pEd = rec.get('PEd');
        status = rec.get('PStatus');
        pUsrs = rec.get('PUsrs');
        url = '../projectm/A0601EditAction.action?id=' + rec.get('PId');
    });

    if(status=="关闭"){
        Ext.MessageBox.show({
            title: '提示',
            msg: '已关闭的项目不能编辑!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }
    
    status_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        data : [['开放','1']]
    //data : [['开放','1'],['关闭','2']]
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
            fieldLabel: '项目名',
            id:'pName',
            name: 'pName',
            allowBlank: false,
            value:pName,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '项目负责人',
            id:'pMan',
            name: 'pMan',
            value:pMan,
            readOnly:true,//设置只读
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '项目成员',
            id:'pUsrs',
            name: 'pUsrs',
            value:pUsrs,
            readOnly:true,//设置只读
            anchor: '90%'  // anchor width by percentage
        },
        new Ext.form.DateField({
            id:'pSd',
            format:'Y年m月d日',//显示日期的格式
            anchor: '90%',
            readOnly:true,//设置只读
            value : pSd,//设置默认值
            fieldLabel:'开发开始日',
            dateRange:{
                begin:'pSd',
                end:'pEd'
            },//用于vtype类型dateRange
            vtype:'dateRange'
        }),new Ext.form.DateField({
            id:'pEd',
            format:'Y年m月d日',//显示日期的格式
            anchor: '90%',
            readOnly:true,//设置只读
            value : pEd,//设置默认值
            fieldLabel:'开发结束日',
            dateRange:{
                begin:'pSd',
                end:'pEd'
            },//用于vtype类型dateRange
            vtype:'dateRange'
        }),
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
        title: '修改项目信息',
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
            text: '清空项目负责人',
            type:'d',
            id:'f_man_d',
            iconCls: 'icon_clear',
            handler: f_man
        },
        {
            text: '添加项目负责人',
            type:'i',
            id:'f_man_i',
            iconCls: 'icon_add',
            handler: f_man
        },
        {
            text: '清空项目成员',
            type:'d',
            id:'f_usrs_d',
            iconCls: 'icon_clear',
            handler: f_usrs
        },
        {
            text: '添加项目成员',
            type:'i',
            id:'f_usrs_i',
            iconCls: 'icon_add',
            handler: f_usrs
        }
        ],
        buttons: [        
        {
            text: '提交',
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

/** 新建项目 */
function addp(){

    var pMan,status,url,pSd,pEd;
    grid.getSelectionModel().each(function(rec){
        pMan = rec.get('PMan');
        pSd = rec.get('PSd');
        pEd = rec.get('PEd');
        status = rec.get('PStatus');
    });
    url = '../projectm/A0602AddPrjAction.action';

    status_store = new Ext.data.SimpleStore({//定义组合框中显示的数据源
        fields: ['province', 'post'],
        //data : [['开放','1'],['关闭','2']]
        data : [['开放','1']]
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        //autoWidth:true,
        width:300,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        items: [
        {
            fieldLabel: '项目名',
            id:'pName',
            name: 'pName',
            allowBlank: false,
            anchor: '90%'  // anchor width by percentage
        },{
            fieldLabel: '项目负责人',
            id:'pMan',
            name: 'pMan',
            value:pMan,
            readOnly:true,//设置只读
            anchor: '90%'  // anchor width by percentage
        },
        new Ext.form.DateField({
            id:'pSd',
            format:'Y年m月d日',//显示日期的格式
            anchor: '90%',
            readOnly:true,//设置只读
            value : pSd,//设置默认值
            fieldLabel:'开发开始日',
            disabledDays:[0,6],
            disabledDaysText : '禁止选择该日期'/*
             //暂时注释掉，以结束日来做check
            dateRange:{
                begin:'pSd',
                end:'pEd'
            },//用于vtype类型dateRange
            vtype:'dateRange'*/
        }),new Ext.form.DateField({
            id:'pEd',
            format:'Y年m月d日',//显示日期的格式
            anchor: '90%',
            readOnly:true,//设置只读
            value : pEd,//设置默认值
            fieldLabel:'开发结束日',
            dateRange:{
                begin:'pSd',
                end:'pEd'
            },//用于vtype类型dateRange
            vtype:'dateRange'
        }),
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
            //value:status,
            anchor: '90%',  // anchor width by percentage
            handleHeight : 10//下拉列表中拖动手柄的高度
        })]
    });

    win = new Ext.Window({
        title: '新建项目',
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
            text: '清空项目负责人',
            type:'d',
            id:'f_man_d',
            iconCls: 'icon_clear',
            handler: f_man
        },
        {
            text: '添加项目负责人',
            type:'i',
            id:'f_man_i',
            iconCls: 'icon_add',
            handler: f_man
        }
        ],
        buttons: [        
        {
            text: '提交',
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

/** 关闭项目(按钮事件)  */
function delp(){
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择项目!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '关闭项目： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('PName') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                p_nuke();
            }
        }
    });
}

/** 关闭项目 */
function p_nuke(){
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
        url : '../projectm/A0604DelAction.action',
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
                Ext.Msg.alert('提示',result.errors);
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
        list.push(rec.get('PId'))
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
                grid.store.reload();
                Ext.Msg.alert('提示','提交成功');
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

/** 修改项目负责人 */
function f_man(param){
    if(param.type=='d'){
        Ext.getCmp("pMan").setValue("");
        return;
    }
    createSelWin('../userm/A0500UserMAction.action?type=m','pMan');
}

/** 修改项目成员 */
function f_usrs(param){
    if(param.type=='d'){
        Ext.getCmp("pUsrs").setValue("");
        return;
    }
    createSelWin('../userm/A0500UserMAction.action?type=u','pUsrs');
}

/** 生成并显示选择框 */
function createSelWin(url,control){
    i_store.proxy = new Ext.data.HttpProxy({//读取远程数据的代理
        url : url//远程地址
    });
    i_store.reload();//important!

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

/** 打开窗口 */
function showWin(){
    Ext.form.Field.prototype.msgTarget = 'side';
    umForm.form.reset();
    umForm.isAdd = true;
    win.show();
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}