/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.mytest.generator;

//import org.hibernate.*;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")  
public class GeneratorTest implements java.io.Serializable  
{  
    private Integer id;  
    private String name;  

    public GeneratorTest()  
    {  
    }  

    public Integer getId()  
    {  
    return this.id;  
    }  

    public void setId(Integer id)  
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
