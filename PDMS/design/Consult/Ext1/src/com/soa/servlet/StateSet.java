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


public class StateSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cmd="";
	private String id="";
	private String actionId="";
	private String actionName="";
	private String sql="";
	private String jndiName="jdbc/Collaboration";
	private String ids="";
	private String stateId="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//获得前面提交参数
		cmd=request.getParameter("cmd");
		id=request.getParameter("id");
		actionId=request.getParameter("action_id");
		actionName=request.getParameter("action_name");
		ids=request.getParameter("json");
		stateId=request.getParameter("stateId");
		String start=request.getParameter("start");
		if(start==null){
			start="0";
		}
		String limit=request.getParameter("limit");
		if(limit==null){
			limit="10";
		}
		String cons=request.getParameter("name");
		if(cons==null){
			cons="";
		}

		//获得用户信息
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		String unitCode="";
		if(info!=null){
			unitCode=info.getUnit_code();
		}else{
			response.sendRedirect("/main.html");
		}
		
		
		cons="action_name like '%"+cons+"%' and dep_type in (select case count(*) when 0 then 2 else 1 end as dep_type from collaboration_instructions where unit_code='"+unitCode+"')";
		if(stateId!=null){
			cons=cons+" and action_id in("+stateId+") order by action_id asc";
		}else{
			cons=cons+" and action_id not in(100,-100,0) order by action_id asc";
		}
		
		//获取JSON
		String table="action";
		String sqlContent="*";
		if(cmd.equals("List")){
			out.print(new InitDB().toExtJson(table,sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		}
		
		//返回更新结果
		if(cmd.equals("Update")){
			out.print(doUpdate(id, actionId, actionName));
		}
		
		//返回新增结果
		if(cmd.equals("Save")){
			out.print(doSave(actionId,actionName));
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
	public String doUpdate(String id,String actionId,String actionName){
		sql="UPDATE action SET action_id="+actionId+", action_name='"+actionName+"' WHERE id="+id+";";
		boolean update=new InitDB().execute(sql, jndiName);
		
		if(update){
			return "{success:true,info:\'更新成功\'}";
		}
			return "{failure:true,info:\'更新失败,请与管理员联系\'}";		
	}
	
	//新增数据
	public String doSave(String actionId,String actionName){
		sql="INSERT INTO action(action_id, action_name) VALUES ("+actionId+", '"+actionName+"');";
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
			sql="DELETE FROM action WHERE id="+id+"";
			boolean re=new InitDB().execute(sql, jndiName);
			
			if(!re){
				return "删除编号为："+id+"的记录失败";
			}			
		}
		return "删除成功";		
	}
}
