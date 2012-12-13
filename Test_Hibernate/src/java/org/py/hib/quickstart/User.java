/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.quickstart;

//import org.hibernate.*;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")  
public class User implements java.io.Serializable  
{  
    private String id;  
    private String name;  

    public User()  
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
}
