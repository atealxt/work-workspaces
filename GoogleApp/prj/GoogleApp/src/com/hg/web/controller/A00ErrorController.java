package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;

@Controller
@RequestMapping("/error.html")
public class A00ErrorController extends EasyController {

    @RequestMapping
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "error";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        // Build the data-model
        model.addAttribute("title", "Page Error");
        if (String.valueOf(HttpServletResponse.SC_NOT_FOUND).equals(req.getParameter("code"))) {
            model.addAttribute("information", make404Html());
        } else if (String.valueOf(HttpServletResponse.SC_FORBIDDEN).equals(req.getParameter("code"))) {
            model.addAttribute("information", make403Html());
        } else {
            model.addAttribute("information", make500Html());
        }
    }

    private String make404Html() {
        return "HTTP Status 404 - Requested address is not available.";
    }

    private String make403Html() {
        return "HTTP Status 403 - Access to the requested resource has been denied.";
    }

    private String make500Html() {
        return "HTTP Status 500 - Internal server error.";
    }
}
