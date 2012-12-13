package test.general;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class CollectionUtilTest {

    @Test
    public void hello() {

        List<String> a = Arrays.asList(new String[] { "a", "b", "c" });
        List<String> b = Arrays.asList(new String[] { "b", "c", "d" });

        System.out.println(CollectionUtils.disjunction(a, b));// a,d
        System.out.println(CollectionUtils.retainAll(a, b));// b,c
        System.out.println(CollectionUtils.subtract(a, b));// a
        System.out.println(CollectionUtils.subtract(b, a));// d

    }

    @Test
    public void deepCopyListJava() {
        // 原理同deepCopyMapJava
    }

    @Test
    public void deepCopyListApache() {
        // TODO
    }

    @Test
    public void deepCopyMapJava() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            IOException, ClassNotFoundException {
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("a", "1");

        Map<String, String> m2 = new HashMap<String, String>(m1);
        Assert.assertNotNull(m2.get("a"));
        Assert.assertSame(m1.get("a"), m2.get("a"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(m1);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Map<String, String> m3 = (Map<String, String>) ois.readObject();
        Assert.assertNotNull(m3.get("a"));
        Assert.assertNotSame(m1.get("a"), m3.get("a"));
    }

    @Test
    public void deepCopyMapApache() {
        // TODO
    }
}