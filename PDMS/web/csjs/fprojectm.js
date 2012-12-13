var panel,p_panel,url,win,umForm,data,recordDefine,store,logo_sel_path,grid,text_search;

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

    //项目基本信息
    p_panel = new Ext.form.FormPanel({
        //title:'项目信息',
        frame:true,//渲染面板
        autoHeight : true,
        autoWidth : true,
        collapsible : true,
        defaults : {//设置默认属性
            bodyStyle:'padding:3px 3px 3px 3px;font-size:12px;',//background-color:#FFFFFF
            //collapsible : true,//允许展开和收缩
            frame : true,
            autoWidth : true
        //width:300
        },
        bbar:[{
            id:'bbar_cl',
            text : '修改LOGO',
            iconCls: 'icon_change1',
            handler : changeLogo
        },{
            id:'bbar_ca',
            text : '修改公告',
            iconCls: 'icon_change3',
            handler : changeAn
        }],
        //defaultType: 'textfield',
        items: [
        {
            id : 'logo_id',
            name: 'logo_nm',
            title:'项目LOGO',
            contentEl : 'logo'
        },{
            id : 'an_id',
            name: 'an_nm',
            title:'项目公告',
            contentEl : 'announcement'
        },{
            id : 'prjUsrs_id',
            name: 'prjUsrs_nm',
            title:'项目组员',
            contentEl : 'prjUsrs'
        }
        ]
    });

    panel = new Ext.Panel({
        title:'项目信息管理',
        frame:true,//渲染面板
        autoHeight : true,
        collapsible : true,
        applyTo :'panel',
        items: [
        p_panel
        ]
    })

    //项目任务
    //创建记录类型，mapping值为字段值对应数组中数据的索引位置
    var fields_Mis = [
    {
        name: 'MId'//任务ID
    },
    {
        name: 'MName'//任务内容
    },
    {
        name: 'MComtime',//完成日限
        type: 'date'
    },
    {
        name: 'MConfirm'//所剩确认时间
    },
    {
        name: 'MD'//分配状态
    },
    {
        name: 'MRece'//接收人
    },
    {
        name: 'MDc'//分配确认状态
    },
    {
        name: 'MCs'//完成状态
    },
    {
        name: 'MIs'//验收状态
    },
    {
        name: 'MInspe'//验收人
    },
    {
        name: 'MDist'//分配人
    }
    ,
    {
        name: 'MSubmit'//提交内容
    }
    ];

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        fields : fields_Mis
    });

    url = '../fprojectm/A0706MisInfAction.action?pid='+Ext.get('id').dom.innerHTML;
    //定义数据集对象
    store = new Ext.data.Store({//配置数据集
        reader: reader,
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
            pageSize : 10,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
            //displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条 <input type="checkbox" id="chkCond">不显示历史任务',
            emptyMsg : "没有记录"
        }),
        tbar:[
        {
            text : '创建任务',
            id:'b_m_add',
            iconCls: 'icon_new',
            handler:addMission
        },{
            text : '编辑任务',
            id:'b_m_edit',
            iconCls: 'icon_change2',
            handler:editMission
        },{
            text : '分配任务',
            id:'b_m_dist',
            iconCls: 'icon_user',
            handler:distMission
        },{
            text : '验收任务',
            id:'b_m_insp',
            iconCls: 'icon_down',
            handler:inspMission
        },{
            text : '删除任务',
            id:'b_m_del',
            iconCls: 'icon_del',
            handler:delMission
        }, '  ',text_search,
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
            width: 150,
            dataIndex: 'MName'
        },
        {
            header: "完成日限",
            width: 80,
            renderer: Ext.util.Format.dateRenderer('Y/m/d'),
            dataIndex: 'MComtime'
        },
        {
            header: "所剩确认时间",
            width: 90,
            dataIndex: 'MConfirm'
        },
        {
            header: "分配状态",
            width: 70,
            renderer: filter,
            dataIndex: 'MD'
        },
        {
            header: "接收人",
            width: 90,
            dataIndex: 'MRece'
        },
        {
            header: "确认状态",
            width: 70,
            renderer: filter,
            dataIndex: 'MDc'
        },
        {
            header: "完成状态",
            width: 70,
            renderer: filter,
            dataIndex: 'MCs'
        },
        {
            header: "验收状态",
            width: 70,
            renderer: filter,
            dataIndex: 'MIs'
        },
        {
            header: "验收人",
            width: 70,
            dataIndex: 'MInspe'
        },
        {
            header: "分配人",
            width: 70,
            dataIndex: 'MDist'
        }
        ]
    });

    new Ext.Panel({
        title:'任务管理',
        frame:true,//渲染面板
        autoHeight : true,
        collapsible : true,
        applyTo :'grid-div-mis',
        items: [
        grid
        ]
    });

    store.load({
        params:{
            start:0,
            limit:10
        }
    });

});

