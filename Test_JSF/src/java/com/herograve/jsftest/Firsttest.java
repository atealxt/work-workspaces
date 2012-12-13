/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.jsftest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class Firsttest implements Serializable {

    private String firstName;
    private String lastName;
    private List<String> items;
    private SelectItem[] color = {new SelectItem("#FF0000","red"), new SelectItem("#00FF00","green"), new SelectItem("#0000FF","blue")};

    public void boolChangeTest(ValueChangeEvent event) {
        System.out.println((Boolean) event.getNewValue());
    }

    public SelectItem[] getColor() {
        return color;
    }

    public void setColor(SelectItem[] color) {
        this.color = color;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void listenerTest(ActionEvent event) {
        System.out.println(event);

        FacesContext context = FacesContext.getCurrentInstance();
        String clientId = event.getComponent().getClientId(context);
        System.out.println(clientId);
        HttpServletRequest request =
                (HttpServletRequest) context.getExternalContext().getRequest();
        String grayLevelString = request.getParameter(clientId + ".x");
        System.out.println(grayLevelString);
    }

    public String execute() {

        System.out.println("Action SUCCESS!");
        System.out.println(getFirstName());

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();

        request.setAttribute("aaa", "request set complete!");

        System.out.println(getFirstName() + " " + getLastName());

        items = new ArrayList<String>();
        items.add("apple");
        items.add("pear");
        items.add("banana");

        return "SUCCESS";
    }
}
