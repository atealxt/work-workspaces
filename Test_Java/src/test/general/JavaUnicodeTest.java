package test.general;

public class JavaUnicodeTest {

    public static void main(final String args[]) {
        System.out.println((int) '中');// 输出20013，为'中'的Unicode码
        System.out.println((char) 20013);// 输出为'中'
        System.out.println(Integer.toHexString('中'));// 输出为4e2d
        System.out.println('\u4e2d');// 输出为'中'
    }
}
