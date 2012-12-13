<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	StringBuffer personName = new StringBuffer();
	personName.append("{");
	personName.append("metaData : {totalProperty: 'results',");
	personName.append("root: 'rows',");
	personName.append("id: 'id' ,");
	personName.append("fields : [");
	personName.append("{name: 'id',mapping:'id'},");
	personName.append("{name: 'personName',mapping:'name'},");
	personName.append("{name: 'personAge',mapping:'age'}");
	personName.append("]},");
	personName.append("results : 5,");
	personName.append("rows : [");
	personName.append("{ id : 0 , name : 'tom' , age : 24 },");
	personName.append("{ id : 1 , name : 'jack' , age : 18 }");
	personName.append("]");
	personName.append("}");
	response.getWriter().write(personName.toString());
%>