/**
 * 
 */
package com.source.service.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.papa.list.ListBean;
import com.papa.list.Pager;
import com.papa.list.Searcher;
import com.source.bean.AnythingType;
import com.source.dao.AnythingTypeDao;
import com.source.service.AnythingTypeService;

/**
 * @author 林跃辉 [Oct 10, 2007]
 */
public class AnythingTypeServiceImpl implements AnythingTypeService
{
	private AnythingTypeDao dao;

	public AnythingTypeDao getDao()
	{
		return dao;
	}

	public void setDao(AnythingTypeDao dao)
	{
		this.dao=dao;
	}

	public void delete(Integer[] refID)
	{
		for (int i=0; i<refID.length; i++)
		{
			AnythingType type=dao.findById(refID[i]);
			dao.delete(type);
		}
	}

	public AnythingType findById(int classId)
	{
		AnythingType type=dao.findById(classId);
		return type;
	}

	public void save(AnythingType type)
	{
		dao.save(type);
	}

	public void search(Pager pager,HttpServletRequest request,Searcher searcher)
	{
		if(pager.isInitial())
			pager.setRowcount(dao.count(searcher));
		Pager newPager=new ListBean().computePager(pager);//重新获取符合条件的记录条数 
		request.setAttribute("pager",newPager);
		List typeList=dao.search(pager.getFirst(),pager.getPagesize(),searcher);
		request.setAttribute("list",typeList);
	}

	

}
