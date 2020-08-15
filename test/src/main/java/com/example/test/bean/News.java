package com.example.test.bean;

import java.util.Date;

public class News {
    private Integer NewsID;
    private Date DateTime;
    private Integer Type;
    private String Content;

    public int getNewsID() {
        return NewsID;
    }

    public void setNewsID(Integer newsID) {
        NewsID = newsID;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public int getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
