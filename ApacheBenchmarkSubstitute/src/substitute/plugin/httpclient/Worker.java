package substitute.plugin.httpclient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;

import substitute.model.HttpMethod;
import substitute.model.Parameter;

public class Worker extends Observable implements Runnable {

    private Parameter p;
    private int count;
    private int groupNo;
    private List<String> threadName;
    private List<String> startTime;
    private List<Integer> during;
    private List<Integer> status;
    private List<Long> bytes;

    private HttpClient httpclient;
    private HttpRequestBase request;

    Worker(final Parameter p, final int groupNo) {
        this.p = p;
        this.count = p.getRequests();
        this.groupNo = groupNo;

        threadName = new ArrayList<String>(count);
        startTime = new ArrayList<String>(count);
        during = new ArrayList<Integer>(count);
        status = new ArrayList<Integer>(count);
        bytes = new ArrayList<Long>(count);
    }

    @Override
    public void run() {

        for (int i = 1; i <= count; i++) {

            threadName.add("线程组 " + groupNo + "-" + i);
            HttpClient httpclient = getHttpClient();

            try {
                HttpRequestBase request = getRequest();

                Date now = new Date();
                startTime.add(initStartTime(now));
                long start = now.getTime();

                HttpResponse resp = httpclient.execute(request);

                during.add((int) (System.currentTimeMillis() - start));
                status.add(resp.getStatusLine().getStatusCode());
                bytes.add(resp.getEntity().getContentLength());

                HttpEntity entity = resp.getEntity();
                entity.getContent().close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!p.isKeepAlive()) {
                    httpclient.getConnectionManager().shutdown();
                }
            }

            setChanged();
            notifyObservers();
        }

        if (p.isKeepAlive()) {
            httpclient.getConnectionManager().shutdown();
        }
    }

    private HttpClient getHttpClient() {
        if (httpclient == null) {
            httpclient = createHttpclient();
        }
        if (!p.isKeepAlive()) {
            httpclient = createHttpclient();
        }
        return httpclient;
    }

    private HttpClient createHttpclient() {
        HttpParams params = new SyncBasicHttpParams();
        DefaultHttpClient.setDefaultHttpParams(params);
        HttpConnectionParams.setSocketBufferSize(params, p.getBufferSize());
        DefaultHttpClient httpclient = new DefaultHttpClient(params);

        for (Entry<String, String> o : p.getCookies().entrySet()) {
            Cookie cookie = new BasicClientCookie(o.getKey(), o.getValue());
            // TODO 暂不支持cookie的特殊设置
            httpclient.getCookieStore().addCookie(cookie);
        }

        return httpclient;
    }

    private HttpRequestBase getRequest() {
        if (request == null) {
            request = createRequest();
        }
        if (!p.isKeepAlive()) {
            request = createRequest();
        }
        return request;
    }

    private HttpRequestBase createRequest() {
        HttpRequestBase request = null;
        switch (p.getHttpMethod()) {
            case POST:
                request = new HttpPost(p.getUrl());
                break;
            case HEAD:
                request = new HttpHead(p.getUrl());
                break;
            case GET:
            default:
                request = new HttpGet(p.getUrl());
                break;
        }
        for (Entry<String, String> o : p.getHeaders().entrySet()) {
            request.setHeader(o.getKey(), o.getValue());
        }
        if (p.isKeepAlive()) {
            request.setHeader("Connection", "keep-alive");
        }
        String postfile = p.getPostFile();
        if (postfile != null && HttpMethod.POST == p.getHttpMethod()) {
            File file = new File(postfile.substring(postfile.indexOf("=") + 1));
            MultipartEntity mpEntity = new MultipartEntity();
            ContentBody content = new FileBody(file);
            mpEntity.addPart(postfile.substring(0, postfile.indexOf("=")), content);
            ((HttpPost) request).setEntity(mpEntity);
        }
        return request;
    }

    private String initStartTime(final Date now) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.S");
        return format.format(now);
    }

    public List<String> getThreadName() {
        return threadName;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public List<Integer> getDuring() {
        return during;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public List<Long> getBytes() {
        return bytes;
    }
}
