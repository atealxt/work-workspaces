package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;
import com.hg.service.CommentService;
import com.hg.util.StringUtil;

@Controller
public class B04CommentController extends EasyController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/commentlist")
    public String list(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) throws IOException {
        if ("d".equals(req.getParameter("m"))) {
            if (!StringUtil.isEmpty(req.getParameter("id"))) {
                commentService.remove(req.getParameter("id"));
            } else {
                final String[] sels = req.getParameterValues("sel");
                if (sels != null) {
                    for (final String id : sels) {
                        commentService.remove(id);
                    }
                }
            }
        }

        makeRootList(req, model);
        return "admin/comment";
    }

    private void makeRootList(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Comments");
        model.addAttribute("chooseComments", getMessage("chooseComments"));
        model.addAttribute("noComments", getMessage("noComments"));

        final int maxP = commentService.getMaxPage();
        final int iPage = getCurrentPage(req, maxP);
        model.addAttribute("currentP", iPage);
        model.addAttribute("maxP", maxP);
        model.addAttribute("comments", commentService.getList(iPage));
    }
}
