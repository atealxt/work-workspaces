package com.easyjf.simpleblog.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
@FormPO(name="相册评论",inject="album")
@Entity
public class AlbumComment extends Comment {
	@POLoad(name="albumId")
	@ManyToOne
	private Album album;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}	
}
