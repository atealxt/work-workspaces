/**
 * 
 */
package com.papa.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.papa.list.Pager;

/**
 * @author ��Ծ�� [Nov 2, 2007]
 */
public class PagerTagSupport extends TagSupport
{
	private static final long serialVersionUID=-1819888585514746537L;

	public PagerTagSupport()
	{
		super();
		// TODO �Զ����ɹ��캯�����
	}

	private Pager pager=null;

	private String form="";

	public int doStartTag() throws JspException
	{
		try
		{
			String bar=getNavigatorBar();
			pageContext.getOut().write(bar);
			return SKIP_BODY;
		}
		catch (IOException ioe)
		{
			throw new JspException(ioe.getMessage());
		}
	}

	private String getNavigatorBar()
	{
		StringBuffer bar=new StringBuffer();
		Pager pager=(Pager)pageContext.getRequest().getAttribute("pager");
		int rowcount=pager.getRowcount();
		int pagesize=pager.getPagesize();
		int pageno=pager.getPageno();
		int pagenum=pager.getPagenum();
		bar.append("<table><tr><td>");
		bar.append("<span style=\"font-size:13px;\">�ܹ� <b>");
		bar.append(rowcount);
		bar.append("</b> ��,ÿҳ <b>");
		bar.append(pagesize);
		bar.append("</b> ��.�� <b>");
		bar.append(pageno);
		bar.append("</b> ҳ,�ܹ� <b>");
		bar.append(pagenum);
		bar.append("</b> ҳ</span>&nbsp;&nbsp;&nbsp;");

		bar.append("<input type=\"hidden\" name=\"cmd\" value=\"0\" />");
		bar
				.append("<input type=\"hidden\" name=\"initial\" value=\"false\" />");
		bar.append("<input type=\"hidden\" name=\"rowcount\" value=\""+rowcount
				+"\" />");
		bar.append("<input type=\"hidden\" name=\"pagesize\" value=\""+pagesize
				+"\" />");
		bar.append("<input type=\"hidden\" name=\"pagenum\" value=\""+pagenum
				+"\" /><br> ");
		if (pagenum>1)
		{
			bar.append("</td></tr><tr><td>");
			if (pageno>1)
			{
				bar.append("<a href=\"javascript:"+form
						+".cmd.value=1;AnythingTypeForm.submit();\"><span style=\"font-size:11px;\">��ҳ</span></a> ");
				bar.append("<a href=\"javascript:"+form
						+".cmd.value=2;AnythingTypeForm.submit();\"><span style=\"font-size:11px;\">��һҳ</span></a> ");
			}
			if (pageno<pagenum)
			{
				bar.append("<a href=\"javascript:"+form
						+".cmd.value=3;AnythingTypeForm.submit();\"><span style=\"font-size:11px;\">��һҳ</span></a> ");
				bar.append("<a href=\"javascript:"+form
						+".cmd.value=4;AnythingTypeForm.submit();\"><span style=\"font-size:13px;\">δҳ</span></a> ");
			}

			bar.append("<span style=\"font-size:12px;\">�鿴��</span>");
			bar.append("<select name=\"pageno\" onchange=\"javascript:"+form
					+".pageno.value=this.value;"+form+".submit();\">");
			bar.append("<option value=\"1\">1</option>");
			for (int i=2; i<=pagenum; i++)
			{
				if (i==pageno)
					bar.append("<option value=\""+i+"\" selected>"+i
							+"</option>");
				else
					bar.append("<option value=\""+i+"\">"+i+"</option>");
			}
			bar.append("</select><span style=\"font-size:12px;\">ҳ</span>&nbsp;&nbsp;&nbsp;");

		}
		bar.append("</td></tr></table>");
		return bar.toString();
	}

	public int doEndTag() throws JspTagException
	{
		return EVAL_PAGE;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager=pager;
	}

	public String getForm()
	{
		return form;
	}

	public void setForm(String form)
	{
		this.form=form;
	}
}
