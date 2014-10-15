package org.thin.keyvalue.criteria;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class OrderBy implements Criterion {

	private Map orderItems= new LinkedHashMap();
	
	public OrderBy(String field, Order order) {
		this.orderItems.put(field, order);
	}

	public static OrderBy desc(String field){
		return new OrderBy(field,Order.DESC);
	}
	
	public static OrderBy asc(String field){
		return new OrderBy(field,Order.ASC);
	}
	
	public OrderBy append(String field,Order order){
		this.orderItems.put(field, order);
		return this;
	}
	
	
	public String toString(){
		String str = orderItems.toString();
		str = str.substring(1, str.length()-1);
		str = str.replaceAll("=", " ");
		return " ORDER BY "+str;
	}
	
}
