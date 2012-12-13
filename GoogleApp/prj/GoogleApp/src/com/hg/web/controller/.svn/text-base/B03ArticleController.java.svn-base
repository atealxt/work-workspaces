package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.constant.CacheConstant;
import com.hg.core.EasyController;
import com.hg.dto.Topic;
import com.hg.service.ArticleService;
import com.hg.service.TypeService;
import com.hg.util.CacheUtil;
import com.hg.util.GaeUtil;
import com.hg.util.ServletUtil;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/articlelist")
public class B03ArticleController extends EasyController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        makeRootList(req, model);
        return "admin/article";
    }

    private void makeRootList(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Articles");
        model.addAttribute("noArticles", getMessage("noArticles"));

        int iPage = 0;
        final String page = req.getParameter("page");
        if (!StringUtil.isEmpty(page)) {
            iPage = Integer.parseInt(req.getParameter("page"));
        }
        final int maxP = articleService.getMaxPage(true);
        if (iPage > maxP) {
            iPage = maxP;
        }
        model.addAttribute("currentP", iPage);
        model.addAttribute("maxP", maxP);

        model.addAttribute("articles", articleService.getList(iPage, true));
    }

    @RequestMapping(params = "m=create", method = RequestMethod.GET)
    public String create(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeRootCreate(req, model);
        return "admin/articleInfo";
    }

    private void makeRootCreate(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Create New Article");
        model.addAttribute("m", "post");
        model.addAttribute("user", GaeUtil.getCurrentUser());
        model.addAttribute("types", typeService.getTypes(false));
    }

    @RequestMapping(params = "m=post", method = RequestMethod.POST)
    public String post(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Topic t = new Topic();
        initTopic(t, req);
        t.setIp(ServletUtil.getReqIp(req));
        if (articleService.postArticle(t)) {
            CacheUtil.remove(CacheConstant.CALENDAR_TIME);
            CacheUtil.remove(CacheConstant.CALENDAR);
            return "redirect:/articlelist";
        } else {
            final StringBuilder sb = new StringBuilder(getMessage("postFail"));
            sb.append(getMessage("aliasDuplicate"));
            sb.append("<br><a href=\"/articlelist\">return</a>");
            initResponse(resp);
            resp.getWriter().println(sb.toString());
            return null;
        }
    }

    @RequestMapping(params = "m=edit", method = RequestMethod.GET)
    public String edit(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeRootEdit(req, model);
        return "admin/articleInfo";
    }

    private void makeRootEdit(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Edit Article");
        model.addAttribute("m", "update");
        model.addAttribute("topic", articleService.getById(req.getParameter("id")));
        model.addAttribute("types", typeService.getTypes(false));
    }

    @RequestMapping(params = "m=update", method = RequestMethod.POST)
    public String update(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Topic t = new Topic();
        initTopic(t, req);
        t.setId(req.getParameter("id"));
        if (articleService.updateArticle(t)) {
            return "redirect:/articlelist";
        } else {
            final StringBuilder sb = new StringBuilder(getMessage("postFail"));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">return</a>");

            initResponse(resp);
            resp.getWriter().println(sb.toString());
            return null;
        }
    }

    private void initTopic(final Topic t,final HttpServletRequest req){
        t.setTitle(req.getParameter("title"));
        t.setSummary(req.getParameter("summery"));
        t.setContent(req.getParameter("content"));
        t.setPostBySummary(!StringUtil.isEmpty(req.getParameter("chkSummery")));
        t.setType(req.getParameter("nCatalog"));
        t.setAlias(req.getParameter("alias"));
    }

    @RequestMapping(params = "m=d")
    public String del(final HttpServletRequest req) {
        articleService.remove(req.getParameter("id"));
        return "redirect:articlelist";
    }
}
