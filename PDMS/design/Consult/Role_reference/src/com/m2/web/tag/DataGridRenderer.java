package com.m2.web.tag;

import java.util.*;
import java.io.Writer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.m2.service.PaginationService;
import com.m2.common.Util;

/**
 * 
 * @author  Augustan    http://www.m2-soft.com  
 *
 */

public class DataGridRenderer extends Component implements ColumnsAppender{
	
	private static final Log logger = LogFactory.getLog(DataGridRenderer.class);
	
	private List columns=new ArrayList();
	
	private String name;
	
	private String styleClass;
	
	private String bindingValue;
	
	private String resource;
	
	@SuppressWarnings("unchecked")   
	public void addColumn(Column col){
		this.columns.add(col);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	
	public String getBindingValue() {
		return bindingValue;
	}

	public void setBindingValue(String bindingValue) {
		this.bindingValue = bindingValue;
	}
	
	

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public DataGridRenderer(ValueStack arg0){
		super(arg0);
	}
	
	
	public boolean end(Writer writer,String body){      //标签结束时渲染整个datagrid
		
		if (bindingValue == null) {
			bindingValue = "pagination";
		} else if (altSyntax()) {
			if (bindingValue.startsWith("%{") && bindingValue.endsWith("}")) {
				bindingValue = bindingValue.substring(2, bindingValue.length() - 1);
			}
		}		
		PaginationService pagination = (PaginationService) this.getStack().findValue(bindingValue);		
		try{
		    pagination.executePagination();
		}catch(RuntimeException e){
			logger.error(e);
			throw e;
		}
		
		int len = pagination.getResult().size();
		StringBuffer sb =new StringBuffer("<table cellpadding=\"3\" cellspacing=\"0\" class='");
		sb.append(this.getStyleClass())
		  .append("' width=\"100%\" align=\"center\" id=\"")
		  .append(this.getName())
		  .append("\">");
		
		renderOrderInfo(pagination.getOrderType(),pagination.getOrderField(),sb);
		sb.append("<tr>");
		for (int i=0;i<this.columns.size();i++){
			renderHeader((Column)columns.get(i),i,sb,pagination.getOrderField(),pagination.getOrderType());
		}
		sb.append("</tr>");
		
		for (int index=0;index<len;index++){
			sb.append("<tr>");
			for (int j=0;j<this.columns.size();j++){
				Column col=(Column)columns.get(j);
				renderCell(col,index,sb);
			}
			sb.append("</tr>");
		}
		sb.append("</table>")	;
		try{
		    writer.write(sb.toString());
		}catch(Exception e){
			logger.error(e);
		}
		return false;
	}
	
	
	/***
	 * 
	 * @param op
	 * @param imgURL
	 * @return
	 * @todo  输出对应操作的图标地址。
	 */
	
	
	private String getOperationImgURL(Operation op,String imgURL){
		
	    String type = op.getType();
	    if ("view".equalsIgnoreCase(type)){
	    	return imgURL+"viewdetail.gif";
	    }
	    if ("update".equalsIgnoreCase(type)){
	    	return imgURL+"edit.gif";
	    }
	    if ("del".equalsIgnoreCase(type)){
	    	return imgURL+"delete.gif";
	    }
	    if ("setrole".equalsIgnoreCase(type)){
	    	return imgURL+"setrole.gif";
	    }
	    return imgURL+"edit.gif"; 
		
	}
	private void renderOrderInfo(String orderType,String field,StringBuffer sb){
		if (field==null) field="id";
		if (orderType==null) orderType="asc";
		String  orderInfo = "_"+field+":"+orderType;
		sb.append("<input type=\"hidden\" name=\"orderInfo\" id=\"orderInfo\" value=\"")
		  .append(orderInfo)
		  .append("\"");
	}
	
	
	private void renderHeader(Column column,int index,StringBuffer sb,String orderField,String orderType){
		String field = column.getField();

		if (column instanceof OperationColumn)
			sb.append("<th id=\"operationTh\" >");
		else if (!field.equalsIgnoreCase(orderField))
			sb.append("<th id=\"_")
			  .append(field).append("\"")
			  .append(">");
		else
			sb.append("<th id=\"_")
			  .append(field).append("\"")
			  .append("class=\"")
			  .append(orderType)
			  .append("\">");
		sb.append(column.getHeader());
		sb.append("</th>");
		
	}
	
	private String renderCell(Column column,int index,StringBuffer sb){                   //输出第index行列column的单元格
		
		if (column instanceof OperationColumn){
			return renderOperationCell((OperationColumn)column,index,sb);
		}
	    sb.append("<td><div class=\"divOut\">");
	    String OGNL=bindingValue+".result["+index+"]."+column.getField();
	    Object value=this.getStack().findValue(OGNL);
	    String strValue=null;
	    if(value==null)
        	strValue="";
	    else
	    	strValue=value.toString();
	    sb.append(strValue);
		sb.append("</div></td>");
		return sb.toString();
	}
	
	
	private String renderOperationCell(OperationColumn column,int index,StringBuffer sb){   //输出"操作"的单元格   
		
		sb.append("<td>");
		String OGNL=this.bindingValue+".result["+index+"]."+column.getField();
		String value=this.getStack().findValue(OGNL).toString();
		List ops = column.getOperations();
		String imgResourceURL = this.getResource()+"/img/";
		for (int i=0;i<ops.size();i++){
			Operation op=(Operation)ops.get(i);
			String url=Util.getActionURLWithoutSuffix(op.getAction());
		    sb.append("&nbsp;<a href=\"")
		      .append(url)
		      .append("&")
		      .append(column.getParam())
		      .append("=")
		      .append(value)
		      .append("\">")
		      .append("<img border=\"0\"")
		      .append(" src=\"")
		      .append(getOperationImgURL(op,imgResourceURL))
		      .append("\"")
		      .append("alt=\"")
		      .append(op.getInfo())
		      .append("\"")
		      .append(" />")
		      .append("</a>");
		}		
		sb.append("</td>");
		return sb.toString();
	}

}
