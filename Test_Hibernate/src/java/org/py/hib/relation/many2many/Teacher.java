/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.relation.many2many;

 import java.util.HashSet;  
 import java.util.Set;  
   
 /** 
  * Teacher entity. 
  */  
   
 @SuppressWarnings("serial")  
 public class Teacher implements java.io.Serializable  
 {  
     private String id;  
   
     private String name;  
   
     private Set<Student> students = new HashSet<Student>(0);  
   
     public Teacher()  
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
   
     public Set<Student> getStudents()  
     {  
         return students;  
     }  
   
     public void setStudents(Set<Student> students)  
     {  
         this.students = students;  
     }  
   
 }
