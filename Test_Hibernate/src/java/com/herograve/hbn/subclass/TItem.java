/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.subclass;

/**
 *
 * @author Administrator
 */
public class TItem {

    private String id;
    private String name;
    private String manufacturer;

    /**
     * Get the value of manufacturer
     *
     * @return the value of manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Set the value of manufacturer
     *
     * @param manufacturer new value of manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
}
