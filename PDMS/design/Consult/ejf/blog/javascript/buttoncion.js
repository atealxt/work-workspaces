Ext.onReady(showButton);

function showButton(){
	
	 
   
   var pan=new Ext.FormPanel({
     height:200,
     width:600,
   	  title:'aaa',
     renderTo:"hello",
     dis: function (){
      alert("dddddddddddd");
    var check = Ext.getCmp("chkbox"); 
   if(check.checked){
   Ext.getCmp("texbs").readOnly=true;
   }
   },
     
     items:[ {fieldLabel:"联系地址",name:"contactAddress",xtype:"textfield",id:"texbs"},
     {
    xtype:"checkbox",
  	name:"chk",
	fieldLabel:"选择性别",
	boxLabel:"男",
    id:"chkbox",
    listeners:{"check":function(checkbox,Boolean){this.dis}}
	}
     ],
   	   
   });
   


}
  
 