package org.ywq.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author ai5qiangshao E-mail:ai5qiangshao@163.com
 * @version 创建时间：Aug 7, 2009 10:05:29 AM
 * @Package org.ywq.tag
 * @Description 类说明
 */
public class PageTag extends SimpleTagSupport {

	public PageTag() {
	}

	private String currentPage;
	private String totalPage;

	private String url;

	private String theme = "both";

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String submitAction() {
		StringBuffer bf = new StringBuffer();
		bf.append("<script language=\"");
		bf.append("JavaScript");
		bf.append("\"").append(">").append("\n");
		bf.append("function topage(");
		bf.append("action");
		bf.append("){");
		bf.append("\n");
		bf.append("var form = document.forms[0];").append("\n");
		bf.append("form.action=action;");
		bf.append("\n");
		bf.append("form.submit();");
		bf.append("}\n");
		bf.append("</script>");
		return bf.toString();
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = super.getJspContext().getOut();
		StringBuffer bf = new StringBuffer();
		bf.append(this.submitAction());
		if (theme.equals("both")) {
			bf.append(this.text());
		} else if (theme.equals("number")) {
			bf.append(this.number());
		} else if (theme.equals("text")) {
			bf.append(this.text());
		}

		//System.out.println(bf.toString());
		out.println(bf.toString());
	}

	private String number() {
		String pageNoStr = getPageNoStr();
		System.out.println(pageNoStr);
		StringBuffer str = new StringBuffer();
		Integer totalInt = Integer.valueOf(totalPage);
		str.append("[ ");
		if (totalInt == 1) {
			str.append("<strong>1</strong> ");
		} else {
			int cpageInt = Integer.parseInt(this.getCurrentPage());
			System.out.println(cpageInt + "============当前页");
			int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;
			int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;
			if (v1 == totalInt) {
				v = totalInt - 6;
				v = (v <= 0 ? 1 : v); // 如果为负数，则修改为1
			} else if (v == 1 && v1 < totalInt) {
				v1 = totalInt > 6 ? 6 : totalInt;
			}
			// 10个为一组显示

			for (int i = v; i <= v1; i++) {
				if (cpageInt == i) { // 当前页要加粗显示
					str.append("<strong>" + i + "</strong> ");
				} else {
					str.append("<a href=\"").append("javascript:").append("topage('" + url + pageNoStr + i + "')");
					str.append("\"").append(">").append(i).append("</a>");
					str.append("&nbsp;");
				}
			}
		}

		str.append("]");
		return str.toString();
	}

	private String text() {
		StringBuilder str = new StringBuilder();
		String pageNoStr = getPageNoStr();
		int cpageInt = Integer.parseInt(currentPage);
		if (currentPage.equals(totalPage)) {
			if ("1".equals(totalPage)) {
				str.append("[第 " + currentPage + " 页]");
				str.append(" [共 " + totalPage + " 页]");
			} else {
				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + 1 + "')");
				str.append("\"").append(">").append("[首页]").append("</a>")
						.append("&nbsp;&nbsp;");

				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + (cpageInt - 1) + "')");
				str.append("\"").append(">").append("[上一页]").append("</a>")
						.append("&nbsp;&nbsp;");

				if (this.theme.equals("both")) {
					str.append(this.number());
				}
				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + totalPage + "')");
				str.append("\"").append(">").append("[末页]").append("</a>")
						.append("&nbsp;&nbsp;");

			}
		} else {

			if ("1".equals(currentPage)) {
				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + 1 + "')");
				str.append("\"").append(">").append("[首页]").append("</a>")
						.append("&nbsp;&nbsp;");
				
				if (this.theme.equals("both")) {
					str.append(this.number());
				}
				
				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + (cpageInt + 1) + "')");
				str.append("\"").append(">").append("[下一页]").append("</a>")
						.append("&nbsp;&nbsp;");

				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + totalPage + "')");
				str.append("\"").append(">").append("[末页]").append("</a>")
						.append("&nbsp;&nbsp;");
				
			} else {
				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + 1 + "')");
				str.append("\"").append(">").append("[首页]").append("</a>")
						.append("&nbsp;&nbsp;");

				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + (cpageInt - 1) + "')");
				str.append("\"").append(">").append("[上一页]").append("</a>")
						.append("&nbsp;&nbsp;");
				if (this.theme.equals("both")) {
					str.append(this.number());
				}

				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + (cpageInt + 1) + "')");
				str.append("\"").append(">").append("[下一页]").append("</a>")
						.append("&nbsp;&nbsp;");

				str.append("<a href=\"").append("javascript:").append(
						"topage('" + url + pageNoStr + totalPage + "')");
				str.append("\"").append(">").append("[末页]").append("</a>")
						.append("&nbsp;&nbsp;");
			}

		}

		return str.toString();
	}

	public static void main(String[] args) {
		PageTag tag = new PageTag();
		tag.setCurrentPage("2");
		tag.setTotalPage("30");
		tag.setUrl("http://localhost:8080/test.do");
		tag.setTheme("text");
		System.out.println(tag.text());

	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPageNoStr() {
		if (url.indexOf("?") != -1) {
			return "&pageNo=";
		} else
			return "?pageNo=";
	}
}
