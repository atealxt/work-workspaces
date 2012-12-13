package test_lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class.
 */
public class LabUtil {

    /** Routers. */
    public static final Map<String, Router> ROUTERS = new HashMap<String, Router>();
    /** The source host. */
    public static final Host HOST_SEND = new Host("X", 0);
    /** the destination host. */
    public static final Host HOST_RECEIVE = new Host("Y", 1);
    /** TOPOLOGY. */
    public static List<String> TOPOLOGY = new ArrayList<String>();
    /** Last router name. */
    public static String lastRouterName;
    /** COST */
    private final static int COST = 2;

    private LabUtil() {
    }

    /**
     * Get next passing router.
     */
    public static Router getNextrouter(String path) {

        //System.out.println(path);
        Router router = null;
        int space = path.indexOf(" ");
        if (space != -1) {
            router = ROUTERS.get(path.substring(0, space));
            router.setNextpath(path.substring(space + 1));
        } else {
            router = ROUTERS.get(path);
            router.setNextpath("");
        }
        return router;
    }

    /**
     * Get first passing router.
     */
    public static Router getFirstrouter(String path) {

        for (String str : TOPOLOGY) {
            if (str.startsWith("##")) {
                int space_last = str.lastIndexOf(" ");
                String key = str.substring(space_last + 1);
                Router router = ROUTERS.get(key);
                int space = path.indexOf(" ");
                router.setNextpath(path.substring(space + 1));
                return router;
            }
        }
        return null;
    }

    /**
     * Run D-V Algorithm to get shortest path.
     * 
     * @return  path(split with space,example: "a c d f").
     * @see test_lab.Distancevector
     */
    public static synchronized String getCurrentShortestPath() {

        Distancevector.analysis();
        Map<String, List<Path>> points_table = Distancevector.getPointstable();

        String path = "";
        for (String str : TOPOLOGY) {
            if (str.startsWith("##")) {
                int space_last = str.lastIndexOf(" ");
                String key = str.substring(space_last + 1);
                List<Path> paths = points_table.get(key);
                int min = -1;
                for (Path p : paths) {
                    String sPath = p.getPath();
                    int sPath_space_last = sPath.lastIndexOf(" ");
                    if (sPath_space_last == -1) {
                        if (sPath.equals(lastRouterName)) {
                            min = p.getLength();
                            path = key + " " + sPath;
                        }
                    } else {
                        sPath = sPath.substring(sPath_space_last + 1);
                        if (sPath.equals(lastRouterName)) {
                            if (min == -1 || min > p.getLength()) {
                                min = p.getLength();
                                path = key + " " + p.getPath();
                            }
                        }
                    }
                }
                break;
            }
        }
        addCost(path);
        return path;
    }

    private static void addCost(String path) {

        List<String> TOPOLOGY_copy = new ArrayList<String>();
        for (String str : TOPOLOGY) {
            TOPOLOGY_copy.add(str);
        }

        String[] sArr = path.split(" ");
        for (int i = 0; i < sArr.length - 1; i++) {
            for (String str : TOPOLOGY) {
                String[] sArrTemp = str.split(" ");
                if ((sArrTemp[0].equals(sArr[i]) && sArrTemp[1].equals(sArr[i + 1])) ||
                    (sArrTemp[1].equals(sArr[i]) && sArrTemp[0].equals(sArr[i + 1]))) {
                    int iCost = COST + Integer.valueOf(sArrTemp[2]);
                    TOPOLOGY_copy.remove(str);
                    TOPOLOGY_copy.add(sArrTemp[0] + " " + sArrTemp[1] + " " + iCost);
                }
            }
        }
        TOPOLOGY = TOPOLOGY_copy;
    }
}
