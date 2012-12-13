/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.relation.one2one;

 /** 
  * Card entity. 
  */  
   
 @SuppressWarnings("serial")  
 public class Card implements java.io.Serializable  
 {  
     private String id;  
   
     private String cardDesc;  
   
     public Card()  
     {  
     }  
   
     public String getId()  
     {  
         return this.id;  
     }  
   
     public void setId(String id)  
     {  
         this.id = id;  
     }  
   
     public String getCardDesc()  
     {  
         return cardDesc;  
     }  
   
     public void setCardDesc(String cardDesc)  
     {  
         this.cardDesc = cardDesc;  
     }     
     
 } 
