package com.example.sauxt.codingclinic.Data.Source;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.sauxt.codingclinic.Data.Entity.Feed;
import com.example.sauxt.codingclinic.Data.Source.Local.FeedDao;
import com.example.sauxt.codingclinic.Data.Source.Local.FeedRoomDatabase;

import java.util.List;

public class FeedRepository {

    private FeedDao feedDao;
    private LiveData<List<Feed>> allFeeds;

    public FeedRepository(Application application){
        FeedRoomDatabase db = FeedRoomDatabase.getDatabase(application);
        feedDao = db.feedDao();
        allFeeds = feedDao.getAllFeeds();
    }

    public LiveData<List<Feed>> getAllFeeds() {
        return allFeeds;
    }

    public void insert(Feed feed) {
        new insertAsyncTask(feedDao).execute(feed);
    }

    private static class insertAsyncTask extends AsyncTask<Feed, Void, Void>{
        private FeedDao asyncTaskDao;

        insertAsyncTask(FeedDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Feed... feeds) {
            asyncTaskDao.insert(feeds[0]);
            return null;
        }
    }
}

