package pdms.platform.service;

import pdms.components.vo.A1100SearchVo;
import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A1100SearchService {

    public A1100SearchVo MakeVo(int maxNum, int startNum, String loginId, String condition) throws PdmsException;
}
