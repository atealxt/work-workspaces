/**
 * 
 */
package com.source.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.papa.list.AbstractActionForm;
import com.papa.list.Pager;
import com.papa.list.Searcher;
import com.source.bean.AnythingType;

/**
 * @author ¡÷‘æª‘ [Oct 10, 2007]
 */
public class AnythingTypeForm extends AbstractActionForm
{
	private static final long serialVersionUID=3963648140216008513L;

	private AnythingType type=null;

	public AnythingType getType()
	{
		return type;
	}

	public void setType(AnythingType type)
	{
		this.type=type;
	}

	@Override
	public void reset(ActionMapping arg0,HttpServletRequest arg1)
	{
		this.searcher=new Searcher();
		this.pager=new Pager();
		this.type=new AnythingType();
	}

	public boolean equals(Object arg0)
	{
		return type.equals(arg0);
	}

	public Integer getClassId()
	{
		return type.getClassId();
	}

	public String getClassMark()
	{
		return type.getClassMark();
	}

	public String getClassName()
	{
		return type.getClassName();
	}

	public int hashCode()
	{
		return type.hashCode();
	}

	public void setClassId(Integer classId)
	{
		type.setClassId(classId);
	}

	public void setClassMark(String classMark)
	{
		type.setClassMark(classMark);
	}

	public void setClassName(String className)
	{
		type.setClassName(className);
	}

	public String toString()
	{
		return type.toString();
	}

}
