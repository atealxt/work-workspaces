/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicarea;


import com.herograve.util.ErrorInfoUtil;
import com.herograve.util.HttpSessionUtil;
import com.herograve.util.RequestValidate;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Topicarea extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    //private RequestValidate requestValidate;    
    private int errCode;
    
    private com.herograve.topic.Topicarea topicarea;
    private List listTopictitle;
    private long topictitleCount;
    private int pageCount;
    private List<Object> listpageCount;
    
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }    
    
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public com.herograve.topic.Topicarea getTopicarea() {
        return topicarea;
    }

    public void setTopicarea(com.herograve.topic.Topicarea topicarea) {
        this.topicarea = topicarea;
    }

    public List getListTopictitle() {
        return listTopictitle;
    }

    public void setListTopictitle(List listTopictitle) {
        this.listTopictitle = listTopictitle;
    }

    public long getTopictitleCount() {
        return topictitleCount;
    }

    public void setTopictitleCount(long topictitleCount) {
        this.topictitleCount = topictitleCount;
    }

    public int getPageCount() {
        if(pageCount<=0) return 1;
        return pageCount;
    }

    public void setPageCount() {
        this.pageCount = (int)(Math.ceil((float)this.topictitleCount/20));
    }

    public List getListpageCount() {
        return listpageCount;
    }

    public void setListpageCount() {
        listpageCount = new ArrayList<Object>(0);
        for(int i=0;i<pageCount;i++)listpageCount.add(i);
    }
    
    
    @Override
    public String execute(){
        request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, new Date().getTime());                
        
        getDojoData();

        return SUCCESS;
    }  

    
    @Override
    public void validate() {
        if(request.getSession().getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY) == null){
            //2008/06/24 15:20:42
            request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, 1214292042828l); 
        }
        
        RequestValidate requestValidate = new RequestValidate(request);
//        if(requestValidate.isTooFast()){
//            this.errCode = requestValidate.getErrCode();
//            request.setAttribute("errRedirect", "Index.action");
//            addActionError("Request validate error!");
//            return;
//        }
        
        String sAreaId = request.getParameter("areaId");
        if(sAreaId == null) {
            this.errCode = ErrorInfoUtil.ERROR_CODE_PARAMETER.ordinal();
            request.setAttribute("errRedirect", "Index.action");
            addActionError("Request validate error!");
            return;
        } 
        try{
            Integer areaId = new Integer(request.getParameter("areaId"));
            //get topicarea.topicarea_level
            CRUDfromDojo crud = new CRUDfromDojo();
            Integer areaLevel = crud.getTopicareaLevel(areaId);
            if(areaLevel>1) return;//ok!
            if(requestValidate.isEnterRefused(areaLevel)){
                this.errCode = requestValidate.getErrCode();
                request.setAttribute("errRedirect", "Index.action");
                addActionError("Request validate error!");
                return;
            }            
            
        }catch(Exception ex){
            this.errCode = ErrorInfoUtil.ERROR_CODE_PARAMETER.ordinal();
            request.setAttribute("errRedirect", "Index.action");
            addActionError("Request validate error!");
            return;
        }         
    }    
    
    private void getDojoData(){
        CRUDfromDojo crud = new CRUDfromDojo();       
        crud.getInfo(request.getParameter("areaId"),request.getParameter("page"));
        
        setTopicarea(crud.getTopicarea());
        setListTopictitle(crud.getListTopictitle());
        setTopictitleCount(crud.getTopictitleCount());
        setPageCount();
        setListpageCount();
    }
    
    
    
    
    
    
}
