package com.m2.dao;

import com.m2.entity.*;

public interface OrgDAO extends BaseDAO{
	
	
	public Org addOrg(Org org);   //添加
	
	
	public void removeOrg(Org org); //删除
	
		
    public Org updateOrg(Org org); //修改
	
	
    public boolean hasChildForOrg(Org parent); //该机构parent有下属机构么
    
    
    public boolean hasOrgWithId(int id);          //存在这个id的机构么
    
    
    public boolean hasOrg();
    
  
	

}
