package com.easyjf.simpleblog.mvc;

import com.easyjf.container.annonation.Action;
import com.easyjf.core.support.query.QueryObject;
import com.easyjf.simpleblog.domain.Album;
import com.easyjf.simpleblog.domain.AlbumComment;
import com.easyjf.simpleblog.domain.Topic;
import com.easyjf.simpleblog.domain.TopicComment;
import com.easyjf.simpleblog.domain.User;
import com.easyjf.simpleblog.query.AlbumQuery;
import com.easyjf.simpleblog.query.TopicQuery;
import com.easyjf.simpleblog.service.IAlbumCategoryService;
import com.easyjf.simpleblog.service.IAlbumService;
import com.easyjf.simpleblog.service.IBlogService;
import com.easyjf.simpleblog.service.ICommentService;
import com.easyjf.simpleblog.service.ILinkService;
import com.easyjf.simpleblog.service.ITopicCategoryService;
import com.easyjf.simpleblog.service.ITopicService;
import com.easyjf.simpleblog.service.IUserService;
import com.easyjf.simpleblog.service.UserContext;
import com.easyjf.util.CommUtil;
import com.easyjf.web.ActionContext;
import com.easyjf.web.Module;
import com.easyjf.web.Page;
import com.easyjf.web.WebForm;
import com.easyjf.web.core.AbstractPageCmdAction;
import com.easyjf.web.tools.IPageList;

@Action(autoToken = true)
public class PortalAction extends AbstractPageCmdAction {
	private ITopicCategoryService topicCategoryService;
	private ITopicService topicService;
	private ICommentService commentService;
	private IBlogService blogService;
	private IUserService userService;
	private ILinkService linkService;
	private IAlbumCategoryService albumCategoryService;
	private IAlbumService albumService;

	@Override
	public Object doBefore(WebForm form, Module module) {	
		QueryObject queryObject = new QueryObject();
		queryObject.setOrderBy("inputTime");
		queryObject.setOrderType("DESC");
		form.addResult("recentCommentList", this.commentService.getCommentBy(
				queryObject).getResult());
		form.addResult("recentPostList", this.topicService.getTopicBy(
				queryObject).getResult());
		form.addResult("categoryList", this.topicCategoryService
				.getTopicCategoryBy(new QueryObject()).getResult());
		form.addResult("albumCategoryList", this.albumCategoryService.getAlbumCategoryBy(new QueryObject()).getResult());
		form.addResult("linkList", this.linkService.getLinkBy(new QueryObject()));
		form.addResult("blog", this.blogService.getBlog());
		return super.doBefore(form, module);
	}

	/**
	 * blog首页
	 * 
	 * @param form
	 * @return
	 */
	public Page doIndex(WebForm form) {
		TopicQuery topicQuery =form.toPo(TopicQuery.class);
		IPageList pageList = this.topicService.getTopicBy(topicQuery);
		QueryObject qo = new QueryObject();
		qo.setOrderBy("inputTime");
		qo.setOrderType("DESC");
		form.addResult("topicCommentList", this.commentService
				.getTopicCommentBy(qo).getResult());
		form.addResult("albumCommentList", this.commentService
				.getAlbumCommentBy(qo).getResult());
		qo=new QueryObject();
		qo.setPageSize(-1);
		form.addResult("linkList", this.linkService.getLinkBy(qo).getResult());
		CommUtil.saveIPageList2WebForm(pageList, form);
		return page("index");
	}

	/**
	 * 显示日志列表
	 * 
	 * @param form
	 * @return
	 */
	public Page doTopicList(WebForm form) {
		TopicQuery query = form.toPo(TopicQuery.class);
		IPageList pageList = this.topicService.getTopicBy(query);
		CommUtil.saveIPageList2WebForm(pageList, form);
		return page("topicList");
	}

	/**
	 * 显示日志主题
	 * 
	 * @param form
	 * @return
	 */
	public Page doTopicShow(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Topic topic = this.topicService.getTopic(id);
		topic.setReadTimes(topic.getReadTimes()+1);
		form.addPo(topic);
		return page("topicShow");
	}

