
package com.source.service;

import javax.servlet.http.HttpServletRequest;

import com.papa.list.Pager;
import com.papa.list.Searcher;
import com.source.bean.AnythingType;

/**
 * @author ¡÷‘æª‘ [Oct 10, 2007]
 */
public interface AnythingTypeService
{
	void delete(Integer[] refID);
	
	void save(AnythingType type);
	
	AnythingType findById(int classId);

	void search(Pager pager,HttpServletRequest request,Searcher searcher);
}
