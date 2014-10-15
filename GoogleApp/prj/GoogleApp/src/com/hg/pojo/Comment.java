package com.hg.pojo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.hg.util.DateUtil;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Comment {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;// use Long type error(increment by 2)
    @Persistent
    private String title;
    @Persistent
    private String content;
    @Persistent
    private java.util.Date createTime;
    @Persistent
    private User founder;
    @Persistent
    private Article article;

    public Comment(String title, String content, User founder, Article article) {
        super();
        this.title = title;
        this.content = content;
        this.founder = founder;
        this.article = article;
        this.createTime = DateUtil.getCurrentTime();
    }

    public String toString() {
        return new StringBuilder("Comment id=").append(id).toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getId() {
        return id;
    }

}