/** 修改LOGO */
function changeLogo(){

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

    var urlfile = '../fprojectm/A0705EditLGAction.action?id=' + Ext.get("id").dom.innerHTML;
    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 120,
        autoWidth:true,
        autoHeight:true,
        url:urlfile,
        items: [
        {
            id : 'logo_show',
            name: 'logo_show',
            html : "<center>"+Ext.get("logo").dom.innerHTML+"</center>"
        },{
            id : 'logo_url',
            name: 'logo_url',
            xtype:'textfield',
            fieldLabel: 'LOGO URL',
            width:300,
            value : Ext.get("logo").dom.innerHTML.split("\"")[1].replace("../","").replace("upload/","")
        //value : sTemp
        },new Ext.form.ComboBox({
            id:'logo_sel',
            name:'logo_sel',
            fieldLabel:'<font color="red">或</font>从<i>我的文件</i>选择',
            store : storefile,//设置数据源
            //allQuery:'',//查询全部信息的查询字符串
            triggerAction: 'all',//单击触发按钮显示全部数据
            listWidth : 300,//指定列表宽度
            editable : false,//禁止编辑
            width:300,
            loadingText : '正在加载文件信息',//加载数据时显示的提示信息
            displayField:'FName',//定义要显示的字段
            mode: 'remote',//远程模式
            //mode: 'local',//远程模式
            pageSize : 10//分页大小
        }),new Ext.form.Hidden({//隐藏域
            id:'file_id',
            name:'file_id'
        })]
    });

    win = new Ext.Window({
        title: '修改LOGO',
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
            handler: function(){

                if(umForm.form.isValid() &&
                    !Ext.getCmp("logo_sel").getValue().trim()=="" &&
                    !Ext.getCmp("logo_url").getValue().trim()==""){
                    Ext.MessageBox.show({
                        title: '请确认',
                        msg: '<i>URL方式</i> 和 <i>文件选择方式</i> 只能使用一个，请删除其中一种后重试',
                        buttons: Ext.MessageBox.OK,
                        icon: Ext.Msg.WARNING
                    });
                    return;
                }
                else{

                    var selected = storefile.getAt(Ext.getCmp("logo_sel").selectedIndex);
                    if(selected != null){
                        //Ext.getCmp("file_id").value = selected.data.FId;
                        Ext.getCmp("file_id").setValue(selected.data.FId);
                        logo_sel_path = selected.data.FAddr;
                    }else{
                        //Ext.getCmp("file_id").value = "";
                        Ext.getCmp("file_id").setValue("");
                        logo_sel_path="";
                    }

                    win_submit(1);
                }
            }
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 修改公告 */
function changeAn(){

    url = '../fprojectm/A0705EditANAction.action?id=' + Ext.get("id").dom.innerHTML;
    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        //autoWidth:true,
        width:600,
        autoHeight:true,
        url:url,
        defaultType: 'textfield',
        items: [
        {
            xtype: 'htmleditor',
            fieldLabel: '公告内容',
            id: 't_content',
            name: 't_content',
            value : Ext.get("announcement").dom.innerHTML
        }]
    });

    win = new Ext.Window({
        title: '修改项目公告',
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
            handler: function(){
                win_submit(2);
            }
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 创建任务 */
function addMission(){

    var content="";
    var urlmission="../fprojectm/A0702AddMAction.action?pId=" + Ext.get('id').dom.innerHTML;
    grid.getSelectionModel().each(function(rec){
        content = rec.get('MName');
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 90,
        //autoWidth:true,
        width:610,
        autoHeight:true,
        url:urlmission,
        defaultType: 'textfield',
        items: [
        {
            xtype: 'htmleditor',
            fieldLabel: '任务内容',
            id: 't_content',
            name: 't_content',
            allowBlank: false,
            value : content
        },
        new Ext.form.DateField({
            id:'completlimit',
            name:'completlimit',
            format:'Y年m月d日',//显示日期的格式
            anchor: '40%',
            readOnly:true,//设置只读
            fieldLabel:'完成日限',
            allowBlank: false,
            minValue : new Date(),//允许选择的最小日期
            disabledDays:[0,6],
            disabledDaysText : '禁止选择该日期'
        }),
        new Ext.form.DateField({
            id:'confirmlimit',
            name:'confirmlimit',
            format:'Y年m月d日',//显示日期的格式
            anchor: '40%',
            readOnly:true,//设置只读
            minValue : new Date(),//允许选择的最小日期
            fieldLabel:'确认截至日期',
            disabledDays:[0,6],
            allowBlank: false,
            disabledDaysText : '禁止选择该日期'
        })]
    });

    win = new Ext.Window({
        title: '新建任务',
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

/** 编辑任务(事件) */
function editMission(){
    if(!isOneSel()){
        return;
    }

    var validate = true;
    grid.getSelectionModel().each(function(rec){
        if(rec.get('MDc') == "是"){
            validate = false;
            return;
        }
    });
    if(!validate){
        Ext.MessageBox.show({
            title: '提示',
            msg: '所选任务不能编辑!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    editM();

}
/** 编辑任务 */
function editM(){

    var content="",completelimit;
    var urlmission="../fprojectm/A0701EditMAction.action?mId=";
    grid.getSelectionModel().each(function(rec){
        urlmission += rec.get('MId');
        content = rec.get('MName');
        completelimit = rec.get('MComtime');
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 90,
        //autoWidth:true,
        width:610,
        autoHeight:true,
        url:urlmission,
        defaultType: 'textfield',
        items: [
        {
            xtype: 'htmleditor',
            fieldLabel: '任务内容',
            id: 't_content',
            name: 't_content',
            allowBlank: false,
            value : content
        },
        new Ext.form.DateField({
            id:'completlimit',
            name:'completlimit',
            format:'Y年m月d日',//显示日期的格式
            anchor: '40%',
            readOnly:true,//设置只读
            fieldLabel:'完成日限',
            allowBlank: false,
            value: completelimit,
            minValue : new Date(),//允许选择的最小日期
            disabledDays:[0,6],
            disabledDaysText : '禁止选择该日期'
        }),
        new Ext.form.DateField({
            id:'confirmlimit',
            name:'confirmlimit',
            format:'Y年m月d日',//显示日期的格式
            anchor: '40%',
            readOnly:true,//设置只读
            minValue : new Date(),//允许选择的最小日期
            fieldLabel:'确认截至日期',
            disabledDays:[0,6],
            allowBlank: false,
            disabledDaysText : '禁止选择该日期'
        })]
    });

    win = new Ext.Window({
        title: '编辑任务',
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

/** 分配任务(事件) */
function distMission(){

    if(!isOneSel()){
        return;
    }

    var validate = true;
    grid.getSelectionModel().each(function(rec){
        //已接收任务，未提交
        if(rec.get('MDc') == "是" &&rec.get('MCs') == "否"){
            validate = false;
            return;
        }
        //已完成任务，不是（未通过）
        if(rec.get('MCs') == "是" &&rec.get('MIs') != "未通过"){
            validate = false;
            return;
        }
    });
    if(!validate){
        Ext.MessageBox.show({
            title: '提示',
            msg: '所选任务不能分配!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    //把任务分配并初始化
    distM();
}
/** 分配任务 */
function distM(){

    var storeusr = new Ext.data.Store({//配置数据集
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
        }),
        proxy : new Ext.data.HttpProxy({
            url : '../userm/A0500UserMAction.action?pId=' +  Ext.get("id").dom.innerHTML
        })
    });

    var urlusr = '../fprojectm/A0707DistMAction.action?mId=';
    grid.getSelectionModel().each(function(rec){
        urlusr += rec.get('MId');
    });

    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 130,
        autoWidth:true,
        autoHeight:true,
        url:urlusr,
        items: [
        new Ext.form.ComboBox({
            id:'usr_sel',
            name:'usr_sel',
            fieldLabel:'选择要分配给的用户',
            store : storeusr,//设置数据源
            triggerAction: 'all',//单击触发按钮显示全部数据
            listWidth : 300,//指定列表宽度
            editable : false,//禁止编辑
            width:300,
            loadingText : '正在加载信息',//加载数据时显示的提示信息
            displayField:'UName',//定义要显示的字段
            mode: 'remote'//,//远程模式
        //暂定不分页
        //pageSize : 10//分页大小

        }),new Ext.form.Hidden({//隐藏域
            id:'u_id',
            name:'u_id'
        })]
    });

    win = new Ext.Window({
        title: '分配任务',
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
            handler: function(){
                if(umForm.form.isValid()){
                    var selected = storeusr.getAt(Ext.getCmp("usr_sel").selectedIndex);
                    if(selected!=null){
                        Ext.getCmp("u_id").setValue(selected.data.UId);
                    }else{
                        Ext.getCmp("u_id").setValue("");
                    }
                    win_submit();
                }
            }
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}

/** 验收任务(事件) */
function inspMission(){
    if(!isOneSel()){
        return;
    }

    var validate = false;
    grid.getSelectionModel().each(function(rec){
        //已接收任务，审核状态不是OK
        if(rec.get('MCs') == "是" && rec.get('MIs') != "是"){
            validate = true;
            return;
        }
    });
    if(!validate){
        Ext.MessageBox.show({
            title: '提示',
            msg: '所选任务不能验收!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var submitValue;
    var mId;
    var urlins = '../fprojectm/A0709InspMAction.action?mId=';
    grid.getSelectionModel().each(function(rec){
        mId = rec.get('MId');
        urlins += rec.get('MId');
        submitValue = rec.get('MSubmit');
    });
    umForm = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 130,
        width:500,
        autoHeight:true,
        url:urlins,
        items: [
        {
            html : submitValue
        },new Ext.form.Hidden({//隐藏域
            id:'passFlg',
            name:'passFlg'
        })]
    });

    win = new Ext.Window({
        title: '验收任务',
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
            text: '通过',
            passFlg:true,
            iconCls: 'icon_pass',
            handler : inspM
        },{
            text: '不通过',
            passFlg:false,
            iconCls: 'icon_nopass',
            handler : inspM
        },{
            text: '下载附件',
            mId : mId,
            iconCls: 'icon_download',
            handler : downloadFile
        },{
            text: '关闭',
            iconCls: 'icon_close',
            handler : win_hide
        }]
    });

    showWin();
}
/** 验收任务 */
function inspM(param){
    if(umForm.form.isValid()){
        Ext.getCmp("passFlg").setValue(param.passFlg);
        win_submit();
    }
}
/** 查看附件 */
function downloadFile(param){
    window.open("../file/A0903GetFAction.action?mId=" + param.mId);
}

/** 删除任务(事件) */
function delMission(){
    if(grid.getSelectionModel().selections.length==0){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请选择任务!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var validate = true;
    grid.getSelectionModel().each(function(rec){
        //已接收任务
        if(rec.get('MDc') == "是"){
            validate = false;
            return;
        }
    });
    if(!validate){
        Ext.MessageBox.show({
            title: '提示',
            msg: '所选任务不能删除!',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var msg = '删除任务： <br>';
    grid.getSelectionModel().each(function(rec){
        msg = msg + rec.get('MName') + '<br>';
    });
    Ext.MessageBox.show({
        title: '请确认',
        msg: msg,
        buttons: Ext.MessageBox.YESNO,
        icon: Ext.Msg.QUESTION,
        fn: function(btn){
            if (btn == "yes"){
                delM();
            }
        }
    });
}
/** 删除任务 */
function delM(){
    var idList = getIdList();
    var ids = idList.join('-');
    var msgTip = Ext.MessageBox.show({
        title: '请等待',
        width : 300,
        wait:true,
        waitConfig: {
            interval:200
        },
        progressText: '删除...',
        msg:'正在删除请稍后......'
    });
    Ext.Ajax.request({
        url : '../fprojectm/A0708DelMAction.action',
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
                umForm.form.reset();
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

/* 刷新Grid数据 */
function reloadGrid(){
    grid.store.reload();
}

/**
 * 提交
 * @param type //1修改Logo 2修改公告
 */
function win_submit(type){
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
                if(type == 1){
                    var orgValue = umForm.items.get(1).getValue();
                    if(orgValue == null || orgValue ==""){
                        orgValue = logo_sel_path;
                    }
                    var strTemp = "<img src=\"../upload/";
                    strTemp += orgValue;
                    strTemp += "\">";
                    if(orgValue.substr(0,4) != "http"){
                        Ext.get("logo").dom.innerHTML = strTemp;
                    }
                    else{
                        Ext.get("logo").dom.innerHTML = umForm.items.get(1).getValue();
                    }
                }
                else if(type == 2){
                    //Ext.getCmp("bbar_ca").setValue(umForm.items.get(1).getValue());
                    Ext.get("announcement").dom.innerHTML = umForm.items.get(0).getValue();
                }
                reloadGrid();
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

/** 设置按钮可用否 */
function setDisable(cmp,flg){
    var vTemp = Ext.getCmp(cmp);
    if(vTemp!=null){
        vTemp.setDisabled(flg);
    }
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

/** 搜索 */
function searchContent(){
    if(text_search.getValue().replace(" ","")==""){
        //return;
    }
    store.baseParams.conditions = text_search.getValue();
	store.load({params : {start : 0,limit : 10}});
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}
