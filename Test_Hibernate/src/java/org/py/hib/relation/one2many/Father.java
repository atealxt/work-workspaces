/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.py.hib.relation.one2many;

import java.util.HashSet;
import java.util.Set;

/** 
 * Father entity. 
 */
@SuppressWarnings("serial")
public class Father implements java.io.Serializable {

  private String id;
  private String name;
  private Integer age;
  private Set<Child> children = new HashSet<Child>(0);

  public Father() {
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Set<Child> getChildren() {
    return children;
  }

  public void setChildren(Set<Child> children) {
    this.children = children;
  }
}
