package com.easyjf.simpleblog.service;

import java.io.Serializable;
import java.util.List;
import com.easyjf.web.tools.IPageList;
import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.simpleblog.domain.Album;
/**
 * AlbumService
 * @author EasyJWeb 1.0-m2
 * $Id: AlbumService.java,v 0.0.1 2008-1-15 9:39:05 EasyJWeb 1.0-m2 Exp $
 */
public interface IAlbumService {
	/**
	 * 保存一个Album，如果保存成功返回该对象的id，否则返回null
	 * 
	 * @param instance
	 * @return 保存成功的对象的Id
	 */
	Long addAlbum(Album instance);
	
	/**
	 * 根据一个ID得到Album
	 * 
	 * @param id
	 * @return
	 */
	Album getAlbum(Long id);
	
	/**
	 * 删除一个Album
	 * @param id
	 * @return
	 */
	boolean delAlbum(Long id);
	
	/**
	 * 批量删除Album
	 * @param ids
	 * @return
	 */
	boolean batchDelAlbums(List<Serializable> ids);
	
	/**
	 * 通过一个查询对象得到Album
	 * 
	 * @param properties
	 * @return
	 */
	IPageList getAlbumBy(IQueryObject queryObject);
	
	/**
	  * 更新一个Album
	  * @param id 需要更新的Album的id
	  * @param dir 需要更新的Album
	  */
	boolean updateAlbum(Long id,Album instance);
}
