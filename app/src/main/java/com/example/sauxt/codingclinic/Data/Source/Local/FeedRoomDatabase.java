package com.example.sauxt.codingclinic.Data.Source.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.sauxt.codingclinic.Data.Entity.Feed;

@Database(entities = {Feed.class},version = 1)
public abstract class FeedRoomDatabase extends RoomDatabase {
    // DB와 연결되는 DAO
    // DAO는 abstract로 getter를 제공
    public abstract FeedDao feedDao();

    // Singleton pattern, room database는 한개만 존재
    private static FeedRoomDatabase INSTANCE;

    public static FeedRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (FeedRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FeedRoomDatabase.class, "feed_database")
                            .build();
                }
            }
        }

        return INSTANCE;

    }
}

