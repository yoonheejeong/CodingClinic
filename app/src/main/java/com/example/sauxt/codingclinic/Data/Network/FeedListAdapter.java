package com.example.sauxt.codingclinic.Data.Network;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sauxt.codingclinic.Data.Entity.Feed;
import com.example.sauxt.codingclinic.Data.Util.FileUtil;
import com.example.sauxt.codingclinic.R;

import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedViewHolder>{

    private List<Feed> mFeeds;
    private Context mContext;
    private PostFeedListener mFeedListener;

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgView;
        private final TextView txtView;
        PostFeedListener mFeedListener;


        public FeedViewHolder(View itemView, PostFeedListener postFeedListener) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.rowText);
            imgView = (ImageView) itemView.findViewById(R.id.rowPicture);

            this.mFeedListener = postFeedListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Feed feed = getFeed(getAdapterPosition());
            this.mFeedListener.onPostClick(feed.getId());

            notifyDataSetChanged();
        }
    }


    public FeedListAdapter (Context context, List<Feed> feeds, PostFeedListener feedListener){
        mContext = context;
        mFeeds = feeds;
        mFeedListener = feedListener;

    }


    @NonNull
    @Override
    public FeedListAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recycler_view_row, parent, false);
        FeedViewHolder viewHolder = new FeedViewHolder(itemView, this.mFeedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedListAdapter.FeedViewHolder holder, int position) {

        TextView textView = holder.txtView;
        ImageView imgView = holder.imgView;

        if(mFeeds != null){
            Feed feed = mFeeds.get(position);

            textView.setText(feed.getComment());

            // TODO : Load Image from Gallery
            String uri = feed.getImgUrl();
            Uri imgUri = Uri.parse(uri);

            Glide.with(mContext).load(FileUtil.getPath(mContext, imgUri)).into(imgView);

        }else{
            textView.setText("No Feeds");

        }


    }


    private Feed getFeed(int adapterPosition){
        return mFeeds.get(adapterPosition);
    }

    public interface PostFeedListener {
        void onPostClick(Integer id);
    }


    @Override
    public int getItemCount() {
        if(mFeeds != null)
            return mFeeds.size();
        else return 0;
    }

    public void setFeeds(List<Feed> feeds){
        mFeeds = feeds;
        notifyDataSetChanged();
    }




}
