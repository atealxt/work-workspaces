/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.relation.one2many;

 /** 
  * Child entity. 
  * @author MyEclipse Persistence Tools 
  */  
   
 @SuppressWarnings("serial")  
 public class Child implements java.io.Serializable  
 {  
     private String id;  
   
     private String name;  
   
     private Father father;  
   
     public Child()  
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
   
     public Father getFather()  
     {  
         return this.father;  
     }  
   
     public void setFather(Father father)  
     {  
         this.father = father;  
     }  
   
     public String getName()  
     {  
         return this.name;  
     }  
   
     public void setName(String name)  
     {  
         this.name = name;  
     }       
          
    @Override
     public boolean equals(Object obj)
     {
         if(obj==null)return false;
         if(this==obj)return true;
         if(!(obj instanceof Child))return false;
         
         final Child child = (Child)obj;
         if(!child.getId().equals(getId()))return false;    //only judge the primary key is well.
         
         return true;
     }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }    
    
   
 }
