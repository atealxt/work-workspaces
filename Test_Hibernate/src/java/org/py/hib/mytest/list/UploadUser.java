/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.py.hib.mytest.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")  
public class UploadUser implements java.io.Serializable{
    
    private Integer id;
    private String name;    
    private List<String> filenames = new ArrayList<String>(0);

    public List<String> getFilenames() {
        return filenames;
    }

    public void setFilenames(List<String> filenames) {
        this.filenames = filenames;
    }
    
    public void addFileName(String str){
        this.filenames.add(str);
    }

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
