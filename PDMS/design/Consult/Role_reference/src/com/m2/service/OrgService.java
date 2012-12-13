package com.m2.service;

import java.util.List;
import com.m2.entity.Org;
import com.m2.exception.M2Exception;

public interface OrgService {
	
	public void addOrg(Org org) throws M2Exception;
	
	
	public void updateOrg(Org org)throws M2Exception;
	 
	/**
	 * @param org
	 * @return boolean  ɾ���ɹ�����true,����false,ע�����ӻ����Ĳ�����ɾ����
	 */
	
	public boolean removeOrg(Org org)throws M2Exception;
	
	/**
	 * @return List �������еĻ���
	 */
	public List findAllOrgs(); 
	
	
	
	public Org findOrgById(int id) throws M2Exception;
	

}
