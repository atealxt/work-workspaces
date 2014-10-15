package com.hg.web.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hg.core.EasyController;
import com.hg.service.ArticleService;
import com.hg.service.CommentService;
import com.hg.service.TypeService;
import com.hg.util.DateUtil;
import com.hg.util.GaeUtil;
import com.hg.util.StringUtil;

import freemarker.template.Template;

@Controller
public class A05BlogController extends EasyController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TypeService typeService;

    @Autowired
    @Qualifier("freemarkerConfig")
    private FreeMarkerConfigurer freemarkerConfig;

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "blog";
    }

    @RequestMapping(value = "/blog/{type}", method = RequestMethod.GET)
    public String main(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final ModelMap model,
            @PathVariable("type") final String type) throws IOException {
        model.addAttribute("t", type);
        return main(req, resp, model);
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        // Build the data-model
        model.addAttribute("title", "Blog");
        model.addAttribute("noComments", getMessage("noComments"));
        model.addAttribute("noArticles", getMessage("noArticles"));
        model.addAttribute("user", GaeUtil.getCurrentUser());

        int iPage = 0;
        if (!StringUtil.isEmpty(req.getParameter("page"))) {
            iPage = Integer.parseInt(req.getParameter("page"));
        }
        final int maxP = articleService.getMaxPage((String) model.get("t"), false);
        if (iPage > maxP) {
            iPage = maxP;
        }
        model.addAttribute("currentP", iPage);
        model.addAttribute("maxP", maxP);
        model.addAttribute("articles", articleService.getList(iPage, false, (String) model.get("t")));
        model.addAttribute("latestCmt", commentService.getLatestCmts());
        model.addAttribute("types", typeService.getTypes());
    }

    @RequestMapping("/blogc/{day}")
    public void content(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final ModelMap model,
            @PathVariable("day") final String day) throws IOException {
        model.addAttribute("day", day);
        makeRootContent(req, model);

        final Template t//
        = new Template("", new StringReader("<#import \"macro/articlelist.ftl\" as articlelist><@articlelist.page />"),
                       freemarkerConfig.getConfiguration());
        initResponse(resp);
        try {
            t.process(model, resp.getWriter());
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void makeRootContent(final HttpServletRequest req, final ModelMap model) {
        final Date zeroClock = DateUtil.getZeroClock(DateUtil.parse((String) model.get("day"), "yyyyMMdd"));
        final Date twentyFourClock = DateUtil.getTwentyFourClock(DateUtil.parse((String) model.get("day"), "yyyyMMdd"));

        int iPage = 0;
        if (!StringUtil.isEmpty(req.getParameter("page"))) {
            iPage = Integer.parseInt(req.getParameter("page"));
        }
        final int maxP = articleService.getMaxPage(zeroClock, twentyFourClock);
        if (iPage > maxP) {
            iPage = maxP;
        }
        model.addAttribute("currentP", iPage);
        model.addAttribute("maxP", maxP);
        model.addAttribute("pagingUrl", "/blogc/" + (String) model.get("day"));

        model.addAttribute("articles", articleService.getList(iPage, zeroClock, twentyFourClock));
        model.addAttribute("noArticles", getMessage("noArticles"));
    }
}
