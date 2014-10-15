package test.general;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringSplit {

    public static void main(final String args[]) {

        test1();
        test2();
    }

    private static void test1() {

        System.out.println("".split("=").length);
        System.out.println("=".split("=").length);
        System.out.println("= ".split("=").length);
        System.out.println("1".split("=").length);
    }

    private static void test2() {

        String s = "a,b,c";

        for (int i = 0; i < s.split(",").length; i++) {
            System.out.println(s.split(",")[i]);// 创建正则进行split。每次都创建新正则，当字符串很长时效率高，但重复使用的话效率低。缓存Pattern.compile比较好
        }

        System.out.println();

        for (int i = 0; i < StringUtils.split(s, ",").length; i++) {
            System.out.println(StringUtils.split(s, ",")[i]);// 创建数组进行split
        }

        String ss = "aa$$bb";
        System.out.println(Arrays.toString(patternUsername.split(ss)));
    }

    private static Pattern patternUsername = Pattern.compile("\\$\\$");
}
