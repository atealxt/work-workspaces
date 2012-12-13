package pdms.platform.action;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.vo.A1100SearchVo;
import pdms.components.vo.A1101SearchDataVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1100SearchService;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1100SearchAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A1100SearchAction.class);
    private A1100SearchService searchService;
    /** file json data */
    private List<A1101SearchDataVo> items;
    /** 记录集 */
    private long results;
    /** grid header */
    private List<String> headers;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String condition = getParamValue("cc");

        int start = 0;
        int limit = 10;
        if (str_start != null) {
            start = Integer.parseInt(getParamValue("start"));
        }
        if (str_limit != null) {
            limit = Integer.parseInt(getParamValue("limit"));
        }
        logger.info("start: " + start);
        logger.info("limit: " + limit);

        logger.info("condition: " + condition);
        //condition: s12s21s31c<br>s12s22s31caaa<br>

//        headers = new ArrayList<String>();
//        headers.add("a");
//        headers.add("b");
//        headers.add("c");
//        headers.add("d");
//        headers.add("e");
//        items = new ArrayList<A1101SearchDataVo>();
//        results = 0;

        A1100SearchVo vo = searchService.MakeVo(limit, start, (String) getSession().get("loginId"), condition);
        headers = vo.getHeaders();
        items = vo.getData();
        results = vo.getResults();

        cleanSess();
        return SUCCESS;
    }

    public List<A1101SearchDataVo> getItems() {
        return items;
    }

    public void setItems(List<A1101SearchDataVo> items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

//    public A1100SearchService getSearchService() {
//        return searchService;
//    }

    public void setSearchService(A1100SearchService searchService) {
        this.searchService = searchService;
    }
}
