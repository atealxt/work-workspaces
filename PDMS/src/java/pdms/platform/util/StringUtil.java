package pdms.platform.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class StringUtil {

    private static Log logger = LogFactory.getLog(StringUtil.class);

    private StringUtil() {
    }

    public static boolean isEmpty(String src) {
        return src == null || src.equals("");
    }

    /** 取得MD5加密字符串 */
    public static String getMD5Code(String src) throws PdmsException {

        if (src == null || src.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte[] hash = md.digest();

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw new PdmsException();
        }

        return hexString.toString();
    }

    /**
     * 取得指定长度的字符串<br>
     * 实际结果可能有出入，只在使用系统支持字符的情况下保证返回结果的正确性
     * 
     * @param src 字符串
     * @param len 长度
     */
    public static String getFirstStr(String src, int len) throws PdmsException {

        if (src == null || len <= 1) {
            logger.error(src);
            logger.error(len);
            throw new PdmsException("InParam Error!");
        }

        String str = null;
        try {
            str = new String(src.getBytes("UTF-8"), "ISO8859_1");
        } catch (UnsupportedEncodingException ex) {
            throw new PdmsException(ex);
        }
        if (str.length() > len) {

            //if (len % 2 != 0) {
            //    len--;
            //}
            //str = str.substring(0, len - 1);

            //UTF-8特点
            str = str.substring(0, len - 1 + 2);
            try {
                str = new String(str.getBytes("ISO8859_1"), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                throw new PdmsException(ex);
            }
            return str;
        } else {
            return src;
        }

    }

    /** 取得年月日 */
    public static String getDateFormat(java.sql.Date date) throws PdmsException {
        if (date == null) {
            throw new PdmsException("InParam date cannnot be null!");
        }
        SimpleDateFormat sy1 = new SimpleDateFormat("yyyy/MM/dd");
        return sy1.format(new Date(date.getTime()));
    }

    /**
     * 取得日期的字符串表示形式
     * @param date java.util.Date
     * @param format "example:yyyy/MM/dd"
     */
    public static String getDateFormat(Date date, String format) throws PdmsException {
        if (date == null || format == null) {
            throw new PdmsException("InParam error!");
        }
        SimpleDateFormat sy1 = new SimpleDateFormat(format);
        return sy1.format(date);
    }

    /**
     * 删除HTMLtag, 显示原始内容
     */
    public static String delFtmlTag(String src) {
        if (isEmpty(src)) {
            return src;
        }
        return src.replaceAll("<[^>]*>", "").replaceAll("&[a-zA-Z0-9]{1,4};", "");
    }

    /**
     * 返回小数的字符串表示形式
     */
    public static String getDecimal(double d, String format) {
        if (isEmpty(format)) {
            format = "#,##0.000";
        }
        return new DecimalFormat(format).format(d);
    }

}
