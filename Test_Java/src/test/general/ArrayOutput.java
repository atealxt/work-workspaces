package test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.lang.ArrayUtils;

/** for the Ajaxful javascript value pass */
public class ArrayOutput {

    public static void main(final String args[]) {

        soutArray();
        System.out.println();
        soutList();
        System.out.println();
        soutMap();
    }

    private static void soutArray() {

        String[] s = { "Aberdeen", "Ada", "Adamsville", "Addyston", "Adelphi", "Adena", "Adrian", "Akron" };

        System.out.println(Arrays.toString(s));
        System.out.println(ArrayUtils.toString(s));
        System.out.println(JSONArray.fromObject(s).toString());
    }

    private static void soutList() {

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        System.out.println(list.toString());
        System.out.println(JSONArray.fromObject(list).toString());
    }

    private static void soutMap() {

        Map<String, String> map = new TreeMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");

        System.out.println(map);
        System.out.println(JSONArray.fromObject(map).toString());
    }

}
