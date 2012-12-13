package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;
import com.hg.service.GreetingService;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/guestbooklist")
public class B06GuestbookController extends EasyController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping
    public String list(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) throws IOException {
        if ("d".equals(req.getParameter("m"))) {
            if (!StringUtil.isEmpty(req.getParameter("id"))) {
                greetingService.removeGreeting(req.getParameter("id"));
            } else {
                final String[] sels = req.getParameterValues("sel");
                if (sels != null) {
                    for (final String id : sels) {
                        greetingService.removeGreeting(id);
                    }
                }
            }
        }

        makeRootList(req, model);
        return "admin/guestbook";
    }

    private void makeRootList(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Guest book list");
        model.addAttribute("chooseGreetings", getMessage("chooseGreetings"));
        model.addAttribute("noGreetings", getMessage("noGreetings"));

        final int maxP = greetingService.getMaxPage();
        final int iPage = getCurrentPage(req, maxP);
        model.addAttribute("currentP", iPage);
        model.addAttribute("maxP", maxP);
        model.addAttribute("greetings", greetingService.getList(iPage));
    }
}
