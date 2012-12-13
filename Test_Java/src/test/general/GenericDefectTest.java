package test.general;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GenericDefectTest {

    public void test(final Map<String, String> map) {
        System.out.println(map.get("a"));// 这行可以正常执行
        System.out.println(map.get("b"));// 运行到这行才会报错
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void main(final String args[]) throws Exception {
        GenericDefectTest genericTest = new GenericDefectTest();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "a");
        map.put("b", 1);
        // new GenericTest().test(map);// 这里有编译验证，没有问题

        // 但是如果用反射调用的话，就没有验证了，在实际使用这个值的时候才会报错
        Method test = GenericDefectTest.class.getDeclaredMethod("test", Map.class);
        test.invoke(genericTest, map);
        // 这是个泛型的缺陷
    }
}

/*
package test.general;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GenericDefectTest
{
  public void test(Map<String, String> map)
  {
    System.out.println((String)map.get("a"));
    System.out.println((String)map.get("b"));
    for (Map.Entry entry : map.entrySet())
      System.out.println((String)entry.getValue());
  }

  public static void main(String[] args) throws Exception
  {
    GenericDefectTest genericTest = new GenericDefectTest();

    Map map = new HashMap();
    map.put("a", "a");
    map.put("b", Integer.valueOf(1));

    Method test = GenericDefectTest.class.getDeclaredMethod("test", new Class[] { Map.class });
    test.invoke(genericTest, new Object[] { map });
  }
}
*/