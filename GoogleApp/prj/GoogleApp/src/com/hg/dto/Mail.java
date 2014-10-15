package com.hg.dto;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private String text;
    private boolean multiPart;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isMultiPart() {
        return multiPart;
    }

    public void setMultiPart(boolean multiPart) {
        this.multiPart = multiPart;
    }

    @Override
    public String toString() {
        return "From: " + getFrom()//
                + " To: " + getTo()//
                + " Subject: " + getSubject()//
                + " MultiPart: " + isMultiPart()//
                + " Text: " + getText();
    }

}
