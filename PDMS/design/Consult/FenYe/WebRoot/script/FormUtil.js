/**************************************************************
 * <p>网站JavaScript Document</p>
 *  @Author:刘志坚(LzjLiu307@163.com)
 **************************************************************/
/**
 *返回一个form中选中的checkbox的以","连接的值
 *@param form 表单对象
 *@param eName checkbox的名称
 */
function choose(form,eName)
{
	var s="";
	for(var i=0;i<form.elements.length;i++)
	{
		var e=form.elements[i];
		if(e.name==eName)
		{
			if (e.checked)
			{
				s=s+e.value+",";
			}
		}
	}
	if(s!=="")
	{
		s=s.substring(0,s.length-1);
	}
	return s;
}

/**
 *返回一个form中选中的checkbox的以","连接的值
 *@param form 表单对象
 *@param eName checkbox的名称
 */
function chooseID(form)
{
	var s="";
	if(form)
	{
		for(var i=0;i<form.elements.length;i++)
		{
			var e=form.elements[i];
			if(e.type=='checkbox' && e.checked)
			{
				s=s+e.id+",";
			}
		}
		if(s!=="")
		{
			s=s.substring(0,s.length-1);
		}
	}
	return s;
}
/**
 * <p></p>
 * 
 */
function setSelected(obj1,obj2,oprate)
{
	if(typeof(obj1)=="object" && typeof(obj2)=="object")
	{
		switch(oprate)
		{
			case "more":				
				if(parseInt(obj1.value)<parseInt(obj2.value))
				{
					obj2.selectedIndex=obj2.defaultSelected;
					return false;	
				}		
				break;
			case "less":	
				//alert(parseInt(obj1.value)>parseInt(obj2.value));	
				if(parseInt(obj1.value)>parseInt(obj2.value))
				{
					obj1.selectedIndex=obj1.defaultSelected;
					return false;
				}
				break;	
		}	
	}
}
/**选中一组radio按钮中的一项*/
function chkRadio(obj,val)
{
	var rLen=obj.length;
	for(var i=0;i<rLen;i++)
	{
		if(val==obj[i].value)
		{
	   		obj[i].checked=true;
			break;	
		}
	}	
}
/**
 * <p>根据指定值选定下拉列表</p>
 * @param varObj 要处理的下拉列表
 * @param varValue 要选中的值
 */
function selOption(varObj,varValue)
{
	if(typeof(varObj)=="object")
	{
		for(var i=0;i<varObj.length;i++)
		{
			if(varObj.options[i].value==varValue)
			{
				varObj.selectedIndex=i;
				break;	
			}	
		}
	}
}

/**
 * <p>使对象得到焦点</p>
 
 */
function setFocus(obj)
{
	if(event.keyCode==9)
	{
		obj.focus();
	}
}
/**
 * 返回一组 或 一个 checkbox 的 checked 属性 为 true 的value，中间用","分隔
 */
function getCheckboxValues(obj){
    var s = "";
    if(obj !== null){
        if(obj[0]){
            for(var i = 0; i < obj.length; i++){
                if(obj[i].checked){
                    s += obj[i].value +  ",";
                }
            }
        }else {
            if(obj.checked){
                s += obj.value + ",";
            }
        }
    }
    if(s !== "") 
    {
    	s = s.substring(0, s.length-1);
    }
}
/**
 * 选择一个列表的所有选项
 */
function selAllOption(objSelect)
{
    for(var i = 0; i < objSelect.length; i++)
    {
        objSelect.options[i].selected = true;
	}
}
/**
 * 往一个下拉列表中增加一项
 */
function putOption(objSelect,v,t)
{
    var objOpt = document.createElement("option");    
    objOpt.value = v;
    objOpt.text = t;
    try
    {
       
        objSelect.add(objOpt);
    }
    catch(e)
    {
        objSelect.add(objOpt, null);
    }
}
/**
 * 往一个下拉列表中增加一项
 */
function putOption1(objSelect,objDesc)
{
    var index=objDesc.selectedIndex;
    if(index>=0)
    {
    	if (objSelect.length<3)
    	{
    		putOption(objSelect,objDesc.options[index].value, objDesc.options[index].text);
    	}
    	else
    	{
    		alert("已经选择三项");
    	}
    }
}

