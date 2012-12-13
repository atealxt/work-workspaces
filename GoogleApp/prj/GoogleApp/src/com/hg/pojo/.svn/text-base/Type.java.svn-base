package com.hg.pojo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * TODO 目前GAE对多对多只支持Key类型的主键，所以暂且不用<br>
 * 另外对Unowned One-to-Many Relationships的支持也不行：<br>
 * 1.一对多一旦设定后，一就不能再变更了。<br>
 * 2.本来想到在Article内用Key来代替，但每次CreateKey都会新建一个，所以也不行<br>
 * 综上所述，本类暂且不做和Article类的联合关系，而是在Article内建立冗余字段。<br>
 * 什么时候GAE对1对多的支持变好了，再修改。<br>
 *
 * 目前只做一个保存文章分类的单纯装载持久类。
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Type {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;
    @Persistent
    private String name;
    @Persistent
    private int countArticle;

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountArticle() {
        return countArticle;
    }

    public void setCountArticle(int countArticle) {
        this.countArticle = countArticle;
    }

    public String getId() {
        return id;
    }

}
