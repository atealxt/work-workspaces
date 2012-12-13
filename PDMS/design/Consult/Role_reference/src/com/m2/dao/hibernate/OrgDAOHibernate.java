package com.m2.dao.hibernate;

import java.util.List;
import com.m2.entity.Org;
import com.m2.dao.OrgDAO;


public class OrgDAOHibernate extends BaseDAOHibernate implements OrgDAO{
	
	public static final String HQL_CHILD_AMOUNT = " select count(*) from Org a where a.parentId=?";
	
	public static final String HQL_ORG_AMOUNT = " select count(*) from Org ";
	
	public static final String HQL_HAS_ORG = " select count(*) from Org a where a.id=?";
	
	public Org addOrg(Org org){
		save(org);
		return org;
	}
		
	public void removeOrg(Org org){
		delete(org);
	}
		
    public Org updateOrg(Org org){
    	update(org);
    	return org;
    }
    
	public  boolean hasChildForOrg(Org parent){ 
		List list=findByParam(HQL_CHILD_AMOUNT,parent.getId());
		long count=(Long)list.get(0);
		return count>0?true:false;
	}	
	
	public boolean hasOrgWithId(int id){
		List list=findByParam(HQL_HAS_ORG,id);
		long count=(Long)list.get(0);
		return count>0?true:false;
	}
	    
	public boolean hasOrg(){
		List list=getHibernateTemplate().find(HQL_ORG_AMOUNT);
		long count=(Long)list.get(0);
		return count>0?true:false;
	}
    
}
