package org.thin.keyvalue.criteria;

/**
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class Pagination implements Criterion {

	private int pageIndex=1;
	private int totalPage;
	private int pageNum;
	private int pageSize;


	public int getBegin() {
		return (pageIndex-1) * pageSize + 1;
	}

	public int getEnd() {
		return this.getBegin()+pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
