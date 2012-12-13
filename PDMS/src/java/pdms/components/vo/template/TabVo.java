package pdms.components.vo.template;

import pdms.platform.core.PdmsException;

/**
 * 页面Tab的VO
 * @author LUSuo(atealxt@gmail.com)
 */
public class TabVo {

    /** new tab url */
    private String url;
    /** new tab title */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws PdmsException {
        //this.title = StringUtil.getFirstStr(title, 20);
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
