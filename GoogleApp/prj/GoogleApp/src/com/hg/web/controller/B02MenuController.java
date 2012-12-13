package com.hg.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.core.EasyController;
import com.hg.dto.PageLink;

@Controller
@RequestMapping("/menu")
public class B02MenuController extends EasyController {

    @RequestMapping(method = RequestMethod.GET)
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) throws IOException {
        makeRootMain(req, model);
        return "admin/menu";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "menu");
        model.addAttribute("recycling", getMessage("recycling"));
        model.addAttribute("setting", getMessage("setting"));
        model.addAttribute("exit", getMessage("exit"));
        model.addAttribute("links", makeLink());
    }

    private List<PageLink> makeLink() {
        final List<PageLink> links = new ArrayList<PageLink>();
        PageLink link = null;

        link = new PageLink();
        link.setLink("/articlelist?m=create");
        link.setMsgShow("New Article");
        links.add(link);

        link = new PageLink();
        link.setLink("/articlelist");
        link.setMsgShow("Article list");
        links.add(link);

        link = new PageLink();
        link.setLink("/commentlist");
        link.setMsgShow("Comment");
        links.add(link);

        link = new PageLink();
        link.setLink("/guestbooklist");
        link.setMsgShow("Guest book");
        links.add(link);

        link = new PageLink();
        link.setLink("/typelist");
        link.setMsgShow("Article type");
        links.add(link);

        link = new PageLink();
        link.setMsgShow("Photo album");
        links.add(link);

        link = new PageLink();
        link.setMsgShow("Links");
        links.add(link);

        return links;
    }
}
