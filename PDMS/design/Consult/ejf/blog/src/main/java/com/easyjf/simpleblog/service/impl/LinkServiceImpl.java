package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.web.tools.IPageList;
import com.easyjf.simpleblog.domain.Link;
import com.easyjf.simpleblog.service.ILinkService;
import com.easyjf.simpleblog.dao.ILinkDAO;


/**
 * LinkServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: LinkServiceImpl.java,v 0.0.1 2008-1-15 9:40:26 EasyJWeb 1.0-m2 Exp $
 */
public class LinkServiceImpl implements ILinkService{
	
	private ILinkDAO linkDao;
	
	public void setLinkDao(ILinkDAO linkDao){
		this.linkDao=linkDao;
	}
	
	public Long addLink(Link link) {	
		this.linkDao.save(link);
		if (link != null && link.getId() != null) {
			return link.getId();
		}
		return null;
	}
	
	public Link getLink(Long id) {
		Link link = this.linkDao.get(id);
		return link;
		}
	
	public boolean delLink(Long id) {	
			Link link = this.getLink(id);
			if (link != null) {
				this.linkDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelLinks(List<Serializable> linkIds) {
		
		for (Serializable id : linkIds) {
			delLink((Long) id);
		}
		return true;
	}
	
	public IPageList getLinkBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Link.class,this.linkDao);		
	}
	
	public boolean updateLink(Long id, Link link) {
		if (id != null) {
			link.setId(id);
		} else {
			return false;
		}
		this.linkDao.update(link);
		return true;
	}	
	
}
