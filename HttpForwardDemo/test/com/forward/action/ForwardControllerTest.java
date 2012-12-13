package com.forward.action;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Before;
import org.junit.Test;

public class ForwardControllerTest {

    private static String URL_GET = "http://localhost:8080/HttpForwardDemo/rest/forward?aaa=1";
    private static String URL_POST = "http://localhost:8080/HttpForwardDemo/rest/forward?aaa=2";
    private static String URL_PUT = "http://localhost:8080/HttpForwardDemo/rest/forward?aaa=3";
    private static String URL_DELETE = "http://localhost:8080/HttpForwardDemo/rest/forward?aaa=4";

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testGet() throws Exception {
        final String url = URL_GET;

        final HttpClient httpClient = new HttpClient();
        final GetMethod method = new GetMethod(url);
        int statusCode = -1;
        String jsonResponse = null;
        try {
            System.out.println("sending");
            statusCode = httpClient.executeMethod(method);
            System.out.println("send complete");
            // 读取内容
            final byte[] responseBody = method.getResponseBody();
            // 处理内容
            jsonResponse = new String(responseBody);
            method.releaseConnection();
        } catch (final Exception e) {
            e.printStackTrace();
            method.releaseConnection();
        }
        System.out.println("statusCode: " + statusCode);
        System.out.println("jsonResponse: " + jsonResponse);
        System.out.println();
    }

    @Test
    public void testPost() throws Exception {
        final String url = URL_POST;

        final HttpClient httpClient = new HttpClient();
        final PostMethod method = new PostMethod(url);

        final String body = "{\"domainName\":\"zhentest0531.com\"}";
        method.setRequestEntity(new StringRequestEntity(body, "application/json", "utf-8"));

        int statusCode = -1;
        String jsonResponse = null;
        try {
            System.out.println("sending");
            statusCode = httpClient.executeMethod(method);
            System.out.println("send complete");
            // 读取内容
            final byte[] responseBody = method.getResponseBody();
            // 处理内容
            jsonResponse = new String(responseBody);
            method.releaseConnection();
        } catch (final Exception e) {
            e.printStackTrace();
            method.releaseConnection();
        }
        System.out.println("statusCode: " + statusCode);
        System.out.println("jsonResponse: " + jsonResponse);
        System.out.println();
    }

    @Test
    public void testPut() throws Exception {
        final String url = URL_PUT;

        final HttpClient httpClient = new HttpClient();
        final PutMethod method = new PutMethod(url);

        int statusCode = -1;
        String jsonResponse = null;
        try {
            System.out.println("sending");
            statusCode = httpClient.executeMethod(method);
            System.out.println("send complete");
            // 读取内容
            final byte[] responseBody = method.getResponseBody();
            // 处理内容
            jsonResponse = new String(responseBody, "gbk");
            method.releaseConnection();
        } catch (final Exception e) {
            e.printStackTrace();
            method.releaseConnection();
        }
        System.out.println("statusCode: " + statusCode);
        System.out.println("jsonResponse: " + jsonResponse);
        System.out.println();
    }

    @Test
    public void testDelete() throws Exception {
        final String url = URL_DELETE;

        final HttpClient httpClient = new HttpClient();
        final DeleteMethod method = new DeleteMethod(url);

        int statusCode = -1;
        String jsonResponse = null;
        try {
            System.out.println("sending");
            statusCode = httpClient.executeMethod(method);
            System.out.println("send complete");
            // 读取内容
            final byte[] responseBody = method.getResponseBody();
            // 处理内容
            jsonResponse = new String(responseBody, "gbk");
            method.releaseConnection();
        } catch (final Exception e) {
            e.printStackTrace();
            method.releaseConnection();
        }
        System.out.println("statusCode: " + statusCode);
        System.out.println("jsonResponse: " + jsonResponse);
        System.out.println();
    }

}
