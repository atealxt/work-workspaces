package com.oa.hibernate.dao;

import com.oa.hibernate.beans.Sms;
import com.oa.struts.util.Pager;

public interface ISmsDAO {

	public Pager findPagerByUsername(final String username, final int pageNo,
			final int pageSize);

	public Sms findById(String id);

	public void insert(Sms sms);

	public void update(Sms sms);

	public void delete(String id);
}
