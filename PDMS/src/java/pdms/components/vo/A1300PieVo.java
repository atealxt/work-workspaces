package pdms.components.vo;

/**
 * amCharts pie chart data vo
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1300PieVo {

    private String title;
    private String value;
    private String color;
    private String description;
    private boolean pull_out = false;
    private String pattern;
    private String pattern_color;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern_color() {
        return pattern_color;
    }

    public void setPattern_color(String pattern_color) {
        this.pattern_color = pattern_color;
    }

    public boolean isPull_out() {
        return pull_out;
    }

    public void setPull_out(boolean pull_out) {
        this.pull_out = pull_out;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
