package com.hg.util;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServletUtil {

    private static Log logger = LogFactory.getLog(ServletUtil.class);

    private static String FORWARDED_FOR_HEADER_NAME = "X-FORWARDED-FOR";
    /** captcha number's Key */
    public static String CAPTCHA = "CAPTCHA";
    /** captcha number's max value */
    private static int MAX_VALUE = 10;
    /** guest name */
    public static String GUEST_NAME = "GUEST_NAME";
    /** guest contact url */
    public static String GUEST_URL = "GUEST_URL";

    private ServletUtil() {}

    public static String getReqIp(final HttpServletRequest request) {

        if (!isProxiedRequest(request)) {
            return request.getRemoteAddr();
        }

        String ipInfo = request.getHeader("x-forwarded-for");
        logger.debug("x-forwarded-for: " + ipInfo);

        int commaIndex = ipInfo.indexOf(',');
        if (commaIndex != -1) {
            ipInfo = ipInfo.substring(0, commaIndex);
        }

        if (StringUtil.isIp(ipInfo)) {
            return ipInfo;
        }
        return request.getRemoteAddr();
    }

    private static boolean isProxiedRequest(final HttpServletRequest request) {
        return !StringUtil.isEmpty(request.getHeader(FORWARDED_FOR_HEADER_NAME));
    }

    /**
     * create captcha and save to session.<br>
     * 在session中生成验证码，并返回运算式的表示形式.<br>
     *
     * @return operation
     */
    public static String makeCaptcha(final HttpSession sess) {

        Random ran = new Random(System.currentTimeMillis());
        int firstNumber = ran.nextInt(MAX_VALUE + 1);
        int secondNumber = ran.nextInt(MAX_VALUE + 1);
        String operator = null;
        int result = -1;
        if (ran.nextInt() % 2 == 0) {
            operator = "+";
            result = firstNumber + secondNumber;
        } else {
            operator = "-";
            result = firstNumber - secondNumber;
        }
        sess.setAttribute(CAPTCHA, result);

        return new StringBuilder().append(firstNumber).append(" ").append(operator)//
                .append(" ").append(secondNumber).append(" ").append("= ").toString();
    }

    public static boolean checkCaptcha(final int result, final HttpSession sess) {
        return ((Integer) sess.getAttribute(CAPTCHA)).intValue() == result;
    }

    public static String getCookie(final HttpServletRequest req, final String ckName) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ckName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookie(
            final HttpServletRequest req,
            final HttpServletResponse resp,
            final String key,
            final String value) {
        delCookie(req, key);
        Cookie c = new Cookie(key, value);
        c.setMaxAge(3600);
        resp.addCookie(c);
    }

    private static void delCookie(final HttpServletRequest req, final String ckName) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ckName)) {
                cookie.setMaxAge(0);
                return;
            }
        }
    }
}
