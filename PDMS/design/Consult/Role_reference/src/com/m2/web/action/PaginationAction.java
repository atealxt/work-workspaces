package com.m2.web.action;

import java.util.Map;
import java.util.StringTokenizer;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.RequestAware;


/***
 * 
 * @author Augustan http://yuetong.javaeye.com
 * �����ṩ��һ��ֱ��ִ��sql����ҳ�ķ�װʵ��
 * ���ڲ�ͬ�����ݿ⣬��ҳ��sql���Ҫ�ֱ�ƴ��
 * 
 *
 */


public abstract class PaginationAction extends BaseAction implements RequestAware{
	
	private static final Log logger = LogFactory.getLog(PaginationAction.class);
	
	private int pageNo;  //��ǰҳ��
	
	private int pageSize =15; //ÿҳ����¼��
	
	private int totalSize; //�ܹ���¼��
	
	private int orderIndex; //����ҳ���ϵڼ��н��������ų������У�
	
	private int totalPageCount;//�ܹ�ҳ��
	
	private int start; 
	
	private int avaCount;//��ǰҳ�ļ�¼��	
	
	
	private String dataGrid;
	
	private String pageBar;
	
	
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Map request;

	public void setRequest(Map request) {
		this.request = request;
	}

	public Map getRequest() {
		return request;
	}	

	abstract public String[] getOperationURLs();//������������������ַ���ֱ��Ӧ�鿴���޸ĺ�ɾ��
	
	abstract public String[] getURLParameters(); //ÿ��������ַ����ҽӵĲ���
	
	abstract public String   getOrderField();   //�������ݿ�����һ�н�������
	
	abstract public String   getViewName();     //����ҳ��View,������һ����ѯ��� 
	
	abstract public String   getKeyId();        //����
	
	abstract public String[] getSQLFields();    //Ҫ��ʾ�����ݿ������
	 
	abstract public String[] getHeaders();      //�еı��⣬������һһ��Ӧ
		
	abstract public String   getWhere();        //ƴ�ղ�ѯ��where�Ӿ�  
	
	public int   getPageSize(){
		return 15;
	}
	
	public String   getResult(){   //ҳ�浼��
		
		return SUCCESS;
		
	}       
	
	
	public String execute(){
		
        try{
        	execPaginationForMySQL();
        	String result=getResult();	
        	return result;
        }catch(Exception e){
        	logger.error(e);
        	addActionError("ϵͳ����������");
        	return ERROR;
        }
		
	}
	
