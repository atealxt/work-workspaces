package com.hg.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.core.EasyController;
import com.hg.dto.PageLink;

@Controller
@RequestMapping("/about")
public class A07AboutController extends EasyController {

    @RequestMapping
    public String main(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) throws IOException {
        makeCommonInfo(req, model);
        makeRootMain(req, model);
        return "about";
    }

    private void makeRootMain(final HttpServletRequest req, final ModelMap model) {
        model.addAttribute("title", "About");
        model.addAttribute("technology", makeTechLink());
    }

    private List<PageLink> makeTechLink() {
        final List<PageLink> links = new ArrayList<PageLink>();
        PageLink link = new PageLink();
        link.setTitle("Google App Engine");
        link.setLink("http://code.google.com/appengine/");
        link.setMsgShow("Offers users the ability to build and host web applications on Google's infrastructure.");
        links.add(link);

        link = new PageLink();
        link.setTitle("Spring Framework");
        link.setLink("http://www.springsource.org/");
        link.setMsgShow("An open source application framework.");
        links.add(link);

        link = new PageLink();
        link.setTitle("FreeMarker");
        link.setLink("http://freemarker.org/");
        link.setMsgShow("Java Template Engine Library.");
        links.add(link);

        link = new PageLink();
        link.setTitle("jQuery");
        link.setLink("http://jquery.com/");
        link.setMsgShow("The Write Less, Do More, JavaScript Library.");
        links.add(link);

        link = new PageLink();
        link.setTitle("TinyMCE");
        link.setLink("http://tinymce.moxiecode.com/");
        link.setMsgShow("A platform independent web based Javascript HTML WYSIWYG editor control.");
        links.add(link);

        link = new PageLink();
        link.setTitle("SyntaxHighlighter");
        link.setLink("http://alexgorbatchev.com/wiki/SyntaxHighlighter/");
        link.setMsgShow("A fully functional self-contained code syntax highlighter developed in JavaScript.");
        links.add(link);

        link = new PageLink();
        link.setTitle("Free CSS Templates");
        link.setLink("http://www.freecsstemplates.org/");
        link.setMsgShow("Free standards compliant CSS templates.");
        links.add(link);

        return links;
    }
}