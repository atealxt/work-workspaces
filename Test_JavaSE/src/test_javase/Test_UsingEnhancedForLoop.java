/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

//import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class Test_UsingEnhancedForLoop {

    public static void main(String[] args) {

        Vector<Object> v = new Vector<Object>();
        v.add(new String("Hello World"));
        v.add(new Integer(10));
        v.add(new Double(11.0));
        v.add(new Long(12));

//        for (Iterator i = v.iterator(); i.hasNext();) {
//            System.out.println(" Vector element is: " + i.next());
//        }
        for (Object o : v) {
            System.out.println(" Vector element is: " + o);
        }

    }
}
