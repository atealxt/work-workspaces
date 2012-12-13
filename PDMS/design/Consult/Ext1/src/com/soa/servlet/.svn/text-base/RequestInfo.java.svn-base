package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.soa.base.GetUnit;
import com.soa.userbean.InitDB;
import com.soa.userbean.UserInfo;


public class RequestInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jndiName="jdbc/Collaboration";
	private String cmd="";
	private String request_title="";
	private String instructions_no="";
	private String unit_code="";
	private String category_code="";
	private String category_code_G="";
	private String action_id="";
	private String require_completion_time="";
	private String request_context="";
	private String require_personnel="";	
	private String remark="";
	private String ids="";
	private String sql="";
	private String statecode="";
	private String contract_time="";
	private String developer_confirm_time="";
	private String issued_date="";
	private String unit_name="";
	private String category_name="";
	private String category_name_G="";
	private String category_explain="";

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Logger logger = Logger.getLogger(CheckLogin.class);	
		//获得用户信息
		UserInfo info=(UserInfo) request.getSession().getAttribute("session");
		require_personnel=info.getPersonnelCode();
		unit_code=info.getUnit_code();
		unit_name=info.getUnitName();
		//==================================================
		
		cmd=request.getParameter("cmd");
		category_explain=request.getParameter("category_explain");
		 instructions_no=request.getParameter("instructions_no");
		 category_code=request.getParameter("category_code");
		 category_code_G=request.getParameter("category_code_G");
		 action_id=request.getParameter("action_id");
		 require_completion_time=request.getParameter("require_completion_time");
		 request_context=request.getParameter("request_context");
		 remark=request.getParameter("remark");
		 statecode=request.getParameter("statecode");
		 contract_time=request.getParameter("contract_time");
		 developer_confirm_time=request.getParameter("developer_confirm_time");
		 issued_date=request.getParameter("issued_date");
		 Map map=new GetUnit().getMap("collaboration_category", "category_code", "category_name");
		 if((category_code_G==null)||(category_code_G.equals(""))){
			 category_code_G="1000";
		 }
		 if((category_code==null)||(category_code.equals(""))){
			 category_code="1000";
		 }
		 category_name_G=(String) map.get(Integer.valueOf(category_code_G));
		 category_name=(String) map.get(Integer.valueOf(category_code));
		 
		 request_title=instructions_no+"||"+unit_name+"||"+category_name_G+"||"+category_name;
		 
		 if(action_id==null){
			 action_id="0";
		 }
		 
		 
		 ids=request.getParameter("json");
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
		 cons="a.instructions_no like '%"+cons+"%' and a.unit_code='"+unit_code+"'";
		 
		 if(statecode!=null){
			 cons=cons+" and a.state=100";
		 }
		 
		 //============================获取JSON============================
		 
		 //表名和所要关连的表
		 String table="collaboration_instructions a "+
						"left join unit_info b  on a.unit_code=b.unit_code "+ 
						"left join ( " +
							"SELECT a.category_code,b.category_name||'||'||a.category_name as category_name " +
							"FROM collaboration_category a " +
							"left join category_sort b on a.superiors_code=b.category_code " +
							") c on a.category_code=c.category_code "+
						"left join action d on a.state=d.action_id  "+
						"left join ( "+
						"select instructions_no,count(*)::int4 as count "+
						"from annex "+
						"where implementation_no='0' group by instructions_no "+
						") e on a.instructions_no=e.instructions_no ";
		 //所要查询的字段
		 String sqlContent="a.id, a.instructions_no, unit_name,category_name, request_title,request_context, contract_time,developer_confirm_time,issued_date," +
		 		"require_completion_time, require_personnel,action_name,a.state, category_explain,remark, case a.dep_type when 1 then '(附)' else '其' end || isnull(count,0) as annex ";
		 if(cmd.equals("List")){
			 out.println(new InitDB().toExtJson(table,sqlContent,Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		 }
		
		 //返回更新结果
		 if(cmd.equals("Update")){
			 out.print(doUpdate(request_title, instructions_no, unit_code, category_code, action_id, require_completion_time, request_context,contract_time,developer_confirm_time,issued_date, remark,category_explain));
		 }
		 
		 //返回新增结果
		 if(cmd.equals("Save")){
			 out.print(doSave(request_title, instructions_no, unit_code, category_code, action_id, require_completion_time, request_context,contract_time,developer_confirm_time,issued_date, remark,category_explain));
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
	public String doUpdate(String request_title,String instructions_no,String unit_code,String category_code,String action_id,String require_completion_time,String request_context,String contract_time,String developer_confirm_time,String issued_date,String remark,String category_explain){
		sql="UPDATE collaboration_instructions "+
			   "SET unit_code='"+unit_code+"', category_code='"+category_code+"', request_title='"+request_title+"',"+ 
			       "request_context='"+request_context+"', require_completion_time=date'"+require_completion_time+"',"+ 
			       "require_personnel='"+require_personnel+"',state="+action_id+",contract_time=date'"+contract_time+"',developer_confirm_time=date'"+developer_confirm_time+"',issued_date=date '"+issued_date+"',remark='"+remark+"',category_explain='"+category_explain+"' "+
			 "WHERE instructions_no='"+instructions_no+"'";
		boolean update=new InitDB().execute(sql, jndiName);
		if(update){
			return "{success:true,info:\'更新成功\'}";
		}
		return "{failure:true,info:\'更新失败,请与管理员联系\'}";
	}
	
	//新增数据
	public String doSave(String request_title,String instructions_no,String unit_code,String category_code,String action_id,String require_completion_time,String request_context,String contract_time,String developer_confirm_time,String issued_date,String remark,String category_explain){
		sql="INSERT INTO collaboration_instructions("+
			            "instructions_no, unit_code, category_code, request_title,"+ 
			            "request_context, require_completion_time, require_personnel,"+ 
			            "state, remark,contract_time,developer_confirm_time,issued_date,dep_type,category_explain)"+
			    "VALUES ('"+instructions_no+"', '"+unit_code+"', '"+category_code+"', '"+request_title+"', '"+request_context+"',"+ 
			            "date'"+require_completion_time+"', '"+require_personnel+"', "+action_id+","+ 
			            "'"+remark+"',date'"+contract_time+"',date'"+developer_confirm_time+"',date'"+issued_date+"',1,'"+category_explain+"')";
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
			sql="DELETE FROM collaboration_instructions WHERE id = "+id+"";
			boolean re=new InitDB().execute(sql, jndiName);
			if(!re){
				return "删除编号为："+id+"的记录失败";
			}
		}
		
		return "删除成功";
	}

}
