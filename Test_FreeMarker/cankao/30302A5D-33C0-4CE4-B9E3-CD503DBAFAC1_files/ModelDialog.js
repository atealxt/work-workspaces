var my_form=document.getElementById('MzForm_mz_i');
var lockwin=document.getElementById('lockwindow');
var titlebar=document.getElementById('MzFormCaptionText_mz_i');
var content=document.getElementById('MzFormContent_mz_i');
var bglayer=document.getElementById('MzFormBgLayer_mz_i');
var draglayer=document.getElementById('MzBehaviorDragLayer');	
function onclosehandler(e){
    (window.event||e).cancelBubble=true;
    closeWindow();
}
function modifyWindow(opts){
	if(opts['url']){
        content.getElementsByTagName("IFRAME")[0].style.display="block";
	    content.getElementsByTagName("DIV")[0].style.display="none";
        try{content.getElementsByTagName("IFRAME")[0].src=opts['url'];}catch(e){}
	}
	if(opts['html']){
	    content.getElementsByTagName("IFRAME")[0].style.display="none";
	    content.getElementsByTagName("DIV")[0].style.display="block";
	    content.getElementsByTagName("DIV")[0].innerHTML=opts['html'];
	 }
}
window.preCloseWindow=function(){};
window.postCloseWindow=function(){};
function closeWindow(){
    preCloseWindow();
	my_form.style.display="none";	
	lockwin.style.display="none";
	try{content.getElementsByTagName("IFRAME")[0].src=""}catch(ex){};   
	postCloseWindow();
}
function showWindow(opts){
	my_form.style.display="";	
	if(opts['title'])
		titlebar.innerHTML=opts['title'];	    
	var width=600;
	var height=400;
	if(opts['width'])
		width=parseInt(opts['width']);
	bglayer.style.width=""+width+"px";
	my_form.style.width=bglayer.style.width;
	content.style.width=""+(width-32)+"px";
	draglayer.style.width=bglayer.style.width;	
	if(opts['height'])
		height=parseInt(opts['height']);
	bglayer.style.height=""+height+"px";
	my_form.style.height=bglayer.style.height;
	content.style.height=""+(height-60)+"px";
	draglayer.style.height=bglayer.style.height;		
	lockwin.style.display="";	
	var l=GetScrollXY().x+(document.body.clientWidth-parseInt(width))/2.0;
	var t=GetScrollXY().y+(document.body.clientHeight-parseInt(height))/2.0;	
	draglayer.style.left=my_form.style.left=""+l+"px";
	draglayer.style.top=my_form.style.top=""+t+"px";
	lockwin.style.left=""+GetScrollXY().x+"px";
	lockwin.style.top=""+GetScrollXY().y+"px";			
    if(opts['url']){
      var frameContent=content.getElementsByTagName("IFRAME")[0];
      frameContent.style.height=""+(height-60)+"px";
	  content.getElementsByTagName("IFRAME")[0].style.display="block";
      content.getElementsByTagName("DIV")[0].style.display="none";
      try{content.getElementsByTagName("IFRAME")[0].src=opts['url'];}catch(ex){}
    } 	
	if(opts['html']){
	    content.getElementsByTagName("IFRAME")[0].style.display="none";
	    content.getElementsByTagName("DIV")[0].style.display="block";
	    content.getElementsByTagName("DIV")[0].innerHTML=opts['html'];
	 }
}

function resetHeight(height){
    height+=60;
	bglayer.style.height=""+height+"px";
	my_form.style.height=bglayer.style.height;
	draglayer.style.height=bglayer.style.height;	
}

var x,y;
function mousedownhandler(e){
var pos=getEventXY(e);
x=pos.x;
y=pos.y;
 draglayer.style.display="";
 if(draglayer.setCapture) draglayer.setCapture(); else
    if(window.captureEvents){window.captureEvents(Event.MOUSEMOVE|Event.MouseUp)};
}

function mousemovehandler(e){
 var offsetx=getEventXY(e).x-x;
 var offsety=getEventXY(e).y-y;
 var left=parseInt(my_form.style.left);
 var top=parseInt(my_form.style.top);
 if(top+offsety<0)
 offsety=-top;
 if(left+offsetx<0)
 offsetx=-left;
 if(top+offsety>parseInt(document.body.clientHeight)+GetScrollXY().y-parseInt(my_form.offsetHeight)){
 offsety=parseInt(document.body.clientHeight)+GetScrollXY().y-parseInt(my_form.offsetHeight)-top;
 }
 if(left+offsetx>parseInt(document.body.clientWidth)-parseInt(my_form.offsetWidth))
 offsetx=parseInt(document.body.clientWidth)-parseInt(my_form.offsetWidth)-left;
 draglayer.style.left=""+(left+offsetx)+"px";
 draglayer.style.top=""+(top+offsety)+"px";
}

function mouseuphandler(e){
 my_form.style.left=draglayer.style.left;
 my_form.style.top=draglayer.style.top;
 draglayer.style.display="none";
 if(draglayer.releaseCapture) draglayer.releaseCapture(); else
 if(window.releaseEvents){ window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);}
}

function closeDialog(needRefresh){
	closeWindow();
	if(needRefresh)
    location.href=location.href;;
}

