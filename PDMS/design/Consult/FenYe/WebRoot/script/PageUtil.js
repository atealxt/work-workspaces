/**************************************************************
 * <p>网站分页JavaScript Document</p>
 *  @Author:Lzj.Liu(LzjLiu307@163.com)
 **************************************************************/
/**显示第一页的资料*/
function goFirst(form)
{
    form.pageno.selectedIndex = 0;
    changPage(form);
}
/**显示上一页的资料*/
function goPrevious(form)
{
    form.pageno.selectedIndex = form.pageno.selectedIndex - 1;
    changPage(form);
}
/**显示下一页的资料*/
function goNext(form)
{
    form.pageno.selectedIndex = form.pageno.selectedIndex + 1;
    changPage(form);
}
/**显示最后一页的资料*/
function goLast(form)
{
    form.pageno.selectedIndex = form.pageno.length - 1;
    changPage(form);
}
/**资料分页提交*/
function changPage(form)
{
	form.nextFlag.value="1";
	form.submit();
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