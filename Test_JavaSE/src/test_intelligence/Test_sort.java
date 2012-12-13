/*
对字符串进行排序，用任意一种编程语言来实现，不能使用现有的类，
在排序中，字符串“Bc”，“Ad”，“aC”,“Hello”，“X man”，“XP”，“During”,“day”
能够排序成 “Ad”，"aC"，“Bc”，“During”，“day”，“Hello”，“X man”，“XP”，
 */
package test_intelligence;

/**
 *
 * @author Administrator
 */
public class Test_sort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] strArr = {"Bc", "Ad", "aC", "Hello", "X man", "XP", "During", "day"};

        sort(strArr);
        for (String s : strArr) {
            System.out.print(s + ", ");
        }
    }

    private static void sort(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            for (int j = i + 1; j < strArr.length; j++) {
                if (bigger(strArr[i], strArr[j])) {
                    String s = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = s;
                }
            }
        }
    }

    private static boolean bigger(String s1, String s2) {

        int lens1 = s1.length();
        int lens2 = s2.length();
        if (lens1 == 0 || lens2 == 0) {
            return lens1 > lens2;
        }

        int len = lens1 <= lens2 ? lens1 : lens2;
        for (int i = 0; i < len; i++) {
            char x = s1.charAt(i);
            char y = s2.charAt(i);

            if (x == y) {
                return bigger(s1.substring(i + 1), s2.substring(i + 1));
            }
            if (x == ' ') {
                return false;
            }
            if (y == ' ') {
                return true;
            }

            if (isMajuscule(x) && isMinuscule(y)) {
                x += 32;
            }
            if (isMinuscule(x) && isMajuscule(y)) {
                x -= 32;
            }
            return (x > y);
        }
        return lens1 > lens2;
    }

    private static boolean isMajuscule(char c) {
        return (c >= 65 && c <= 90);
    }

    private static boolean isMinuscule(char c) {
        return (c >= 97 && c <= 122);
    }
}
