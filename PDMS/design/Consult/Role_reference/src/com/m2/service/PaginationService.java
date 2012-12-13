package com.m2.service;

import java.util.Map;
import java.util.List;

/****
 * 
 * @author Augustan
 *  
 */

public interface PaginationService {
	
	public void setCurrentPage(int currentPage) ;
	
	public void setPageSize(int pageSize) ;
		
	public void setConditions(Map conditions);
	
	public void setOrderField(String orderField);
	
	public void setOrderType(String orderType);
	
	public void setClazz(Class clazz);
	
	public String getOrderType();
	
	public String getOrderField();
		
	public int  calTotalCount();	
	
	public List getResult();
		
	public String renderPageBar(String queryJSFunction,String styleClass);
	
	public void executePagination();    //Ö´ÐÐ·ÖÒ³Âß¼­
	

}
