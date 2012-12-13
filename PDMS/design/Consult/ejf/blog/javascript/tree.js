var comboxWithTree = new Ext.form.ComboBox({   
        store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
        editable:false,   
        mode: 'local',   
        triggerAction:'all',   
        maxHeight: 200,   
        tpl: "<tpl for='.'><div style='height:200px'><div id='tree'></div></div></tpl>",   
        selectedClass:'',   
        onSelect:Ext.emptyFn   
    });   
    var tree = new Ext.tree.TreePanel({   
        loader: new Ext.tree.DWRTreeLoader({dwrCall:Tmplt.getTmpltTree}),   
        border:false,   
         root:new Ext.tree.AsyncTreeNode({text: '模板根目录',id:'0'})   
      });   
      tree.on('click',function(node){   
          comboxWithTree.setValue(node.text);   
          comboxWithTree.collapse();   
      });   
    comboxWithTree.on('expand',function(){   
        tree.render('tree');   
      });   
    comboxWithTree.render('comboxWithTree'); 
    
    
    
var comboxWithPanel = new Ext.form.ComboBox({   
    store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
    editable:false,   
    mode: 'local',   
    triggerAction:'all',   
    maxHeight: 200,   
    tpl: '<div style="height:200px"><div id="panel"></div></div>',   
    selectedClass:'',   
    onSelect:Ext.emptyFn   
});   
comboxWithPanel.render('comboxWithPanel');   
var tree2 = new Ext.tree.TreePanel({   
    loader: new Ext.tree.DWRTreeLoader({dwrCall:Tmplt.getTmpltTree}),   
    border:false,   
    autoScroll:true,   
    root:new Ext.tree.AsyncTreeNode({text: '模板根目录',id:'0'})   
    });   
var border = new Ext.Panel({   
    title:'面板title',   
    layout:'fit',   
    border:false,   
    height :200,   
    tbar:[{text:'确定一'},'-',new Ext.form.TextField({id: 'paramCnName',width:60}),{text:'查找一'}],   
    bbar:[{text:'确定二'},'-',new Ext.form.TextField({id: 'aa',width:60}),{text:'查找二'}],   
    items: tree2   
    });   
comboxWithPanel.on('expand',function(){   
    border.render('panel');   
    });  
