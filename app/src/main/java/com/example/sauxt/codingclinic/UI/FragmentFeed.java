package com.example.sauxt.codingclinic.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sauxt.codingclinic.Data.Entity.Feed;
import com.example.sauxt.codingclinic.Data.Network.FeedListAdapter;
import com.example.sauxt.codingclinic.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentFeed extends Fragment {

    View view;
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private FeedListAdapter feedAdapter;
    private FeedViewModel mFeedViewModel;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feed_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createAdapter();

        recyclerviewSetting();

        viewModelSetting();
    }

    public void createAdapter(){
        feedAdapter = new FeedListAdapter(getContext(), new ArrayList<Feed>(0), new FeedListAdapter.PostFeedListener() {

            @Override
            public void onPostClick(int cnt) {
                Toast.makeText(getActivity(), "Like Count : " + cnt, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recyclerviewSetting(){
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(feedAdapter);

    }

    public void viewModelSetting(){
        // Model Provider
        mFeedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        // Observe : Model의 LiveData를 관찰
        mFeedViewModel.getAllFeeds().observe(this, new Observer<List<Feed>>() {
            @Override
            public void onChanged(@Nullable List<Feed> feeds) {
                feedAdapter.setFeeds(feeds);
            }
        });

    }

}
