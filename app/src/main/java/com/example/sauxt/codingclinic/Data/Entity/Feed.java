package com.example.sauxt.codingclinic.Data.Entity;

import java.util.List;
import java.util.Date;

public class Feed {

    private Integer id;

    private GitUsers user;

    private String imgUrl;

    private String comment;

    private Integer likeCount;

    private Integer replyCount;

    private List<Reply> replyList;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public GitUsers getUser() {
        return user;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getComment() {
        return comment;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(GitUsers user) {
        this.user = user;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
