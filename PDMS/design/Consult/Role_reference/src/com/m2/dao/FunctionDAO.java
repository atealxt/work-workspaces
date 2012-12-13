package com.m2.dao;

public interface FunctionDAO  extends BaseDAO{

	static final String FIND_FUNTIONS_BY_TREE_NODE=" from Function f where f.funcTree=?";
	
	static final String FIND_FUNTIONS_ALL=" from Function f where 1=1";
	
	static final String AMOUNT_OF_FUNC="select count(*) from Function f where f.funcTree=?";
	
	static final String AMOUNT_OF_FUNC_TREE="select count(*) from FuncTree f ";

}
