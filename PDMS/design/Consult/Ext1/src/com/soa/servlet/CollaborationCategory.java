package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.soa.userbean.InitDB;


public class CollaborationCategory extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String cmd="";
	private String code="";
	private String name="";
	private String superior="";	
	private String remark="";
    private String sql="";
    private String jndiName="jdbc/Collaboration";
    private String ids="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out = response.getWriter();
			
		//获得前面提交参数
		cmd=request.getParameter("cmd");
		code=request.getParameter("category_code");
		name=request.getParameter("category_name");
		superior=request.getParameter("superiors_code");
		if((superior==null)||(superior.equals(""))){
			superior="0";
		}

		remark=request.getParameter("category_description");
		ids=request.getParameter("json");
		String start=request.getParameter("start");
		if(start==null){
			start="0";
		}
		String limit=request.getParameter("limit");
		if(limit==null){
			limit="100";
		}
		String cons=request.getParameter("name");
		if(cons==null){
			cons="";
		}

		cons="a.category_name like '%"+cons+"%' order by a.category_code asc";

		
		//获取JSON
		String table="collaboration_category a " +
				"left join ( " +
				"select category_code,category_name from collaboration_category " +
				") b on a.superiors_code=b.category_code";
		String sqlContent="a.category_code,a.category_name,isnull(b.category_name,'') as superiors,category_description";
		if(cmd.equals("List")){
			out.print(new InitDB().toExtJson(table,sqlContent, Integer.parseInt(start), Integer.parseInt(limit), cons, jndiName));
		}

		
		//返回更新结果
		if(cmd.equals("Update")){
			out.print(doUpdate(code,name, remark, superior));
		}
		
		//返回新增结果
		if(cmd.equals("Save")){
			out.print(doSave(code,name,remark, superior));
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
	public String doUpdate(String code,String name,String remark,String superior){
		sql="UPDATE collaboration_category "+
				 " SET category_name='"+name+"', category_description='"+remark+"', superiors_code='"+superior+"' "+
				 "WHERE category_code="+code+"";
		
		boolean update=new InitDB().execute(sql, jndiName);
		if(update){
			return "{success:true,info:\'更新成功\'}";
		}
			return "{failure:true,info:\'更新失败,请与管理员联系\'}";
	}
	
	//新增数据
	public String doSave(String code,String name,String remark,String superior){
		
		sql="INSERT INTO collaboration_category("+
		            "category_name, category_description, superiors_code)"+
		    "VALUES ('"+name+"', '"+remark+"', '"+superior+"')";
		
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
			sql="DELETE FROM collaboration_category "+
					"WHERE category_code="+id+"";	
			boolean re=new InitDB().execute(sql, jndiName);
			if(!re){
				return "删除编号为："+id+"的记录失败";
			}
		}
		
		
		return "删除成功";
	}
	
}
