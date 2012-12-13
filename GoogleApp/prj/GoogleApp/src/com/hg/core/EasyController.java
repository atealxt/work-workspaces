package com.hg.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ui.ModelMap;

import com.hg.util.StringUtil;

public abstract class EasyController {

    protected Log logger = LogFactory.getLog(EasyController.class);

    protected void initResponse(final HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");// for AJAX encode
    }

    protected int getCurrentPage(final HttpServletRequest req, final int maxPage) {
        int iPage = 0;
        final String page = req.getParameter("page");
        if (!StringUtil.isEmpty(page)) {
            iPage = Integer.parseInt(req.getParameter("page"));
        }
        if (iPage > maxPage) {
            iPage = maxPage;
        }
        return iPage;
    }

    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor messageAccessor;

    private void chkMessageAccessor() {
        if (messageAccessor == null) {
            messageAccessor = new MessageSourceAccessor(messageSource);
        }
    }

    /** get message from resource file: ApplicationResourcesXXX.properties */
    protected String getMessage(final String key) {
        chkMessageAccessor();
        return messageAccessor.getMessage(key);
    }

    /** get message from resource file: ApplicationResourcesXXX.properties */
    protected String getMessage(final String key, final Object[] param) {
        chkMessageAccessor();
        return messageAccessor.getMessage(key, param);
    }

    protected void makeCommonInfo(final HttpServletRequest req, final ModelMap model) {
        // Jsp Tag in Freemarker is not stable in GAE, so get i18n in action instead of in ftl tag.
        model.addAttribute("subtitle", getMessage("subtitle"));
    }
}
