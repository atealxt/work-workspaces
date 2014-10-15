package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;

@Controller
public class Z00ReleaseController extends EasyController {

    @RequestMapping("/releasenote")
    public String releaseNote(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        makeRootRelease(req, model);
        return "releasenote";
    }

    private void makeRootRelease(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Release Notes");
    }
}
