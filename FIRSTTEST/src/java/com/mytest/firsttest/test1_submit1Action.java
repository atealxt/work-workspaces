/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytest.firsttest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Administrator
 */
public class test1_submit1Action extends org.apache.struts.action.Action {
  
    /* forward name="success" path="" */
    private final static String SUCCESS = "display";    
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        request.setAttribute("test1_Dispatch", "I'm submit 1 !");
        return mapping.findForward(SUCCESS);
        
    }
}