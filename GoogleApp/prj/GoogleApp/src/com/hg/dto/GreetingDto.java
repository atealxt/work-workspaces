package com.hg.dto;

import java.util.List;

import com.hg.pojo.Greeting;

public class GreetingDto extends Paging {

    protected List<Greeting> greetings;

    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }

}
