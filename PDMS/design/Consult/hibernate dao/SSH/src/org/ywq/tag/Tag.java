package org.ywq.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag extends TagSupport {
	/**
	 * 当前页
	 */
	private String curPage;
	/**
	 * 总页数
	 */
	private String totalPage;
	/**
	 * 页大小(一页显示的大小)
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
		// 得到分页后的页数,(总页数/页大小)+1
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
		// 显示给用户操作的页面开始端
		int start = Integer.parseInt(curPage) - 4;
		// 显示给用户操作的页面结束端
		int end = Integer.parseInt(curPage) + 4;
		// 特殊情况处理(开始端小于0)
		if ((Integer.parseInt(curPage) - 4) <= 0) {
			start = 1;
		}
		// 特殊情况处理(结束端大于总页数)
		if ((Integer.parseInt(curPage) + 4) > pageNumber) {
			end = pageNumber;
		}
		builder.append("<form action='bbs/SubjectListServlet'><table align=center><tr height=10 align=justify ><td width=100>");
		builder
				.append("<a href='bbs/SubjectListServlet?curpage=1'>[首页]</a>  <a href='bbs/SubjectListServlet?curpage="
						+ (((Integer.parseInt(curPage) - 1) == 0) ? curPage
								: (Integer.parseInt(curPage) - 1))
						+ "'>[上一页]</a></td><td width=180 align=center>");
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
						+ "'>[下一页]</a>  <a href='bbs/SubjectListServlet?curpage="
						+ pageNumber + "'>[末页]</a>");
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
