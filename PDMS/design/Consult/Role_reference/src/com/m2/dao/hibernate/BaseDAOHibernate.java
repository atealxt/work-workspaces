package com.m2.dao.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.m2.common.Constant;
import com.m2.dao.BaseDAO;

public class BaseDAOHibernate extends HibernateDaoSupport implements BaseDAO{
	
	public  static final String ENTITY_BEAN_PACKAGE="com.m2.entity";
	

	
    public void save(Object obj) {
    	
       getHibernateTemplate().save(obj);

    }	
	
	
    public void update(Object obj){

       getHibernateTemplate().update(obj);

   }	
    
    
    public void saveOrUpdate(Object obj){

        getHibernateTemplate().saveOrUpdate(obj);

    }	 
    
    
	public void delete(Object obj) {
		
		getHibernateTemplate().delete(obj);
		
    }  
	
	
	public void executeHQL(final String hql,final Object obj){
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException{
				Query query = s.createQuery(hql);
				query.executeUpdate();
				return null;
			}
		});
	}

	
	
	public Object findById(Class clazz,Integer id){
		Object obj=getHibernateTemplate().get(clazz, id);
		return obj;
	}
	
	
	
	public Object findById(String shortClassName,Integer id){
     
        Object obj = getHibernateTemplate().get(ENTITY_BEAN_PACKAGE+"."+shortClassName, id);
        return obj;

	}
	

	
	
	
	public List findAll(String shortClassName){

	    List list=getHibernateTemplate().find(" from "+ENTITY_BEAN_PACKAGE+"."+shortClassName+" where 1=1");
	    return list;

	}	
	
	
	public List findByParam(String hql,Object obj){
	
	    List list=getHibernateTemplate().find(hql,obj);
		return list;
		
	}	
	
	
	public int calculateAmount(final Class clazz,final Map conditions){
		
		List l =getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session s) throws HibernateException{
				
				Criteria c = s.createCriteria(clazz);
				c.setProjection(Projections.count("id"));
				if (conditions!=null){
				Iterator it=conditions.entrySet().iterator();
				while (it.hasNext()){
					Map.Entry entry = (Map.Entry)it.next();
					String[] key =(String[]) entry.getKey();
					String field = key[0];              
					String condition = key[1];        
					if (condition.equalsIgnoreCase(Constant.CONDITION_BETWEEN)){
						Object []  values = (Object [])entry.getValue(); 
						c.add(Restrictions.between(field, values[0], values[1]));
					}
					if (condition.equalsIgnoreCase(Constant.CONDITION_EQUAL)){
						Object value = entry.getValue();
						c.add(Restrictions.eq(field, value));
					}					
					if (condition.equalsIgnoreCase(Constant.CONDITION_GE)){
						Object value = entry.getValue();
						c.add(Restrictions.ge(field, value));
					}		
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_GT)){
						Object value = entry.getValue();
						c.add(Restrictions.gt(field, value));
					}	
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LE)){
						Object value = entry.getValue();
						c.add(Restrictions.le(field, value));
					}	
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LT)){
						Object value = entry.getValue();
						c.add(Restrictions.lt(field, value));
					}		
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LIKE)){
						Object value = entry.getValue();
						c.add(Restrictions.like(field,value));
					}					

						
				}
				}
				return c.list();
			}
		});
		if (l == null || l.isEmpty()) {
			return 0;
		} else {
			return ((Integer) l.get(0)).intValue();
		}		
	}
	
	
	
	
	
	
	/**
	 * 该方法应用于hibernate查询分页
	 * 
	 * @param clazz       待查询的类
	 * @param conditions  条件组合
	 * @param orderType   排序方式
	 * @param orderField  按哪个属性进行排序
	 * @param start       开始位置    
	 * @param pageSize    返回的页大小
	 * @return
	 */
	
	
	
	public List filter(final Class clazz,final Map conditions, final String orderType,
			final String orderField,final int start,final int pageSize){
		
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session s) throws HibernateException{
				
				Criteria c = s.createCriteria(clazz);
				
				if (conditions!=null){
				Iterator it=conditions.entrySet().iterator();
				
				while (it.hasNext()){
					
					Map.Entry entry = (Map.Entry)it.next();
					String[] key =(String[]) entry.getKey();
					String field = key[0];              //要进行条件限制的属性
					String condition = key[1];          //条件
					if (condition.equalsIgnoreCase(Constant.CONDITION_BETWEEN)){
						Object [] values = (Object [])entry.getValue();
						c.add(Restrictions.between(field, values[0], values[1]));
					}
					if (condition.equalsIgnoreCase(Constant.CONDITION_EQUAL)){
						Object value = entry.getValue();
						c.add(Restrictions.eq(field, value));
					}					
					if (condition.equalsIgnoreCase(Constant.CONDITION_GE)){
						Object value = entry.getValue();
						c.add(Restrictions.ge(field, value));
					}		
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_GT)){
						Object value = entry.getValue();
						c.add(Restrictions.gt(field, value));
					}	
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LE)){
						Object value = entry.getValue();
						c.add(Restrictions.le(field, value));
					}	
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LT)){
						Object value = entry.getValue();
						c.add(Restrictions.lt(field, value));
						
					}		
					
					if (condition.equalsIgnoreCase(Constant.CONDITION_LIKE)){
						Object value = entry.getValue();
						c.add(Restrictions.like(field,value));
					}					
		
				}
				}


				if ((orderType!=null)&&(orderType.equalsIgnoreCase(Constant.ORDER_ASC))){
			        c.addOrder(Order.asc(orderField));
			       
				}else
					c.addOrder(Order.desc(orderField));
				
				c.setFirstResult(start);              
				c.setMaxResults(pageSize);				
				return c.list();
				
			}
			
		});
		
		
		
	}
    
    

}
