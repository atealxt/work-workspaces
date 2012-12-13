package test_lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import static test_lab.LabUtil.TOPOLOGY;

/**
 * Distance Vector Algorithm.
 */
public class Distancevector {

    private static Set<String> points = new TreeSet<String>();

    /*
     * key:point name
     * value:table
     */
    private static Map<String, List<Path>> points_table = new TreeMap<String, List<Path>>();
    private static Map<String, List<Path>> points_table_copy = new TreeMap<String, List<Path>>();

    private Distancevector() {
    }

    private static void init() {

        //init table
        List<String> TOPOLOGY_copy = new ArrayList<String>();
        for (String str : TOPOLOGY) {
            TOPOLOGY_copy.add(str);
        }
        for (int i = 0; i < TOPOLOGY.size(); i++) {

            if (TOPOLOGY.get(i).startsWith("#")) {
                continue;
            }
            String sTem = TOPOLOGY.get(i);
            String[] sArr = sTem.split(" ");

            if (LabUtil.lastRouterName != null) {
                continue;
            }
            TOPOLOGY_copy.add(sArr[1] + " " + sArr[0] + " " + sArr[2]);

            //get the last router
            boolean bHave = false;
            for (int j = 0; j < TOPOLOGY.size(); j++) {

                if (i == j || TOPOLOGY.get(j).startsWith("#")) {
                    continue;
                }
                String sTem_j = TOPOLOGY.get(j);
                String[] sTem_Arr = sTem_j.split(" ");
                if (sTem_Arr[0].equals(sArr[1])) {
                    bHave = true;
                    break;
                }
            }
            if (!bHave) {
                LabUtil.lastRouterName = sArr[1];
            }
        }
        TOPOLOGY = TOPOLOGY_copy;

        //init points
        for (String str : TOPOLOGY) {
            if (!str.startsWith("#")) {
                int space = str.indexOf(" ");
                points.add(str.substring(0, space));
            }
        }

        //init points_table
        for (String point : points) {
            List<Path> list = new ArrayList<Path>();
            for (String str : TOPOLOGY) {
                if (str.startsWith(point)) {
                    int space = str.indexOf(" ");
                    int space_last = str.lastIndexOf(" ");
                    String path = str.substring(space + 1, space_last);
                    int len = Integer.valueOf(str.substring(space_last + 1));
                    list.add(new Path(path, len));
                }
            }
            points_table.put(point, list);
        }

        points_table_copy = getCopymap(points_table);
    }

    private static List<Path> getCopylist(List<Path> paths) {

        List<Path> copy = new ArrayList<Path>();
        for (Path p : paths) {
            copy.add(new Path(p.getPath(), p.getLength()));
        }
        return copy;
    }

    private static Map<String, List<Path>> getCopymap(Map<String, List<Path>> map) {

        Map<String, List<Path>> copy = new TreeMap<String, List<Path>>();
        for (String s : map.keySet()) {
            copy.put(s, getCopylist(map.get(s)));
        }
        return copy;
    }

    private static void addTransport(String from, String to, int step) {

        List<Path> paths = points_table.get(from);
        List<Path> paths_add = new ArrayList<Path>();

        for (Path path : paths) {
            if (path.getPath().equals(to)) {
                return;
            }
        }
        //System.out.println("test transport " + from + " to " + to);
        for (Path p : paths) {
            String sTem = p.getPath();
            String[] nextArr = sTem.split(" ");
            if (nextArr.length == 1) {
                List<Path> paths_next = points_table.get(nextArr[nextArr.length - 1]);
                if (paths_next == null) {
                    continue;
                }
                for (Path p_next : paths_next) {
                    String path = p_next.getPath();
                    String[] pArr = path.split(" ");
                    if (pArr.length == step && pArr[pArr.length - 1].equals(to) && !path.contains(from)) {
                        path = nextArr[nextArr.length - 1] + " " + path;
                        int length = p.getLength() + p_next.getLength();
                        paths_add.add(new Path(path, length));
                    }
                }
            }
        }
        if (paths_add.size() != 0) {
            List<Path> paths_update = points_table_copy.get(from);
            for (Path p : paths_add) {
                paths_update.add(p);
            }
        }
        return;
    }

    /**
     * Calculate router table.
     */
    public static void analysis() {
        init();

        int size = points.size();
        for (int step = 1; step < size; step++) {
            for (String point : points) {
                for (String sp : points_table.keySet()) {
                    if (!point.equals(sp)) {
                        continue;
                    }
                    for (String p : points) {
                        if (p.equals(point)) {
                            continue;
                        }
                        addTransport(point, p, step);
                    }
                }
            }
            points_table = getCopymap(points_table_copy);
        }
        return;
    }

    /**
     * Get router table.
     */
    public static Map<String, List<Path>> getPointstable() {
        return points_table;
    }
}
