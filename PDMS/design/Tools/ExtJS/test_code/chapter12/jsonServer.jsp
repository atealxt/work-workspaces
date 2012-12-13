<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String[] persons = new String[6];
	persons[0] = "{ id : 0 , name : 'tom' , age : 24 }";
	persons[1] = "{ id : 1 , name : 'jack' , age : 18 }";
	persons[2] = "{ id : 2 , name : 'mary' , age : 23 }";
	persons[3] = "{ id : 3 , name : 'jone' , age : 19 }";
	persons[4] = "{ id : 4 , name : 'ada' , age : 22 }";
	persons[5] = "{ id : 5 , name : 'alex' , age : 28 }";

	int start = Integer.parseInt(request.getParameter("start").toString());
	int limit = Integer.parseInt(request.getParameter("limit").toString());

	System.out.println("start="+start+ " limit="+limit);
	StringBuffer personName = new StringBuffer();
	personName.append("{");
	personName.append("metaData : {totalProperty: 'results',");
	personName.append("root: 'rows',");
	personName.append("id: 'id' ,");
	personName.append("fields : [");
	personName.append("{name: 'personId',mapping:'id'},");
	personName.append("{name: 'personName',mapping:'name'},");
	personName.append("{name: 'personAge',mapping:'age'}");
	personName.append("]},");
	personName.append("results : 6,");
	personName.append("rows : [");
	int length = start + limit;
	if(length > 6){
		length = 6;
	}
	for(int i = start ; i < length ; i++){
		personName.append(persons[i]);
		if(i < length - 1){
			personName.append(",");
		}
	};
	personName.append("]");
	personName.append("}");
	response.getWriter().write(personName.toString());
%>