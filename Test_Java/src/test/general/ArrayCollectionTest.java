package test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayCollectionTest {

    @Test
    public void Collection2Array() {

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        String[] arr = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(arr));
    }

}
