package com.hg.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * TODO 用于本地化设置的保存与更改。目前GAE对HttpSession只提供了有限的支持，因此暂不使用。
 */
public class LocaleFilter extends OncePerRequestFilter {

    private static Log logger = LogFactory.getLog(LocaleFilter.class);
    public static final String LOCALE_KEY = "LOCALE";

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain) throws ServletException, IOException {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            logger.warn("no session exist!");
            chain.doFilter(request, response);
            return;
        }

        Locale myLocale = request.getLocale();
        final String locale = request.getParameter("locale");
        if (locale != null) {
            logger.debug("req locale: " + locale);
            final int indexOfUnderscore = locale.indexOf('_');
            if (indexOfUnderscore != -1) {
                final String language = locale.substring(0, indexOfUnderscore);
                final String country = locale.substring(indexOfUnderscore + 1);
                myLocale = new Locale(language, country);
            } else {
                myLocale = new Locale(locale);
            }
        }

        final Locale sessionLocale = (Locale) session.getAttribute(LOCALE_KEY);
        if (sessionLocale == null || !sessionLocale.toString().equals(myLocale.toString())) {
            logger.debug("set locale: " + myLocale);
            session.setAttribute(LOCALE_KEY, myLocale);
            LocaleContextHolder.setLocale(myLocale);
        }

        chain.doFilter(request, response);
    }
}
