package substitute.model;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

    private String url;

    private int bufferSize = 8192;
    private HttpMethod httpMethod = HttpMethod.GET;

    /* Number of multiple requests to make */
    private int concurrency = 1;
    /* Number of requests to make */
    private int requests = 1;
    /* Add Arbitrary header line, eg. 'Accept-Encoding:gzip' Inserted after all normal header lines. (repeatable) */
    private Map<String, String> headers = new HashMap<String, String>();
    /* Add cookie, eg. 'Apache=1234' (repeatable) */
    private Map<String, String> cookies = new HashMap<String, String>();
    /* Use HTTP KeepAlive feature */
    private boolean keepAlive = false;
    /* File containing data to POST */
    private String postFile;
    private int timeLimit;

    public int getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(final int concurrency) {
        this.concurrency = concurrency;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(final int requests) {
        this.requests = requests;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(final int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(final boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPostFile() {
        return postFile;
    }

    public void setPostFile(final String postFile) {
        this.postFile = postFile;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(final Map<String, String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public String toString() {
        return "Parameter [url=" + url + ", concurrency=" + concurrency + ", requests=" + requests + "]";
    }

}
