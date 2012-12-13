package com.oa.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.oa.hibernate.beans.Meeting;
import com.oa.hibernate.beans.Sms;
import com.oa.hibernate.dao.MeetingDAO;
import com.oa.struts.forms.MeetingForm;
import com.oa.struts.forms.PageForm;
import com.oa.struts.util.Constants;
import com.oa.struts.util.Pager;

public class MeetingAction extends BaseAction {

	protected MeetingDAO meetingDAO;

	Logger log = Logger.getLogger(this.getClass());

	public MeetingDAO getMeetingDAO() {
		return meetingDAO;
	}

	public void setMeetingDAO(MeetingDAO meetingDAO) {
		this.meetingDAO = meetingDAO;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageForm pageForm = (PageForm) form;

		// get pager
		Pager pager = meetingDAO.findPager(pageForm.getPageSize(), pageForm
				.getPageNo());

		// set meetingList
		request.setAttribute("meetingList", pager.getResultList());

		// set pager
		request.setAttribute("pager", pager);

		// save session
		setSession(request, Constants.PAGER_MEETING, pageForm);

		ActionForward forward = mapping.findForward(Constants.LIST_KEY);
		return (forward);
	}

	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageForm pageForm = (PageForm) getSession(request,
				Constants.PAGER_MEETING);

		// get pager
		Pager pager = meetingDAO.findPager(pageForm.getPageSize(), pageForm
				.getPageNo());

		// set meetingList
		request.setAttribute("meetingList", pager.getResultList());

		// set pager
		request.setAttribute("pager", pager);

		// save session
		setSession(request, Constants.PAGER_MEETING, pageForm);

		ActionForward forward = mapping.findForward(Constants.LIST_KEY);
		return (forward);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = mapping.findForward(Constants.ADD_KEY);
		return (forward);
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		MeetingForm meetingForm = (MeetingForm) form;
		String username = getUsername(request);

		// insert object
		Meeting meeting = new Meeting();
		meeting.setSender(username);
		meeting.setStarttime(meetingForm.getStarttime());
		meeting.setEndtime(meetingForm.getEndtime());
		meeting.setAddress(meetingForm.getAddress());
		meeting.setTitle(meetingForm.getTitle());
		meeting.setContent(meetingForm.getContent());
		//meeting.setIsread(Constants.NO);
		meetingDAO.insert(meeting);

