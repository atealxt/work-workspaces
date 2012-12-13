/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.relation.one2one;

 /** 
  * Person entity. 
  */  
   
 @SuppressWarnings("serial")  
 public class Person implements java.io.Serializable  
 {  
     private String id;  
   
     private String name;  
   
     private Card card;  
   
     public Person()  
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
   
     public Card getCard()  
     {  
         return this.card;  
     }  
   
     public void setCard(Card card)  
     {  
         this.card = card;  
     }  
   
     public String getName()  
     {  
         return this.name;  
     }  
   
     public void setName(String name)  
     {  
         this.name = name;  
     }  
   
 }
