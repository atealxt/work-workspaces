package com.hg.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.hg.util.RoleUtil;
import com.hg.util.ServletUtil;

public class RoleResolver implements WebArgumentResolver {

    private static Log logger = LogFactory.getLog(RoleResolver.class);

    @Override
    public Object resolveArgument(final MethodParameter methodParameter, final NativeWebRequest webRequest) throws Exception {
        final String className = methodParameter.getMethod().getDeclaringClass().getName();
        if (!className.startsWith("com.hg.web.controller.B")) {
            return UNRESOLVED;
        }

        if (!RoleUtil.isMaster()) {
            try {
                final HttpServletResponse resp = (HttpServletResponse) webRequest.getNativeResponse();
                if (!resp.isCommitted()) {
                    logger.warn(new StringBuilder("HTTP Status 403 - Access denied. IP=")
                            .append(ServletUtil.getReqIp((HttpServletRequest) webRequest.getNativeRequest()))
                            .append(", url=").append(
                                                     ((HttpServletRequest) webRequest.getNativeRequest())
                                                             .getRequestURI()));
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return UNRESOLVED;
    }
}