	/*
	 * ���������mysql�����з�ҳ���
	 * mysqlר�еķ�ҳ������磺
	 * 
	 *  select * from m2_user 
	 *  where id>100               
	 *  order by name asc 
	 *  limit 0,14 ;
	 *  ѡ����ɸѡ�������ĵ�1��15������
	 * 
	 * @throws Exception
	 */
	
	
	public void execPaginationForMySQL()  throws Exception{  
		
		
        try{
        	String curPage=(String)getRequest().get("pageno");
        	pageNo=Integer.valueOf(curPage);
        }catch(NumberFormatException e){
        	pageNo=1;
        }				
		String sql = null;
		Connection conn=null;
		Statement stmt =null;
		ResultSet rs=null;
		try{
		    conn = getDataSource().getConnection();
		    sql = " select count(*) from  "+getViewName()+" where "+getWhere()+";";
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    rs.next();
		    totalSize = rs.getInt(1);		    
		    if (totalSize==0) pageNo=1;	
    	    int startRecord = (pageNo-1)* getPageSize() + 1;
    	    int endRecord = startRecord + getPageSize() - 1;
    		String orderField=null;
    		String[]SQLFields=getSQLFields();
    		String[]operationURLs=getOperationURLs();		
    		int index=-1;
       		String order=null;
       		StringTokenizer st=null; 
       		String orderParam=(String)getRequest().get("orderParam");
       		if ((orderParam!=null)&&(!"".equals(orderParam))){
       			st=new StringTokenizer(orderParam,",");
       			try{
       			    index=Integer.parseInt(st.nextToken());
       			}catch(NumberFormatException e){
       				index=-1;
       			}
       			order=st.nextToken();
       		}    		
    		if (index!=-1){
    			if ((operationURLs!=null)&&(operationURLs.length>0)){
    				index--;
    			}
    			orderField=SQLFields[index]+" "+order;
    		}else{
    			orderField=getOrderField();
    		}    	    
    		StringBuffer sb = new StringBuffer(" select * from ");
    		sb.append(getViewName());
            String where =getWhere();
            if ((where!=null)&&!"".equals(where))
            	sb.append(" where ").append(where);
            sb.append(" order by ").append(orderField)
            .append(" limit ").append(startRecord-1).append(",").append(endRecord-1).append(";");
    	    sql=sb.toString();	 
    	    this.start=getStartOfAnyPage(pageNo, pageSize);
    	    this.totalPageCount = (totalSize + pageSize -1) / pageSize;
    	    if (totalPageCount<=0) 
    	    	totalPageCount=1;
            if(this.pageNo==this.totalPageCount){
            	this.avaCount=this.totalSize-(this.pageNo-1)*this.pageSize;
            }else if (this.totalPageCount==0) 
            	this.avaCount=0;
             else 
            	this.avaCount=pageSize;	 
    		String [] headers =getHeaders();     
        	boolean hasOperation=false;
        	if ((operationURLs!=null)&&(operationURLs.length>0)) hasOperation=true;
        	
        	sb=new StringBuffer();
        	sb.append("<input type='hidden' id='hasOperation' value='").append(hasOperation).append("'>");
        	if (orderIndex==-1)
        	    sb.append("<input type='hidden' id='orderParam' name='orderParam' >");
        	else
        		sb.append("<input type='hidden' id='orderParam' name='orderParam' value='")
        		.append(hasOperation?(orderIndex+1):orderIndex).append(",").append(order).append("'>");
        	int len=headers.length; 
        	sb.append("<TR>");
        	if (hasOperation){
        		sb.append("<TH width='60px' id='operation_th'>����</TH>");
        	}    	   
        	for(int i=0;i<len;i++){
        		if (i==orderIndex)
        			sb.append("<TH class='").append(order).append("'>").append(headers[i]).append("</TH>");
        		else
        			sb.append("<TH>").append(headers[i]).append("</TH>");
        	}
        	sb.append("</TR>");
        	len=SQLFields.length;
        	int i=0;
        	if (len==0){
        		this.dataGrid="";
        		return;
        	}    	    
        	String keyId=getKeyId();
        	String []URLParameters=getURLParameters();
    		rs=stmt.executeQuery(sql);
    		logger.info(sql);
    		while(rs.next()){
    			sb.append("<TR>");
    			if ((operationURLs!=null)&&(operationURLs.length>0)&&(keyId!=null)){
    				sb.append("<TD>");
    				for(int j=0;j<operationURLs.length;j++){
    					if ((j==0)&&(operationURLs[0]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[0]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[0].toString()).append("'>�鿴</a>");
    					}
    					if ((j==1)&&(operationURLs[1]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[1]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[1].toString()).append("'>�޸�</a>");
    					}
    					if ((j==2)&&(operationURLs[2]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[2]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[2].toString())
    						.append("' onclick=\"return confirm('ȷ��Ҫɾ��ô��')\"").append(">ɾ��</a>");
    					}    					
    				}
    				sb.append("</TD>");
    			}
    		   for(i=0;i<len;i++){
    		       sb.append("<TD><div class=divout>")
    		       .append(rs.getString(SQLFields[i])==null?"":rs.getString(SQLFields[i]))	
    			   .append("</div></TD>");
    		   }    			
    		   sb.append("</TR>");
    		}
    		this.dataGrid = sb.toString();
    		renderPageBar("submitForm", null);
		}catch(Exception e){
			logger.error(e);
			throw  e;
		}finally{
			closeAll(rs, stmt, conn);
		}
	    
	}
	
	
	
