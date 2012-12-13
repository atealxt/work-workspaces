package com.easyjf.simpleblog.dao;

import com.easyjf.simpleblog.domain.Album;

public class AlbumDAOTest extends JpaDaoTest {
private IAlbumDAO dao;

public void setDao(IAlbumDAO dao) {
	this.dao = dao;
}

public void testAdd()
{
	Album a=new Album();
	a.setTitle("测试");
}
}
