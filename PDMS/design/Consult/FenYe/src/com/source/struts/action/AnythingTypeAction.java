package com.source.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.papa.list.Pager;
import com.papa.list.Searcher;
import com.papa.util.DataValidator;
import com.papa.util.DateUtil;
import com.source.bean.AnythingType;
import com.source.service.AnythingTypeService;
import com.source.struts.form.AnythingTypeForm;

public class AnythingTypeAction extends DispatchAction
{
	private AnythingTypeService service;

	public AnythingTypeService getService()
	{
		return service;
	}

	public void setService(AnythingTypeService service)
	{
		this.service=service;
	}

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		AnythingTypeForm sForm=(AnythingTypeForm)form;
		ActionMessages errors=new ActionMessages();
		Pager pager=sForm.getPager();
		pager.setPagesize(13);
		Searcher searcher=sForm.getSearcher();
		if(searcher.getSearchType()==0&&!searcher.getSearchValue().equals(""))
		{
			sForm.setSearchValue("");
			errors.add("noSearchType",new ActionMessage(
					"error.common.noSearchType"));
			this.saveErrors(request,errors);
			return this.unspecified(mapping,form,request,response);
		}
		String beginDate=sForm.getBeginDate(); // 搜索开始日期
		String endDate=sForm.getEndDate(); // 搜索结束日期

		errors.add(DataValidator.isDataFormat(beginDate,"yyyy-MM-dd",false,
				"error","error.common.wrongBeginDate"));
		if (!errors.isEmpty())
		{
			sForm.setBeginDate(DateUtil.getNowDate());
			this.saveErrors(request,errors);
			return this.unspecified(mapping,form,request,response);
		}

		errors.add(DataValidator.isDataFormat(endDate,"yyyy-MM-dd",false,
				"error","error.common.wrongEndDate"));
		if (!errors.isEmpty())
		{
			sForm.setEndDate(DateUtil.getNowDate());
			this.saveErrors(request,errors);
			return this.unspecified(mapping,form,request,response);
		}
		service.search(pager,request,searcher);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		AnythingTypeForm sForm=(AnythingTypeForm)form;
		sForm.setInitial(true);
		Integer[] refID=sForm.getRefID();
		service.delete(refID);
		return this.unspecified(mapping,form,request,response);
	}

	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		AnythingTypeForm sForm=(AnythingTypeForm)form;
		int classId=sForm.getClassId();
		System.out.println(sForm.getClassId());
		AnythingType type=new AnythingType();
		type=service.findById(classId);
		sForm.setType(type);
		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		AnythingTypeForm sForm=(AnythingTypeForm)form;
		AnythingType type=sForm.getType();
		service.save(type);
		return this.unspecified(mapping,form,request,response);
	}
	


}
