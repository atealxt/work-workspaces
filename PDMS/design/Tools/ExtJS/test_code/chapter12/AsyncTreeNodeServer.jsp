<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String nodeId = request.getParameter("node");
	System.out.println("nodeId="+nodeId);
	StringBuffer tree = new StringBuffer();
	if(nodeId.equals("root") == true){
		tree.append("[");
		tree.append("{id:'level1-1',text:'一级节点',leaf:true,cust:'server'},");
		tree.append("{id:'level1-2',text:'一级节点',leaf:false},");
		tree.append("{id:'level1-4',text:'一级节点',leaf:true}");
		tree.append("]");
	}else{
		tree.append("[");
		tree.append("{id:'level2-1',text:'二级节点',leaf:true},");
		tree.append("{id:'level2-4',text:'二级节点',leaf:true}");
		tree.append("]");
	}
	response.getWriter().write(tree.toString());
%>