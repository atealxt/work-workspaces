function ResumeError() { 
return true; 
} 
window.onerror = ResumeError; 
// 相对尺寸
function GetOffsetTop (el, p) {
    var _t = el.offsetTop;
    var _p = el.offsetParent;

    while (_p) {
        if (_p == p) break;
        _t += _p.offsetTop;
        _p = _p.offsetParent;
    }

    return _t;
};
function GetOffsetLeft (el, p) {
    var _l = el.offsetLeft;
    var _p = el.offsetParent;

    while (_p) {
        if (_p == p) break;
        _l += _p.offsetLeft;
        _p = _p.offsetParent;
    }

    return _l;
};
function showMenu (baseID, divID) {
    baseID =  document.getElementById(baseID);
    divID  =  document.getElementById(divID);

    //var l = GetOffsetLeft(baseID);
    //var t = GetOffsetTop(baseID);

    //divID.style.left = l + 'px';
//    divID.style.top = t + baseID.offsetHeight + 'px';
    if (showMenu.timer) clearTimeout(showMenu.timer);
	hideCur();
    divID.style.display = 'block';
	showMenu.cur = divID;

    if (! divID.isCreate) {
        divID.isCreate = true;
        //divID.timer = 0;
        divID.onmouseover = function () {
            if (showMenu.timer) clearTimeout(showMenu.timer);
			hideCur();
            divID.style.display = 'block';
        };

        function hide () {
            showMenu.timer = setTimeout(function () {divID.style.display = 'none';}, 1000);
        }

        divID.onmouseout = hide;
        baseID.onmouseout = hide;
    }
	function hideCur () {
		showMenu.cur && (showMenu.cur.style.display = 'none');
	}
}

function doClick_down(o){
	 o.className="current";
	 var j;
	 var id;
	 var e;
	 for(var i=1;i<=4;i++){
	   id ="down"+i;
	   j = document.getElementById(id);
	   e = document.getElementById("d_con"+i);
	   if(id != o.id){
	   	 j.className="";
	   	 e.style.display = "none";
	   }else{
		e.style.display = "block";
	   }
	 }
	 }
	 
function doClick_jy(o){
	 o.className="current";
	 var j;
	 var id;
	 var e;
	 for(var i=1;i<=8;i++){
	   id ="jy"+i;
	   j = document.getElementById(id);
	   e = document.getElementById("jy_con"+i);
	   if(id != o.id){
	   	 j.className="";
	   	 e.style.display = "none";
	   }else{
		e.style.display = "block";
	   }
	 }
	 }


function doZoom(size){
	document.getElementById('textbody').style.fontSize=size+'px'
}


/// 修改及新增
function doClick_menu (o) {
	o.className = 'menu_gg';
	var j, id, e;
	for (var i = 1; i <= 5; i++) {
	    id = 'menu' + i;
	    j = document.getElementById(id);
	    e = document.getElementById('menu_con' + i);
	    if (id != o.id) {
	   	   j.className = '';
	   	   e.style.display = 'none';
	    }
        else {
		    e.style.display = 'block';
	    }
	 }
     var url = '';
     switch (o.innerHTML) {
        case '本站内容':
            url = '/plus/search.php?searchtype=titlekeyword&keyword=';
            break;
        case '帖子搜索':
            url = 'http://search.qihoo.com/usearch.html?site=bbs.okajax.com&kw=';
            break;
        case 'alexa排名':
            url = '/alexa/?url=';
            break;
        case 'google':
            url = 'http://www.google.cn/search?hl=zh-CN&q=site%3Aokajax.com+';
            break;
        case 'baidu':
            url = 'http://www.baidu.com/baidu?tn=zhuchaomn_pg&cl=3&myselect=1&myselectvalue=0&wd=site%3Aokajax.com+';
            break;
     }
     doClick_submit.url = url;
}
doClick_submit.url = '/plus/search.php?searchtype=titlekeyword&keyword=';
function doClick_submit () {
    var keyword = document.getElementsByName('keyword')[0].value;
    window.open(doClick_submit.url + keyword);
}
window.onload = function (){
	var keyword = document.getElementsByName('keyword')[0];
	keyword.onkeydown = function (e) {
		e = e || window.event;
		if (e.keyCode == 13) {
			doClick_submit();
		}
	};
}