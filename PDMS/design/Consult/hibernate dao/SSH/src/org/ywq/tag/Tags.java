package org.ywq.tag;

/**
 * @author ai5qiangshao E-mail:ai5qiangshao@163.com
 * @version ����ʱ�䣺Aug 7, 2009 2:19:53 PM
 * @Package org.ywq.tag
 * @Description ��˵��
 */
public class Tags {

	private String currentPage;
	private String totalPage;

	private String url;

	private String theme = null;

	private String styleClass;

	public void end() {


		StringBuilder str = new StringBuilder();

		String pageNoStr = "?pageNo=";

		// ������δ�����Ҫ����������̬��ѯ�Ĳ�������ƴ�ӳ�url

		StringBuffer perUrl = new StringBuffer("");

		Integer cpageInt = Integer.valueOf(currentPage);

		str.append("<span ");

		if (styleClass != null) {

			str.append(" class='" + styleClass + "'>");

		} else {

			str.append(">");

		}

		if (theme == null || "text".equals(theme)) {

			if (currentPage.equals(totalPage)) {

				if ("1".equals(totalPage)) {
					str.append("[�� " + currentPage + " ҳ]");
					str.append(" [�� " + totalPage + " ҳ]");
				} else {
					str.append("<a href='" + url + pageNoStr + "1" + perUrl
							+ "'>[��ҳ]</a> ");

					str.append("<a href='" + url + pageNoStr + (cpageInt - 1)
							+ perUrl + "'>[��һҳ]</a>");

					str.append(" <a href='" + url + pageNoStr + totalPage
							+ perUrl + "'>[ĩҳ]</a> ");
				}
			} else {

				if ("1".equals(currentPage)) {

					str.append("<a href='" + url + pageNoStr + "1" + perUrl
							+ "'>[��ҳ]</a>");

					str.append("<a href='" + url + pageNoStr + (cpageInt + 1)
							+ perUrl + "'>[��һҳ]</a>");

					str.append("<a href='" + url + pageNoStr + totalPage
							+ perUrl + "'>[ĩҳ]</a>");

				} else {

					str.append("<a href='" + url + pageNoStr + "1" + perUrl
							+ "'>[��ҳ]</a>");

					str.append("<a href='" + url + pageNoStr + (cpageInt - 1)
							+ perUrl + "'>[��һҳ]</a>");

					str.append("<a href='" + url + pageNoStr + (cpageInt + 1)
							+ perUrl + "'>[��һҳ]</a>");

					str.append("<a href='" + url + pageNoStr + totalPage
							+ perUrl + "'>[ĩҳ]</a>");

				}

			}

		} else if ("number".equals(theme)) {
			Integer totalInt = Integer.valueOf(totalPage);

			str.append("[ ");

			if (totalInt == 1) {

				str.append("<strong>1</strong> ");

			} else {

				int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;

				int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;

				if (v1 == totalInt) {
					v = totalInt - 6;
					v = (v <= 0 ? 1 : v); // ���Ϊ���������޸�Ϊ1
				} else if (v == 1 && v1 < totalInt) {
					v1 = totalInt > 6 ? 6 : totalInt;
				}

				// 10��Ϊһ����ʾ

				for (int i = v; i <= v1; i++) {
					if (cpageInt == i) { // ��ǰҳҪ�Ӵ���ʾ
						str.append("<strong>" + i + "</strong> ");
					} else {
						str.append("<a href='" + url + pageNoStr + i + perUrl
								+ "'>" + i + "</a> ");
					}
				}

			}

			str.append("]");
		}

		str.append("</span>");
		System.out.println(str.toString());

	}

	public static void main(String[] args) {
		Tags ts = new Tags();
		ts.currentPage = "4";
		ts.styleClass = "pg";
		ts.theme = "number";
		ts.url = "http://localhost:8080/myzhidao/split.do";
		ts.totalPage = "200";
		ts.end();
	}

}
