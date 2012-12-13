package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.Link;
/**
 * LinkService
 * @author EasyJWeb 1.0-m2
 * $Id: LinkService.java,v 0.0.1 2008-1-15 9:40:26 EasyJWeb 1.0-m2 Exp $
 */
public interface ILinkService {
	/**
	 * 保存一个Link，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addLink(Link instance);
	
	/**
	 * 根据一个ID得到Link
	 * 
	 * @param id
	 * @return
	 */
	Link getLink(Long id);
	
	/**
	 * 删除一个Link
	 * @param id
	 * @return
	 */
	boolean delLink(Long id);
	
	/**
	 * 批量删除Link
	 * @param ids
	 * @return
	 */
	boolean batchDelLinks(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Link
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getLinkBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Link
	  * @param id 需要更新的Link的id
	  * @param dir 需要更新的Link
	  */
	boolean updateLink(Long id,Link instance);
}
