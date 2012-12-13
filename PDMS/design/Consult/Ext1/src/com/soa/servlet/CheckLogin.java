package com.soa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.soa.userbean.InitDB;
import com.soa.userbean.UserInfo;

/**
 * @author Gavin
 * 
 */
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	String json = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Logger logger = Logger.getLogger(CheckLogin.class);		
		UserInfo info = null;

		// 获得登陆的用户名和密码
		String userName = request.getParameter("name");
		String userpswd = request.getParameter("pws");

		try {
			new InitDB();
			// 连接数据库
			conn = InitDB.InitDB("jdbc/Fortune-Sun");
			sql = "select * from personnel_info a left join unit_info b on a.unit_code=b.unit_code where zjm=? and login_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userpswd);
			rs = ps.executeQuery();

			// 验证用户是否存在
			if (rs.next()) {
				info = new UserInfo();
				info.setPersonnelCode(rs.getString("personnel_code"));
				info.setPersonnelName(rs.getString("personnel_name"));
				info.setUnit_code(rs.getString("unit_code"));
				info.setUnitName(rs.getString("unit_name"));
				info.setPart_id(rs.getInt("part_id"));
				session.setAttribute("session", info);
				
				//登录信息
				logger.info("用户信息："+rs.getString("unit_code")+"||"+rs.getString("unit_name")+"||"+rs.getString("personnel_code")+"||"+rs.getString("personnel_name"));
			
				
				json = "{success:true,msg:\'ok\'}";
			} else {
				json = "{success:true,msg:\'帐户或密码错误\'}";
			}

			// 输入判断结果
			out.println(json);
		} catch (Exception e) {
			logger.error("登录时错误信息(一)：",e);
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("登录时错误信息(二)：",e);
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