/**
 * 在两个下拉列表中移动项目
 * @param sour 要移动的下拉列表(来源)
 * @param dest 要移至的下挟列表(目标)
 */
function moveOption(sour, dest)
{  
    for(var i=sour.length-1;i>=0;i--)
    {
    	if(sour.options[i].selected===true)
    	{
			sour.remove(i);	
		}
    }
}

function chkSelCall(sObj,mObj)
{
	var str="";
	for(var i=0;i<mObj.length;i++)
	{
		if(mObj.options[i].text.indexOf("不限")!=-1)
		{
			str="对不起，您已经选择了行业类别不限！";
			break;
		}
	}
	return str;
}
//=============================================================
/**
 * 全选中与全不选中checkbox资料(selChkBox(this.form,this,'selID'))
 * @param objForm 	表单form
 * @param objSel	选中所有资料的checkbox
 * @param objEle	点击objSel后要选中的checkbox
 */
function selChkBox(objForm,objSel,objEle) 
{
	if (objSel.checked===true)
	{
		for (var i=0;i<objForm.elements.length;i++) 
		{
			var e = objForm.elements[i];
			if (e.name == objEle)
			{
				e.checked=true;
			}
		}
	}
	else
	{
		for (var j=0;j<objForm.elements.length;j++) 
		{
			var f = objForm.elements[j];
			if (f.name == objEle)
			{
				f.checked=false;
			}
		}
	}
}
/**
 * 当反选一个checkbox时将全选的checkbox置为没有选中的状态
 * 当选中一个checkbox时检查是否objEle已全选中,是的话选中objSel
 * @param objForm 	表单form
 * @param objSel	选中所有资料的checkbox
 * @param objEle	点击objSel后要选中的checkbox
 */
function selChkAll(objForm,objEle,objSel)
{
	var flag=isAllCheck(objForm,objEle);
	if(flag)
	{
		if(objSel)
		{
			objSel.checked=true;
		}
	}
	else
	{
		if(objSel)
		{
			objSel.checked=false;
		}
	}
}
/**
 * 检查一组checkbox是否全部选中
 * @param objEle checkbox对象
 * @return boolean true:已全选中;false:没有全选中
 */
function isAllCheck(objForm,objEle)
{
	//var flag;
	var allLen=0;
	var selLen=0;
	for(var i=0;i<objForm.elements.length;i++)
	{
		var ele = objForm.elements[i];
		if (ele.name == objEle)	
		{
			allLen++;
		}
	}
	for(var j=0;j<objForm.elements.length;j++)
	{
		var elf = objForm.elements[j];
		if (elf.name == objEle && elf.checked===true)
		{	
			selLen++;
		}
	}
	if(allLen==selLen)
	{
		return true;
	}
	else
	{
		return false;
	}
}
//=============================================================
/**
 * 判断一组checkbox是否有选中(true:至少选中了一个;false:一个都没有选中)
 */
function isCheck(obj)
{
	var flag=false;
	if(obj)
	{		
	        if(obj[0])
	        {
	            for(var i = 0; i < obj.length; i++)
	            {
	                if(obj[i].checked)	//只要有选中一个就返回true
	                {
	                    flag = true;
	                    break;
	                }
	            }
	        }
	        else 
	        {
	            if(obj.checked)
	            {
	                flag = true;
	            }
	        }
	}	
	return flag;
}
/**返回一组checkbox中选中的checkbox个数*/
function chkNum(obj)
{
	var num=0;
	if(obj!==null)
	{
		if(obj[0])
		{
			for(var i = 0; i < obj.length; i++)
			{
				if(obj[i].checked)
				{
					num++;
				}
			}
		}
		else 
		{
			if(obj.checked)
			{
				num=1;
			}
		}
	}	
	return num;
}

