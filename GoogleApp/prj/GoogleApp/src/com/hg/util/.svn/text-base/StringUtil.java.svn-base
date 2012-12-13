package com.hg.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.anakia.Escape;

public class StringUtil {

    private static Log logger = LogFactory.getLog(StringUtil.class);

    private StringUtil() {}

    public static int getByteSize(final String psStr) {
        if (psStr == null) {
            return -1;
        }
        int size = -1;
        try {
            size = psStr.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return size;
    }

    public static boolean isEmpty(final String str) {
        return isEmpty(str, false);
    }

    public static boolean isEmpty(final String str, final boolean trim) {
        if (str == null) {
            return true;
        } else if ("".equals(str)) {
            return true;
        } else if (trim && "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String delHtmlTag(final String src) {
        if (isEmpty(src)) {
            return src;
        }
        return src.replaceAll("<[^>]*>", "").replaceAll("&[a-zA-Z0-9]{1,4};", "");
    }

    public static String escape(final String s) {
        String escaped = Escape.getText(s);
        return escaped.replace("\n", "<br>");
    }

    public static String getContactUrl(final String contact) {
        if (StringUtil.isEmpty(contact)) {
            return contact;
        }
        if (contact.contains("@")) {
            return "mailto:".concat(contact);
        }
        if (!contact.startsWith("http://")) {
            return "http://".concat(contact);
        }
        return contact;
    }

    private static Pattern charOrNumPattern = Pattern.compile("^[0-9a-zA-Z-_]+$");

    public static boolean isCharOrNum(final String src) {
        return charOrNumPattern.matcher(src).matches();
    }

    private static Pattern numPattern = Pattern.compile("^-{0,1}[0-9]+$");

    public static boolean isNum(final String src) {
        return numPattern.matcher(src).matches();
    }

    private static Pattern ipPattern = Pattern.compile("([\\d]{1,3}\\.){3}[\\d]{1,3}");

    public static boolean isIp(final String src) {
        return ipPattern.matcher(src).matches();
    }
}
