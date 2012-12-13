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
	 * @param shortClassName    entity bean的类名，省去包名   
	 * @param id
	 * @return
	 */
		
	public Object findById(String shortClassName,Integer id);
	
	
	public List findAll(String shortClassName);
	
	
	public List findByParam(String hql,Object obj);
	
	
	public int calculateAmount(final Class clazz,final Map conditions);
	
	
	
	/**
	 * 该方法应用于查询分页
	 * 
	 * @param clazz       待查询的类
	 * @param conditions  条件组合
	 * @param orderType   排序方式
	 * @param orderField  按哪个属性进行排序
	 * @param start       开始位置    
	 * @param pageSize    返回的页大小
	 * @return
	 */
	public List filter(final Class clazz,final Map conditions, final String orderType,final String orderField,final int start,final int pageSize);
    
    

}
