package com.papa.list;

public class Searcher
{
	private byte searchType=0; // 搜索类型

	private String searchValue=""; // 搜索关 键字

	private String beginDate=""; // 搜索开始日期

	private String endDate=""; // 搜索结束日期
	
	public String getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(String beginDate)
	{
		this.beginDate=beginDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate=endDate;
	}

	public byte getSearchType()
	{
		return searchType;
	}

	public void setSearchType(byte searchType)
	{
		this.searchType=searchType;
	}

	public String getSearchValue()
	{
		return searchValue;
	}

	public void setSearchValue(String searchValue)
	{
		this.searchValue=searchValue;
	}
}
