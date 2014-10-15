package test.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionIterator {

    public static void main(final String[] args) {
        safetyDeleteInLoop();
    }

    private static void safetyDeleteInLoop() {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        System.out.println("Map-size before " + map.size());
        for (final Iterator<Map.Entry<Integer, Integer>> i = map.entrySet().iterator(); i.hasNext();) {
            final Map.Entry<Integer, Integer> entry = i.next();
            final Integer key = entry.getKey();
            final Integer value = entry.getValue();
            System.out.println("key: " + key + ", value: " + value);
            i.remove();
        }
        System.out.println("Map-size after " + map.size());

        System.out.println();

        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("List-size before " + list.size());
        for (final Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
            final Integer i = iterator.next();
            System.out.println("i: " + i);
            iterator.remove();
        }
        System.out.println("List-size after " + list.size());
    }

}