/*
创建xml访问对像
*/
function newXMLHttpRequest()
{
	var xmlreq = false;
	if (window.XMLHttpRequest)
	{
		xmlreq = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) 
	{ 
		try 
		{
			xmlreq = new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		catch(e1)
		{
			try
			{
				xmlreq = new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (e2)
			{
			}
		}
	}
	return xmlreq; 
}
/**
 * 选择工作地区,岗位等
 * @param bObj	地区省
 * @param sObj	来源
 * @param mObj	目的地
 * @param flag	操作标志
 */
function addOption(bObj,sObj,mObj,flag)
{
	if(sObj.selectedIndex==-1)
	{
		alert("请选择一项!");
		return;
	}
	if(flag=="area")					//选择工作地区
	{	
		var pre=bObj.options[bObj.selectedIndex].text;	
		var val=sObj.value;
		var txt=sObj.options[sObj.selectedIndex].text;
		//if(val.substr(2,2)!="00")
			//txt=pre+"-"+txt;		
		addArea(sObj,mObj,val,txt);
	}
	if(flag=="job")						//选择岗位类别
	{
		var pre=bObj.options[bObj.selectedIndex].text;	
		var val=sObj.value;
		var txt=sObj.options[sObj.selectedIndex].text;
		//if(val.substr(2,2)!="00")
			//txt=pre+"-"+txt;	
		gainJob(sObj,mObj,val,txt);
	}
	if(flag=="call")					//选择行业类别
	{
		for(var i=0;i<sObj.length;i++)	//考虑到增加多选的功能
		{
			if(sObj.options[i].selected==true)
			{
				var val=sObj.options[i].value;
				var txt=sObj.options[i].text;
				addCall(sObj,mObj,val,txt);
			}
		}
	}
}
/**
 * 增加地区下拉菜单
 * @param mObj 	要增加的地区下拉菜单
 * @param val	要增加的下拉项value
 * @param txt	要增加的下拉项text
 */
function addArea(sObj,mObj,val,txt)
{
	var sLen=mObj.options.length;
	if(sLen<=2)
	{
		var strChk=chkSelArea(sObj,mObj,val);
		if(strChk=="")
		{
			var flag="false";	//是否已选择的标志		
			for(var i=0;i<sLen;i++)
			{
				if(val==mObj.options[i].value)
				{
					flag="true";
					break;
				}	
			}
			if(flag=="false")
				putOption(mObj,val,txt);
			else
				alert("对不起,您已经选择了"+txt);	
		}
		else					//进行搜索时如果选择了不限则提示用户不可以再选择
		{
			alert(strChk);
			return;
		}
	}
	else
	{
		alert("对不起,您最多只能选择三项!");
		return;
	}
}
/**
 * 在新增一个工作地区时,检查用户是否已经选择了该地区的省不限,如果有则返回该text
 * 检查1:选择了地区大类不可以新增该大类下的小类
 * 检查2:选择了该地区小类,不可以新增该小类之上的大类
 * @param objArea 要新增下拉项的下拉列表
 * @param val 要新增的下拉项的值
 */
function chkSelArea(sObj,objArea,val)
{
	var strTmp="";
	var sTxt=sObj.options[sObj.selectedIndex].text;
	for(var i=0;i<objArea.length;i++)
	{
		var objVal=objArea.options[i].value;
		if(objVal.substr(2,2)=="00" && val.substr(0,2)==objVal.substr(0,2))//已经选择了该地区的省不限又选择该省下的城市
		{
			var txt=objArea.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",您目前选择的地区已包含在"+txt+"中!";
			break;	
		}
		if(objVal.substr(2,2)=="00" && objVal.substr(0,2)==val.substr(0,2))//已经选择该地区的城市,又想选择该省不限
		{
			var txt=objArea.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",该地区包含在"+sTxt+"中!";
			break;	
		}
		/*修正:选择子项后还可选择父项*/
		if(val.substr(2,2)=="00" && objVal.substr(0,2)==val.substr(0,2))//已经选择该地区的城市,又想选择该省不限
		{
			var txt=objArea.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",该地区包含在"+sTxt+"中,请删除后再选不限!";
			break;	
		}
	}
	return strTmp;
}
/**
 * 增加岗位类别下拉菜单
 * @param sObj	来源对象
 * @param mObj 	要增加的岗位类别下拉菜单
 * @param val	要增加的下拉项value
 * @param txt	要增加的下拉项text
 */
function gainJob(sObj,mObj,val,txt)
{
	var sLen=mObj.options.length;
	if(sLen<=2)
	{
		var strChk=chkSelJob(sObj,mObj,val);
		if(strChk=="")
		{
			var flag="false";	//是否已选择的标志		
			for(var i=0;i<sLen;i++)
			{
				if(val==mObj.options[i].value)
				{
					flag="true";
					break;
				}	
			}
			if(flag=="false")
				putOption(mObj,val,txt);
			else
				alert("对不起,您已经选择了"+txt+"的岗位!");	
		}
		else					//进行搜索时如果选择了不限则提示用户不可以再选择
		{
			alert(strChk);
			return false;
		}
	}
	else
	{
		alert("对不起,您最多只能选择三项工作岗位!");
		return false;
	}
}
/**
 * 在新增一个工作岗位时,检查用户是否已经选择了该岗位的不限,如果有则返回该text
 * 检查1:选择了岗位大类不可以新增该大类下的小类
 * 检查2:选择了该岗位小类,不可以新增该小类之上的大类
 * @sObj  资料来源对象
 * @param objJob 要新增下拉项的下拉列表
 * @param val 要新增的下拉项的值
 * 
 */
function chkSelJob(sObj,objJob,val)
{
	var strTmp="";
	var sTxt=sObj.options[sObj.selectedIndex].text;
	for(var i=0;i<objJob.length;i++)
	{
		var objVal=objJob.options[i].value;
		if(objVal.substr(2,2)=="00" && val.substr(0,2)==objVal.substr(0,2))//已经选择了该岗位不限又选择该岗位下的岗位
		{
			var txt=objJob.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",您目前选择的岗位已包含在"+txt+"中!";
			break;	
		}
		if(objVal.substr(2,2)=="00" && objVal.substr(0,2)==val.substr(0,2))//已经选择该岗位,又想选择该岗位不限
		{
			var txt=objJob.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",该岗位包含在"+sTxt+"中!";
			break;	
		}
		/*修正:先选择子项可再先父项*/
		if(val.substr(2,2)=="00" && objVal.substr(0,2)==val.substr(0,2))//已经选择该岗位的子岗位,又想选择该岗位不限
		{
			var txt=objJob.options[i].text;
			strTmp="对不起,您已经选择了"+txt+",该岗位包含在"+sTxt+"中,请你删除后再选不限!";
			break;	
		}
	}
	return strTmp;
}
/**
 * 增加行业类别下拉菜单
 * @param mObj 	要增加的行业类别下拉菜单
 * @param val	要增加的下拉项value
 * @param txt	要增加的下拉项text
 */
function addCall(sObj,mObj,val,txt)
{
	var sLen=mObj.options.length;
	if(sLen<=2)
	{
		var strChk=chkSelCall(sObj,mObj);
		if(strChk=="")
		{
			var flag="false";	//是否已选择的标志		
			for(var i=0;i<sLen;i++)
			{
				if(val==mObj.options[i].value)
				{
					flag="true";
					break;
				}	
			}
			if(flag=="false")
				putOption(mObj,val,txt);
			else
				alert("对不起,您已经选择了"+txt+"的行业!");	
		}
		else					//进行搜索时如果选择了不限则提示用户不可以再选择
		{
			alert(strChk);
			return false;
		}
	}
	else
	{
		alert("对不起,您最多只能选择三项行业!");
		return false;
	}
}
function chkSelCall(sObj,mObj)
{
	var str="";
	for(var i=0;i<mObj.length;i++)
	{
		if(mObj.options[i].text.indexOf("不限")!=-1)
		{
			str="对不起，您已经选择了行业类别不限！";
			break;
		}
	}
	return str;
}
function getMenu()
{
	var xh = newXMLHttpRequest();
	xh.open("POST","/manage/system/SystemRight.jhtml?method=getButton",false);
	xh.send("");
	document.write(xh.responseText);
}
/**
 * 根据指定值取得一个下拉列表的
 * @param value 指定值
 * @obj 要查找的下拉菜单
 */
function fetchOptText(value,obj)
{
	var text="";
	for(var i=0;i<obj.length;i++)
	{
		if(obj.options[i].value==value)
		{
			text=obj.options[i].text;
			break;
		}
	}
	return text;
}