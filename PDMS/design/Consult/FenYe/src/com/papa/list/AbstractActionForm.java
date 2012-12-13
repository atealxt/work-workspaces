/*
 * @(#)AbstractActionForm.java 2007-1-2
 *
 * Copyright 2007 富海人才(www.jobbaidu.com), Inc. All rights reserved.
 * Fortune PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.papa.list;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 所有需要使用分页查询的ActionForm的抽象类</p>
 * <p>Description: 抽象所有分页查询ActionForm</p>
 * <p>Copyright: Copyright (c) 2007-1-2</p>
 * <p>Company: Co.,Ltd</p>
 * @author <a href="mailto:lzjliu307@163.com">刘志坚</a>
 * @version 1.0
 */
public abstract class AbstractActionForm extends ActionForm
{

	/**
	 * 类的构造器，用于该类的实例化并产生该对象
	 */
	public AbstractActionForm()
	{
		super();
	}

	protected Pager pager;

	protected Searcher searcher;

	private Integer[] refID=null; //选择的资料编号

	private int count=0; //用于统计符合条件的记录总数

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count=count;
	}

	/**
	 * 取得用户选择的资料编号
	 * @return int[] 资料编号
	 */
	public Integer[] getRefID()
	{
		return refID;
	}

	/**
	 * 设置用户选择的资料编号
	 * @param refID 资料编号
	 */
	public void setRefID(Integer[] refID)
	{
		this.refID=refID;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager=pager;
	}

	public boolean equals(Object obj)
	{
		return pager.equals(obj);
	}

	public int getPageno()
	{
		return pager.getPageno();
	}

	public int getRowcount()
	{
		return pager.getRowcount();
	}

	public int hashCode()
	{
		return pager.hashCode();
	}

	public int getFirst()
	{
		return pager.getFirst();
	}

	public boolean isInitial()
	{
		return pager.isInitial();
	}

	public void setFirst(int first)
	{
		pager.setFirst(first);
	}

	public void setInitial(boolean initial)
	{
		pager.setInitial(initial);
	}

	public void setPageno(int pageno)
	{
		pager.setPageno(pageno);
	}

	public void setRowcount(int rowcount)
	{
		pager.setRowcount(rowcount);
	}

	public String toString()
	{
		return pager.toString();
	}

	public int getPagenum()
	{
		return pager.getPagenum();
	}

	public int getPagesize()
	{
		return pager.getPagesize();
	}

	public void setPagenum(int pagenum)
	{
		pager.setPagenum(pagenum);
	}

	public void setPagesize(int pagesize)
	{
		pager.setPagesize(pagesize);
	}

	public byte getCmd()
	{
		return pager.getCmd();
	}

	public void setCmd(byte cmd)
	{
		pager.setCmd(cmd);
	}

	public Searcher getSearcher()
	{
		return searcher;
	}

	public void setSearcher(Searcher searcher)
	{
		this.searcher=searcher;
	}

	public String getBeginDate()
	{
		return searcher.getBeginDate();
	}

	public String getEndDate()
	{
		return searcher.getEndDate();
	}

	public byte getSearchType()
	{
		return searcher.getSearchType();
	}

	public String getSearchValue()
	{
		return searcher.getSearchValue();
	}

	public void setBeginDate(String beginDate)
	{
		searcher.setBeginDate(beginDate);
	}

	public void setEndDate(String endDate)
	{
		searcher.setEndDate(endDate);
	}

	public void setSearchType(byte searchType)
	{
		searcher.setSearchType(searchType);
	}

	public void setSearchValue(String searchValue)
	{
		searcher.setSearchValue(searchValue);
	}

}
