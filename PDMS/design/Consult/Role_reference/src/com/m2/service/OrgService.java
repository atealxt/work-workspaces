package com.m2.service;

import java.util.List;
import com.m2.entity.Org;
import com.m2.exception.M2Exception;

public interface OrgService {
	
	public void addOrg(Org org) throws M2Exception;
	
	
	public void updateOrg(Org org)throws M2Exception;
	 
	/**
	 * @param org
	 * @return boolean  删除成功返回true,否则false,注意有子机构的不允许删除。
	 */
	
	public boolean removeOrg(Org org)throws M2Exception;
	
	/**
	 * @return List 返回所有的机构
	 */
	public List findAllOrgs(); 
	
	
	
	public Org findOrgById(int id) throws M2Exception;
	

}
