package com.sitechasia.ipcc.webservices.proxy.service.impl;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Filter;
import org.restlet.Router;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.springframework.beans.factory.InitializingBean;

public class WebServiceUrlProcessor extends Filter implements InitializingBean {

    Log log = LogFactory.getLog(getClass());

    private Router targetRouter = null;

    private String path = "";

    @Override
    protected int beforeHandle(final Request request, final Response response) {
        Reference reference = null;
        String queryString = "";
        try {
            reference = request.getResourceRef();
            if (reference.getQuery() != null) {
                queryString = reference.getQuery();
            }
            log.info(org.apache.commons.lang.StringUtils.repeat("_", 100));
            queryString = getForwardQueryStr(request);


            final String urlPre = "http://172.20.23.171:8888";
            final String url = request.getResourceRef().getPath();
            final int index = url.toLowerCase().indexOf(urlPre.toLowerCase());
            final String urlAfter = "";



            log.info("url previous: " + urlPre);
            log.info("url after: " + url.substring(index + urlPre.length()));
            log.info("queryString:" + queryString);


            request.getAttributes().put("serviceUrl", urlPre);
            request.getAttributes().put("refpath", urlAfter);
            request.getAttributes().put("querystr", queryString);

            log.info(org.apache.commons.lang.StringUtils.repeat("_", 100));
        } catch (final Exception e) {
            log.error(String.format("处理REST请求(%s)失败!", request.getResourceRef()));
            e.printStackTrace();
            return SKIP;
        }
        return CONTINUE;
    }

    private String getForwardQueryStr(final Request restreq) {
        String queryStr = "";
        try {
            queryStr = restreq.getResourceRef().getQuery();
        } catch (final Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            log.error("从请求中获取查询字符串出现错误!");
        }
        return queryStr;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        log.info("attach the dispather filter to router !");
        getTargetRouter().attach(getPath(), this);
    }

    public Router getTargetRouter() {
        return targetRouter;
    }

    public void setTargetRouter(final Router targetRouter) {
        this.targetRouter = targetRouter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }
}
