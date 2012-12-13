package com.source.dao;

import java.util.List;

import com.papa.list.Searcher;
import com.source.bean.AnythingType;

/**
 * Data access object (DAO) for domain model class AnythingType.
 * @see com.source.bean.AnythingType
 * @author MyEclipse - Hibernate Tools
 */
public interface AnythingTypeDao
{

	void save(AnythingType type);

	void delete(AnythingType type);

	AnythingType findById(Integer classId);

	List findByExample(AnythingType type);

	List findByProperty(String propertyName, Object AnythingType);

	List search(int first,int pageno,Searcher searcher);
	
	int count(Searcher searcher);

}
