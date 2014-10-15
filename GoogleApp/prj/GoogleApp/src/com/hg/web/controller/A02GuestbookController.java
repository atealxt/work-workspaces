package com.hg.web.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.users.User;
import com.hg.core.EasyController;
import com.hg.dto.GreetingDto;
import com.hg.pojo.Greeting;
import com.hg.service.GreetingService;
import com.hg.util.GaeUtil;
import com.hg.util.RoleUtil;
import com.hg.util.ServletUtil;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/guestbook")
public class A02GuestbookController extends EasyController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "guestbook";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Guestbook");
        model.addAttribute("guestbookclose", getMessage("guestbookclose"));

        final User user = GaeUtil.getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("logoutUrl", GaeUtil.getLogoutURL(req.getRequestURI()));
        } else {
            model.addAttribute("openId", GaeUtil.getLoginURL(req));
            model.addAttribute("captcha", ServletUtil.makeCaptcha(req.getSession()));

            final String guestName = ServletUtil.getCookie(req, ServletUtil.GUEST_NAME);
            if (!StringUtil.isEmpty(guestName)) {
                model.addAttribute("guestName", guestName);
            }
        }

        final UUID uuid = UUID.randomUUID();
        model.addAttribute("captchaUUID", uuid.toString());
        req.getSession().setAttribute("captchaUUID", uuid.toString());
        model.addAttribute("master", RoleUtil.isMaster());

        int iPage = 0;
        final String page = req.getParameter("page");
        if (!StringUtil.isEmpty(page)) {
            iPage = Integer.parseInt(req.getParameter("page"));
        }
        final GreetingDto dto = greetingService.makeInfo(iPage);
        model.addAttribute("greeting", dto);
    }

    @RequestMapping(params = "putmsg")
    public String putmsg(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        initResponse(resp);

        if (GaeUtil.getCurrentUser() == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        final String guestName = req.getParameter("guest");
        if (!StringUtil.isEmpty(guestName)) {
            ServletUtil.setCookie(req, resp, ServletUtil.GUEST_NAME, guestName);
        }

        final String content = req.getParameter("content");
        final int contentSize = StringUtil.getByteSize(StringUtil.delHtmlTag(content));
        if (content == null || contentSize < 5 || contentSize > Greeting.getMaxLen()) {
            final StringBuilder sb = new StringBuilder(getMessage("inputLengthErr",
                                                                  new Object[] { Greeting.getMaxLen() }));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">back</a>");
            resp.getWriter().println(sb.toString());
            return null;
        }

        greetingService.addGreeting(content, guestName);
        return "redirect:/guestbook";
    }

    @RequestMapping(params = "delmsg")
    public String delmsg(@RequestParam("delmsg") final String greetingId) throws IOException {
        greetingService.removeGreeting(greetingId);
        return "redirect:/guestbook";
    }
}
