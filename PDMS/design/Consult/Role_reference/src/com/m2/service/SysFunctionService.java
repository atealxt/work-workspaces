package com.m2.service;

import java.util.*;
import com.m2.entity.FuncTree;
import com.m2.entity.Function;
import com.m2.exception.M2Exception;

public interface SysFunctionService {

	
	public void addFuncTreeNode(FuncTree treeNode)throws M2Exception;
	
	
	public void updateFuncTreeNode(FuncTree treeNode)throws M2Exception;
	
	
	public void removeFuncTreeNode(FuncTree treeNode)throws M2Exception;
		
	
	public boolean isLeaf(FuncTree treeNode) throws M2Exception;
	
	
	public boolean hasSubFunctions(FuncTree treeNode) throws M2Exception;
	
	
	public boolean hasFuncTreeNode() throws M2Exception;

	
	public List findAllFuncTreeNodes()throws M2Exception;
	
	
	public List findAllChildNodes(FuncTree parentNode)throws M2Exception;
	
	
	public FuncTree findByFuncTreeId(int id)throws M2Exception;
	
	
	
	
	public Function findByFunctionId(int id)throws M2Exception;
	
	
	

    public List findFunctionsByTreeNode(FuncTree treeNode) throws M2Exception;
    
    
    public List findAllFunctions() throws M2Exception;
    
	
    public void addFunction(Function func)throws M2Exception;
    	
    
    public void updateFunction(Function func)throws M2Exception;
    
    
    public void removeFunction(Function func)throws M2Exception;
    
    
    public String  createXMLStringForRoleSet(int roleId)throws M2Exception;
    

}
