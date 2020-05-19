package com.example.maddiemin.viewsnews;

/**
 * Created by maddiemin on 8/17/17.
 */

public class Article {
    private String src;
    private String head;
    private String desc;
    private String link;
    private String iUrl;

    public Article (String source, String headline, String description, String url, String imageUrl){
        src = source;
        head = headline;
        desc = description;
        link = url;
        iUrl = imageUrl;
    }
    public String getSrc() {
        return src;
    }

    public void setSrc(String head) {
        this.src = src;
    }
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getiUrl() {
        return iUrl;
    }

    public void setiUrl(String iUrl) {
        this.iUrl = iUrl;
    }

    @Override
    public String toString() {
        return head;
    }
}
