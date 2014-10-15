package chart.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import chart.PingJFC;
import chart.bean.ChartInfo;
import chart.bean.Line;
import chart.bean.LineData;

public class PingJFCTest {

    @Test
    public void testMakeImage() {
        PingJFC jfc = new PingJFC(getChartInfo(), getData());
        jfc.makeImage();
    }

    private ChartInfo getChartInfo() {
        ChartInfo info = new ChartInfo();

        info.setName("Line Chart Demo");
        info.setXLine("X");
        info.setYLine("Y");

        return info;
    }

    private List<Line> getData() {
        List<Line> list = new ArrayList<Line>();

        Line line = new Line("First");
        List<LineData> datalist = new ArrayList<LineData>();
        datalist.add(new LineData(1D, 1D));
        datalist.add(new LineData(2.0D, 4.0D));
        datalist.add(new LineData(3.0D, 3.0D));
        datalist.add(new LineData(4.0D, 5.0D));
        datalist.add(new LineData(5.0D, 5.0D));
        datalist.add(new LineData(6.0D, 7.0D));
        datalist.add(new LineData(7.0D, 7.0D));
        datalist.add(new LineData(8.0D, 8.0D));
        line.setDatalist(datalist);
        list.add(line);

        line = new Line("Second");
        datalist = new ArrayList<LineData>();
        datalist.add(new LineData(1D, 5.0D));
        datalist.add(new LineData(1.2D, 7.0D));
        datalist.add(new LineData(3.0D, 6.0D));
        datalist.add(new LineData(4.0D, 8.0D));
        datalist.add(new LineData(5.0D, 4.0D));
        datalist.add(new LineData(6.0D, 4.0D));
        datalist.add(new LineData(7.0D, 2.0D));
        datalist.add(new LineData(8.0D, 1D));
        line.setDatalist(datalist);
        list.add(line);

        return list;
    }

}
