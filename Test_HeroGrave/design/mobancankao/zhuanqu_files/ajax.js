function createXMLHttp() {
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	} else if(window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	} 
	throw new Error("XMLHttp object could be created.");
}

function _sendRequest(url,func,isxml,ispost)
{
	var xhr=createXMLHttp();
	if(!ispost)ispost=null;
	xhr.open(ispost?"POST":"GET",url,true);
	if(func){
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				func(isxml&&xhr.responseXML?xhr.responseXML:xhr.responseText)
			}
		}
	}
	if(is_ie >=7 )
	{
		ispost = null;
	}
	xhr.send(ispost)
}
function ajaxRead(file,fun){
	var xmlObj = createXMLHttp();

	xmlObj.onreadystatechange = function(){
		if(xmlObj.readyState == 4){
			if (xmlObj.status ==200){
				obj = xmlObj.responseXML;
				eval(fun);
			}
			else{
				alert("读取文件出错,错误号为 [" + xmlObj.status  + "]");
			}
		}
	}
	xmlObj.open ('GET', file, true);
	xmlObj.send (null);
}

function getRequestBody(oForm) {
	var aParams = new Array();
	for (var i=0 ; i < oForm.elements.length; i++) {
		/*
		if (oForm.elements[i].type == "checkbox" && oForm.elements[i].checked == false)
		{
			continue;
		}
		*/
		var sParam = encodeURIComponent(oForm.elements[i].name);
		sParam += "=";
		sParam += encodeURIComponent(oForm.elements[i].value);
		aParams.push(sParam);
	}
	return aParams.join("&");
}


function getSpecificNodeValue(doc, tagname, index)
{
	try{
		var oNodes = doc.getElementsByTagName(tagname);
		if (oNodes[index] != null && oNodes[index] != undefined)
		{
			if (oNodes[index].childNodes.length > 1) {
				return oNodes[index].childNodes[1].nodeValue;
			} else {
				return oNodes[index].firstChild.nodeValue;    		
			}
		}
	}
	catch(e){}
	return '';
}

function getSingleNodeValue(doc, tagname)
{
	try{
		var oNodes = doc.getElementsByTagName(tagname);
		if (oNodes[0] != null && oNodes[0] != undefined)
		{
			if (oNodes[0].childNodes.length > 1) {
				return oNodes[0].childNodes[1].nodeValue;
			} else {
				return oNodes[0].firstChild.nodeValue;    		
			}
		}
	}
	catch(e){}
	return '';
}

function ajaxReadText(file,fun){
	var xmlObj = createXMLHttp();

	xmlObj.onreadystatechange = function(){
		if(xmlObj.readyState == 4){
			if (xmlObj.status ==200){
				obj = xmlObj.responseText;
				eval(fun);
			}
			else{
				alert("读取文件出错,错误号为 [" + xmlObj.status  + "]");
			}
		}
	}
	xmlObj.open ('GET', file, true);
	xmlObj.send (null);
}