	public void execPaginationForSqlServer2005()throws Exception{  //���������sqlserver2005�ķ�ҳ���

		try{
        	String curPage=(String)getRequest().get("pageno");
        	pageNo=Integer.valueOf(curPage);
        }catch(NumberFormatException e){
        	pageNo=1;
        }				
		String sql = null;
		Connection conn=null;
		Statement stmt =null;
		ResultSet rs=null;
		try{
		    conn = getDataSource().getConnection();
		    sql = " select count(*) from  "+getViewName()+" where "+getWhere();
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    rs.next();
		    totalSize = rs.getInt(1);
		    if (totalSize==0) pageNo=1;	
    	    int startRecord = (pageNo-1)* getPageSize() + 1;
    	    int endRecord = startRecord + getPageSize() - 1;
    		String orderField=null;
    		String[]SQLFields=getSQLFields();
    		String[]operationURLs=getOperationURLs();		
    		int index=-1;
       		String order=null;
       		StringTokenizer st=null; 
       		String orderParam=(String)getRequest().get("orderParam");
       		if ((orderParam!=null)&&(!"".equals(orderParam))){
       			st=new StringTokenizer(orderParam,",");
       			try{
       			    index=Integer.parseInt(st.nextToken());
       			}catch(NumberFormatException e){
       				index=-1;
       			}
       			order=st.nextToken();
       		}    		
    		if (index!=-1){
    			if ((operationURLs!=null)&&(operationURLs.length>0)){
    				index--;
    			}
    			orderField=SQLFields[index]+" "+order;
    		}else{
    			orderField=getOrderField();
    		}    	    
    	    StringBuffer sb=new StringBuffer("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ");
    	    sb.append(orderField)
  	        .append(") AS ROWID,")
  	        .append("*").append(" FROM ")
  	        .append(getViewName());    	    
    	    String where = getWhere();
    	    if ((where!=null)&&!"".equals(where))
    	    	sb.append(" WHERE ").append(where);
    	    sb.append(") AS ")
    	    .append(getViewName())
    	    .append(" WHERE ROWID BETWEEN ")
    	    .append(startRecord).append(" AND ").append(endRecord); 
    	    sql=sb.toString();	 
    	    this.start=getStartOfAnyPage(pageNo, pageSize);
    	    this.totalPageCount = (totalSize + pageSize -1) / pageSize;
    	    if (totalPageCount<=0) 
    	    	totalPageCount=1;
            if(this.pageNo==this.totalPageCount){
            	this.avaCount=this.totalSize-(this.pageNo-1)*this.pageSize;
            }else if (this.totalPageCount==0) 
            	this.avaCount=0;
             else 
            	this.avaCount=pageSize;	 
    		String [] headers =getHeaders();     
        	boolean hasOperation=false;
        	if ((operationURLs!=null)&&(operationURLs.length>0)) hasOperation=true;
        	sb=new StringBuffer();
        	sb.append("<input type='hidden' id='hasOperation' value='").append(hasOperation).append("'>");
        	if (orderIndex==-1)
        	    sb.append("<input type='hidden' id='orderParam' name='orderParam' >");
        	else
        		sb.append("<input type='hidden' id='orderParam' name='orderParam' value='")
        		.append(hasOperation?(orderIndex+1):orderIndex)
        		.append(",").append(order).append("'>");
        	int len=headers.length; 
        	sb.append("<TR>");
        	if (hasOperation){
        		sb.append("<TH width='60px' id='operation_th'>����</TH>");
        	}    	   
        	for(int i=0;i<len;i++){
        		if (i==orderIndex)
        			sb.append("<TH class='").append(order).append("'>")
        			.append(headers[i]).append("</TH>");
        		else
        			sb.append("<TH>").append(headers[i]).append("</TH>");
        	}
        	sb.append("</TR>");
        	len=SQLFields.length;
        	int i=0;
        	if (len==0){
        		this.dataGrid="";
        		return;
        	}    	    
        	String keyId=getKeyId();
        	String []URLParameters=getURLParameters();
    		rs=stmt.executeQuery(sql);
    		while(rs.next()){
    			sb.append("<TR>");
    			if ((operationURLs!=null)&&(operationURLs.length>0)&&(keyId!=null)){
    				sb.append("<TD>");
    				for(int j=0;j<operationURLs.length;j++){
    					
    					if ((j==0)&&(operationURLs[0]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[0]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[0].toString()).append("'>�鿴</a>");
    					}
    					if ((j==1)&&(operationURLs[1]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[1]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[1].toString()).append("'>�޸�</a>");
    						
    					}
    					if ((j==2)&&(operationURLs[2]!=null)){
    						sb.append("&nbsp;<a href='")
    						.append(operationURLs[2]).append("?")
    						.append(keyId).append("=")
    						.append(rs.getString(keyId))
    						.append(URLParameters[2].toString())
    						.append("' onclick=\"return confirm('ȷ��Ҫɾ��ô��')\"").append(">�h��</a>");
    					}    					
    				}
    				sb.append("</TD>");
    			}
    			
    		   for(i=0;i<len;i++){
    		       sb.append("<TD><div class=divout>")
    		       .append(rs.getString(SQLFields[i])==null?"":rs.getString(SQLFields[i]))	
    			   .append("</div></TD>");
    		   }    			
    		   sb.append("</TR>");
    		}
    		this.dataGrid = sb.toString();
    		renderPageBar("submitForm", null);
		}catch(Exception e){
			logger.error(e);
			throw  e;
		}finally{
			closeAll(rs, stmt, conn);
		}
	}	
	
	
	private void renderPageBar(String queryJSFunctionName, String pageNoParamName){

  	  
        if (getTotalPageCount()<1){
            this.pageBar= "<input type='hidden' name='"+pageNoParamName+"' value='1' >";
            return;
        }
        if (queryJSFunctionName == null || queryJSFunctionName.trim().length()<1) {
            queryJSFunctionName = "gotoPage";
        }
        if (pageNoParamName == null || pageNoParamName.trim().length()<1){
            pageNoParamName = "pageno";
        }

        String gotoPage = "_"+queryJSFunctionName;

        StringBuffer html = new StringBuffer("\n");
        
        html.append("<script language=\"Javascript1.2\">\n")
             .append("function ").append(gotoPage).append("(pageNo){  \n")
             .append(  "   var curPage=1;  \n")
             .append(  "   try{ curPage = document.all[\"")
             .append(pageNoParamName).append("\"].value;  \n")
             .append(  "        document.all[\"").append(pageNoParamName)
             .append("\"].value = pageNo;  \n")

             .append(  "        ").append(queryJSFunctionName).append("(); \n")
             .append(  "        return true;  \n")
             .append(  "   }catch(e){ \n")

             .append(  "          alert('��δ�����ѯ������function ")
             .append(queryJSFunctionName).append("()'); \n")
             .append(  "          document.all[\"").append(pageNoParamName)
             .append("\"].value = curPage;  \n")
             .append(  "          return false;  \n")

             .append(  "   }  \n")
             .append(  "}")
             .append(  "</script>  \n")
             .append(  "");
        html.append( "<table  border=0 cellspacing=0 cellpadding=0 align=left width=100%>  \n")
             .append( "  <tr>  \n")
             .append( "    <td align=left class='font-size:12px'WIDTH=100%>  ");
        html.append(  "       ��<font color=red>" ).append( getTotalPageCount() ).append( "</font>ҳ")
             .append(  "       [") .append(getStart()).append("..").append(getEnd())
             .append("/").append(this.getTotalSize()).append("] ")
             .append( "    ")
             .append( " &nbsp;&nbsp;\n");
        if (hasPreviousPage()){
             html.append( "[<a href='javascript:").append(gotoPage)
             .append("(") .append(getCurrentPageNo()-1) 
             .append( ")'>��һҳ</a>]   \n");
        }else{
        	html.append("[��һҳ]&nbsp;");
        }
        int startPageNo=getStartPageNo();
        int endPageNo=getEndPageNo();
        
        for(int i=startPageNo;i<=endPageNo;i++){
        	if (i==pageNo){
        		html.append("<font color=red>"+i+"</font>&nbsp;");
        		
        	}else{
        		html.append("<a  href='javascript:onclick=")
        		.append(gotoPage)
        		.append("(").append(i).append(")';>")
        		.append(i).append("</a>&nbsp;");
        	}
        }
        html.append("<input type='hidden' name='")
        .append(pageNoParamName).append("'>\n");

        if (hasNextPage()){
             html.append( "    [<a href='javascript:")
             .append(gotoPage) 
             .append("(").append((getCurrentPageNo()+1)) 
             .append( ")'>��һҳ</a>]   \n");
        }else{
        	html.append("&nbsp;[��һҳ]");
        }
        html.append("��<input type='text'  class='input' name='topage' value='")
        .append(pageNo+"'>ҳ")
        .append("<input type=button class='button' value='��ת' size=3 height=3 onclick=\"")
        .append(gotoPage)
        .append("(document.all['topage'].value);\">");
        
        html.append( "</td></tr></table>  \n");

        this.pageBar= html.toString();    	
    	
    }
	
	
	
