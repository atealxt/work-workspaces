package com.oa.hibernate.dao;

import com.oa.hibernate.beans.Worklog;
import com.oa.struts.util.Pager;

public interface IWorklogDAO {

	public Pager findPagerByUsername(final String username, final int pageNo,
			final int pageSize);

	public Worklog findById(String id);

	public void insert(Worklog worklog);

	public void update(Worklog worklog);

	public void delete(String id);
}
