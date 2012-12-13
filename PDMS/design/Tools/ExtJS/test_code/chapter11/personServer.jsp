<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String personName = "[[1,'张三',24],[2,'李四',30],[3,'王五',29]]";
	response.getWriter().write(personName);
%>