package com.hg.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hg.core.Configuration;
import com.hg.core.DIManager;

public class SystemInitialization extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public final void init() throws ServletException {
        DIManager.getBean(Configuration.class).config();
    }
}