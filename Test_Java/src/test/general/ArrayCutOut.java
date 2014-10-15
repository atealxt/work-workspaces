package test.general;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class ArrayCutOut {

    private static final int MAX_LENGTH_RESULT_INFO = 10 - 1;

    @Test
    public void execute() {

        String s1 = "ab啊";
        String s2 = "a啊bcdefghijklmnopqrstuvwxyz";

        Assert.assertEquals(s1, abbreviate(s1, MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals("a啊bcd...", abbreviate(s2, MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals("a啊bcde...", StringUtils.abbreviate(s2, MAX_LENGTH_RESULT_INFO));

        String s4 = "你好世界你好世界你好世界你好世界";
        Assert.assertEquals("你好世...", abbreviate(s4, MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals("你好世界你好...", StringUtils.abbreviate(s4, MAX_LENGTH_RESULT_INFO));

        Assert.assertEquals("", abbreviate("", MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals("", StringUtils.abbreviate("", MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals(null, abbreviate(null, MAX_LENGTH_RESULT_INFO));
        Assert.assertEquals(null, StringUtils.abbreviate(null, MAX_LENGTH_RESULT_INFO));
    }

    public static String abbreviate(final String src, final int maxLen) {

        if (src == null) {
            return null;
        }
        try {
            byte[] b = src.getBytes("GBK");
            if (b.length > maxLen) {
                byte[] bCut = new byte[maxLen - 3];
                System.arraycopy(b, 0, bCut, 0, maxLen - 3);
                return new String(bCut, "GBK") + "...";
            }
            return src;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
