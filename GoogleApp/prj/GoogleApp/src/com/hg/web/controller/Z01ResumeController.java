package com.hg.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;
import com.hg.util.DateUtil;
import com.hg.util.ServletUtil;

@Controller
public class Z01ResumeController extends EasyController {

    private static Log logger = LogFactory.getLog(Z01ResumeController.class);

    @RequestMapping(value = { "/resume.html", "/resume" })
    public String resume(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final ModelMap model,
            final String psw) throws IOException {

        logger.info(ServletUtil.getReqIp(req) + " visited resume.");

        final String currentYearAndMonth = DateUtil.format(DateUtil.getCurrentTime(), "yyyyMM");
        if (!currentYearAndMonth.equals(psw)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        if ("txt".equals(req.getParameter("type"))) {
            logger.info(ServletUtil.getReqIp(req) + " downloaded resume.");
            downloadTxtResume(resp);
            return null;
        } else {
            model.addAttribute("currentYearAndMonth", currentYearAndMonth);
            return "resume/resume";
        }
    }

    private void downloadTxtResume(final HttpServletResponse resp) throws IOException {
        initResponse(resp);
        resp.setHeader("Content-disposition", "attachment;filename=LUSuo[CN].txt");
        resp.setContentType("text/plain");
        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("else/resume");
        final byte[] b = new byte[in.available()];
        in.read(b);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(b);
        final ServletOutputStream output = resp.getOutputStream();
        out.writeTo(output);
        output.flush();
        output.close();
    }
}
