package com.example.sauxt.codingclinic.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.sauxt.codingclinic.Data.Entity.Feed;
import com.example.sauxt.codingclinic.Data.Source.FeedRepository;

import java.util.List;

public class FeedViewModel extends AndroidViewModel {
    private FeedRepository repository;

    private LiveData<List<Feed>> allFeeds;

    public FeedViewModel(Application application){
        super(application);
        repository = new FeedRepository(application);
        allFeeds = repository.getAllFeeds();

    }

    LiveData<List<Feed>> getAllFeeds() { return allFeeds; }

    public void insert (Feed feed) { repository.insert(feed); }


}
