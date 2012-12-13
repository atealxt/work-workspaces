/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytest.firsttest;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.LookupDispatchAction;

/**
 *
 * @author Administrator
 */
public class test1_DispatchAction extends LookupDispatchAction {
    
    protected Map getKeyMethodMap() {
        //System.out.print("getKeyMethodMap"); 
        Map map = new HashMap();
        map.put("test1.submit1","test1_submit1Action");
        map.put("test1.submit2","test1_submit2Action");
        return map;
    }
    
    public ActionForward test1_submit1Action(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.out.print("test1_submit1Action");
        return mapping.findForward("test1_submit1");
    }  
    
    public ActionForward test1_submit2Action(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.out.print("test1_submit2Action");
        return mapping.findForward("test1_submit2");
    }     
    

}