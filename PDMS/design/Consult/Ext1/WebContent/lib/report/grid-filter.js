Ext.onReady(function(){
	
	//======================部门名称数据源==========================
	var unitStore=new Ext.data.JsonStore({
		id:"Id",
		url: "BaseData.do"+'?cmd=List',
		root:'rows',
		totalProperty:"totalCount",
		remoteSort:true,
		fields:['unit_code','unit_name']
	});
	unitStore.load();
	
	//======================项目类型 数据源==========================
	var projectStore=new Ext.data.JsonStore({
		id:"Id",
		url: "CollaborationCategory.do"+'?cmd=List',
		root:'rows',
		totalProperty:'totalCount',
		remoteSort:true,
		fields:['category_code','category_name']
	});
	projectStore.load();	
	
//	var myNewRecord=new TopicRecord({
//		category_code:'all',
//		category_name:'全部'
//	});
//	
//	projectStore.add(myNewRecord);
	
	
	
	
	Ext.menu.RangeMenu.prototype.icons = {
	  gt: 'Images/greater_then.png', 
	  lt: 'Images/less_then.png',
	  eq: 'Images/equals.png'
	};
	Ext.grid.filter.StringFilter.prototype.icon = 'Images/find.png';
    

	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	  
	var ds = new Ext.data.JsonStore({
    id: 'id',
	url:'ProjectInfoSearch.do?cmd=List',    
	root:'rows',
	totalProperty:'totalCount',
    fields: [
      {name:'id'}, 
      {name:'instructions_no'},
      {name:'unit_name'},
      {name:'category_name'},
      {name:'request_title'},
      {name:'request_context'},
      {name:'developer_confirm_time'},
      {name:'require_completion_time'},
      {name:'contract_time'},
      {name:'action_name'}, 
      {name:'state'}, 
      {name:'remark'}, 
      {name:'annex'}
    ],
	  sortInfo: {field: 'instructions_no', direction: 'ASC'},
	  remoteSort: true
	});
  
	var filters = new Ext.grid.GridFilters({
	  filters:[
	    {type: 'string',  dataIndex: 'instructions_no'},
	    {type: 'string',  dataIndex: 'unit_name'},
	    {type: 'string',  dataIndex: 'category_name'},
	    {type: 'string',  dataIndex: 'request_title'},
	    {type: 'string',  dataIndex: 'request_context'},
	    {type: 'date',  dataIndex: 'developer_confirm_time'},
	    {type: 'date',  dataIndex: 'require_completion_time'},
	    {type: 'date',  dataIndex: 'contract_time'},
	    {type: 'string',  dataIndex: 'action_name'}
//	    {
//	      type: 'list',  
//	      dataIndex: 'size', 
//	      options: ['small', 'medium', 'large', 'extra large'],
//	      phpMode: true
//	    },
//	    {type: 'boolean', dataIndex: 'visible'}
	]});
	
	var cm = new Ext.grid.ColumnModel([
       new Ext.grid.RowNumberer(),
	  {dataIndex: "instructions_no", header: "项目编号", id: "instructions_no"},
	  {dataIndex: "unit_name", header: "部门名称"},
	  {dataIndex: "category_name", header: "项目类型"},
	  {dataIndex: "request_title",width: 300, header: "项目标题"},
	  {dataIndex: "request_context", header: "项目内容"},
	  {dataIndex: "require_completion_time", header: "完成时间"},
	  {dataIndex: "developer_confirm_time", header: "提报时间"},
	  {dataIndex: "contract_time",header: "发包时间"}, 
	  {dataIndex: "action_name", header: "审批状态"}
	]);
	cm.defaultSortable = true;
	
	var grid = new Ext.grid.GridPanel({
	  id: 'example',
	  title: '项目进度查询',
	  ds: ds,
	  cm: cm,
	  enableColLock: false,
	  loadMask: true,
	  plugins: filters,
	  height:600,
	  bodyStyle:'width:100%',
	  el: 'grid-example',
    autoExpandColumn: 'instructions_no',
  	  bbar: new Ext.PagingToolbar({
	    store: ds,
	    pageSize: 15,
	    plugins: filters
	  })
	});
	grid.render();
	
	ds.load({params:{start: 0, limit: 15}});

//项目类型下拉列表
	var comboCa=new Ext.form.ComboBox({
		    emptyText:'选择类型...',
			store:projectStore,
			name:'category_name',
			hiddenName:'category_code',
			valueField:'category_code',
			displayField:'category_name',
			fieldLabel:'项目类型',
			typeAhead:true,
			mode:'local',
			triggerAction:"all",
			selectOnFocus:true
		})
//		comboCa.store.on('load', function(my, record, options) {
//			Ext.MessageBox.alert("aa",record[0].get('category_code'));
//		    comboCa.setValue(record[0].get('category_code')); //设置第一个为默认值
//		}); 
	//====================================================================//
	
	//搜索
    var search = new Ext.FormPanel({
        labelWidth: 60, 
        frame:true,
        title: '协作查询',
        bodyStyle:'padding:5px 5px 0',
        width: 600,
//        defaults: {width: 230},
//        defaultType: 'textfield',
        items:[
{
	xtype:'fieldset',
	title:'基本查询',
	autoHeight:true,
	items:[{
		layout:'column',
        border:false,
        defaults:{border:false},
        items:[{
            	columnWidth:.5,
                layout:'form',
                defaultType:'textfield',
//                defaults:{width:120},
                items:[{xtype:"hidden",name:"id"},
						new Ext.form.ComboBox({
					    emptyText:'选择部门...',							
						store:unitStore,
						hiddenName:'unit_code',
						valueField:'unit_code',
						displayField:'unit_name',
						fieldLabel:'协作部门',
						typeAhead:true,
						mode:'local',
						triggerAction:'all',
						selectOnFocus:true
					})							
				]},{
            	columnWidth:.5,
                layout:'form',
                defaultType:'textfield',
//                defaults:{width:104},
                items:[comboCa]
            }]
	}]
}               
       ],
	buttons: [{
            text: '查询',
	    handler: function() {
		ds.load({
		    params: {
			unit_code: document.getElementById('unit_code').value,
			category_code: document.getElementById('category_code').value
		    }});
	    }
        }]
    });

    search.render('search_form');
    grid.render('search_results');	
});