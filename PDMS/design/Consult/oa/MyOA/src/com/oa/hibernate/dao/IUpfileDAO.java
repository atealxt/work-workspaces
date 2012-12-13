package com.oa.hibernate.dao;

import java.util.List;

import com.oa.hibernate.beans.Upfile;
import com.oa.struts.util.Pager;

public interface IUpfileDAO {

	public Pager findPagerAllFile(final int pageSize,final int pageNo);
	
	public Pager findPagerAllFile();

	public Upfile findById(String id);
	
	public Upfile findByUptime(final String fileuptime);

	public void upload(Upfile upfile);

	public void view(Upfile upfile);

	public void delete(String id);

}
