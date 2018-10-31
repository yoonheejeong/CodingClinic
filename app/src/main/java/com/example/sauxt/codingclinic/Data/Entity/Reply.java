package com.example.sauxt.codingclinic.Data.Entity;

import java.util.Date;

public class Reply {
    private Integer id;

    private Integer feedId;

    private String comment;

    private GitUsers user;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public String getComment() {
        return comment;
    }

    public GitUsers getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(GitUsers user) {
        this.user = user;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
