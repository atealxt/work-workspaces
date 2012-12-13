package pdms.platform.action;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1300ChartService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1300ChartAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A1300ChartAction.class);
    private A1300ChartService chartService;
    /** 饼图数据文件地址 */
    private String pieDataFilePath;
    /** 柱状图数据文件地址 */
    private String columnDataFilePath;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        //D:/workspace/netbeans/PDMS/build/web/amcharts/ampie/data/200903/ampie_data_1211.xml
        String path = chartService.getPieDataFilePath();
        path = path.substring(path.lastIndexOf("amcharts"));
        pieDataFilePath = "../" + path;
        logger.info("pieDataFilePath: " + pieDataFilePath);

        path = chartService.getColumnDataFilePath();
        path = path.substring(path.lastIndexOf("amcharts"));
        columnDataFilePath = "../" + path;
        logger.info("columnDataFilePath: " + columnDataFilePath);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 导出图片
     */
    public String exportImg() throws IOException {

        // 页面flash的宽度和高度
        int width = Integer.parseInt(getParamValue("width"));
        int height = Integer.parseInt(getParamValue("height"));

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 页面是将一个个像素作为参数传递进来的,所以如果图表越大,处理时间越长
        for (int y = 0; y < height; y++) {
            int x = 0;
            String[] row = getParamValue("r" + y).split(",");
            for (int c = 0; c < row.length; c++) {
                String[] pixel = row[c].split(":"); // 十六进制颜色数组  
                int repeat = pixel.length > 1 ? Integer.parseInt(pixel[1]) : 1;
                for (int l = 0; l < repeat; l++) {
                    result.setRGB(x, y, Integer.parseInt(pixel[0], 16));
                    x++;
                }
            }
        }

        getResponse().setContentType("image/jpeg");
        getResponse().addHeader("Content-Disposition", "attachment; filename=\"amchart.jpg\"");
        Graphics2D g = result.createGraphics();
        // 处理图形平滑度
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(result, 0, 0, width, height, null);
        g.dispose();

        ServletOutputStream f = getResponse().getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(f);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(result);
        encoder.encode(result, param);
        ImageIO.write(result, "JPEG", getResponse().getOutputStream());// 输出图片
        f.close();

        return null;
    }

    public String getPieDataFilePath() {
        return pieDataFilePath;
    }

    public void setPieDataFilePath(String pieDataFilePath) {
        this.pieDataFilePath = pieDataFilePath;
    }

    public String getColumnDataFilePath() {
        return columnDataFilePath;
    }

    public void setColumnDataFilePath(String columnDataFilePath) {
        this.columnDataFilePath = columnDataFilePath;
    }

//    public A1300ChartService getChartService() {
//        return chartService;
//    }

    public void setChartService(A1300ChartService chartService) {
        this.chartService = chartService;
    }
}