	private int getStart(){
  	    if (totalSize==0) return 0;
        return start;
    }

	private int getEnd(){
  	    int end = this.getStart() + this.getSize() -1;
        if (end<0) {
            end = 0;
        }
        return end;
    }
	private int getSize() {
        return avaCount;
    }    	
	
	private int getTotalSize() {
        return this.totalSize;
    }


	private int getCurrentPageNo(){
        return  this.pageNo;
    }

	private int getTotalPageCount(){
        return this.totalPageCount;
    }
	private int getStartPageNo(){
    	return ((pageNo-1)/10)*10+1;
    }	

		
	private boolean hasNextPage() {
        return (this.getCurrentPageNo()<this.getTotalPageCount());
    }
	private boolean hasPreviousPage() {
        return (this.getCurrentPageNo()>1);
    }
    
    
	private  int getEndPageNo(){
      	return Math.min(totalPageCount,(pageNo-1)/10*10+10);
    }	
	


	private  int getStartOfAnyPage(int pageNo, int pageSize){
        int startIndex = (pageNo-1) * pageSize + 1;
        if ( startIndex < 1) startIndex = 1;
        return startIndex;
    }    
    	
	public  void closeAll(ResultSet rs,Statement stmt,Connection conn){
		try{
			if (rs!=null){
				rs.close();
				rs=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			if (conn!=null){
				conn.close();
				conn=null;
			}
		}catch(SQLException e){
			logger.error("close failed ",e);
		}
		
		
	}

	public String getDataGrid() {
		return dataGrid;
	}

	public void setDataGrid(String dataGrid) {
		this.dataGrid = dataGrid;
	}

	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}	
	
	
	
	
}
