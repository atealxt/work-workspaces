/*
 * 对象串行化,用途1：持久化；用途2：网络间/JVM间传输(分布式系统)。
 */
package test.general;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import test.MyBean;



public class ObjectSerialization {

    public static void main(final String args[]) {

        // Object serialization
        try {
            MyBean object1 = new MyBean();
            object1.setCcc(123);
            System.out.println("object1:" + object1);

            // FileOutputStream fos = new FileOutputStream("serial");
            FileOutputStream fos = new FileOutputStream("D:/Data/a.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // 持久化操作：
            oos.writeObject(object1);

            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception during serialization:" + e);
            System.exit(0);
        }

        // Object deserialization
        try {
            MyBean object2;

            // FileInputStream fis = new FileInputStream("serial");
            FileInputStream fis = new FileInputStream("D:/Data/a.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            object2 = (MyBean) ois.readObject();
            ois.close();

            System.out.println("object2:" + object2);

        } catch (Exception e) {
            System.out.println("Exception during deserialization:" + e);
            System.exit(0);
        }
    }
}
