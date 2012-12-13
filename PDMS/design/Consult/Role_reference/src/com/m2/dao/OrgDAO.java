package com.m2.dao;

import com.m2.entity.*;

public interface OrgDAO extends BaseDAO{
	
	
	public Org addOrg(Org org);   //���
	
	
	public void removeOrg(Org org); //ɾ��
	
		
    public Org updateOrg(Org org); //�޸�
	
	
    public boolean hasChildForOrg(Org parent); //�û���parent����������ô
    
    
    public boolean hasOrgWithId(int id);          //�������id�Ļ���ô
    
    
    public boolean hasOrg();
    
  
	

}
