package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.hg.core.EasyController;
import com.hg.dto.Topic;
import com.hg.service.ArticleService;
import com.hg.service.CommentService;
import com.hg.service.TypeService;
import com.hg.util.GaeUtil;
import com.hg.util.ServletUtil;
import com.hg.util.StringUtil;

@Controller
public class A06ArticleController extends EasyController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = { "/article/{id}", "/article/{id}.html" }, method = RequestMethod.GET)
    public String main(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            @PathVariable("id") final String id,
            final ModelMap model) throws IOException {
        model.addAttribute("id", id);
        return main(req, resp, model);
    }

    @RequestMapping(value = {"/article/{ct}/{alias}","/article/{ct}/{alias}.html"}, method = RequestMethod.GET)
    public String main(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            @PathVariable("ct") final String ct,
            @PathVariable("alias") final String alias,
            final ModelMap model) throws IOException {
        model.addAttribute("ct", ct);
        model.addAttribute("alias", alias);
        return main(req, resp, model);
    }

    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        return makeRootMain(req, resp, model);
    }

    private String makeRootMain(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        Topic topic = null;
        if (!StringUtil.isEmpty((String) model.get("id"))) {
            topic = articleService.getById((String) model.get("id"));
        } else {
            topic = articleService.getByAlias((String) model.get("alias"), (String) model.get("ct"));
        }
        if (topic != null) {
            model.addAttribute("article", topic);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        final User user = GaeUtil.getCurrentUser();
        if (user == null) {
            final StringBuilder url = new StringBuilder(req.getRequestURI()).append("?1=1");
            if (!StringUtil.isEmpty((String) model.get("id"))) {
                url.append("&id=").append((String) model.get("id"));
            } else {
                url.append("&alias=").append((String) model.get("alias")).append("&ct=")
                        .append((String) model.get("ct"));
            }
            model.addAttribute("openId", GaeUtil.getLoginURL(req));
        }
        model.addAttribute("user", user);

        model.addAttribute("latestCmt", commentService.getLatestCmts());
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("noComments", getMessage("noComments"));

        final String guestName = ServletUtil.getCookie(req, ServletUtil.GUEST_NAME);
        if (!StringUtil.isEmpty(guestName)) {
            model.addAttribute("guestName", guestName);
        }
        final String guestUrl = ServletUtil.getCookie(req, ServletUtil.GUEST_URL);
        if (!StringUtil.isEmpty(guestUrl)) {
            model.addAttribute("guestUrl", guestUrl);
        }

        return "article";
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.POST)
    public void updcnt(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            @PathVariable("id") final String id,
            final ModelMap model) throws IOException {
        articleService.updReadCnt(id, ServletUtil.getReqIp(req));
    }
}