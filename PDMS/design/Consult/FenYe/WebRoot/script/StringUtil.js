/**
 * 取一以分号分割字符串的第一个字串(1,2,4,5,6)
 * @param idList 要取值的字符串
 * @return id idList的第一个字符串
 */
function getFirstID(idList)
{
	var id;
	var iLop=idList.indexOf(",");
	if(iLop!=-1)
		id=idList.substring(0,iLop);
	else
		id=idList;
	return id;
}
function len(s)
 {
    var len=0;
    for(var i=0;i<s.length;i++){

        if(!ischinese(s.charAt(i))){
            len+=1;
        }else {
            len+=2;
        }
    }
       return len;

  }
  /**
 *判断是否中文函数
 */
function ischinese(s){
    var ret=true;

    for(var i=0;i<s.length;i++)
        ret=ret && (s.charCodeAt(i)>=256);

    return ret;
}
function isEmail(s) 
{
	if (s.length > 100)	return false;
	if (s.indexOf("'")!=-1 || s.indexOf("/")!=-1 || s.indexOf("\\")!=-1 || s.indexOf("<")!=-1 || s.indexOf(">")!=-1) return false;
    s = s.replace('(', '');
    s = s.replace(')', '');
    s = s.replace('（', '');
    s = s.replace('）', '');

	var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[_.0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+(.+)$";
	var re = new RegExp(regu);
	if (s.search(re) != -1)
		return true;
	else
		return false;
}
function makeKeyword(str, keyword){
    if(s == "") return "";

    var rgExpHtml1 = new RegExp("<", "gi");
    var rgExpHtml2 = new RegExp(">", "gi");
    var rgExpKey = new RegExp(keyword, "gi");
    var replaceText = "<span style=\"color:#FF0000;\">" + keyword + "</span>";

    if(str.search(rgExpHtml1) == -1){
        return str.replace(rgExpKey, replaceText);
    }else{
        var result = "";
        var begin = 0;
        var end = 0;
        var transact = false;
        var s;
        for(var i = 0; i < str.length; i++){
            s = str.charAt(i);

            if(s == '<'){
                end = i;
                result += str.substring(begin, end).replace(rgExpKey, replaceText);
                begin = i;
                end = i;
            }

            if(s == '>'){
                result += str.substring(begin, i + 1);
                begin = i + 1;
                end = i + 1;
            }

        }
        result += str.substring(begin, i + 1).replace(rgExpKey, replaceText);

        return result;
    }
}

/**
 * 为字符串增加trim方法，以去除左右空格
 */
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function disKeyWord(strx)
{
	var keyword = strx;
    keyword = keyword.trim();
    if(keyword.length == 0) return;	
    var filter = "&nbsp;";

    var arrKeyword = keyword.split(",");

    for(var j = 0; j < arrKeyword.length; j++){
        keyword = arrKeyword[j].trim();
        if(filter.indexOf(keyword) >= 0) break;
		if(typeof(resume)=="object")
		{
	        if(resume.length > 0){
	            for(var i = 0; i < resume.length; i++){
	                resume[i].innerHTML = makeKeyword(resume[i].innerHTML, keyword);
	            }
	        }else{
	            resume.innerHTML = makeKeyword(resume.innerHTML, keyword);
	        }
	    }
    }
}