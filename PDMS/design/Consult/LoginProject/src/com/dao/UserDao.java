package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.po.User;

public class UserDao extends HibernateDaoSupport{
	
	//�������ݿ⣬����ҵ�ƥ���¼���򷵻�true�����򷵻�false
	//����������Ϊ��Ŀ����hibernate��������Ϊ����������Spring���ɵ�HibernateTemplate
	//����Ҫ��dao���в������ݶ���User,���ԣ����ǻ���Ҫ��д��ӦUser��ӳ���ļ�
	//��������������Ҫ���Ѿ������õ�UserDao����Spring����������Ҳ����˵����Ҫ����spring�����ļ���
	public boolean loginCheck(User loginUser){
		List results = super.getHibernateTemplate().find("from User where userName=? and password=?", 
				new String[]{loginUser.getUserName(),loginUser.getPassword()});
		return results!=null && !results.isEmpty();
	}
}
