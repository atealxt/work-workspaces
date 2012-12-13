package com.soa.base;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.soa.userbean.InitDB;
import com.soa.userbean.UserInfo;



public class BaseData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String jndiNameF="jdbc/Fortune-Sun";
	private String jndiNameC="jdbc/Collaboration";
	private String cmd="";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//获得登录人员信息
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		String personCode=info.getPersonnelCode();

		
		//获得前面提交参数
		String cmdPerson=request.getParameter("cmdPerson");
		cmd=request.getParameter("cmd");
		String table="unit_info";
		String start=request.getParameter("start");
		if(start==null){
			start="0";
		}
		String limit=request.getParameter("limit");
		if(limit==null){
			limit="1000";
		}
		String cons=request.getParameter("name");
		if(cons==null){
			cons="";
		}
		cons="state=1 order by unit_type,unit_code";		
		
		//获取JSON
		String sqlContent="unit_code,unit_code||'||'||unit_name as unit_name";
		if(cmd==null){
			cmd="";
		}
		if(cmd.equals("List")){
			out.println(new InitDB().toExtJson(table,sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiNameF));
		}
		
		//获取人员信息
		table=" personnel_info ";
		sqlContent=" personnel_code,personnel_name";
		cons=" unit_code like '%%' and state=1 order by personnel_name ";
		if(cmdPerson==null){
			cmdPerson="";
		}
		if(cmdPerson.equals("PersonData")){
			out.print(new InitDB().toExtJson(table, sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiNameF));
		}
		
		
		//获取与登录人员相关的项目
		String statecode=request.getParameter("statecode");		
		if(statecode==null){
			statecode="10000";
		}
		table=" collaboration_instructions a " +
				"left join unit_info b on a.unit_code=b.unit_code " +
				"left join  ( " +
							"SELECT a.category_code,b.category_name||'||'||a.category_name as category_name " +
							"FROM collaboration_category a " +
							"left join category_sort b on a.superiors_code=b.category_code " +
							") c on a.category_code=c.category_code " +
				"left join action d on a.state=d.action_id ";
		sqlContent=" a.instructions_no,unit_name,category_name,request_title,request_context,developer_confirm_time,require_completion_time,contract_time,action_name,a.state,remark,category_explain ";
		cons=" a.unit_code in (select unit_code from project_collaboration where personnel_code='"+personCode+"') and a.state="+statecode+"";
		if(cmd.equals("Cooperate")){
			out.print(new InitDB().toExtJson(table, sqlContent, 0, 1000, cons, jndiNameC));
		}
		
		//==================获得项目类型分类信息===========================//
		String category_code=request.getParameter("category_code");
		if((category_code==null)||(category_code.equals(""))){
			category_code="10000";
		}
		
		table=" collaboration_category ";
		sqlContent=" category_code,category_name ";
		cons=" category_code in (select superiors_code from collaboration_category group by superiors_code) order by category_code";
		if(cmd.equals("getGroupsBySort")){
			out.print(new InitDB().toExtJson(table, sqlContent, 0, 1000, cons, jndiNameC));
		}
		
		table=" collaboration_category ";
		sqlContent=" category_code,category_name ";
		cons=" superiors_code in ("+category_code+") order by category_code";
		if(cmd.equals("getTypeBySort")){
			out.print(new InitDB().toExtJson(table, sqlContent, 0, 1000, cons, jndiNameC));
		}
		
		
		//================================================================//

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
