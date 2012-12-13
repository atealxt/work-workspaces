/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

/**
 *
 * @author Administrator
 */
public class Test_DotGramma {

    public static void main(String args[]) {
        test("aaa", "bbb");
    }

    public static void test(String... strArr) {

        for (String str : strArr) {
            System.out.println(str);
        }

    }
}


