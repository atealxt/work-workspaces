//page load init
function init(){
    
    //alert("init");
    
    //make submits enabled.
    var inputs = document.getElementsByTagName("input");
    var iLoop=0;
    for(iLoop=0;iLoop<inputs.length;iLoop++){
        if(inputs[iLoop].type=="submit") inputs[iLoop].disabled = false;
    }

}

//before submit
function beforSubmit(control,str){
    control.value = str;    
    
    saveLocationUrl();        
}

//save url
function saveLocationUrl(){
    document.getElementById("urlLocation").value = window.location.toString();
}

function $(objid)
{
  return document.getElementById(objid);
}

function pageChk(evt){
    if( evt.keyCode == 13 ){
        var page = $("text1").value;
        var patrn=/^[0-9]$/;
        if (!patrn.exec(page)) {
            return false;
        }               
        return true;
    }    
    return false;
}

function replyChk(evt){
	if($(evt).value.replace(/(^\s*)|(\s*$)/g, "") == ""){
		alert("Please input content.");
		return false;
	}
	return true;
}

//usercenter - face choice
var winIMG;
function selectIMG(){
    if(!winIMG){               	
        var pics = "";
        pics += " <img src='../picture/default/face/apple.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/asparagus.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/banana.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/broccoli.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/cantaloupe.jpg' onclick='saveIMG(this.src)'><br/> ";
        pics += " <img src='../picture/default/face/carrot.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/corn.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/grapefruit.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/grapes.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/kiwi.jpg' onclick='saveIMG(this.src)'><br/> ";
        pics += " <img src='../picture/default/face/onion.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/peach.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/pear.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/pepper.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/pickle.jpg' onclick='saveIMG(this.src)'><br/> ";
        pics += " <img src='../picture/default/face/pineapple.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/raspberry.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/strawberry.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/tomato.jpg' onclick='saveIMG(this.src)'> ";
        pics += " <img src='../picture/default/face/watermelon.jpg' onclick='saveIMG(this.src)'> ";  

        winIMG=new Ext.Window({title:"face select",width:500,closeAction:'hide',html:pics});
        }
    winIMG.show(this);
}
function saveIMG(img){              		
    winIMG.hide(); 
    $("imgid").src  = img;  
    $("hiddenIMG").value = img;
    
}

//usercenter - email choice
function saveEmail(btn, text){
            if(btn == "ok"){
                    var reg = /^[^@]+@[^@]+[.]+[^@]+$/;
                    if(reg.test(text))
                            $("txtEmail") .value=text;
                    else {
                            alert	("input error!");        			
                    }
            }
};






