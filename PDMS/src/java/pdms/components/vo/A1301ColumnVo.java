package pdms.components.vo;

import java.util.List;

/**
 * amCharts column chart data vo
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1301ColumnVo {

    private List<A1302GAndSVo> sVo;
    private List<List<A1302GAndSVo>> gVos;

    public List<List<A1302GAndSVo>> getGVos() {
        return gVos;
    }

    public void setGVos(List<List<A1302GAndSVo>> gVos) {
        this.gVos = gVos;
    }

    public List<A1302GAndSVo> getSVo() {
        return sVo;
    }

    public void setSVo(List<A1302GAndSVo> sVo) {
        this.sVo = sVo;
    }
}
