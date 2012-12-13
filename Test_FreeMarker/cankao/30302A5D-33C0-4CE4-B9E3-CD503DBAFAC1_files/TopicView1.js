
function changeViewMode(select)
{
    if(!select || !select.tagName || select.tagName!="SELECT") return;
    var divs = document.body.getElementsByTagName("TABLE");
    for(var i=0, items=[], n=divs.length; i<n; i++)
    {if(divs[i].className=="item reply") items.push(divs[i])}
    switch(select.options[select.selectedIndex].value)
    {
        case "0" : //all reply
            for(var i=0; i<items.length; i++) items[i].style.display="";
            break;
        case "1" : //star reply
            for(var i=0; i<items.length; i++)
            {
                var lis = items[i].rows[0].cells[0].getElementsByTagName("LI");
                for(var k=0, n=lis.length; k<n; k++)
                {
                    if(lis[k].className && lis[k].className=="grade")
                    {
                        var imgs=lis[k].getElementsByTagName("IMG");
                        if(imgs.length==0 || !imgs[0].className) continue;
                        if(imgs[0].className.indexOf("user")>-1) items[i].style.display="none";
                        else if(/(star)|(diam)/.test(imgs[0].className)) items[i].style.display="";
                    }
                }
            }
            break;
        case "2" : //my reply
            var username = document.getElementById("topicUserName").innerHTML.trim();
            for(var i=0; i<items.length; i++)
            {
                var lis = items[i].rows[0].cells[0].getElementsByTagName("LI");
                for(var k=0, n=lis.length; k<n; k++)
                {
                    if(lis[k].className && lis[k].className=="username")
                    {
                        var a = lis[k].getElementsByTagName("VAR");
                        if(a.length!=1) continue; else
                        {
                            if(a[0].innerHTML.trim()==username)items[i].style.display="";
                            else items[i].style.display="none";
                        }
                    }
                }
            }
            break;
        case "3" : //point reply
            break;
        case "4" :
            break;
        case "5" :
            break;
    }
}



function recommand(url)
{
    showWindow({title:"\u63a8\u8350\u7ed9\u597d\u53cb",url:url,width:700,height:220});
}

  var now = new Date();
  now.setTime(now.getTime() + 1000*60*60*24*3); //Save 3 days
  var expires=now;
