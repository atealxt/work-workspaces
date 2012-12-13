package com.m2.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.m2.service.PaginationService;
import com.m2.common.Constant;
import com.m2.dao.BaseDAO;

public class PaginationServiceImpl implements PaginationService{
	
	private static final Log logger = LogFactory.getLog(PaginationServiceImpl.class);
	
	private int currentPage = 0;
	
	private int totalCount  = -1;
	
	private int pageSize    = Constant.PAGESIZE_DEFAULT; 
	
	private String orderType= Constant.ORDER_DESC;
	
	private String orderField ;
	
	private Class clazz;
	
	private List result;
	
	private String pageBar;
	
	
	private BaseDAO baseDAO ;
	
	private Map conditions = new HashMap();
	
	

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	public Map getConditions() {
		return conditions;
	}

	public void setConditions(Map conditions) {
		this.conditions = conditions;
	}



	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
	

	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}

	public Class getClazz() {
		return clazz;
	}
	
	



	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	
	
	public int  calTotalCount(){
		
		if (this.totalCount!=-1)
			return this.totalCount;
		else{
			this.totalCount=this.baseDAO.calculateAmount(getClazz(), getConditions());
			return this.totalCount;
		}
		
	}
	
	
	public int getTotalPageCount(){
		int pageCount = calTotalCount() /this.pageSize ;
		if (calTotalCount()%this.pageSize!=0) 
			pageCount+=1;
		return pageCount;
		
	}	
	
    public  int getStartOfAnyPage(){
        int startIndex = (currentPage-1) * pageSize + 1;
        if ( startIndex < 1) startIndex = 1;
        return startIndex;
    }    	
	

	public void executePagination(){
		
		if (this.currentPage<=0)
			this.result =null;
		
		int pageCount = getTotalPageCount();

		if (this.currentPage >pageCount)
			this.currentPage = pageCount;
		
		final int start = (this.currentPage-1)*this.pageSize;
		
		try{
		this.result = this.baseDAO.filter(
				getClazz(), 
				getConditions(),
				getOrderType(), 
				getOrderField(), 
				start, 
				getPageSize()
	   );
		}catch(Exception e){
			logger.error(e);
		}
		
	}
	
	/***
	 * 
	 * @param queryJSFunctionName   引发页面提交的javascript函数名称
	 * @return
	 */
	
	
	public String renderPageBar(String queryJSFunctionName,String styleClass){
		
		if (this.getPageBar()!=null) 
			return this.getPageBar();
		
		int totalSize = this.calTotalCount();   //计算总数
		int pageCount = getTotalPageCount();

		int start = getStartOfAnyPage();
		int avaCount;
        if(currentPage==pageCount){
        	avaCount=totalSize-(currentPage-1)*this.pageSize;
        }else if (totalSize==0) 
        	avaCount=0;
         else 
        	avaCount=this.pageSize;			
  	    int end = start + avaCount -1;
        if (end<0) {
            end = 0;
        }
		if (styleClass==null) styleClass="dataGrid";
        
		String pageNoParamName = "pageno";
		
        if (queryJSFunctionName == null || queryJSFunctionName.trim().length()<1) {
            queryJSFunctionName = "submitForm";   //如果页面标签未定义提交的函数，则默认函数是"submitForm()"
        }
        if (pageNoParamName == null || pageNoParamName.trim().length()<1){
            pageNoParamName = "pageno";
        }

        String gotoPage = "_"+queryJSFunctionName;

        StringBuffer html = new StringBuffer("\n");
        html.append("<script language=\"javascript\">\n")
             .append("function ").append(gotoPage).append("(pageNo){  \n")
             .append(  "   var curPage=1;  \n")
             .append(  "   try{ curPage = document.all[\"")
             .append(pageNoParamName).append("\"].value;  \n")
             .append(  "    document.getElementById(\"").append(pageNoParamName)
             .append("\").value = pageNo;  \n")

             .append(  "   ").append(queryJSFunctionName).append("(); \n")
             .append(  "   return true;  \n")
             .append(  "   }catch(e){ \n")

             .append(  "  alert(\"尚未定义查询方法：function ")
             .append(queryJSFunctionName).append("()\"); \n")
             .append(  "  document.all[\"").append(pageNoParamName)
             .append("\"].value = curPage;  \n")
             .append(  "  return false;  \n")

             .append(  "  } \n")
             .append(  "}")
             .append(  "</script>  \n");
             //.append(  "<div class=\"").append(styleClass).append("\">");
        html.append( "<table  border=0 cellspacing=0 cellpadding=0 align=left width=100%>  \n")
             .append( "  <tr>  \n")
             .append( "    <td align=left width=\"100%\">  ");
        html.append(  "    共<font color=red>" )
             .append(pageCount)
             .append( "</font>页")
             .append(  "   [") 
             .append(start)
             .append("..")
             .append(end)
             .append("/")
             .append(totalSize)
             .append("] ")
             .append( "   ")
             .append( " &nbsp;&nbsp;\n");
        if (currentPage>1){
             html.append( "[<a href=\"javascript:").append(gotoPage)
             .append("(") .append(currentPage-1) 
             .append( ")\">上一页</a>]   \n");
        }else{
        	html.append("[上一页]&nbsp;");
        }
        int startPageNo=((currentPage-1)/10)*10+1;
        int endPageNo=Math.min(pageCount,(currentPage-1)/10*10+10);
        
        for(int i=startPageNo;i<=endPageNo;i++){
        	if (i==currentPage){
        		html.append("<font color=red>"+i+"</font>&nbsp;");
        		
        	}else{
        		html.append("<a  href=\"javascript:onclick=")
        		.append(gotoPage)
        		.append("(")
        		.append(i)
        		.append(")\";>")
        		.append(i)
        		.append("</a>&nbsp;");
        	}
        }
        html.append("<input type=\"hidden\" name=\"")
            .append(pageNoParamName)
            .append("\" id=\"")
            .append(pageNoParamName)
            .append("\">\n");

        if (currentPage<pageCount){
            html.append( "    [<a href=\"javascript:")
            .append(gotoPage) 
            .append("(").append((currentPage+1)) 
            .append( ")\">下一页</a>]   \n");
        }else{
        	html.append("&nbsp;[下一页]");
        }
        html.append("第<input type=\"text\" class=\"input\"  name=\"topage\" value=\"")
        .append(currentPage+"\">页")
        .append("<input type=button  value=\"跳转\" class=\"button\" height=3 onclick=\"")
        .append(gotoPage)
        .append("(document.all['topage'].value);\">");
        
        html.append( "</td></tr></table>");
        
      //  html.append( "</div> \n");
        String pageBar= html.toString(); 
        this.setPageBar(pageBar);
        return pageBar;
	}
			

	
	
	

}
