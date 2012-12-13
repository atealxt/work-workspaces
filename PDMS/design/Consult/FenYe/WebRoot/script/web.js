
/**
 * 取一以分号分割字符串的第一个字串(1,2,4,5,6)
 * @param idList 要取值的字符串
 * @return id idList的第一个字符串
 */
function getFirstID(idList) {
	var id;
	var iLop = idList.indexOf(",");
	if (iLop != -1) 
	{
		id = idList.substring(0, iLop);
	} 
	else 
	{
		id = idList;
	}
	return id;
}
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
	            for(var i = 0; i < obj.length; i=i+1)
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