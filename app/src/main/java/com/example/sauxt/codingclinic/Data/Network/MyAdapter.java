package com.example.sauxt.codingclinic.Data.Network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sauxt.codingclinic.Data.Entity.GitUsers;
import com.example.sauxt.codingclinic.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    private List<GitUsers> mUsers;
    private Context mContext;
    private PostUserListener mUserListener;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;
        private TextView titleTv;
        PostUserListener mUserListener;

        private MyViewHolder (View userView, PostUserListener postUserListener){
            super(userView);
            titleTv = (TextView) userView.findViewById(R.id.rowText);
            img = (ImageView) userView.findViewById(R.id.rowPicture);

            this.mUserListener = postUserListener;
            userView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            GitUsers user = getUser(getAdapterPosition());
            this.mUserListener.onPostClick(user.getId());

            notifyDataSetChanged();
        }

    }

    public MyAdapter (Context context, List<GitUsers> posts, PostUserListener userListener){
        mUsers = posts;
        mContext = context;
        mUserListener = userListener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.recycler_view_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(postView, this.mUserListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        GitUsers user = mUsers.get(position);
        TextView textView = holder.titleTv;
        textView.setText(user.getLogin());

        ImageView imgView = holder.img;
        String url = user.getAvatarUrl();
        Glide.with(mContext).load(url).into(imgView);

    }


    private GitUsers getUser(int adapterPosition){
        return mUsers.get(adapterPosition);
    }

    public interface PostUserListener {
        void onPostClick(Integer id);
    }



    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateLists(List<GitUsers> users){
        mUsers = users;
        notifyDataSetChanged();
    }
}