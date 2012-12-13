package com.m2.web.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import com.m2.service.PaginationService;
import com.m2.common.Util;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionContext;


/****
 * 
 * @author http://yuetong.javaeye.com
 * 所有分页的action都继承该类（底层使用hibernate分页）
 */


public abstract class BasePagination extends BaseAction implements RequestAware{
	
	private PaginationService pagination ;         
	
    private Map request;

	
	public PaginationService getPagination() {
		return pagination;
	}

	public void setPagination(PaginationService pagination) {
		this.pagination = pagination;
	}


	public Map getConditions(){return null;}    //构造查询的条件
		
	abstract public Class   getClazz();        //待分页的类
	
	public Map getRequest() {
		return request;
	}

	public void setRequest(Map request) {
		this.request = request;
	}

	public String execute(){
		
		ActionContext ac=ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
		String currentPage = request.getParameter("pageno");
		String orderInfo = request.getParameter("orderInfo"); //该参数包含了按照哪一个字段进行何种方式排序的信息
		
		int curPage =Util.parseInt(currentPage);
		if (curPage==-1) curPage = 1;
		this.pagination.setCurrentPage(curPage);
		
		String orderField="_id";
		String order=null;
		StringTokenizer st=null; 

		if ((orderInfo!=null)&&(!"".equals(orderInfo))){
			st=new StringTokenizer(orderInfo,":");
			if (st.hasMoreTokens())
			    orderField =st.nextToken();         //排序的字段
            if (st.hasMoreTokens())
			    order=st.nextToken();               //排序的类型（升序还是降序）
		}		
		orderField = orderField.substring(1);
		this.pagination.setClazz(getClazz());      
		this.pagination.setOrderField(orderField);
		this.pagination.setOrderType(order);
		this.pagination.setConditions(getConditions());		
		return SUCCESS;
		
	}
	
	
	
	
	

}
