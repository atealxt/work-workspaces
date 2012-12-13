package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.simpleblog.dao.IAlbumCategoryDAO;
import com.easyjf.simpleblog.domain.AlbumCategory;
import com.easyjf.simpleblog.service.IAlbumCategoryService;
import com.easyjf.simpleblog.service.LogicException;
import com.easyjf.simpleblog.service.UserContext;
import com.easyjf.web.tools.IPageList;


/**
 * AlbumCategoryServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: AlbumCategoryServiceImpl.java,v 0.0.1 2008-1-15 9:40:03 EasyJWeb 1.0-m2 Exp $
 */
public class AlbumCategoryServiceImpl implements IAlbumCategoryService{
	
	private IAlbumCategoryDAO albumCategoryDao;
	
	public void setAlbumCategoryDao(IAlbumCategoryDAO albumCategoryDao){
		this.albumCategoryDao=albumCategoryDao;
	}
	
	public Long addAlbumCategory(AlbumCategory albumCategory) {	
		this.albumCategoryDao.save(albumCategory);
		if (albumCategory != null && albumCategory.getId() != null) {
			return albumCategory.getId();
		}
		return null;
	}
	
	public AlbumCategory getAlbumCategory(Long id) {
		AlbumCategory albumCategory = this.albumCategoryDao.get(id);
		return albumCategory;
		}
	
	public boolean delAlbumCategory(Long id) {
			if(!UserContext.isAdmin())throw new LogicException("无权限"); 
			AlbumCategory albumCategory = this.getAlbumCategory(id);
			if(albumCategory.getAlbums().size()>0)throw new LogicException("分类"+albumCategory.getName()+"下还有照片，不能删除！");
			if (albumCategory != null) {
				for(AlbumCategory c:albumCategory.getChildren())
					delAlbumCategory(c.getId());
				this.albumCategoryDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelAlbumCategorys(List<Serializable> albumCategoryIds) {
		for (Serializable id : albumCategoryIds) {
			delAlbumCategory((Long) id);
		}
		return true;
	}
	
	public IPageList getAlbumCategoryBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, AlbumCategory.class,this.albumCategoryDao);		
	}
	
	public boolean updateAlbumCategory(Long id, AlbumCategory albumCategory) {
		if (id != null) {
			albumCategory.setId(id);
		} else {
			return false;
		}
		this.albumCategoryDao.update(albumCategory);
		return true;
	}	
	
}
