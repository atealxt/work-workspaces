Ext.onReady(function(){

 showToast = function(){
        alert("ddd");
    };
	
var task = {
    run: showToast,
    interval: 100000000000 //1 second
    
}
Ext.TaskMgr.start(task);

});