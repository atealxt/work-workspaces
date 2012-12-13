/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.mytest.list;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class UploadFile implements java.io.Serializable{
    
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
