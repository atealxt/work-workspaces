Ext.onReady(loadPage);
function loadPage(){
  var hp=new Ext.data.HttpProxy({url:'data1.xml'});
  var ds=new Ext.data.Store({
      proxy:hp,
      reader:new Ext.data.XmlReader({
          record:'row',
          id:'guid'
      },['createDate','title','type'])
  });
  var cm=new Ext.grid.ColumnModel([
      {header:"发布日期",width:150,dataIndex:'createDate'},
      {header:"标题",width:300,dataIndex:'title'},
      {header:"类型",width:150,dataIndex:'type'}
  ]);
  var grid=new Ext.grid.GridPanel({
      renderTo:'griddiv',
      width:600,
      height:200,
      ds:ds,
      cm:cm
  })
  grid.render();
  ds.load();
}
