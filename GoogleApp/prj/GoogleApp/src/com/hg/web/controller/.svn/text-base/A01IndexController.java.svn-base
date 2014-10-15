package com.hg.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.hg.core.EasyController;
import com.hg.dto.PageLink;
import com.hg.service.ArticleService;
import com.hg.util.GaeUtil;

@Controller
public class A01IndexController extends EasyController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
    public String indexing(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        makeRootIndexing(req, model);
        return "index";
    }

    private void makeRootIndexing(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Hero's Grave");
        model.addAttribute("welcome", getMessage("welcome"));
        model.addAttribute("noArticles", getMessage("noArticles"));
        model.addAttribute("links", makeLink());
        model.addAttribute("articles", articleService.getLatest(1));

        final User user = GaeUtil.getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("logoutUrl", GaeUtil.getLogoutURL(req.getRequestURI()));
        } else {
            model.addAttribute("openId", GaeUtil.getLoginURL(req));
            model.addAttribute("logoutUrl", "");
        }
        logger.debug("openId: " + model.get("openId"));

    }

    private List<PageLink> makeLink() {
        final List<PageLink> links = new ArrayList<PageLink>();

        PageLink link = null;

        link = new PageLink();
        link.setTitle("Only webmaster can use");
        link.setLink("/admin");
        link.setMsgShow("Website Administer");
        links.add(link);

        link = new PageLink();
        link.setTitle("Website Source Code");
        link.setLink("http://code.google.com/p/herogravebygae/");
        link.setMsgShow("Google Code");
        links.add(link);

        link = new PageLink();
        link.setTitle("Google Applications Overview");
        link.setLink("http://appengine.google.com/");
        link.setMsgShow("My Applications");
        links.add(link);

        link = new PageLink();
        link.setTitle("Google Website Analysis");
        link.setLink("https://www.google.com/webmasters/tools");
        link.setMsgShow("Google Webmaster Tool");
        links.add(link);

        link = new PageLink();
        link.setTitle("Google App Engine Official Site");
        link.setLink("http://code.google.com/appengine/");
        link.setMsgShow("Google App Engine");
        links.add(link);

        link = new PageLink();
        link.setTitle("Free Online Google Sitemap Generator");
        link.setLink("http://www.xml-sitemaps.com/");
        link.setMsgShow("Google Sitemap Generator");
        links.add(link);

        link = new PageLink();
        link.setTitle("My Chinese Programming Technology Blog");
        link.setLink("http://www.blogjava.net/atealxt");
        link.setMsgShow("BlogJava");
        links.add(link);

        link = new PageLink();
        link.setTitle("My Chinese Life Blog");
        link.setLink("http://atealxt.ycool.com/");
        link.setMsgShow("Atea的勇士坟墓");
        links.add(link);

        return links;
    }
}
