package com.example.sauxt.codingclinic.Data.Source.Local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sauxt.codingclinic.Data.Entity.Feed;

import java.util.List;

@Dao
public interface FeedDao {

    @Insert
    void insert(Feed feed);

    @Query("DELETE FROM feed_table")
    void deleteAll();

    @Query("SELECT * from feed_table ORDER BY id ASC")
    LiveData<List<Feed>> getAllFeeds();

}
