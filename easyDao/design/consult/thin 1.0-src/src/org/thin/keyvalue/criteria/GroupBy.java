package org.thin.keyvalue.criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class GroupBy implements Criterion {

	private List<String> fields= new ArrayList();
	
	public GroupBy(String... fields) {
		this.fields.addAll(Arrays.asList(fields));
	}

	public static GroupBy by(String... fields){
		return new GroupBy(fields);
	}
	
	public String toString(){
		String str = fields.toString();
		str = str.substring(1, str.length()-1);
		return " GROUP BY "+str;
	}
	
}
