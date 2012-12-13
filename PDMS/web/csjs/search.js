var s_panel,panel,data,recordDefine,reader,store,grid,fields,gridForm,url;

//设置主题皮肤
Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    p_panel = new Ext.form.FormPanel({
        title:'搜索页面说明',
        frame:true,//渲染面板
        autoHeight : true,
        autoWidth : true,
        collapsible : true,
        defaults : {//设置默认属性
            bodyStyle:'padding:3px 3px 3px 3px;font-size:12px;',//background-color:#FFFFFF
            collapsible : true,//允许展开和收缩
            frame : true,
            width:200//autoWidth : true
        },
        bbar:[{
            id:'b_add',
            text : '添加所选条件',
            iconCls: 'icon_search_add',
            handler : addSearch
        },{
            id:'b_ser',
            text : '重置',
            iconCls: 'icon_search_clear',
            handler : condition_clear
        },{
            id:'b_ser',
            text : '搜索',
            iconCls: 'icon_search',
            handler : search
        }],
        items: [
        {
            id : 'ext_condition',
            name: 'ext_condition',
            autoWidth : true,
            contentEl : 'condition'
        }
        ]
    });

    fields = [
    {
        name: 'COl1'
    },
    {
        name: 'COl2'
    },
    {
        name: 'COl3'
    },
    {
        name: 'COl4'
    },
    {
        name: 'COl5'
    },
    {
        name: 'HEader'
    },
    {
        name: 'LInk'
    }
    ];

    reader = new Ext.data.JsonReader({
        totalProperty: 'results',
        results: "results",
        root : "items",
        headers : 'headers',
        fields : fields
    });

    url = '../search/A1100SearchAction.action?'+Ext.get('condition_code').dom.innerHTML;
    //定义数据集对象
    store = new Ext.data.Store({//配置数据集
        reader: reader,
        //data: data
        proxy : new Ext.data.HttpProxy({
            url : url
        })
    });

    //分页工具栏
    var ptb = new Ext.PagingToolbar({
        id : 'ptb',
        store : store,
        pageSize : 10,
        displayInfo : true,
        displayMsg : '第 {0} 条到 {1} 条，一共 {2} 条',
        emptyMsg : "没有记录"
    });

    //创建Grid表格组件
    grid = new Ext.grid.GridPanel({
        title:'搜索结果',
        width:'100%',
        autoHeight : true,
        autoScroll : true,
        frame:true,
        loadMask: true,
        store: store,
        tbar : ptb,
        viewConfig : {
            autoFill : true,
            forceFit : true
        },
        columns: [//配置表格列
        {
            id:'COl1',
            width: 120,
            dataIndex: 'COl1'
        },
        {
            id:'COl2',
            width: 120,
            dataIndex: 'COl2'
        },
        {
            id:'COl3',
            width: 120,
            dataIndex: 'COl3'
        },
        {
            id:'COl4',
            width: 120,
            dataIndex: 'COl4'
        },
        {
            id:'COl5',
            width: 120,
            dataIndex: 'COl5'
        },
        {
            id:'LInk',
            width: 20,
            minWidth: 20,
            dataIndex: 'LInk'
        }
        ]
    });

    panel = new Ext.Panel({
        frame:true,//渲染面板
        autoHeight : true,
        collapsible : true,
        applyTo :'panel',
        items: [
        p_panel,grid
        ]
    });

    if(Ext.get('condition_code').dom.innerHTML!=""){
        search();
        condition_clear();
    }

});

/** 取得HTML控件 */
function $(id){
    return document.getElementById(id);
}

/** 级联菜单类 */
function Dsy(){
    this.Items = {};
}
Dsy.prototype.add = function(id,iArray){
    this.Items[id] = iArray;
}
Dsy.prototype.get = function(id){
    return this.Items[id];
}
Dsy.prototype.Exists = function(id){
    if(typeof(this.Items[id]) == "undefined") {
        return false;
    }
    return true;
}

/** 输入搜索条件事件 */
function change(v){
    $("text1").value="";
    var str="0";
    for(i=0;i<v;i++){
        str+=("_"+($(s[i]).selectedIndex-1));
    }
    var ss=$(s[v]);
    with(ss){
        length = 0;
        options[0]=new Option(opt0[v],opt0[v]);
        if(v && $(s[v-1]).selectedIndex>0 || !v){
            if(dsy.Exists(str)){
                ar = dsy.Items[str];
                for(i=0;i<ar.length;i++){
                    options[length]=new Option(ar[i],ar[i]);
                }
                if(v){
                    options[1].selected = true;
                }
            }
        }
        if(++v<s.length){
            change(v);
        }
        else{
            if($("s1").selectedIndex<1){
                $("text1").disabled=true;
            }
            else if($("s1").selectedIndex==2 && $("s2").selectedIndex==1){
                $("text1").disabled=true;
            }
            else if($("s1").selectedIndex==3 && $("s2").selectedIndex==1){
                $("text1").disabled=true;
            }
            else if($("s1").selectedIndex==3 && $("s2").selectedIndex==2){
                $("text1").disabled=true;
            }
            else{
                $("text1").disabled=false;
            }
        }
        }
}

