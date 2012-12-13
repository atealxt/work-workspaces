package org.ywq.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ywq.entity.User;
import org.ywq.service.IUserService;
import org.ywq.service.impl.UserService;

/**
 * @author ai5qiangshao E-mail:ai5qiangshao@163.com
 * @version 创建时间：Aug 7, 2009 6:21:58 PM
 * @Package org.ywq.struts.action
 * @Description 类说明
 */
public class UserAction extends Action {

	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = request.getParameter("method");
		System.out.println(method);
		if (method.equals("login")) {
			User us = userService.login(request.getParameter("uname"));
			if (us != null) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("error");
			}
		} else if (method.equals("reg")) {
			try {
				User us = new User();
				us.setUname(request.getParameter("uname"));
				us.setUsex(request.getParameter("sex"));
				us.setUage(Integer.parseInt("11"));
				User newus = userService.save(us);
				System.out.println(newus.getUname() + "=====" + newus.getUid());
				return mapping.findForward("success");
			} catch (Exception e) {
				e.printStackTrace();
				mapping.findForward("error");
			}
		} else if (method.equals("list")) {
			String strnum=request.getParameter("pageNo");
			System.out.println(strnum);
			Integer page = Integer.parseInt(strnum==null?"1":strnum);
			String where=((DynaActionForm)form).getString("query");
			System.out.println(where+"===========");
			request.setAttribute("pageModel", userService.list(page, 5,where));
			
			return mapping.findForward("list");
		}

		return super.execute(mapping, form, request, response);
	}

}
