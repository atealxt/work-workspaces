Ext.onReady(pageload);
function pageload(){
   var pan=new Ext.Panel({
       title:'aaa',
       html:'<div id="griddiv"></div>'
   });
}

var grid=function(){
 return{
   init:function(){
   var hp=new Ext.data.HttpProxy({url:'data.xml'});
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
 var grid1=new Ext.grid.GridPanel({
     renderTo:'griddiv',
     width:600,
     height:200,
     ds:ds,
     viewConfig:{   
           forceFit:true  
     },   
     cm:cm
 })
 grid1.render();
 ds.load();
 }
}
}