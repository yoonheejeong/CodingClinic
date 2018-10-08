package com.example.sauxt.codingclinic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView rowPicture;
        TextView rowMessage;

        MyViewHolder(View view){
            super(view);
            rowPicture = view.findViewById(R.id.row_picture);
            rowMessage = view.findViewById(R.id.row_msg);
        }
    }

    private ArrayList<ImoticonInfo> imoticonInfoArrayList;

    MyAdapter(ArrayList<ImoticonInfo> imoticonInfoArrayList){
        this.imoticonInfoArrayList = imoticonInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.rowPicture.setImageResource(imoticonInfoArrayList.get(position).drawableId);
        myViewHolder.rowMessage.setText(imoticonInfoArrayList.get(position).msg);
    }

    @Override
    public int getItemCount() {
        return imoticonInfoArrayList.size();
    }
}