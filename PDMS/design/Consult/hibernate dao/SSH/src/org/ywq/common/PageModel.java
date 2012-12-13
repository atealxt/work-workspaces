package org.ywq.common;

import java.util.List;

/**
 * @author ai5qiangshao  E-mail:ai5qiangshao@163.com
 * @version 创建时间：Aug 5, 2009 9:47:42 PM
 * @Package org.ywq.common
 * @Description 类说明
 */
public class PageModel<T> {
	private List<T> datas;

	private Integer total;
	
	private Integer totalPage=1;
	
	private Integer pagesize;
	
	private Integer currenPage=1;

	public Integer getCurrenPage() {
		return currenPage;
	}

	public void setCurrenPage(Integer currenPage) {
		this.currenPage = currenPage;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	

}
