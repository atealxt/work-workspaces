package com.hg.dto;

public class PageLink {

    private String title;
    private String link;
    private String msgShow;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMsgShow() {
        return msgShow;
    }

    public void setMsgShow(String msgShow) {
        this.msgShow = msgShow;
    }

    @Override
    public String toString() {
        return "PageLink [link=" + link + ", msgShow=" + msgShow + ", title=" + title + "]";
    }

}
