package test_intelligence;

import java.util.HashMap;

/*
 * 将汉字转换成数字, 如下
 * 一          1
 * 十          10
 * 十一        11
 * 二十        20
 * 二十一      21
 * 一百        100
 * 一百零一    101
 * 一百一十    110
 * 一百一十一  111
 */
public class Test_NumberFormat {

    private final String UNIT_YI = "亿";
    private final String UNIT_WAN = "万";
    private final String UNIT_QIAN = "千";
    private final String UNIT_BAI = "百";
    private final String UNIT_SHI = "十";
    private final HashMap<String, Integer> UNIT = new HashMap<String, Integer>();
    private final HashMap<String, Integer> NUMBER = new HashMap<String, Integer>();

    public Test_NumberFormat() {
        UNIT.put(UNIT_YI, 100000000);
        UNIT.put(UNIT_WAN, 10000);
        UNIT.put(UNIT_QIAN, 1000);
        UNIT.put(UNIT_BAI, 100);
        UNIT.put(UNIT_SHI, 10);
        NUMBER.put("一", 1);
        NUMBER.put("二", 2);
        NUMBER.put("三", 3);
        NUMBER.put("四", 4);
        NUMBER.put("五", 5);
        NUMBER.put("六", 6);
        NUMBER.put("七", 7);
        NUMBER.put("八", 8);
        NUMBER.put("九", 9);
    }

    public String getArab(String strCHN) {

        if (strCHN == null || strCHN.trim().equals("")) {
            return strCHN;
        }
        String strCHNTemp = strCHN.replace("零", "");
        long result = getArab(strCHNTemp, getUnit(strCHNTemp));
        return Long.toString(result);
    }

    private long getArab(String strCHN, String sUNIT) {

        if (sUNIT == null) {
            return NUMBER.get(strCHN);
        }
        String sArr[] = strCHN.split(sUNIT);
        long num = 0;
        if (!sArr[0].equals("")) {
            num = UNIT.get(sUNIT) * getArab(sArr[0], getUnit(sArr[0]));
        } else {
            num = UNIT.get(sUNIT);
        }
        if (sArr.length == 1) {
            return num;
        }
        return num + getArab(sArr[1], getUnit(sArr[1]));
    }

    private String getUnit(String str) {

        if (str == null || str.trim().equals("")) {
            return str;
        }
        if (str.contains(UNIT_YI)) {
            return UNIT_YI;
        }
        if (str.contains(UNIT_WAN)) {
            return UNIT_WAN;
        }
        if (str.contains(UNIT_QIAN)) {
            return UNIT_QIAN;
        }
        if (str.contains(UNIT_BAI)) {
            return UNIT_BAI;
        }
        if (str.contains(UNIT_SHI)) {
            return UNIT_SHI;
        }
        return null;
    }
}