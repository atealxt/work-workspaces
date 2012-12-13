package com.easyjf.simpleblog.mvc;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

import com.easyjf.container.annonation.Action;
import com.easyjf.container.annonation.Inject;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.Album;
import com.easyjf.simpleblog.query.AlbumQuery;
import com.easyjf.simpleblog.service.IAlbumService;
import com.easyjf.util.CommUtil;
import com.easyjf.util.FileUtil;
import com.easyjf.web.Globals;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.tools.IPageList;

/**
 * AlbumAction
 * 
 * @author EasyJWeb 1.0-m2 $Id: AlbumAction.java,v 0.0.1 2008-1-15 9:39:05
 *         EasyJWeb 1.0-m2 Exp $
 */
@Action
public class AlbumAction extends BaseAction {
	@Inject
	private IAlbumService service;

	/*
	 * set the current service return service
	 */
	public void setService(IAlbumService service) {
		this.service = service;
	}

	public Page doSave(WebForm form) {
		Album album = form.toPo(Album.class);
		parseFile(form, album);
		if(!hasErrors())
		{
			this.service.addAlbum(album);
		}
		Page page = pageForExtForm(form);
		page.setContentType("html");
		return page;
	}

	public Page doUpdate(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Album album = service.getAlbum(id);
		form.toPo(album, true);
		parseFile(form, album);
		if (!hasErrors())
			service.updateAlbum(id, album);
		Page page = pageForExtForm(form);
		page.setContentType("html");
		return page;
	}

	private void parseFile(WebForm form, Album album) {
		FileItem item = (FileItem) form.getFileElement().get("pathFile");
		if (item != null) {
			String fileName = item.getName();
			if ("".equals(fileName)) {
				if ("".equals(album.getPath()))
					this.addError("pathFile", "请选择要上传的文件!");
			} else {
				if (FileUtil.isImgageFile(fileName)
						|| FileUtil.isAttacheFile(fileName))

				{
					String ext = fileName.substring(fileName.lastIndexOf('.'));
					String path = "/upload/" + CommUtil.getRandomString(16)
							+ ext;
					File f = new File(Globals.APP_BASE_DIR + path);
					try {
						item.write(f);
					} catch (Exception e) {
						this.addError("pathFile", "文件上传错误" + e.getMessage());
					}
					album.setPath(path);
				} else {
					this.addError("pathFile", fileName + "为非法的文件格式!");
				}
			}
		}
	}

	public Page doRemove(WebForm form) {		
		Long id = new Long(CommUtil.null2String(form.get("id")));
		service.delAlbum(id);
		return pageForExtForm(form);
	}

	public Page doList(WebForm form) {
		QueryObject qo = form.toPo(AlbumQuery.class);
		IPageList pageList = this.service.getAlbumBy(qo);
		form.jsonResult(pageList);
		return Page.JSONPage;
	}
}