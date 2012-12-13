/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.py.hib.mytest.criteria;

/**
 *
 * @author Administrator
 */
public class CriteriaTest implements java.io.Serializable {

  private Integer id;
  private String name;
  private Integer price;

  public CriteriaTest() {
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}