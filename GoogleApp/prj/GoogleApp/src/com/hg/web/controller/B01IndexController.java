package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.core.EasyController;

@Controller
@RequestMapping("/admin")
public class B01IndexController extends EasyController {

    @RequestMapping(method = RequestMethod.GET)
    public String indexing(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeRootIndexing(model);
        return "admin/index";
    }

    private void makeRootIndexing(final ModelMap model) {
        model.addAttribute("title", "Website Administer");
    }
}
