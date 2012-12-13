package com.m2.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.m2.entity.Org;
import com.m2.service.OrgService;
import com.m2.dao.OrgDAO;
import com.m2.exception.M2Exception;

public class OrgServiceImpl implements OrgService{
	
	private static final Log logger = LogFactory.getLog(OrgServiceImpl.class);
	
	private OrgDAO orgDAO;
	
	public OrgDAO getOrgDAO() {
		return orgDAO;
	}

	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	public void addOrg(Org org)throws M2Exception{
		try{
			if(!this.orgDAO.hasOrg()){   //还没有结点存在，第一个结点作为根结点。
				org.setParentId(-1);
				this.orgDAO.addOrg(org);
				return;
			}
			if ((org.getParentId()!=-1)   //添加子结点
			 &&(this.orgDAO.hasOrgWithId(org.getParentId()))){
		        this.orgDAO.addOrg(org);
		        return;
			}    
			if (org.getParentId()!=-1)
				throw new M2Exception("新增机构没有合法的上级机构");
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	public void updateOrg(Org org)throws M2Exception{
		try{
		    this.orgDAO.updateOrg(org);
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	public boolean removeOrg(Org org)throws M2Exception{
		try{
		    if (this.orgDAO.hasChildForOrg(org))
			    return false;
		    this.orgDAO.removeOrg(org);
		    return true;
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}

	}
	
	public List findAllOrgs(){ 
		List orgs = this.orgDAO.findAll("Org");
		return orgs;
	}
	
	
	public Org findOrgById(int id) throws M2Exception{
		try{
		    Org org 
		    = (Org)this.orgDAO.findById("Org", id);
		    return org;
		}catch(Exception e){
			logger.error(e);
			throw new M2Exception(e);
		}
	}
	
	
}
