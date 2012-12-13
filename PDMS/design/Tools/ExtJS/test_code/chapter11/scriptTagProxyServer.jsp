<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String personName = "[[1,'张三',24],[2,'李四',30],[3,'王五',29]]";
	boolean scriptTag = false;
	//获取回调函数名称
	String cb = request.getParameter("callback");
	if (cb != null) {
		scriptTag = true;
		response.setContentType("text/javascript;charset=UTF-8");
	} else {
		response.setContentType("application/x-json;charset=UTF-8");
	}
	String msg = "";
	if (scriptTag) {
		msg = cb + "(";
	}

	msg += personName;

	if (scriptTag) {
		msg += ");";
	}
	response.getWriter().write(msg);
%>