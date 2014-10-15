package chart.bean;

public class ChartInfo {

    private String id;
    private String name;
    private String xLine;
    private String yLine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXLine() {
        return xLine;
    }

    public void setXLine(String line) {
        xLine = line;
    }

    public String getYLine() {
        return yLine;
    }

    public void setYLine(String line) {
        yLine = line;
    }
}
