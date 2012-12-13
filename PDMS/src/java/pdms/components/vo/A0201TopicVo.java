package pdms.components.vo;

import pdms.components.vo.template.TabVo;
import pdms.platform.core.PdmsException;
import pdms.platform.util.StringUtil;

/**
 * 
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0201TopicVo extends TabVo {

    /** Topic内容预览(只取前57个字节) */
    private String text;

    public String getText() {
        return text;
    }

    /** 设定Topic内容预览(只取前57个字节) */
    public void setText(String text) throws PdmsException {
        this.text = StringUtil.getFirstStr(text, 57);
    }
}
