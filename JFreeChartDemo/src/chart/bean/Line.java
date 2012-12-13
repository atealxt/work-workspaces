package chart.bean;

import java.util.List;

// import java.util.Date;

public class Line {

    private String lineName;

    private List<LineData> datalist;

    public Line(String lineName) {
        super();
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public List<LineData> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<LineData> datalist) {
        this.datalist = datalist;
    }

    // private Date currentDate;
    //
    // public Date getCurrentDate() {
    // return currentDate;
    // }
    //
    // public void setCurrentDate(Date currentDate) {
    // this.currentDate = currentDate;
    // }
}