		// save messages
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"meeting.message.add.success"));
		saveErrors(request, messages);

		// get pageform from session
		PageForm pageForm = (PageForm) getSession(request,
				Constants.PAGER_MEETING);

		// get pager form list page
		Pager pager = meetingDAO.findPager(pageForm.getPageSize(), pageForm
				.getPageNo());
		request.setAttribute("meetingList", pager.getResultList());
		request.setAttribute("pager", pager);

		return mapping.findForward(Constants.LIST_KEY);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		String id = request.getParameter("id");

		if (id == null) {
			// id not exist, save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"meeting.message.edit.notexist"));
			saveErrors(request, messages);
			return mapping.findForward(Constants.LIST_KEY);
		} else {
			// get object
			Meeting meeting = meetingDAO.findById(id);
			if (meeting == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"meeting.message.edit.notexist"));
				saveErrors(request, messages);
				return mapping.findForward(Constants.LIST_KEY);
			}

			// save form object
			MeetingForm meetingForm = new MeetingForm();
			meetingForm.setId(meeting.getId().toString());
			meetingForm.setSender(meeting.getSender());
			meetingForm.setStarttime(meeting.getStarttime());
			meetingForm.setEndtime(meeting.getEndtime());
			meetingForm.setAddress(meeting.getAddress());
			meetingForm.setTitle(meeting.getTitle());
			meetingForm.setContent(meeting.getContent());
			//meetingForm.setIsread(meeting.getIsread());

			// save in request
			request.setAttribute("meetingFormBean", meetingForm);

			return mapping.findForward(Constants.EDIT_KEY);
		}
	}
	/*
	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		String id = request.getParameter("id");
		
		if (id == null) {
			// id not exist, save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"meeting.message.edit.notexist"));
			saveErrors(request, messages);
			return mapping.findForward(Constants.LIST_KEY);
		} else {
			// get object
			Meeting meeting = meetingDAO.findById(id);
			if (meeting == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"metting.message.edit.notexist"));
				saveErrors(request, messages);
				return mapping.findForward(Constants.LIST_KEY);
			}
			
			// set isread
			//meeting.setIsread(Constants.YES);
			meetingDAO.update(meeting);
			
			String username = getUsername(request);
			
			// get pageForm from session
			PageForm pageForm = (PageForm)getSession(request, Constants.PAGER_MEETING);
			
			// get pager form list page
			Pager pager = meetingDAO.findPager(pageForm
					.getPageSize(), pageForm.getPageNo());
			request.setAttribute("meetingList", pager.getResultList());
			request.setAttribute("pager", pager);
		}
		return mapping.findForward(Constants.LIST_KEY);
	}
	*/
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		String id = request.getParameter("id");

		if (id == null) {
			// id not exist, save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"meeting.message.edit.notexist"));
			saveErrors(request, messages);
			return mapping.findForward(Constants.LIST_KEY);
		} else {
			// get object
			Meeting meeting = meetingDAO.findById(id);
			if (meeting == null) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"meeting.message.edit.notexist"));
				saveErrors(request, messages);
				return mapping.findForward(Constants.LIST_KEY);
			}

			// save form object
			MeetingForm meetingForm = new MeetingForm();
			meetingForm.setId(meeting.getId().toString());
			meetingForm.setSender(meeting.getSender());
			meetingForm.setStarttime(meeting.getStarttime());
			meetingForm.setEndtime(meeting.getEndtime());
			meetingForm.setAddress(meeting.getAddress());
			meetingForm.setTitle(meeting.getTitle());
			meetingForm.setContent(meeting.getContent());

			// save in request
			request.setAttribute("meetingFormBean", meetingForm);

			return mapping.findForward(Constants.VIEW_KEY);
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		MeetingForm meetingForm = (MeetingForm) form;
		Meeting meeting = new Meeting();

		// update object
		meeting.setId(new Integer(meetingForm.getId()));
		meeting.setSender(meetingForm.getSender());
		meeting.setStarttime(meetingForm.getStarttime());
		meeting.setEndtime(meetingForm.getEndtime());
		meeting.setAddress(meetingForm.getAddress());
		meeting.setTitle(meetingForm.getTitle());
		meeting.setContent(meetingForm.getContent());
		//meeting.setIsread(Constants.NO);
		meetingDAO.update(meeting);

		// save messages
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"meeting.message.edit.success"));
		saveErrors(request, messages);

		// get pageForm from session
		PageForm pageForm = (PageForm) getSession(request,
				Constants.PAGER_MEETING);

		// get pager form list page
		Pager pager = meetingDAO.findPager(pageForm.getPageSize(), pageForm
				.getPageNo());
		request.setAttribute("meetingList", pager.getResultList());
		request.setAttribute("pager", pager);

		ActionForward forward = mapping.findForward(Constants.LIST_KEY);
		return (forward);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();

		String id = request.getParameter("id");
		if (id == null) {
			// if id not exist
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"meeting.message.edit.notexist"));
		} else {
			// delete object
			meetingDAO.delete(id);

			// save messages
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"meeting.message.delete.success"));

			// get pageForm from session
			PageForm pageForm = (PageForm) getSession(request,
					Constants.PAGER_MEETING);

			// get pager form list page
			Pager pager = meetingDAO.findPager(pageForm.getPageSize(), pageForm
					.getPageNo());
			request.setAttribute("meetingList", pager.getResultList());
			request.setAttribute("pager", pager);
		}
		saveErrors(request, messages);
		return mapping.findForward(Constants.LIST_KEY);
	}
}
