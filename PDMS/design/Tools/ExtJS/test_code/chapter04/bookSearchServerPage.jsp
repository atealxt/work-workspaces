<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String bookName = request.getParameter("query");
int start = Integer.parseInt(request.getParameter("start"));
int limit = Integer.parseInt(request.getParameter("limit"));
String [] booksArray = {"java编程思想","java入门","javascript程序设计",
				        "c++编程思想","c++入门","c++程序设计",
				        "php程序设计","php入门","php从入门到精通"};
StringBuffer books = new StringBuffer();
books.append("{totalNum:'"+booksArray.length+"',");
books.append("books:[");
if(bookName.equals("allbook")){
	for(int i = start ; i < start + limit ; i++){
		books.append("{bookName:'"+booksArray[i]+"'}");
		if(i != (start + limit - 1)){
			books.append(",");
		}
	}
	books.append("]}");
	System.out.println(books.toString());
	response.getWriter().write(books.toString());

}else{
	response.getWriter().write("[totalNum:0,books:['数据不存在']]");
}
%>