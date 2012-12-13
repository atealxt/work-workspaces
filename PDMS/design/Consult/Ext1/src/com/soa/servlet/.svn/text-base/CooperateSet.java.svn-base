package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soa.userbean.InitDB;
import com.soa.userbean.UserInfo;

public class CooperateSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jndiName="jdbc/Collaboration";
	private String cmd="";
	private String instructions_no="";
	private String action_id="";
	private String operating_time="";
	private String operating_description="";
	private String ids="";
	private String sql="";
	private String cons="";
	private String table=" implementation_collaboration ";
	private String sqlContent="*"; 
	private String operating_personnel="";
	private String id="";
	private String unitCode="";
	private String dep_type="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//===========获取用户信息==================
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		operating_personnel=info.getPersonnelCode();
		unitCode=info.getUnit_code();
		if(unitCode!=null){
			if(unitCode.equals("B3")){
				dep_type="2";
			}else{
				dep_type="1";
			}
		}else{
			dep_type="0";
		}
		
		cmd=request.getParameter("cmd");
		instructions_no=request.getParameter("instructions_no");
		action_id=request.getParameter("action_id");
		operating_time=request.getParameter("operating_time");
		operating_description=request.getParameter("operating_description");
		id=request.getParameter("id");
		ids=request.getParameter("json");
		String start=request.getParameter("start");
		if(start==null){
			start="0";
		}
		
		String limit=request.getParameter("limit");
		if(limit==null){
			limit="30";
		}
		
		cons=request.getParameter("name");
		if(cons==null){
			cons="";
		}
		cons="a.instructions_no like '%"+cons+"%' and c.unit_code in ( select unit_code from project_collaboration where personnel_code='"+operating_personnel+"')";
		
		
		//=========================获取JSON===========================
		table=" implementation_collaboration a " +
				"left join action b on a.action_id=b.action_id " +
				"left join collaboration_instructions c on a.instructions_no=c.instructions_no "+
				"left join personnel_info d on a.operating_personnel=d.personnel_code " +
				"left join (" +
				"select instructions_no,implementation_no,count(*)::int4 as count from annex group by instructions_no,implementation_no" +
				") " +
				"e on a.instructions_no=e.instructions_no and a.implementation_no=e.implementation_no";
		sqlContent=" a.id,a.instructions_no,request_title,a.implementation_no,action_name,a.action_id,operating_time,operating_description," +
				"personnel_name,case a.dep_type when 1 then '(附)' when 2 then '(作)' else '(其)' end || isnull(count,0) as annex  ";
		if(cmd.equals("List")){
			out.print(new InitDB().toExtJson(table, sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		}
		
		//=============================返回更新结果==================
		if(cmd.equals("Update")){
			out.print(doUpdate(id,instructions_no, action_id, operating_time, operating_description));
		}
		
		//返回新增结果
		if(cmd.equals("Save")){
			out.print(doSave(instructions_no, action_id, operating_time, operating_description));
		}
		
		//返回删除结果
		if(cmd.equals("Remove")){
			out.print(doRemove(ids));
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//更新数据
	public String doUpdate(String id,String instructions_no,String action_id,String operating_time,String operating_description){
		sql="UPDATE implementation_collaboration "+
			   "SET instructions_no='"+instructions_no+"',action_id="+action_id+", operating_time=date'"+operating_time+"', "+ 
			       "operating_description='"+operating_description+"' "+
			 "WHERE  id="+id+"";
		boolean update=new InitDB().execute(sql, jndiName);
		if(update){
			return "{success:true,info:\'更新成功\'}";
		}
		
		return "{failure:true,info:\'更新失败,请与管理员联系\'}";
	}
	
	//新增数据
	public String doSave(String instructions_no,String action_id,String operating_time,String operating_description){
		//获得明细序号
		String strCons="a.instructions_no = '"+instructions_no+"'";
		String newSql=new InitDB().generateSql(table, sqlContent, strCons);
		int total=new InitDB().findCount(newSql, jndiName);		
	
		total+=1;
		sql="INSERT INTO implementation_collaboration( "+
			            "instructions_no, implementation_no, action_id, operating_time, "+ 
			            "operating_description, operating_personnel,dep_type) "+
			    "VALUES ('"+instructions_no+"', "+total+", "+action_id+", date'"+operating_time+"', '"+operating_description+"','"+operating_personnel+"',"+dep_type+")";
		
		boolean save=new InitDB().execute(sql, jndiName);
		if(save){
			return "{success:true,info:\'添加成功\'}";
		}
		return "{failure:true,info:\'添加失败,请与管理员联系\'}";
	}
	//删除数据
	public String doRemove(String ids){
		StringTokenizer st=new StringTokenizer(ids,",");
		while(st.hasMoreElements()){
			String id=(String) st.nextElement();
			sql="DELETE FROM implementation_collaboration WHERE id="+id+"";
			boolean re=new InitDB().execute(sql, jndiName);
			if(!re){
				return "删除编号为："+id+"的记录失败";
			}
		}
		
		
		return "删除成功";
	}

}
