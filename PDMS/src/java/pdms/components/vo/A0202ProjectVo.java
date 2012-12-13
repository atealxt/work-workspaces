package pdms.components.vo;

import pdms.components.vo.template.TabVo;

/**
 * 
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0202ProjectVo extends TabVo {

    /** logo img src */
    private String logo;
    /** 项目名称 */
    private String name;
    /** 项目简要 */
    private String summary;
    /** 开发日期 */
    private String developDate;
    /** 项目人数 */
    private int menberCnt;

    public int getMenberCnt() {
        return menberCnt;
    }

    public void setMenberCnt(int menberCnt) {
        this.menberCnt = menberCnt;
    }

    public String getDevelopDate() {
        return developDate;
    }

    public void setDevelopDate(String developDate) {
        this.developDate = developDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
