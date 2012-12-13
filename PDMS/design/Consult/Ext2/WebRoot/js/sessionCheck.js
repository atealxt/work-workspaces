function sessionChcek(){
	Ext.Ajax.request({
	       url: 'system/user!checkLogin.action',
	       params:{},
	       success: function(response, options) {
	       		try{
					var result = Ext.util.JSON.decode(response.responseText);
					if(result.timeout=='no'){
						Ext.MessageBox.alert("系统提示","网络超时，请重新登录！",function(){   
							top.location.href = 'login.jsp';	
						});       			            
					}else{
						SetTimer();
					}	
				}catch(e) {
	                // ignore
	            }                                              
	       },
	       failure : function(response,options) { 
				Ext.MessageBox.alert("系统提示", "网络链接失败",function(){   
					top.location.href = 'login.jsp'; 	
				});
		   }
	});
} 

/*
function ConfirmUpdate(){
  //Ask them to extend
  if(confirm("Your session is about to expire. Press 'OK' to renew your session.")){
    //load server side page if ok
    sessionChcek(); 
  }
}
*/
var timerObj;
function SetTimer(){
  //How long before timeout (should be a few minutes before your server's timeout
  var dblMinutes = 1;
  //set timer to call function to confirm update 
  timerObj = setTimeout("sessionChcek()",1000*60*dblMinutes);
}
     
//start the timer 
//SetTimer();
sessionChcek();

