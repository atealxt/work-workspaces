package pdms.platform.service;

import pdms.platform.core.PdmsException;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public interface A1300ChartService {

    public String getPieDataFilePath() throws PdmsException;
    
    public String getColumnDataFilePath() throws PdmsException;
}
