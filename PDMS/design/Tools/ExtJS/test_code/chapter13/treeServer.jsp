<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	StringBuffer tree = new StringBuffer();
	tree.append("[");
	tree.append("{text:'一级节点',leaf:true},");
	tree.append("{text:'一级节点',children:[");
	tree.append("{text:'二级节点',leaf:true},");
	tree.append("{text:'二级节点',children:[");
	tree.append("{text:'三级节点',leaf:true},");
	tree.append("{text:'三级节点',leaf:true},");
	tree.append("{text:'三级节点',leaf:true}");
	tree.append("]}");
	tree.append("]},");
	tree.append("{text:'一级节点',leaf:true},");
	tree.append("{text:'一级节点',leaf:true}");
	tree.append("]");
	response.getWriter().write(tree.toString());
%>