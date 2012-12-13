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
public class pagetagbak extends SimpleTagSupport {

	public pagetagbak() {
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

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = super.getJspContext().getOut();
		StringBuffer bf = new StringBuffer();
		bf.append("");
		if (theme.equals("both")) {
			bf.append(this.text());
		} else if (theme.equals("number")) {
			bf.append(this.number());
		} else if (theme.equals("text")) {
			bf.append(this.text());
		}

		System.out.println(bf.toString());
		// out.println(bf.toString());
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
			System.out.println(cpageInt+"============当前页");
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
					str.append("<a href='" + url + pageNoStr + i + "'>" + i
							+ "</a> ");
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
				str.append("<a href='" + url + pageNoStr + "1" + "'>[首页]</a> ");

				str.append("<a href='" + url + pageNoStr + (cpageInt - 1)
						+ "'>[上一页]</a>");
				if (this.theme.equals("both")) {
					str.append(this.number());
				}

				str.append(" <a href='" + url + pageNoStr + totalPage
						+ "'>[末页]</a> ");
			}
		} else {

			if ("1".equals(currentPage)) {

				str.append("<a href='" + url + pageNoStr + "1" + "'>[首页]</a>");
				if (this.theme.equals("both")) {
					str.append(this.number());
				}

				str.append("<a href='" + url + pageNoStr + (cpageInt + 1)
						+ "'>[下一页]</a>");

				str.append("<a href='" + url + pageNoStr + totalPage
						+ "'>[末页]</a>");
			} else {
				str.append("<a href='" + url + pageNoStr + "1" + "'>[首页]</a>");

				str.append("<a href='" + url + pageNoStr + (cpageInt - 1)
						+ "'>[上一页]</a>");
				if (this.theme.equals("both")) {
					str.append(this.number());
				}

				str.append("<a href='" + url + pageNoStr + (cpageInt + 1)
						+ "'>[下一页]</a>");

				str.append("<a href='" + url + pageNoStr + totalPage
						+ "'>[末页]</a>");
			}

		}

		return str.toString();
	}

	public static void main(String[] args) {
		pagetagbak tag = new pagetagbak();
		tag.setCurrentPage("2");
		tag.setTotalPage("30");
		tag.setUrl("http://localhost:8080/test.do");
		tag.setTheme("number");
		System.out.println(tag.number());

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
