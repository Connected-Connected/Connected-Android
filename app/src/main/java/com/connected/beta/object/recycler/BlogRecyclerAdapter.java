package com.connected.beta.object.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.connected.beta.connected.R;

import java.util.List;

/**
 * Created by kirkee on 2017. 2. 17..
 */

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {
    private List<BlogRow> blogRowList;
    private int itemLayout;


    public BlogRecyclerAdapter(List<BlogRow> items, int itemLayout) {
        this.blogRowList = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {


        final BlogRow item = blogRowList.get(position);
        viewHolder.title.setText(item.getTitle());
        //viewHolder.userId.setText(item.getUserId());


        viewHolder.itemView.setTag(item);

    }


    @Override
    public int getItemCount() {
        return blogRowList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbNailImage;
        public TextView title;
        public TextView userId;


        public ViewHolder(View itemView) {
            super(itemView);

            thumbNailImage = (ImageView) itemView.findViewById(R.id.thumbNailImage);
            title = (TextView) itemView.findViewById(R.id.title);
            //userId = (TextView) itemView.findViewById(R.id.userId);

        }

    }

    public void clearData() {
        int size = this.blogRowList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.blogRowList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
