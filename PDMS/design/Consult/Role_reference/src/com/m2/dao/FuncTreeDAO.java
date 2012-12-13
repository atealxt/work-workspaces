package com.m2.dao;


public interface FuncTreeDAO extends BaseDAO{
	
	static final String FIND_NODE_EXIST="select count(*) from FuncTree t where t.id=? ";
	
	static final String AMOUNT_OF_CHILDS="select count(*) from FuncTree t where t.parentId=?";
	
	

}
