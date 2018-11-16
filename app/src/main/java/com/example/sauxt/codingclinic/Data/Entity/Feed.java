package com.example.sauxt.codingclinic.Data.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName="feed_table")
public class Feed {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    public Feed(@NonNull String msg, @NonNull String uri) {
        this.comment = msg;
        this.imgUrl = uri;
    }

    public Feed(){
        this.id = 0;
        this.comment = "";
        this.imgUrl = "";
    }

    @Ignore
    private GitUsers user;

    private String imgUrl;

    private String comment;

    @Ignore
    private Integer likeCount;

    @Ignore
    private Integer replyCount;

    @Ignore
    private List<Reply> replyList;

    @Ignore
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

    public void setImgUrl(String imgUri) { this.imgUrl = imgUri; }

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
