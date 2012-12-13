package com.hg.web.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.User;
import com.hg.core.EasyController;
import com.hg.dto.Mail;
import com.hg.util.DateUtil;
import com.hg.util.GaeUtil;
import com.hg.util.MailUtil;
import com.hg.util.ServletUtil;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/contact")
public class A04ContactController extends EasyController {

    @RequestMapping
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "contact";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {

        model.addAttribute("title", "Contact Me");
        model.addAttribute("guestbookclose", getMessage("guestbookclose"));

        final User user = GaeUtil.getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("logoutUrl", GaeUtil.getLogoutURL(req.getRequestURI()));
        } else {
            model.addAttribute("openId", GaeUtil.getLoginURL(req));
        }
        final String guestName = ServletUtil.getCookie(req, ServletUtil.GUEST_NAME);
        if (!StringUtil.isEmpty(guestName)) {
            model.addAttribute("guestName", guestName);
        }
        final String guestUrl = ServletUtil.getCookie(req, ServletUtil.GUEST_URL);
        if (!StringUtil.isEmpty(guestUrl)) {
            model.addAttribute("guestUrl", guestUrl);
        }

        final UUID uuid = UUID.randomUUID();
        model.addAttribute("captchaUUID", uuid.toString());
        req.getSession().setAttribute("captchaUUID", uuid.toString());

        // CAPTCHA
        model.addAttribute("captcha", ServletUtil.makeCaptcha(req.getSession()));
    }

    @RequestMapping(params = "sending", method = RequestMethod.POST)
    public void sending(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        initResponse(resp);

        if (GaeUtil.getCurrentUser() == null) {
            //close anonymity
            final StringBuilder sbb = new StringBuilder();
            sbb.append("Login to post! <a href=\"");
            sbb.append(req.getRequestURI());
            sbb.append("\">back</a>");
            resp.getWriter().println(sbb.toString());
            return;
        }

        final String captcha = req.getParameter("captcha");
        if (StringUtil.isEmpty(captcha)//
                || !StringUtil.isNum(captcha)//
                || !ServletUtil.checkCaptcha(Integer.parseInt(captcha), req.getSession())) {
            final StringBuilder sb = new StringBuilder(getMessage("wrongCaptcha"));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">back</a>");
            resp.getWriter().println(sb.toString());
            return;
        }

        final String captchaUUID = req.getParameter("captchaUUID");
        if (captchaUUID == null || req.getSession().getAttribute("captchaUUID") == null
                || !captchaUUID.equals(req.getSession().getAttribute("captchaUUID"))) {
            final StringBuilder sb = new StringBuilder(getMessage("wrongCaptcha"));
            sb.append("<a href=\"");
            sb.append(req.getRequestURI());
            sb.append("\">back</a>");
            resp.getWriter().println(sb.toString());
            return;
        }

        final String guestName = req.getParameter("yourname");
        if (!StringUtil.isEmpty(guestName)) {
            ServletUtil.setCookie(req, resp, ServletUtil.GUEST_NAME, guestName);
        }
        final String guestUrl = req.getParameter("youremail");
        if (!StringUtil.isEmpty(guestUrl)) {
            ServletUtil.setCookie(req, resp, ServletUtil.GUEST_URL, guestUrl);
        }

        final String from = "atealxt@gmail.com";
        final String to = "atealxt@gmail.com";
        final String subject = new StringBuilder(guestName).append(" ")//
                .append(DateUtil.getCurrentTime()).toString();
        final String content = new StringBuilder(guestName).append(" ").append(guestUrl).append("<hr>")
                .append(req.getParameter("content")).toString();
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
            sb.append("/home");
            sb.append("\">return</a>");
        }

        resp.getWriter().println(sb.toString());
    }
}