var dsy = new Dsy();
dsy.add("0",["帖子","用户","任务","项目"]);
dsy.add("0_0",["主题帖","回帖"]);
dsy.add("0_0_0",["发表人用户ID","发表人姓名","主题名","主题内容"]);
dsy.add("0_0_1",["发表人用户ID","发表人姓名","回复内容"]);
dsy.add("0_1",["角色","其他"]);
dsy.add("0_1_0",["系统管理员","职员","项目负责人"]);
dsy.add("0_1_1",["用户ID","用户名","公司IP"]);
dsy.add("0_2",["完成状态","验收确认状态","分配人","接收人","验收人","其他"]);
dsy.add("0_2_0",["已完成","未完成"]);
dsy.add("0_2_1",["已验收","未验收"]);
dsy.add("0_2_2",["用户ID","用户名"]);
dsy.add("0_2_3",["用户ID","用户名"]);
dsy.add("0_2_4",["用户ID","用户名"]);
dsy.add("0_2_5",["任务ID","任务内容"]);
dsy.add("0_3",["项目成员","其他"]);
dsy.add("0_3_0",["用户ID","用户名"]);
dsy.add("0_3_1",["项目ID","项目名","项目简要"]);

var s=["s1","s2","s3"];
var opt0 = ["一级分类","二级分类","三级分类"];

/** 装载级联菜单 */
function setup(){
    for(i=0;i<s.length-1;i++){
        $(s[i]).onchange=new Function("change("+(i+1)+")");
    }
    change(0);
}

/** 增加搜索条件 */
function addSearch(){

    for(i=0;i<s.length;i++){
        if($(s[i]).selectedIndex <=0){
            Ext.MessageBox.show({
                title: '提示',
                msg: '请选择搜索条件',
                buttons: Ext.MessageBox.OK,
                icon: Ext.Msg.WARNING,
                fn:function(){
                    $(s[i]).focus();
                }
            });

            return;
        }
    }

    var text_value = $("text1").value;
    if(!$("text1").disabled && text_value==""){
        Ext.MessageBox.show({
            title: '提示',
            msg: '请输入关键字',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING,
            fn:function(){
                $("text1").focus();
            }
        });

        return;
    }

    var cd = $("condition_code");
    var condition_value = cd.innerHTML;
    var condition_value_temp = "";
    condition_value_temp = "s1" + $("s1").selectedIndex;

    if(cd.innerHTML != "" && condition_value.indexOf(condition_value_temp) == -1){
        Ext.MessageBox.show({
            title: '提示',
            msg: '只能添加一个一级分类条件',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    condition_value_temp = condition_value_temp + "s2" + $("s2").selectedIndex;

    if(cd.innerHTML != "" && condition_value.indexOf(condition_value_temp) == -1 && $("s1").selectedIndex!=3 ){
        Ext.MessageBox.show({
            title: '提示',
            msg: '只能添加一个二级分类条件',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    if(condition_value.indexOf(condition_value_temp) != -1 && $("text1").disabled == true){
        Ext.MessageBox.show({
            title: '提示',
            msg: '已经添加了相关搜索条件',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    condition_value_temp = condition_value_temp + "s3" + $("s3").selectedIndex;

    if(condition_value.indexOf(condition_value_temp) != -1){
        Ext.MessageBox.show({
            title: '提示',
            msg: '不能重复添加相同分类条件',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    var disableChar = ['='];
    for(var i in disableChar){
        if($("text1").value.indexOf(disableChar[i])!=-1){
            Ext.MessageBox.show({
                title: '提示',
                msg: '不能输入特殊字符: ' + disableChar,
                buttons: Ext.MessageBox.OK,
                icon: Ext.Msg.WARNING,
                fn:function(){
                    $("text1").focus();
                }
            });
            return;
        }
    }

    condition_value = condition_value + condition_value_temp;
    condition_value = condition_value + "c" + $("text1").value + "<br>";
    cd.innerHTML = condition_value;

    var cd_zh = $("condition_zh");
    cd_zh.innerHTML = cd_zh.innerHTML
    + $("s1").value + "．"
    + $("s2").value + "．"
    + $("s3").value;
    if(!$("text1").disabled)    {
        cd_zh.innerHTML = cd_zh.innerHTML + " = " + $("text1").value;
    }
    cd_zh.innerHTML = cd_zh.innerHTML + "<br>";
}

/** 清空搜索条件 */
function condition_clear(){
    var cd = $("condition_code");
    cd.innerHTML = "";
    var cd_zh = $("condition_zh");
    cd_zh.innerHTML = "";
}

/** 搜索 */
function search(){
    var condition_param = $("condition_code").innerHTML;
    if(condition_param == ""){
        Ext.MessageBox.show({
            title: '提示',
            msg: '无搜索条件！',
            buttons: Ext.MessageBox.OK,
            icon: Ext.Msg.WARNING
        });
        return;
    }

    //alert(condition_param);
    //search
    url = '../search/A1100SearchAction.action?cc=' + condition_param;
    store.proxy = new Ext.data.HttpProxy({
        url : url
    });
    store.load({
        params:{
            start:0,
            limit:10
        },
        callback :function(){
            //add header
            for(var i = 0;i<reader.jsonData.headers.length;i++){
                grid.getColumnModel().setColumnHeader(i,reader.jsonData.headers[i]);
            }
        }
    });

}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}