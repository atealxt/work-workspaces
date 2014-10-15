package chart;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import chart.bean.ChartInfo;
import chart.bean.FileInfo;
import chart.bean.Line;
import chart.bean.LineData;

public class PingJFC implements JFC {

    private ChartInfo chartInfo;
    private List<Line> datalist;

    public PingJFC(ChartInfo chartInfo, List<Line> datalist) {
        super();
        this.chartInfo = chartInfo;
        this.datalist = datalist;
    }

    public void makeImage() {
        JFreeChart chart = createChart(createDataset());
        ChartUtil.saveChartAsPNG(new FileInfo(("pingjfc.png")), chart);
    }

    private XYDataset createDataset() {

        XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();

        for (Line line : datalist) {
            XYSeries series = new XYSeries(line.getLineName());
            for (LineData data : line.getDatalist()) {
                series.add(data.getX(), data.getY());
            }
            localXYSeriesCollection.addSeries(series);
        }

        return localXYSeriesCollection;
    }

    private JFreeChart createChart(XYDataset paramXYDataset) {
        JFreeChart localJFreeChart = ChartFactory.createXYLineChart(chartInfo.getName(), chartInfo.getXLine(),
                                                                    chartInfo.getYLine(), paramXYDataset,
                                                                    PlotOrientation.VERTICAL, true, true, false);
        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
        localXYPlot.setDomainPannable(true);
        localXYPlot.setRangePannable(true);
        XYLineAndShapeRenderer localXYLineAndShapeRenderer = (XYLineAndShapeRenderer) localXYPlot.getRenderer();
        localXYLineAndShapeRenderer.setBaseShapesVisible(true);
        localXYLineAndShapeRenderer.setBaseShapesFilled(true);
        NumberAxis localNumberAxis = (NumberAxis) localXYPlot.getRangeAxis();
        localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return localJFreeChart;
    }
}
