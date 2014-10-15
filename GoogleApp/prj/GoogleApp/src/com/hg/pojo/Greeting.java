package com.hg.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.hg.util.DateUtil;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Greeting implements Serializable {

    private static final long serialVersionUID = 1l;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;

    @Persistent
    private User author;

    @Persistent
    private Text content;

    @Persistent
    private Date date;

    @Persistent
    private String guestName;

    @NotPersistent
    private static int pagingSize = 10;

    @NotPersistent
    private static int maxLen = 5000;

    public Greeting(User author, String content) {
        this.author = author;
        this.content = new Text(content);
        this.date = DateUtil.getCurrentTime();
    }

    public Greeting(String guestName, String content) {
        this.guestName = guestName;
        this.content = new Text(content);
        this.date = DateUtil.getCurrentTime();
    }

    public Greeting(String id) {
        this.id = id;
    }

    public Greeting() {
    }

    public String getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int getPagingSize() {
        return pagingSize;
    }

    public static int getMaxLen() {
        return maxLen;
    }

    public String getContent() {
        return content.getValue();
    }

    public void setContent(Text content) {
        this.content = content;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

}
