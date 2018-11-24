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
import com.example.sauxt.codingclinic.UI.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedViewHolder>{

    private List<Feed> mFeeds;
    private Context mContext;
    private PostFeedListener mFeedListener;

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgView;
        private final ImageView imgHeart;
        private final TextView comment;
        private final TextView date;
        PostFeedListener mFeedListener;


        public FeedViewHolder(View itemView, PostFeedListener postFeedListener) {
            super(itemView);
            comment = itemView.findViewById(R.id.rowText);
            imgView = itemView.findViewById(R.id.rowPicture);
            date = itemView.findViewById(R.id.dateText);
            imgHeart = itemView.findViewById(R.id.heartImg);

            this.mFeedListener = postFeedListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Feed feed = getFeed(getAdapterPosition());

            this.mFeedListener.onPostClick(feed.getLikeCount());

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

        TextView textView = holder.comment;
        ImageView imgView = holder.imgView;
        TextView date = holder.date;
        final ImageView imgHeart = holder.imgHeart;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        if(mFeeds != null){
            final Feed feed = mFeeds.get(position);

            textView.setText(feed.getComment());

            String dateStr = sdf.format(feed.getCreatedAt());
            date.setText(dateStr);

            // TODO : Update likeCount when user clicks heart_icon
            imgHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MainActivity.feedReposit.updateLikeCnt(feed);

                }
            });

            if(feed.getLikeCount() > 0){
                imgHeart.setImageResource(R.drawable.filled_heart);
            }

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
        void onPostClick(int cnt);
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
