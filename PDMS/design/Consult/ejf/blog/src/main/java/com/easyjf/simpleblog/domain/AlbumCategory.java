package com.easyjf.simpleblog.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.easyjf.container.annonation.Field;
import com.easyjf.container.annonation.FormPO;
import com.easyjf.container.annonation.POLoad;
import com.easyjf.container.annonation.Validator;
@FormPO(name = "相册分类", disInject = "id,children,albums")
@Entity
public class AlbumCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Field(name = "相册分类名称", validator = @Validator(name = "string", required = true))
	@Column(length = 200)
	private String name;
	
	@Column(length = 1000)
	private String intro;
	
	@POLoad(name="parentId")
	@ManyToOne
	private AlbumCategory parent;
	
	@OneToMany(mappedBy = "parent")
	private List<AlbumCategory> children = new java.util.ArrayList<AlbumCategory>();
	
	@OneToMany(mappedBy = "category")
	@OrderBy("inputTime desc")
	private List<Album> albums = new java.util.ArrayList<Album>();
	public AlbumCategory()
	{
		
	}
	public AlbumCategory(Long id,String name,String intro)
	{
		this.id=id;
		this.name=name;
		this.intro=intro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public AlbumCategory getParent() {
		return parent;
	}
	public void setParent(AlbumCategory parent) {
		this.parent = parent;
	}
	public List<AlbumCategory> getChildren() {
		return children;
	}
	public void setChildren(List<AlbumCategory> children) {
		this.children = children;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	public Album getLastAlbum()
	{
		if(this.albums.size()>0)return this.albums.get(0);
		else return null;
	}
}