window.setTheme=function(theme){document.cookie = "CsdnCssThemeName="+escape(theme)+";path=/;expires=" + expires.toGMTString()};
window.changeTheme=function(value)
{
  if(value)
  {
    window.setTheme(value);
    var url = location.href.replace(/(\?|\&)t=\w+/g, "");
    url=url.replace(/#\w+/g,"")
    location.href = url + (url.indexOf('?')==-1 ? '?' : '&') + 't='+ (new Date().getTime().toString(36));
  }
}

function displayVisitor(){
 var tempName=getCookie("UserName");
 if(!tempName||tempName=="Guest"){
 var visitorName="游客";
 $("exit").style.display="none";
 $("login").style.display="";
 }
 else{
 visitorName=tempName;
 $("exit").style.display="";
 $("login").style.display="none";
 }
 $("vName").innerHTML=visitorName;
}
displayVisitor();


(function()
{
  var themeName = getCookie("CsdnCssThemeName");
  if(themeName!="old") return;
  var tables = document.body.getElementsByTagName("TABLE");
  var topicItem;
  for(var i=0, items=[], n=tables.length; i<n; i++)
  {
    if(tables[i].className=="item topic")
    {
      topicItem=tables[i];
    }
    else if(tables[i].className=="item reply")
    {
      items.push(tables[i]);
    }
  }

  if(topicItem)
  {
    var c = topicItem.rows[1].cells[0].getElementsByTagName("LI");
    var user = {"username": "", "nickname": "", "grade": ""};
    for(var k=0; k<c.length; k++)
    {
      if(c[k].className)
      {
        switch(c[k].className)
        {
          case "username" : user.username = c[k].innerHTML; break;
          case "nickname" : user.nickname = c[k].children[0].value.trim(); break;
          case "grade"    : user.grade    = c[k].children[0].outerHTML; break;
        }
      }
    }
    topicItem.rows[1].cells[1].children[0].rows[0].cells[0].insertAdjacentHTML("afterBegin", "<span style='margin-right: 8px;'>"+ user.username +"("+ user.grade +")</span>");
  }

  for(var i=0; i<items.length; i++)
  {
      var c = items[i].rows[0].cells[0].getElementsByTagName("LI");
      var user = {"username": "", "nickname": "", "grade": ""};
      for(var k=0; k<c.length; k++)
      {
        if(c[k].className)
        {
          switch(c[k].className)
          {
            case "username" : user.username = c[k].innerHTML; break;
            case "nickname" : user.nickname = c[k].children[0].value.trim(); break;
            case "grade"    : user.grade    = c[k].children[0].outerHTML; break;
          }
        }
      }

    items[i].rows[0].cells[1].children[0].rows[0].cells[0].insertAdjacentHTML("afterBegin", "<span style='margin-right: 8px;'>"+ user.username +"("+ user.grade +")</span>");
  }
})();


function report(e){
    e = (window.event||e); e.cancelBubble=true;
    var type="topic";
    var url=document.getElementById("topicViewUrl").content;
    if(arguments[1])
        type="reply";
    var dialogTitle="";
    var temp="<b>以下信息将以短消息的形式发送给本帖所在版的大小版主和系统管理员</b><br /><br />";
    var content;    
    switch(type){
        case "reply":
        dialogTitle="举报回复";
        content=document.getElementById('reply'+arguments[1]+'_body').innerHTML.subByte(200);
        temp+="尊敬的版主，您好！<br />&nbsp;&nbsp;&nbsp;&nbsp;我是CSDN一名用户，我认为以下回复不合适在CSDN出现:<br /><br />";
        temp+="回复内容:<br/><textarea style=\"border:0px;overflow:hidden;width:300px;font-size:12px;background-color:#f6f8f9\" readonly='true'>"+content+"</textarea>";
        temp+="<br />链接:<br/><input style=\"border:0px;overflow:hidden;width:300px;font-size:12px;background-color:#f6f8f9\" readonly='true' type=\"text\" value=\""+url+"#rp"+arguments[1]+"\">";
        break;
        case "topic":
        dialogTitle="举报帖子";
        content=document.getElementById('topicSubject').innerHTML.subByte(200);
        temp+="尊敬的版主，您好！<br />&nbsp;&nbsp;我是CSDN一名用户，我认为以下帖子不合适在CSDN出现:<br /><br />";
        temp+="帖子标题:<br/><textarea style=\"border:0px;overflow:hidden;width:300px;font-size:12px;background-color:#f6f8f9\" readonly='true'>"+content+"</textarea>";
        temp+="<br />链接:<br/><input style=\"border:0px;overflow:hidden;width:300px;font-size:12px;background-color:#f6f8f9\" readonly='true' type=\"text\" value=\""+url+"\">";
        break;
    }
    temp+="<div style=\"text-align:center\"><br />";
    temp+="<input  type=\"button\" value=\"确定\" onclick=\"SendReport(\'"+getUrl(tinfo,content,arguments[1])+"\')\" />&nbsp&nbsp";
    temp+="<input type=\"button\" value=\"取消\" onclick=\"closeWindow()\" /></div>";
    try{
        showWindow({html:temp, width:350, height: 300, title: dialogTitle}); 
    }catch(ex){}
}

function getUrl(tinfo,content,replyid)
{
 var url=document.getElementById("hf_cardUrl").value.replace("UserCard.ashx?user=","report.ashx");
 url+="?s="+encodeURI(content)+"&sid="+tinfo.sid+"&pdate="+tinfo.pdate+"&tid="+tinfo.tid;
 if(replyid)
 url+="&rid="+replyid;
 return url;
}


function addToWZ(){
u=location.href;
t=document.title;
c = '' + (window.getSelection ? window.getSelection() : document.getSelection ? document.getSelection() : document.selection.createRange().text);
var link='http://wz.csdn.net/storeit.aspx?noui=yes&jump=close&u=' + escape(u) + '&t=' + escape(t) + '&c=' + escape(c).replace(/ /g, '+'); 
showWindow({url:link, width:650, height: 400, title: '放进我的网摘'});
}

function SendReport(url){
  modifyWindow({html:"<br/><br/><br/><br/><div style='text-align:center'><b>正在发送消息...</b><br />请不要关闭此窗口</div>"});
  SendRequest(url);
}

function reportCallBack(info){
    modifyWindow({html:"<br/><br/><br/><br/><div style='text-align:center'><b>"+info+"</b><br />点击右上角的关闭按钮关闭此窗口</div>"});
}


function Quote(replybodyid,username,layer){
if(layer>0)
replyframe.document.getElementById("tb_ReplyBody___Editor").value="[Quote=引用 "+layer+" 楼 "+username+" 的回复:]\r\n"+document.getElementById(replybodyid).innerText.subByte(400)+"\r\n[/Quote]";
else
replyframe.document.getElementById("tb_ReplyBody___Editor").value="[Quote=引用楼主 "+username+" 的帖子:]\r\n"+document.getElementById(replybodyid).innerText.subByte(400)+"\r\n[/Quote]";
window.location.href="#replyachor";
ReplyBoxFocus();
}

function ReplyBoxFocus(){
replyframe.document.getElementById("tb_ReplyBody___Editor").focus();
}



function Search(ev, type) {
    var url = document.forms["TopicSearchForm"].key.value;
    var searchPage;
    if (type == "advanced") searchPage = "http://so.csdn.net/advanced_search.aspx";
    else searchPage = "http://so.csdn.net/bbsSearchResult.aspx";
    if ((url == "") || (url == "这里也许就有你要的答案")) {
        if (type == "advanced")
            url = "http://so.csdn.net/advanced_search.aspx";
        else
            url = "http://so.csdn.net/bbs.aspx";
    }
    else if (encodeURIComponent) url = searchPage + "?q=" + encodeURIComponent(url);
    else url = searchPage + "?q=" + escape(url);
    if (document.all && typeof (document.all) == "object") {
        var a = document.createElement("A");
        a.target = "_blank";
        a.href = url;
        document.body.appendChild(a);
        a.click();
        setTimeout(function() { a.parentNode.removeChild(a); }, 50);
    }
    else window.open(url, "_blank");
}

function GoKeyDown(ev) { if (ev.keyCode == 13) Search(ev, ""); }