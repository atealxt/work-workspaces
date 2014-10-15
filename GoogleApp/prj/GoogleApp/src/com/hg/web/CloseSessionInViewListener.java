package com.hg.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hg.core.dao.JdoManager;

public class CloseSessionInViewListener implements ServletRequestListener {

    private static Log logger = LogFactory.getLog(JdoManager.class);

    @Override
    public void requestInitialized(final ServletRequestEvent arg0) {
        // do nothing
    }

    @Override
    public void requestDestroyed(final ServletRequestEvent arg0) {
        try {
            JdoManager.closeSession();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
