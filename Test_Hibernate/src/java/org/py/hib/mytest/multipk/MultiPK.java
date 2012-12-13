/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.mytest.multipk;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class MultiPK implements java.io.Serializable
{
    private Integer id1;
    private Integer id2;
    private String name;
    
    public MultiPK()
    {
    }

    public int getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
     public boolean equals(Object obj)
     {
         if(obj==null)return false;
         if(this==obj)return true;
         if(!(obj instanceof MultiPK))return false;
         
         final MultiPK child = (MultiPK)obj;
         if(!(child.getId1()==getId1()))return false;
         if(!(child.getId2()==getId2()))return false;
         
         return true;
     }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id1;
        return hash;
    }      
    
}
