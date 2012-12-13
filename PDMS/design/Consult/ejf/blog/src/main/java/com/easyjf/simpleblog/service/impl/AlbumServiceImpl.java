package com.easyjf.simpleblog.service.impl;
import java.io.Serializable;
import java.util.List;

import com.easyjf.core.support.query.IQueryObject;
import com.easyjf.core.support.query.QueryUtil;
import com.easyjf.web.tools.IPageList;
import com.easyjf.simpleblog.domain.Album;
import com.easyjf.simpleblog.service.IAlbumService;
import com.easyjf.simpleblog.service.LogicException;
import com.easyjf.simpleblog.dao.IAlbumDAO;


/**
 * AlbumServiceImpl
 * @author EasyJWeb 1.0-m2
 * $Id: AlbumServiceImpl.java,v 0.0.1 2008-1-15 9:39:05 EasyJWeb 1.0-m2 Exp $
 */
public class AlbumServiceImpl implements IAlbumService{
	
	private IAlbumDAO albumDao;
	
	public void setAlbumDao(IAlbumDAO albumDao){
		this.albumDao=albumDao;
	}
	
	public Long addAlbum(Album album) {	
		this.albumDao.save(album);
		if (album != null && album.getId() != null) {
			return album.getId();
		}
		return null;
	}
	
	public Album getAlbum(Long id) {
		Album album = this.albumDao.get(id);
		return album;
		}
	
	public boolean delAlbum(Long id) {	
			Album album = this.getAlbum(id);
			if(album.getComments().size()>0)throw new LogicException("该照片下还有评论，不能删除！");
			if (album != null) {
				this.albumDao.remove(id);
				return true;
			}			
			return false;	
	}
	
	public boolean batchDelAlbums(List<Serializable> albumIds) {
		
		for (Serializable id : albumIds) {
			delAlbum((Long) id);
		}
		return true;
	}
	
	public IPageList getAlbumBy(IQueryObject queryObject) {	
		return QueryUtil.query(queryObject, Album.class,this.albumDao);		
	}
	
	public boolean updateAlbum(Long id, Album album) {
		if (id != null) {
			album.setId(id);
		} else {
			return false;
		}
		this.albumDao.update(album);
		return true;
	}	
	
}
