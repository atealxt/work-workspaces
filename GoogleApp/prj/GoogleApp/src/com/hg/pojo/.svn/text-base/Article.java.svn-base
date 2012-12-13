package com.hg.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.hg.constant.TypeConstant;
import com.hg.util.DateUtil;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Article{

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;
    @Persistent
    private String alias;
    @Persistent
    private String title;
    @Persistent
    private String summary;
    @Persistent
    private boolean postBySummary;
    @Persistent
    private User founder;
    @Persistent
    private java.util.Date createTime;
    @Persistent
    private Set<String> readIP;// do not use static initialization
    @Persistent
    private ArticleDetail detail;
    @Persistent(mappedBy = "article")
    @Element(dependent = "true")
    private List<Comment> comments = new ArrayList<Comment>(0);
    @Persistent
    private String type = TypeConstant.DEFAULT; // redundance field.see Type

    public Article(final String title, final User founder) {
        super();
        this.title = title;
        this.founder = founder;
        this.createTime = DateUtil.getCurrentTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public User getFounder() {
        return founder;
    }

    public void setFounder(final User founder) {
        this.founder = founder;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Set<String> getReadIP() {
        return readIP != null ? readIP : new HashSet<String>(1, 0.9F);
    }

    public void setReadIP(final Set<String> readIP) {
        this.readIP = readIP;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        if (detail != null) {
            return detail.getContent();
        }
        return null;
    }

    public void setContent(final String content) {
        if (detail == null) {
            detail = new ArticleDetail();
        }
        detail.setContent(content);
    }

    public java.util.List<Comment> getComments() {
        return comments;
    }

    public boolean isPostBySummary() {
        return postBySummary;
    }

    public void setPostBySummary(final boolean postBySummary) {
        this.postBySummary = postBySummary;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", type=" + type + "]";
    }
}
