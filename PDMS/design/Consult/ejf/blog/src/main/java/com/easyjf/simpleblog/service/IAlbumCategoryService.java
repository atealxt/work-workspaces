package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.AlbumCategory;
/**
 * AlbumCategoryService
 * @author EasyJWeb 1.0-m2
 * $Id: AlbumCategoryService.java,v 0.0.1 2008-1-15 9:40:03 EasyJWeb 1.0-m2 Exp $
 */
public interface IAlbumCategoryService {
	/**
	 * 保存一个AlbumCategory，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addAlbumCategory(AlbumCategory instance);
	
	/**
	 * 根据一个ID得到AlbumCategory
	 * 
	 * @param id
	 * @return
	 */
	AlbumCategory getAlbumCategory(Long id);
	
	/**
	 * 删除一个AlbumCategory
	 * @param id
	 * @return
	 */
	boolean delAlbumCategory(Long id);
	
	/**
	 * 批量删除AlbumCategory
	 * @param ids
	 * @return
	 */
	boolean batchDelAlbumCategorys(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到AlbumCategory
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getAlbumCategoryBy(IQueryObject queryObject);
	
	/**
	  * 更新一个AlbumCategory
	  * @param id 需要更新的AlbumCategory的id
	  * @param dir 需要更新的AlbumCategory
	  */
	boolean updateAlbumCategory(Long id,AlbumCategory instance);
}
