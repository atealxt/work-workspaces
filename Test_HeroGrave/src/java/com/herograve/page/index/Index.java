/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.index;

import com.herograve.topic.Topicarea;
import com.herograve.util.HttpSessionUtil;
import com.herograve.util.RequestValidate;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Index extends ActionSupport implements ServletRequestAware{
    
    private HttpServletRequest request;
    private RequestValidate requestValidate;    
    private int errCode;
    private List listTopicarea;
    
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }    
    
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }        

    public List getListTopicarea() {
        return listTopicarea;
    }

    public void setListTopicarea(List listTopicarea) {
        this.listTopicarea = listTopicarea;
    }     

    @Override
    public void validate() {        
        if(request.getSession().getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY) == null){
            //2008/06/24 15:20:42
            request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, 1214292042828l); 
        }
        
        requestValidate = new RequestValidate(request);
//        if(requestValidate.isTooFast()){   
//            this.errCode = requestValidate.getErrCode();
//            request.setAttribute("errRedirect", "welcome.jsp");
//            addActionError("Request validate error!");
//        }
    }
    
    @Override
    public String execute(){
        request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, new Date().getTime());                

        getDojoData();
        
        return SUCCESS;
    }    
    
    private void getDojoData(){
        CRUDfromDojo crud = new CRUDfromDojo();
        crud.getInfo();
        request.setAttribute("TitleCount", crud.getTitleCount());  
        request.setAttribute("ReplyCount", crud.getReplyCount());
        request.setAttribute("UserCount", crud.getUserCount());
        request.setAttribute("LatestUserName", crud.getLatestUserName());   

        List list = crud.getTopicarea();
        Topicarea topicarea = null;
        List<Topicarea> listLevel0 = new ArrayList<Topicarea>(0);
        List<Topicarea> listLevel1 = new ArrayList<Topicarea>(0);
        List<Topicarea> listLevel2 = new ArrayList<Topicarea>(0);
        List<List> listRet = new ArrayList<List>(0);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            topicarea = (Topicarea)iterator.next();
            switch(topicarea.getLevel()){
                case 0:
                    listLevel0.add(topicarea);
                    break;
                case 1:
                    listLevel1.add(topicarea);
                    break;
                case 2:
                    listLevel2.add(topicarea);
                    break;
                default:
                        break;
            }
        }
        listRet.add(listLevel0);
        listRet.add(listLevel1);
        listRet.add(listLevel2);        
        setListTopicarea(listRet);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

