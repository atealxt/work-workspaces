/*
一个数组，“支配者”是在数组中出现频率超过一半的整数，
例如[3,4,3,2,-1,3,3,3]数值“3”出现过5次，5除以8大于0.5
所以数值“3”是一个支配者；
而在这个数组中的支配者出现在数组下标[0,2,5,6,7]
写一个函数，在给定的整数数组中找出支配者所在的任意一个数组下标，如果一个数组中没有支配者返回-1；
 */
package test_intelligence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Test_Ruler {

    private final static String STATUS = "status";
    private final static String NAME = "name";
    private final static String COUNT = "count";
    private final static String STATION = "station";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //int[] iArr = {3, 4, 3, 2, -1, 3, 3, 3};
        int[] iArr = {5,4,3,2,4,3,4,4,4,4,4};
        Map<String, Object> map = getRuler(iArr);
        if (map.get(STATUS) instanceof Integer) {
            System.out.println("no ruler.");
        } else {
            System.out.println("NAME: " + map.get(NAME));
            System.out.println("COUNT: " + map.get(COUNT));
            System.out.println("STATION: " + map.get(STATION));
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getRuler(final int[] src) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put(STATUS, -1);

        Map<Integer, Map<String, Object>> map = new HashMap<Integer, Map<String, Object>>();
        for (int i = 0; i < src.length; i++) {
            if (map.containsKey(src[i])) {
                Map<String, Object> mapTemp = map.get(src[i]);

                //set count
                Integer count = (Integer) mapTemp.get(COUNT);
                mapTemp.put(COUNT, ++count);

                //set station
                List<Integer> station = (ArrayList<Integer>) mapTemp.get(STATION);
                station.add(i);

                if (count * 2 > src.length) {
                    ret.put(STATUS, "exist");
                    ret.put(NAME, src[i]);
                    ret.put(COUNT, count);
                    ret.put(STATION, station);
                    break;
                }
            } else {
                //set count
                Map<String, Object> mapTemp = new HashMap<String, Object>();
                mapTemp.put(COUNT, 1);

                //set station
                List<Integer> station = new ArrayList<Integer>();
                station.add(i);
                mapTemp.put(STATION, station);

                map.put(src[i], mapTemp);
            }
        }

        return ret;
    }
}
