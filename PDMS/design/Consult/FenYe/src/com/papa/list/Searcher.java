package com.papa.list;

public class Searcher
{
	private byte searchType=0; // ��������

	private String searchValue=""; // ������ ����

	private String beginDate=""; // ������ʼ����

	private String endDate=""; // ������������
	
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
