package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.hg.core.EasyController;
import com.hg.dto.Mail;
import com.hg.util.GaeUtil;
import com.hg.util.MailUtil;
import com.hg.util.StringUtil;

@Controller
public class A03MailController extends EasyController {

    @RequestMapping(value = "/sendmail/{to}", method = RequestMethod.GET)
    public String main(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final ModelMap model,
            @PathVariable("to") final String to) throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "mail";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Send Mail");

        final User user = GaeUtil.getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("touser", req.getParameter("to"));
            model.addAttribute("logoutUrl", GaeUtil.getLogoutURL(req.getRequestURI()));
        } else {
            model.addAttribute("openId", GaeUtil.getLoginURL(req));
        }
    }

    private boolean validate(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final User user = GaeUtil.getCurrentUser();
        if (user == null) {
            final StringBuilder sb = new StringBuilder(getMessage("pleaseLogin"));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">back</a>");
            resp.getWriter().println(sb.toString());
            return false;
        }
        return true;
    }

    @RequestMapping(params = "sending", method = RequestMethod.POST)
    public void sending(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        initResponse(resp);

        if (!validate(req, resp)) {
            return;
        }

        final User user = GaeUtil.getCurrentUser();
        final String from = user.getEmail(); // only support gmail
        final String to = req.getParameter("to");
        final String subject = req.getParameter("Subject");
        final String content = req.getParameter("content");
        final Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(content);
        mail.setMultiPart(StringUtil.isEmpty(req.getParameter("base")));

        final StringBuilder sb = new StringBuilder();
        final boolean sendOk = MailUtil.sendMail(mail);
        if (!sendOk) {
            sb.append(getMessage("sendFail"));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">back</a>");
        } else {
            sb.append(getMessage("sendSuccess"));
            sb.append("<a href=\"");
            sb.append("/guestbook");
            sb.append("\">return</a>");
        }

        resp.getWriter().println(sb.toString());
    }
}
