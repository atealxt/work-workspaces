<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="page" uri="/WEB-INF/page.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'pagesplit.jsp' starting page</title>
	</head>

	<body>
		<table>
			<tr>
				<td>
					ID
				</td>
				<td>
					NAME
				</td>
				<td>
					AGE
				</td>
				<td>
					SEX
				</td>
			</tr>

			<c:forEach var="user" items="${pageModel.datas}">
				<tr>
					<td>
						${user.uid }
					</td>
					<td>
						${user.uname }
					</td>
					<td>
						${user.usex }
					</td>
					<td>
						${user.uage }
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${! empty pageModel.datas}">
			<html:form action="user/userManager.do?method=list" method="post">
				<html:text property="query" />


				<page:pg url="/SSH/user/userManager.do?method=list"
					totalPage="${pageModel.totalPage}"
					currentPage="${pageModel.currenPage}"></page:pg>
			</html:form>

		</c:if>
	</body>
</html>
