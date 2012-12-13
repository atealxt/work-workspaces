/*---------------------------------------------------------------------------*\
|  NameSpace:  Csdn.Forum.UserOnline
|  Author:     yizhu
|  Version:    2007-11-13
\*---------------------------------------------------------------------------*/
  
showUserCard.database = {}; //global userinfo database
    
resourcePath="/u/ui/scripts/System/_resource";
  
window._stopMouseOver=function(e){(window.event||e).cancelBubble=true;}
function UserCardCallback(json)
{	
    if(typeof(json)=="undefined" || !json.username) return;
    showUserCard.database[json.username] = json;
    bindData(json.username);
}

function showUserCard(e, user)
{
    e = (window.event||e); e.cancelBubble=true;
    var img = e.srcElement || e.target; 
    if(!user) return;
    var layer=document.getElementById("UserCard_layer");
    if(!layer)
    return; 
    layer.style.display="";
    var xy;
    if(navigator.userAgent.toLowerCase().indexOf("opera")<0)
    { xy=realOffset(img); layer.style.left = (xy.x+img.offsetWidth-2) +"px";}
    else//is opera
    { xy=getEventXY(e); layer.style.left = xy.x +"px";}
    layer.style.top  = (xy.y) +"px";
    var left = "<a target='_blank' href='http://hi.csdn.net/{0}/'><img alt='' class='face' src='http://profile.csdn.net/{0}/picture/2.jpg' /></a><br/>"
        +"<a href='http://hi.csdn.net/{0}/' target='_blank'>\u4e2a\u4eba\u7a7a\u95f4</a><br/>"
        +"<a href='http://webim.csdn.net/AddFriends/"+user+".ashx' target='_blank' ><img alt='add friend' class='addFriend' src='"+ resourcePath +"/blank.gif' /></a><br/>"
        +"<a href='http://webim.csdn.net/Messages/"+user+".ashx' target='_blank'><img alt='send message' class='sendIMMsg' src='"+ resourcePath +"/blank.gif' /></a><br/>"
        +"<a href='http://blog.csdn.net/{0}/' target='_blank'><img alt='blog' class='goBlog' src='"+ resourcePath+"/blank.gif' /></a><br/>"
        +"<a href=''>"     
    document.getElementById("UserCard_left").innerHTML = left.format(user);  
    var right = "<span>\u5e10\u53f7\uff1a</span><a target='_blank' href='http://hi.csdn.net/{0}/'><var>{0}</var></a><br/>"+  //username
        "<img src='http://counter.csdn.net/pv.aspx?id=241' border='0' style='display:none'/>"+ //counter
        "<span>\u6635\u79f0\uff1a</span><a target='_blank' href='http://hi.csdn.net/{0}/'><var id='UserCard_nickname'></var></a><br/>"+ //nickname
        "<span>\u6700\u65b0\u5e16\u5b50\uff1a</span><br/><span id='UserCard_topiclist'></span>"+ //last topic
        "<span style='margin-left:90px'><a href='http://forum.csdn.net/PointForum/Forum/UserTopicList.aspx?user={0}'>\u66f4\u591a\u5e16\u5b50...</a></span>"
    document.getElementById("UserCard_right").innerHTML = right.format(user);

    if(typeof(showUserCard.database[user])=="undefined")
    {
        SendRequest(document.getElementById("hf_cardUrl").value + user);
    }
    else bindData(user);
}


function bindData(username)
{
    var json = showUserCard.database[username];
    $("UserCard_nickname").innerHTML = json.nickname;
    for(var s="", i=0; i<json.topicList.length; i++)
    {
        s += "<li><a href='{0}' title='{2}' target='_blank'>{1}</a></li>".format(json.topicList[i].url, json.topicList[i].text.subByte(20), json.topicList[i].text.replace(/</g, "&lt;").replace(/\'/, "&#39;"));
    }
    if(s!="") $("UserCard_topiclist").innerHTML = "<ol>"+s+"</ol>";
}
document.attachEvent("onmouseover", function(){try{document.getElementById("UserCard_layer").style.display="none";}catch(ex){}});

function SendRequest(url){	
	if(this.element){this.element.parentNode.removeChild(this.element)};
	this.element=document.createElement("SCRIPT");	 
	this.element.type="text/javascript";
	this.element.id = "MzJsonElement";
	this.element.src=url;
	document.getElementsByTagName("HEAD")[0].appendChild(this.element);
}



