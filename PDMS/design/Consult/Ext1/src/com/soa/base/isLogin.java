package com.soa.base;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soa.userbean.UserInfo;

public class isLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String json="";
	private String personCode="";
	private String cmd=""; 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		cmd=request.getParameter("cmd");
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		
		if(cmd.equals("isLogin")){
			if(info!=null){
				json="[{'msg':'OK'}]";
			}else{
				json="[{'msg':'null'}]";
			}			
			out.println(json);
		}else if(cmd.equals("getPersonCode")){
			personCode="[{'personCode':'"+info.getPersonnelCode()+"'}]";
			out.print(personCode);
		}else{
			out.println(json);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
