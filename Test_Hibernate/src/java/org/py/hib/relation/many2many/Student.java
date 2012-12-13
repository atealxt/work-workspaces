/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.relation.many2many;

 import java.util.HashSet;  
 import java.util.Set;  
   
 /** 
  * Student entity. 
  */  
   
 @SuppressWarnings("serial")  
 public class Student implements java.io.Serializable  
 {  
     private String id;  
   
     private String name;  
   
     private Set<Teacher> teachers = new HashSet<Teacher>(0);  
   
     public Student()  
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
   
     public String getName()  
     {  
         return this.name;  
     }  
   
     public void setName(String name)  
     {  
         this.name = name;  
     }  
   
     public Set<Teacher> getTeachers()  
     {  
         return teachers;  
     }  
   
     public void setTeachers(Set<Teacher> teachers)  
     {  
         this.teachers = teachers;  
     }  
 }
