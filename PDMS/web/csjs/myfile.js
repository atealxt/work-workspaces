var data,recordDefine,sm,grid,gridForm,store,uploadForm,win;

Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    var url = "../file/A0900FileAction.action?mode=" + Ext.get('mode').dom.innerHTML;    
    store = new Ext.data.Store({//配置数据集
        reader: new Ext.data.JsonReader({
            totalProperty: 'results',
            results: "results",
            root : "items",
            fields : [
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
            ]
        }),
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    //定义复选框选择模式变量
    //sm = new Ext.grid.CheckboxSelectionModel();
    sm = new Ext.grid.CheckboxSelectionModel({
        listeners: {
            rowselect: function(sm_temp, row, rec) {
                Ext.getCmp("myfile-form").getForm().loadRecord(rec);
            }
        }
    });

    //创建Grid表格组件
    grid = new Ext.grid.GridPanel({
        //title : 'Ext.grid.CheckboxSelectionModel',
        //applyTo : 'grid-div',
        //width : 700,
        width:'100%',
        //height : 200,
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
            header: "文件名",
            width: 320,
            dataIndex: 'FName'
        },
        {
            header: "类型",
            width: 80,
            dataIndex: 'FType'
        },
        {
            header: "地址",
            width: 440,
            dataIndex: 'FAddr'
        },
        {
            header: "大小",
            width: 50,
            dataIndex: 'FSize'
        },
        {
            header: "上传日期",
            width: 160,
            renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s'),
            dataIndex: 'FDate'
        }
        ]
    });

    //manager mode.
    var buttons =[
    {
        text : '上传新文件',
        iconCls: 'icon_new',
        handler : file_upload
    },
    {
        text : '删除所选文件',
        iconCls: 'icon_del',
        handler : file_del
    }];
    if(Ext.get('mode').dom.innerHTML!=""){
        buttons = [
        {
            text : '删除所选文件',
            iconCls: 'icon_del',
            handler : file_del
        }]
    }
    gridForm = new Ext.FormPanel({
        id: 'myfile-form',
        applyTo :'grid-div',
        frame: true,
        labelAlign: 'left',
        title: '',
        bodyStyle:'padding:5px',
        autoHeight : true,
        autoWidth : true,
        //width: 725,
        //height : 750,
        items: [{
            //title: 'grid',
            items : grid
        },{
            xtype: 'fieldset',
            labelWidth: 90,
            title:'<br>详细信息',
            defaults: {
                width: 300
            },
            defaultType: 'textfield',
            autoHeight: true,
            //border: false,
            items: [{
                id : 'FName',
                name: 'FName',
                fieldLabel: '文件名',
                readOnly : true
            },{
                id : 'FType',
                name: 'FType',
                fieldLabel: '类型',
                readOnly : true
            },{
                id : 'FAddr',
                name: 'FAddr',
                fieldLabel: '地址',
                readOnly : true
            },{
                id : 'FSize',
                name: 'FSize',
                fieldLabel: '大小',
                readOnly : true
            },{
                id : 'FDate',
                name: 'FDate',
                fieldLabel: '上传日期',
                xtype: 'datefield',
                readOnly : true,
                hideTrigger : true //不显示日期按钮
            }],
            buttons:buttons
        }]
    });

    uploadForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        url:'../file/A0902CreateAction.action',
        fileUpload:true,
        defaultType: 'textfield',
        items: [{
            xtype: 'textfield',
            fieldLabel: '文件别名',
            name: 'fName',
            allowBlank: false,
            blankText: '别名不能为空.',
            anchor: '75%'  // anchor width by percentage
        },{
            xtype: 'textfield',
            fieldLabel: '选择文件',
            name: 'UF',
            inputType: 'file',
            allowBlank: false,
            blankText: '文件不能为空.',
            anchor: '75%'  // anchor width by percentage
        }]
    });

    win = new Ext.Window({
        title: '上传文件',
        width: 400,
        height:200,
        minWidth: 300,
        minHeight: 100,
        layout: 'fit',
        plain:true,
        closable:false,
        hideParent:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        shadow:true,
        modal:true,
        animcollapse:true,
        items: uploadForm,
        buttons: [{
            text: '上传',
            iconCls: 'icon_up',
            handler: function() {
                if(uploadForm.form.isValid()){
                    Ext.MessageBox.show({
                        title: '请等待',
                        msg: '上传中...',
                        progressText: 'uploading',
                        width:300,
                        waitConfig: {
                            interval:200
                        },
                        wait:true,
                        closable:false
                    });
                    uploadForm.getForm().submit({
                        success: function(){
                            grid.store.reload();
                            gridForm.form.reset();
                            win.hide();
                            Ext.Msg.alert('提示','上传成功');
                            uploadForm.form.reset();
                        },
                        failure: function(form,action){
                            if(action.failureType =="server"){
                                Ext.Msg.alert('提示',action.result.errors);
                            }else{
                                Ext.Msg.alert('提示','上传失败，原因：'+action.failureType);
                            }
                            uploadForm.form.reset();
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

    //Loading数据，渲染
    grid.render();
    //store.load();

    store.load({
        params:{
            start:0,
            limit:10
        }
    });

//grid.getSelectionModel().selectRow(0);//选择第一行
});

/* 刷新Grid数据 */
function reloadGrid(){
    grid.store.reload();
}

/** 删除文件按钮事件 */
function file_del(param1,param2){
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择文件!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '删除文件： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('FName') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                file_nuke();
            }
        }
    });
};

/** 删除文件 */
function file_nuke(btn){
    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        //title:'提示',
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        progressText: '删除...',
        msg:'正在删除请稍后......'
    });
    Ext.Ajax.request({
        url : '../file/A0901DelAction.action',
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
                Ext.Msg.alert('提示','删除成功！');
            }else{
                Ext.Msg.alert('提示','删除失败！');
            }
        },
        failure : function(response,options){
            msgTip.hide();
            Ext.Msg.alert('提示','删除过程中发生错误！');
        }
    });

}

/** 取得所选文件ID */
function getIdList(){
    var recs = grid.getSelectionModel().getSelections();
    var list = [];
    for(var i = 0 ; i < recs.length ; i++){
        var rec = recs[i];
        list.push(rec.get('FId'))
    }
    return list;
}

/* 上传文件按钮事件 */
function file_upload(param1,param2){
    Ext.form.Field.prototype.msgTarget = 'side';
    uploadForm.form.reset();
    uploadForm.isAdd = true;
    win.setTitle("上传文件");
    win.show();
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}