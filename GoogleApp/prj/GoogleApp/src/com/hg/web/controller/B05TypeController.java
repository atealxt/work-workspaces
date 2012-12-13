package com.hg.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.core.EasyController;
import com.hg.dto.TypeInfo;
import com.hg.service.TypeService;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/typelist")
public class B05TypeController extends EasyController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model)
            throws IOException {
        makeRootMain(req, model);
        return "admin/type";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "Type");
        model.addAttribute("noTypes", getMessage("noTypes"));
        model.addAttribute("types", typeService.getTypes());
    }

    @RequestMapping(params = "m=add", method = RequestMethod.POST)
    public String addType(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if (StringUtil.isEmpty(req.getParameter("Category"), true)) {
            setErr(req, resp,//
                   getMessage("nullValidate", new Object[] { getMessage("typeName") }));
            return null;
        } else {
            final TypeInfo info = new TypeInfo();
            info.setName(req.getParameter("Category"));
            if (!typeService.postType(info)) {
                setErr(req, resp, getMessage("typeDuplicate"));
                return null;
            }
        }

        return "redirect:/typelist";
    }

    @RequestMapping(params = "m=del")
    public String delType(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if (!typeService.removeType(req.getParameter("id"))) {
            setErr(req, resp, getMessage("typeDelErr"));
            return null;
        }
        return "redirect:/typelist";
    }

    @RequestMapping(params = "m=upd", method = RequestMethod.POST)
    public String updType(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final TypeInfo info = new TypeInfo();
        info.setId(req.getParameter("id"));
        info.setName(req.getParameter("Category"));
        if (!typeService.updateType(info)) {
            setErr(req, resp, getMessage("updateErr"));
            return null;
        }
        return "redirect:/typelist";
    }

    private void setErr(final HttpServletRequest req, final HttpServletResponse resp, final String errMsg)
            throws IOException {
        final StringBuilder sb = new StringBuilder("<b>");
        sb.append(errMsg);
        sb.append("</b>");
        sb.append("<br><a href=\"");
        sb.append(req.getRequestURI());
        sb.append("\">back</a>");

        initResponse(resp);
        resp.getWriter().println(sb.toString());
    }
}
