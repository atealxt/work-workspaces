/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.autocreatecode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class Topic implements Serializable {

    private final static long serialVersionUID = 1L;
    private String id;
    private String info;
    private Topictype type;
    private Set<Topictype> types = new HashSet<Topictype>(0);

    public Set<Topictype> getTypes() {
        return types;
    }

    public void setTypes(Set<Topictype> types) {
        if (type != null) {
            //business check
            return;
        }
        this.types = types;
    }

    public Topictype getType() {
        return type;
    }

    public void setType(Topictype type) {
        if (types.size() > 0) {
            //business check
            return;
        }
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }
}
