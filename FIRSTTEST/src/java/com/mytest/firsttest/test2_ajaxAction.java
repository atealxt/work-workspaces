/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytest.firsttest;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Administrator
 */
public class test2_ajaxAction extends org.apache.struts.action.Action {
       
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
     
        response.setContentType("text/xml"); 
        response.setHeader("Cache-Control",   "no-cache"); 
        response.setHeader("Content-Type",   "text/xml;   charset=gb2312"); 
        PrintWriter pw = response.getWriter(); 
        pw.write("ajaxTest!"); 
        pw.close(); 
        
        return null;        
    }
}