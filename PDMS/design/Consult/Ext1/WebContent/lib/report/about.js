Ext.onReady(function(){
	Ext.menu.RangeMenu.prototype.icons = {
	  gt: 'Images/greater_then.png', 
	  lt: 'Images/less_then.png',
	  eq: 'Images/equals.png'
	};
	Ext.grid.filter.StringFilter.prototype.icon = 'Images/find.png';
    

	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	  
	var ds = new Ext.data.JsonStore({
    id: 'id',
	url:"BaseData.do"+'?cmd=Cooperate&statecode=100',    
	root:'rows',
	totalProperty:'totalCount',
    fields: [
      {name:'id'}, 
      {name:'instructions_no'},
      {name:'unit_name'},
      {name:'category_name'},
	  {name:'category_explain'},
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
		{type: 'string',  dataIndex: 'category_explain'},
	    {type: 'string',  dataIndex: 'request_title'},
	    {type: 'string',  dataIndex: 'request_context'},
	    {type: 'date',  dataIndex: 'developer_confirm_time'},
	    {type: 'date',  dataIndex: 'require_completion_time'},
	    {type: 'date',  dataIndex: 'contract_time'},
	    {type: 'string',  dataIndex: 'action_name'}
	]});
	
	var cm = new Ext.grid.ColumnModel([
       new Ext.grid.RowNumberer(),
	  {dataIndex: "instructions_no", header: "项目编号", id: "instructions_no"},
	  {dataIndex: "unit_name", header: "部门名称"},
	  {dataIndex: "category_name", header: "项目类型"},
	  {dataIndex: "category_explain", header: "类型说明"},
	  {dataIndex: "request_title", header: "项目标题"},
	  {dataIndex: "request_context", header: "项目内容"},
	  {dataIndex: "require_completion_time", header: "完成时间"},
	  {dataIndex: "developer_confirm_time", header: "提报时间"},
	  {dataIndex: "contract_time",header: "发包时间"}, 
	  {dataIndex: "action_name", header: "审批状态"}
	]);
	cm.defaultSortable = true;
	var grid = new Ext.grid.GridPanel({
	  title: '需要配合的项目',
	  ds: ds,
	  cm: cm,
	  enableColLock: false,
	  loadMask: true,
	  plugins: filters,
	  height:600,
	  bodyStyle:'width:99%',
	  el: 'TipsGrid',
    autoExpandColumn: 'instructions_no',
  	  bbar: new Ext.PagingToolbar({
	    store: ds,
	    pageSize: 15,
	    plugins: filters
	  })
	});
	grid.render();
	ds.load({params:{start: 0, limit: 15}});
});