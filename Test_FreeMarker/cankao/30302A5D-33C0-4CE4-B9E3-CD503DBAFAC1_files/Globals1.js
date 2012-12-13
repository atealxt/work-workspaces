 var $=function()
{
  for(var i=0, a=[]; i<arguments.length; i++)
  {
    var e=arguments[i]; a[i]=null
    if("object"==typeof(e) && e.tagName && e!=window) a[i]=e;
    if("string"==typeof(e) &&(e=document.getElementById(e)))a[i]=e;
  }
  return a.length<2 ? a[0] : a;
}


String.prototype.subByte = function(n)
{
  if(this.getByteLength()<=n) return this;
  for(var i=Math.floor((n=n-2)/2), l=this.length; i<l; i++)
  if(this.substr(0,i).getByteLength()>=n)return this.substr(0,i) +"\u2026";
  return this;
};

String.prototype.getByteLength=function(){return this.replace(/[^\x00-\xff]/g, "mm").length;};

String.prototype.trim=function(){return this.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "")};

String.prototype.format=function()
{
  if(arguments.length==0) return this;
  for(var s=this, i=0; i<arguments.length; i++)
    s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);
  return s;
};
String.format=function(str)
{
  if("string"!=typeof(str)||arguments.length<=1) return str;
  for(var i=1; i<arguments.length; i++)
    str=str.replace(new RegExp("\\{"+(i-1)+"\\}","g"), arguments[i]);
  return str;
};

if(window.HTMLElement && navigator.userAgent.toLowerCase().indexOf("opera")==-1){
  HTMLElement.prototype.contains=function(e){do if(e==this)return true;while(e=e.parentNode);return false;};
  HTMLElement.prototype.__defineGetter__("outerHTML",function()
  {
    var a=this.attributes, str="<"+this.tagName, i=0;for(;i<a.length;i++)
    if(a[i].specified) str+=" "+a[i].name+'="'+a[i].value+'"';
    if(!this.canHaveChildren) return str+" />";
    return str+">"+this.innerHTML+"</"+this.tagName+">";
  });
  HTMLElement.prototype.__defineSetter__("outerHTML",function(s)
  {
    var r = this.ownerDocument.createRange();
    r.setStartBefore(this);
    r = r.createContextualFragment(s);
    this.parentNode.replaceChild(r, this);
    return s;
  });
  HTMLElement.prototype.__defineGetter__("canHaveChildren",function()
  {
    switch(this.tagName.toLowerCase())
    {
      case "area": case "base":  case "basefont":
      case "col":  case "frame": case "hr":
      case "img":  case "br":    case "input":
      case "link": case "meta":  case "isindex":
      case "param":return false;
    } return true;
  });
  HTMLElement.prototype.__defineGetter__("currentStyle", function()
  {
    return this.ownerDocument.defaultView.getComputedStyle(this,null);
  });
  HTMLElement.prototype.__defineGetter__("children",function()
  {
    for(var a=[],j=0,n,i=0; i<this.childNodes.length; i++){
    n=this.childNodes[i];if(n.nodeType==1){a[j++]=n;if(n.name){
    if(!a[n.name])a[n.name]=[]; a[n.name][a[n.name].length]=n;}
    if(n.id) a[n.id]=n;}}return a;
  });
  HTMLElement.prototype.insertAdjacentHTML=function(where, html)
  {
    var e=this.ownerDocument.createRange();
    e.setStartBefore(this);
    e=e.createContextualFragment(html);
    switch (where)
    {
      case 'beforeBegin': this.parentNode.insertBefore(e, this);break;
      case 'afterBegin': this.insertBefore(e, this.firstChild); break;
      case 'beforeEnd': this.appendChild(e); break;
      case 'afterEnd':
        if(!this.nextSibling) this.parentNode.appendChild(e);
        else this.parentNode.insertBefore(e, this.nextSibling); break;
    }
  };
        
    HTMLElement.prototype.__defineGetter__("innerText", function (){
            var anyString = "";
            var childS = this.childNodes;
            for(var i=0; i<childS.length; i++)
            {
                if(childS[i].nodeType==1)
                    anyString += childS[i].tagName=="BR" ? '\r\n' : childS[i].innerText;
                else if(childS[i].nodeType==3)
                    anyString += childS[i].nodeValue.trim();
            }
            return anyString;
        }
    ); 

 }
 
 if(!window.attachEvent && window.addEventListener)
{
  window.attachEvent = HTMLElement.prototype.attachEvent=
  document.attachEvent = function(en, func, cancelBubble)
  {
    var cb = cancelBubble ? true : false;
    this.addEventListener(en.toLowerCase().substr(2), func, cb);
  };
  window.detachEvent = HTMLElement.prototype.detachEvent=
  document.detachEvent = function(en, func, cancelBubble)
  {
    var cb = cancelBubble ? true : false;
    this.removeEventListener(en.toLowerCase().substr(2), func, cb);
  };
}

function getCookie (name)
{
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg)) return unescape(arr[2]);
  else return null;
}

function GetScrollXY(){
    var x=0,y=0
    if(document.documentElement.scrollTop){
        x=document.documentElement.scrollLeft;
        y=document.documentElement.scrollTop;
    }
    else{
        x=document.body.scrollLeft;
        y=document.body.scrollTop;
    }
    return {x:x,y:y}
}

function getEventXY(e){
    var posx=0,posy=0;
    if(e==null) e=window.event;
    if(e.pageX || e.pageY)
    {
        posx=e.pageX; posy=e.pageY;
    }
    else if(e.clientX || e.clientY)
    {
       posx=e.clientX+GetScrollXY().x;
       posy=e.clientY+GetScrollXY().y;
    }
    return {"x":posx, "y":posy};
}

function realOffset(o)
{
  if(!o) return null; var e=o, x=y=l=t=0;
  do{l+=e.offsetLeft||0; t+=e.offsetTop||0; e=e.offsetParent;}while(e);
  do{x+=o.scrollLeft||0; y+=o.scrollTop||0; o=o.parentNode;}while(o);
  var xy=GetScrollXY();
  return {"x":l-x+xy.x, "y":t-y+xy.y};
};
 
 