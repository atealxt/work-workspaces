package org.thin.keyvalue.criteria;

import java.util.Collection;
import java.util.Iterator;


/**
 * 这个SQL中where条件的一个逻辑表达式。如：name='wanghh'  age>23
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class SQLCriterion implements Criterion {

	private static final long serialVersionUID = 1L;

	private String field;
	
	private Operator op;
	
	private Object value;

	public SQLCriterion(){
		
	}
	
	public SQLCriterion(String field,Operator op,Object value){
		this.field = field;
		this.op = op;
		this.value = value;
	}
	
	public SQLCriterion(String field,Object value){
		this.field = field;
		this.op = Operator.EQ;
		this.value = value;
	}
	
	public static SQLCriterion get(String field,Operator op,Object value){
		return new SQLCriterion(field,op,value);
	}
	
	public static SQLCriterion get(String field,Object value){
		return new SQLCriterion(field,value);
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	public String toString(){
		Object fvalue="";
		if(CharSequence.class.isAssignableFrom(this.value.getClass())){
			fvalue = "'"+this.value+"'";
		}else if(Collection.class.isAssignableFrom(this.value.getClass())){
			StringBuffer sb = new StringBuffer();
			Collection c = (Collection)this.value;
			Iterator it = c.iterator();
			while(it.hasNext()){
				Object obj = it.next();
				if(CharSequence.class.isAssignableFrom(obj.getClass())){
					sb.append("'"+obj+"',");
				}else{
					sb.append(obj.toString()+",");
				}
			}
			int length = sb.length();
			sb.deleteCharAt(length-1);
			fvalue = "("+sb.toString()+")";
		}else{
			fvalue=this.value;
		}
		return this.field+" "+this.op.getOperator()+" "+fvalue;
	}
	
	
}
