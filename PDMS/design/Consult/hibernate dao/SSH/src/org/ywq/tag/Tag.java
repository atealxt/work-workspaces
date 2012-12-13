package org.ywq.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag extends TagSupport {
	/**
	 * ��ǰҳ
	 */
	private String curPage;
	/**
	 * ��ҳ��
	 */
	private String totalPage;
	/**
	 * ҳ��С(һҳ��ʾ�Ĵ�С)
	 */
	private String pageSize;

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public int doStartTag() throws JspException {
		//JspWriter out = pageContext.getOut();

		StringBuilder builder = new StringBuilder();
		// �õ���ҳ���ҳ��,(��ҳ��/ҳ��С)+1
		if (pageSize == null || pageSize == "") {
			pageSize = "1";
		}
		int pageNumber = (Integer.parseInt(totalPage) / Integer
				.parseInt(pageSize));
		if (Integer.parseInt(curPage) > pageNumber) {
			curPage = String.valueOf(pageNumber);
		}
		if (Integer.parseInt(curPage) < 1) {
			curPage = "1";
		}
		// ��ʾ���û�������ҳ�濪ʼ��
		int start = Integer.parseInt(curPage) - 4;
		// ��ʾ���û�������ҳ�������
		int end = Integer.parseInt(curPage) + 4;
		// �����������(��ʼ��С��0)
		if ((Integer.parseInt(curPage) - 4) <= 0) {
			start = 1;
		}
		// �����������(�����˴�����ҳ��)
		if ((Integer.parseInt(curPage) + 4) > pageNumber) {
			end = pageNumber;
		}
		builder.append("<form action='bbs/SubjectListServlet'><table align=center><tr height=10 align=justify ><td width=100>");
		builder
				.append("<a href='bbs/SubjectListServlet?curpage=1'>[��ҳ]</a>  <a href='bbs/SubjectListServlet?curpage="
						+ (((Integer.parseInt(curPage) - 1) == 0) ? curPage
								: (Integer.parseInt(curPage) - 1))
						+ "'>[��һҳ]</a></td><td width=180 align=center>");
		for (int i = start; i <= end; i++) {
			if (i != Integer.parseInt(curPage)) {
				builder.append("<a href='bbs/SubjectListServlet?curpage=" + i
						+ "'>[" + i + "]</a>  ");
			} else {
				builder.append("<b>" + i + "</b>  ");
			}

		}
		builder
				.append("</td><td width=100><a href='bbs/SubjectListServlet?curpage="
						+ (((Integer.parseInt(curPage) + 1) > pageNumber) ? curPage
								: (Integer.parseInt(curPage) + 1))
						+ "'>[��һҳ]</a>  <a href='bbs/SubjectListServlet?curpage="
						+ pageNumber + "'>[ĩҳ]</a>");
		builder.append("</td><td width=100><input name='curpage' style='width:22px;height:22px;' /><input type=submit value=go /></td></tr></table></form>");
		//return super.doStartTag();
		System.out.println(builder.toString());
		return 1;
	}
	
	public static void main(String[] args) throws JspException {
		Tag t=new Tag();
		t.setCurPage("6");
		t.setTotalPage("60");
		t.setPageSize("5");
		t.doStartTag();
	}
	
}
