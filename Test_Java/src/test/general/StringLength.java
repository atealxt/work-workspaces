package test.general;

import java.io.UnsupportedEncodingException;

public class StringLength {

    public static void main(final String args[]) {

        System.out.println("abc".length());
        System.out.println("a啊c".length());
        try {
            System.out.println("a啊c".getBytes("gbk").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
