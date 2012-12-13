/**
 * 
 */
package com.test.util;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class CheckLoginInterceptor extends AbstractInterceptor {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		/*
		ActionContext ac = actionInvocation.getInvocationContext();
		Map session = ac.getSession(); 
		//String namespace = actionInvocation.getProxy().getNamespace();
		//String ActionName = actionInvocation.getProxy().getActionName();		
		UserModel user = (UserModel)session.get("login_user"); 
		if(user!=null){ 
			return actionInvocation.invoke(); 
		}else{ 
			HttpServletRequest request= (HttpServletRequest) ac.get(StrutsStatics.HTTP_REQUEST);
			if(request.getParameter("useAjax")!=null&&"yes".equals(request.getParameter("useAjax"))){
				HttpServletResponse res = (HttpServletResponse) ac.get(StrutsStatics.HTTP_RESPONSE);
				PrintWriter out = res.getWriter();
				JSONObject json = new JSONObject();
				json.put("timeout", "yes");
				out.print(json);
				//out.print("timeout");
				out.close();
				return null; 
			}else{
				return "timeout";
			}
		}
		*/
		return null;
	}

}
