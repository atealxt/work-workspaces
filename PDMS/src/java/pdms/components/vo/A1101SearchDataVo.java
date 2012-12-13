package pdms.components.vo;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1101SearchDataVo {

    private String COl1;
    private String COl2;
    private String COl3;
    private String COl4;
    private String COl5;
    private String LInk;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" COl1: ").append(getCOl1());
        sb.append(" COl2: ").append(getCOl2());
        sb.append(" COl3: ").append(getCOl3());
        sb.append(" COl4: ").append(getCOl4());
        sb.append(" COl5: ").append(getCOl5());
        sb.append(" LInk: ").append(getLInk());
        return sb.toString();
    }

    public String getLInk() {
        return LInk;
    }

    public void setLInk(String LInk) {
        this.LInk = LInk;
    }

    public String getCOl1() {
        return COl1;
    }

    public void setCOl1(String COl1) {
        this.COl1 = COl1;
    }

    public String getCOl2() {
        return COl2;
    }

    public void setCOl2(String COl2) {
        this.COl2 = COl2;
    }

    public String getCOl3() {
        return COl3;
    }

    public void setCOl3(String COl3) {
        this.COl3 = COl3;
    }

    public String getCOl4() {
        return COl4;
    }

    public void setCOl4(String COl4) {
        this.COl4 = COl4;
    }

    public String getCOl5() {
        return COl5;
    }

    public void setCOl5(String COl5) {
        this.COl5 = COl5;
    }
}
