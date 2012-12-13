package pdms.platform.util;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.vo.A1300PieVo;
import pdms.components.vo.A1301ColumnVo;
import pdms.components.vo.A1302GAndSVo;

/**
 * amCharts data file helper
 * 
 * @author LUSuo(atealxt@gmail.com)
 */
public class ChartUtil {

    private static Log logger = LogFactory.getLog(ChartUtil.class);

    private ChartUtil() {
    }

    public static String getPieXmlData(List<A1300PieVo> vos) {
        StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<pie>\n");

        if (vos != null) {
            for (A1300PieVo vo : vos) {
                xml.append("  ");
                xml.append("<slice title=\"");
                xml.append(vo.getTitle());
                xml.append("\" ");
                if (vo.isPull_out()) {
                    xml.append("pull_out=\"true\" ");
                }
                if (!StringUtil.isEmpty(vo.getColor())) {
                    xml.append("color=\"");
                    xml.append(vo.getColor());
                    xml.append("\" ");
                }
                if (!StringUtil.isEmpty(vo.getDescription())) {
                    xml.append("description=\"");
                    xml.append(vo.getDescription());
                    xml.append("\" ");
                }
                if (!StringUtil.isEmpty(vo.getPattern_color())) {
                    xml.append("pattern_color=\"");
                    xml.append(vo.getPattern_color());
                    xml.append("\" ");
                }
                if (!StringUtil.isEmpty(vo.getPattern())) {
                    xml.append("pattern=\"");
                    xml.append(vo.getPattern());
                    xml.append("\" ");
                }
                xml.append(">");
                xml.append(vo.getValue());
                //xml.append("</slice>\n");
                xml.append("</slice>");
            }
        }
        xml.append("\n</pie>");
        logger.info(xml);
        return xml.toString();
    }

    public static String getColumnXmlData(A1301ColumnVo vo) {
        StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<chart>\n");

        if (vo != null) {
            List<A1302GAndSVo> series = vo.getSVo();
            if (series != null && series.size() > 0) {
                xml.append("  <series>\n");
                for (A1302GAndSVo se : series) {
                    xml.append("    ");
                    xml.append("<value xid=\"");
                    xml.append(se.getXid());
                    xml.append("\" ");
                    xml.append(">");
                    xml.append(se.getValue());
                    //xml.append("</value>\n");
                    xml.append("</value>");
                }
                xml.append("\n  </series>\n");
            }

            List<List<A1302GAndSVo>> graphs = vo.getGVos();
            if (graphs != null && graphs.size() > 0) {
                xml.append("  <graphs>\n");
                for (int i = graphs.size() - 1; i >= 0; i--) {
                    List<A1302GAndSVo> graph = graphs.get(i);
                    xml.append("    <graph gid='");
                    xml.append(i);
                    xml.append("'>\n");
                    for (A1302GAndSVo se : graph) {
                        xml.append("      ");
                        xml.append("<value xid=\"");
                        xml.append(se.getXid());
                        xml.append("\" ");
                        xml.append(">");
                        xml.append(se.getValue());
                        //xml.append("</value>\n");
                        xml.append("</value>");
                    }
                    xml.append("\n    </graph>\n");
                }
                xml.append("\n  </graphs>");
            }
        }
        xml.append("\n</chart>");
        logger.info(xml);
        return xml.toString();
    }
}
