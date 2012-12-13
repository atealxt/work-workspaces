package chart;

import java.io.File;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import chart.bean.FileInfo;

public class ChartUtil {

    public static void saveChartAsPNG(FileInfo file, JFreeChart chart) {
        try {
            File myFilePath = new File(ChartUtil.class.getResource("/").getPath().concat("\\")
                    .concat(file.getFileName()));
            ChartUtilities.saveChartAsPNG(myFilePath, chart, file.getWidth(), file.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
