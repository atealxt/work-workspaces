package com.hg.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hg.core.EasyController;
import com.hg.dto.CommentInfo;
import com.hg.pojo.Article;
import com.hg.pojo.Comment;
import com.hg.service.ArticleService;
import com.hg.service.CommentService;
import com.hg.util.GaeUtil;
import com.hg.util.ServletUtil;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/comment")
public class A08CommentController extends EasyController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public void writeCommentInfo(@RequestParam("id") final String articleId, final HttpServletResponse resp)
            throws IOException {
        final Article art = articleService.getArticleById(articleId);
        final List<Comment> comments = art.getComments();
        final String commentHTML = commentService.genCommentHTML(articleId, comments);
        initResponse(resp);
        resp.getWriter().println(commentHTML);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addComment(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if (GaeUtil.getCurrentUser() == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        final CommentInfo info = new CommentInfo();
        info.setArticleId(req.getParameter("aid"));
        info.setTitle(req.getParameter("title"));

        final String name = req.getParameter("yourname");
        if (!StringUtil.isEmpty(name)) {
            ServletUtil.setCookie(req, resp, ServletUtil.GUEST_NAME, name);
        }
        info.setName(name);
        final String contact = req.getParameter("contact");
        if (!StringUtil.isEmpty(contact)) {
            ServletUtil.setCookie(req, resp, ServletUtil.GUEST_URL, contact);
        }
        info.setContact(contact);
        info.setContent(req.getParameter("rep_content"));
        info.setIp(ServletUtil.getReqIp(req));
        articleService.addComment(info);

        final StringBuffer sb = new StringBuffer("article/");
        sb.append(req.getParameter("aid"));
        sb.append("#comment");
        resp.sendRedirect(sb.toString());
    }
}
