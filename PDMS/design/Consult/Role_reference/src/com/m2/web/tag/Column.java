package com.m2.web.tag;

import java.io.Writer;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;

public class Column extends Component{
	
	protected String header;
	
	protected String field;
		
	protected String type;
	
	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public String getHeader() {
		return header;
	}



	public void setHeader(String header) {
		this.header = header;
	}
	
		


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Column(ValueStack arg0) {
		super(arg0);
	}	
	
	
	public boolean start(Writer writer) {
		
		boolean result = super.start(writer);
		
		Component com= this.findAncestor(DataGridRenderer.class);
		ColumnsAppender appender=(ColumnsAppender)com;
		appender.addColumn(this);
		
		return result;
		
		
	}
	
	
	

}