	/**
	 * 发表日志评论
	 * 
	 * @param form
	 * @return
	 */
	public Page doTopicComment(WebForm form) {
		User u=UserContext.getCurrentUser();
		if(u==null){return page("login");}
		TopicComment comment = form.toPo(TopicComment.class);
		comment.setContent(CommUtil
				.cleanHtmlTag(form.get("content").toString()));
		comment.setIp(ActionContext.getContext().getRequest().getRemoteAddr());
		comment.setUser(u);
		this.commentService.addComment(comment);
		return new Page("gotoTopic", "portal.ejf?cmd=topicShow&id="
				+ comment.getTopic().getId(), "html");
	}

	/**
	 * 显示相册列表
	 * 
	 * @param form
	 * @return
	 */
	public Page doAlbumList(WebForm form) {
		AlbumQuery query = form.toPo(AlbumQuery.class);
		IPageList pageList = this.albumService.getAlbumBy(query);
		CommUtil.saveIPageList2WebForm(pageList, form);
		return page("albumList");
	}

	/**
	 * 显示相册内容
	 * 
	 * @param form
	 * @return
	 */
	public Page doAlbumShow(WebForm form) {
		Long id = new Long(CommUtil.null2String(form.get("id")));
		Album album = this.albumService.getAlbum(id);
		album.setReadTimes(album.getReadTimes()+1);
		form.addPo(album);
		return page("albumShow");
	}

	/**
	 * 发表相册评论
	 * 
	 * @param form
	 * @return
	 */
	public Page doAlbumComment(WebForm form) {
		User u=UserContext.getCurrentUser();
		if(u==null){return page("login");}
		AlbumComment comment = form.toPo(AlbumComment.class);
		comment.setIp(ActionContext.getContext().getRequest().getRemoteAddr());
		comment.setUser(u);
		this.commentService.addComment(comment);
		return new Page("gotoTopic", "portal.ejf?cmd=albumShow&id="
				+ comment.getAlbum().getId(), "html");
	}

	/**
	 * 获得博客rss
	 * 
	 * @param form
	 * @return
	 */
	public Page doRss(WebForm form) {
		TopicQuery topicQuery = new TopicQuery();
		topicQuery.setPageSize(20);
		IPageList pageList = this.topicService.getTopicBy(topicQuery);
		CommUtil.saveIPageList2WebForm(pageList, form);
		Page page = page("rss.xml");
		page.setContentType("xml");
		return page;
	}

	public Page doSaveRegister(WebForm form) {
		User user=form.toPo(User.class);
		if(this.hasErrors())
		{
			return page("register");
		}
		this.userService.addUser(user);
		return go("index");
	}

	/**
	 * 普通用户登录
	 * 
	 * @param form
	 * @return
	 */
	public Page doLogin(WebForm form) {
		User user = this.userService.login(CommUtil.null2String(form
				.get("userName")), CommUtil.null2String(form.get("password")));
		if (user != null) {
			ActionContext.getContext().getSession().setAttribute("user", user);
			return go("index");
		} else {
			form.addResult("msg", "用户名或密码不正确，请重新输入！");
		}
		return page("login");
	}
	public Page doLogout(WebForm form) {
		ActionContext.getContext().getSession().removeAttribute("user");
		return go("index");
	}
	/**
	 * 管理员登录
	 * 
	 * @param form
	 * @return
	 */
	public Page doAdminLogin(WebForm form) {
		String name=CommUtil.null2String(form.get("userName"));
		boolean ret = blogService.adminLogin(name, CommUtil.null2String(form.get("password")));
		if (ret) {
			ActionContext.getContext().getSession().setAttribute("ADMIN","true");
		} else {
			this.addError("msg", "用户名或密码错误，请重新登录!");
		}
		return pageForExtForm(form);
	}
	public Page doAdminLogout(WebForm form) {
		ActionContext.getContext().getSession().removeAttribute("ADMIN");
		return go("index");
	}
	public Page doHomePage(WebForm form) {
		this.doIndex(form);
		return page("homePage");
	}
	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setTopicCategoryService(
			ITopicCategoryService topicCategoryService) {
		this.topicCategoryService = topicCategoryService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	public void setLinkService(ILinkService linkService) {
		this.linkService = linkService;
	}

	public void setAlbumCategoryService(
			IAlbumCategoryService albumCategoryService) {
		this.albumCategoryService = albumCategoryService;
	}

	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}
}
