package com.hg.pojo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;
    @Persistent
    private String name;
    @Persistent
    private String contact;
    @Persistent
    private String ip;

    public User(String name) {
        super();
        this.name = name;
    }

    public User(String name, String contact, String ip) {
        this.name = name;
        this.contact = contact;
        this.ip = ip;
    }

    public User(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String toString() {
        return getName() + " id=" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
