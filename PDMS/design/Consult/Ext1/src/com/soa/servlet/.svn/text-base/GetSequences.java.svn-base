package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soa.base.Sequences;


public class GetSequences extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String cmd=request.getParameter("cmd");
		String jndiName="jdbc/Collaboration";		
		
		//获得项目编号
		if(cmd.equals("ProjectCode")){
			new Sequences();
			out.println("[{'instructions_no':'"+Sequences.Automatic(jndiName, "CO")+"'}]");
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
