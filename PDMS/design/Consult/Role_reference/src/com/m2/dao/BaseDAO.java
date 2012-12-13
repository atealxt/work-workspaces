package com.m2.dao;

import java.util.List;
import java.util.Map;

public interface BaseDAO {
	

	
	
    public void save(Object obj);
	
	
    public void update(Object obj);
    
    
    public void saveOrUpdate(Object obj);
    
    
	public void delete(Object obj) ;
	
	
	
	
	public void executeHQL(String hql,Object obj);
	
	
	public Object findById(Class clazz,Integer id);
	
	/**
	 * 
	 * @param shortClassName    entity bean��������ʡȥ����   
	 * @param id
	 * @return
	 */
		
	public Object findById(String shortClassName,Integer id);
	
	
	public List findAll(String shortClassName);
	
	
	public List findByParam(String hql,Object obj);
	
	
	public int calculateAmount(final Class clazz,final Map conditions);
	
	
	
	/**
	 * �÷���Ӧ���ڲ�ѯ��ҳ
	 * 
	 * @param clazz       ����ѯ����
	 * @param conditions  �������
	 * @param orderType   ����ʽ
	 * @param orderField  ���ĸ����Խ�������
	 * @param start       ��ʼλ��    
	 * @param pageSize    ���ص�ҳ��С
	 * @return
	 */
	public List filter(final Class clazz,final Map conditions, final String orderType,final String orderField,final int start,final int pageSize);
    
    

}
