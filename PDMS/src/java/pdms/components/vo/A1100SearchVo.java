package pdms.components.vo;

import java.util.List;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1100SearchVo {

    /** 搜索结果 */
    private List<A1101SearchDataVo> data;
    /** 显示列名 */
    private List<String> headers;
    /** 记录集总数 */
    private long results;

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public List<A1101SearchDataVo> getData() {
        return data;
    }

    public void setData(List<A1101SearchDataVo> data) {
        this.data = data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
