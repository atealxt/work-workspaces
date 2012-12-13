<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	response.setContentType("application/xml;charset=UTF-8");
	StringBuffer personName = new StringBuffer();
	personName.append("<dataset>");
	personName.append("<results>5</results>");
	personName.append("<row>");
	personName.append("<id>0</id>");
	personName.append("<name>tom</name>");
	personName.append("<age>24</age>");
	personName.append("</row>");
	personName.append("<row>");
	personName.append("<id>1</id>");
	personName.append("<name>jack</name>");
	personName.append("<age>18</age>");
	personName.append("</row>");
	personName.append("</dataset>");
	System.out.println(personName.toString());
	response.getWriter().write(personName.toString());
%>