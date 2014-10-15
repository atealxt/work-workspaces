package com.forward.action;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noelios.restlet.http.HttpConstants;

@Controller
public class ForwardController implements InitializingBean {

    private static final long UNKNOWN_SIZE = -1;
    private final Logger logger = Logger.getLogger(ForwardController.class);
    private HttpClient httpClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        final MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(10000);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(10);
        connectionManager.getParams().setMaxTotalConnections(100);
        connectionManager.getParams().setTcpNoDelay(true);

        // Create the internal client connector
        this.httpClient = new HttpClient(connectionManager);
        httpClient.getParams().setAuthenticationPreemptive(false);
        httpClient.getParams().setConnectionManagerTimeout(10000);
        httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        httpClient.getParams().setSoTimeout(10000);

        System.out.println("Starting the HTTP client");
    }

    @RequestMapping(value = "/rest/forward", method = RequestMethod.GET)
    public void get(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        forward(req, resp, model, "get");
    }

    @RequestMapping(value = "/rest/forward", method = RequestMethod.POST)
    public void post(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        forward(req, resp, model, "post");
    }

    @RequestMapping(value = "/rest/forward", method = RequestMethod.PUT)
    public void put(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        forward(req, resp, model, "put");
    }

    @RequestMapping(value = "/rest/forward", method = RequestMethod.DELETE)
    public void delete(final HttpServletRequest req, final HttpServletResponse resp, final ModelMap model) {
        forward(req, resp, model, "delete");
    }

    private void forward(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final ModelMap model,
            final String method) {
        final HttpMethod httpMethod = getHttpMethod(req, method);
        final int status = sendRequest(httpMethod, req);
        System.out.println("forward status: " + status);
        // Send the request to the client
        resp.setStatus(status);

        // Read the response headers
        setHeaders(httpMethod, resp);

        // Set the entity
        try {
            setEntity(resp, httpMethod);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void setHeaders(final HttpMethod httpMethod, final HttpServletResponse resp) {
        for (final Header header : httpMethod.getResponseHeaders()) {
            resp.addHeader(header.getName(), header.getValue());
        }
    }

    private void setEntity(final HttpServletResponse resp, final HttpMethod httpMethod) throws IOException {
        long size = UNKNOWN_SIZE;

        // Compute the content length
        String transferEncoding = null;
        final Header header = httpMethod.getResponseHeader(HttpConstants.HEADER_TRANSFER_ENCODING);
        if (header != null) {
            transferEncoding = header.getValue();
        }
        if ((transferEncoding != null) && !"identity".equalsIgnoreCase(transferEncoding)) {
            size = UNKNOWN_SIZE;
        } else {
            size = getContentLength(httpMethod);
        }

        if (!httpMethod.getName().equals("HEAD") && !isInformational(httpMethod.getStatusCode())
                && httpMethod.getStatusCode() != HttpServletResponse.SC_NOT_MODIFIED
                && httpMethod.getStatusCode() != HttpServletResponse.SC_NO_CONTENT
                && httpMethod.getStatusCode() != HttpServletResponse.SC_RESET_CONTENT) {
            // Make sure that an InputRepresentation will not be instantiated
            // while the stream is closed.
            final InputStream stream = getUnClosedResponseEntityStream(getResponseEntityStream(httpMethod, size));
            if (stream != null) {
                final byte[] bytes = new byte[1024];
                while (stream.read(bytes) != UNKNOWN_SIZE) {
                    resp.getOutputStream().write(bytes);
                }
                resp.getOutputStream().flush();
            }
        }
        resp.setContentLength((int) size);

        if (size == UNKNOWN_SIZE) {
            logger.info("The length of the message body is unknown. The entity must be handled carefully and consumed entirely in order to surely release the connection.");
        }
    }

    private InputStream getResponseEntityStream(final HttpMethod httpMethod, final long size) {
        InputStream result = null;
        try {
            // Return a wrapper filter that will release the connection when
            // needed
            final InputStream responseBodyAsStream = httpMethod.getResponseBodyAsStream();
            if (responseBodyAsStream == null) {
                return null;
            }
            result = new FilterInputStream(responseBodyAsStream) {

                @Override
                public void close() throws IOException {
                    super.close();
                    httpMethod.releaseConnection();
                }
            };
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    private InputStream getUnClosedResponseEntityStream(final InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        InputStream result = null;
        try {
            if (inputStream.available() > 0) {
                result = inputStream;
            } else {
                final PushbackInputStream is = new PushbackInputStream(inputStream);
                final int i = is.read();
                if (i >= 0) {
                    is.unread(i);
                    result = is;
                }
            }
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    private boolean isInformational(final int code) {
        return (code >= 100) && (code <= 199);
    }

    private long getContentLength(final HttpMethod httpMethod) {
        long contentLength = UNKNOWN_SIZE;

        // Extract the content length header
        for (final Header header : httpMethod.getResponseHeaders()) {
            if (!header.getName().equalsIgnoreCase(HttpConstants.HEADER_CONTENT_LENGTH)) {
                continue;
            }
            try {
                contentLength = Long.parseLong(header.getValue());
            } catch (final NumberFormatException e) {
                contentLength = UNKNOWN_SIZE;
            }
        }

        return contentLength;
    }

    private HttpMethod getHttpMethod(final HttpServletRequest req, final String method) {
        HttpMethod httpMethod = null;
        if (!req.getProtocol().startsWith("HTTP")) {
            throw new IllegalArgumentException("Only HTTP resource URIs are allowed here");
        }
        final String forwardUrl = getForwardUrl(req);
        if ("get".equals(method)) {
            httpMethod = new GetMethod(forwardUrl);
        } else if ("post".equals(method)) {
            httpMethod = new PostMethod(forwardUrl);
            try {
                if (req.getInputStream() != null) {
                    ((PostMethod) httpMethod).setRequestEntity(new InputStreamRequestEntity(req.getInputStream()));
                }
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else if ("put".equals(method)) {
            httpMethod = new PutMethod(forwardUrl);
            try {
                if (req.getInputStream() != null) {
                    ((PutMethod) httpMethod).setRequestEntity(new InputStreamRequestEntity(req.getInputStream()));
                }
            } catch (final IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else if ("delete".equals(method)) {
            httpMethod = new DeleteMethod(forwardUrl);
        } else {
            throw new UnsupportedOperationException();
        }
        httpMethod.setFollowRedirects(false);
        httpMethod.setDoAuthentication(false);
        return httpMethod;
    }

    private String getForwardUrl(final HttpServletRequest req) {
        // TODO Auto-generated method stub
        if ("1".equals(req.getParameter("aaa"))) {
            return "http://172.20.23.171:8888/immanager/groups/userspace";
        } else if ("2".equals(req.getParameter("aaa"))) {
            return "http://172.20.23.171:8888/groupmanager/logs/purge";
        } else if ("3".equals(req.getParameter("aaa"))) {
            return "http://172.20.23.171:8888/groupmanager/logs/purge";
        } else if ("4".equals(req.getParameter("aaa"))) {
            return "http://172.20.23.171:8888/immanager/domains/123456.com";
        }
        return null;
    }

    private int sendRequest(final HttpMethod httpMethod, final HttpServletRequest request) {
        try {
            // Set the request headers
            @SuppressWarnings("rawtypes") final Enumeration e = request.getHeaderNames();
            while (e.hasMoreElements()) {
                final Object s = e.nextElement();
                if (!(s instanceof String)) {
                    continue;
                }
                httpMethod.addRequestHeader((String) s, request.getHeader((String) s));
            }

            System.out.println("forward to: " + httpMethod.getURI());
            // Ensure that the connection is active
            httpClient.executeMethod(httpMethod);

            // If there is no response body, immediately release the connection
            if (httpMethod.getResponseBodyAsStream() == null) {
                httpMethod.releaseConnection();
            }
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
        return httpMethod.getStatusCode();
    }
}
