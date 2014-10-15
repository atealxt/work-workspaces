package com.forward.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HttpForwardController {

    /** 用url转向的方式曲线实现转发，但只能转发http get请求 */
    @RequestMapping(value = "/forward301", method = RequestMethod.GET)
    public void forward301(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        // forward(req, resp, model, "get");
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.setHeader("Location", "http://www.google.com/");
    }
}
