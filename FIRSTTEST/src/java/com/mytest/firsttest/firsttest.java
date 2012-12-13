/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytest.firsttest;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Administrator
 */
public class firsttest extends org.apache.struts.action.ActionForm {
    
   private String name;   
   private String stringFirstTest;
   private List<String> IteratorTest;

   /**
    * @return
    */
   public String getName() {
       return name;
   }

   /**
    * @param string
    */
   public void setName(String string) {
       name = string;
   }

   /**
    * @return
    */
   public String getStringFirstTest() {
       return stringFirstTest;
   }

   /**
    * @param i
    */
   public void setStringFirstTest(String sTem) {
       stringFirstTest = sTem;
   }
   
   public List getIteratorTest(){
       IteratorTest = new ArrayList<String>();
       IteratorTest.add("aaa");
       IteratorTest.add("bbb");
       IteratorTest.add("ccc");
       return IteratorTest;
   } 
   
   public void setIteratorTest(List<String> list){
       IteratorTest =list;
   }

   /**
    *
    */
   public firsttest() {
       super();
       // TODO Auto-generated constructor stub
   }

    @Override
   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
       ActionErrors errors = new ActionErrors();
       errors.add("error", new ActionMessage("error.name.required"));
       /*
       if (getName() == null || getName().length() < 1) {
           errors.add("name", new ActionMessage("error.name.required"));
           // TODO: add 'error.name.required' key to your resources
       }
       */
       System.out.println("ActionErrors validate");
       
       return errors;
   }
   
}
