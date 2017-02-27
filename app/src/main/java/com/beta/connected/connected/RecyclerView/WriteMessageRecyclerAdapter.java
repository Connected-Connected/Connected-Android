package com.beta.connected.connected.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.connected.connected.R;

import java.util.List;

/**
 * Created by kirkee on 2017. 2. 27..
 */


public class WriteMessageRecyclerAdapter extends RecyclerView.Adapter<WriteMessageRecyclerAdapter.ViewHolder> {
    private List<WriteMessageRow> writeMessageRowList;
    private int itemLayout;


    public WriteMessageRecyclerAdapter(List<WriteMessageRow> items, int itemLayout) {
        this.writeMessageRowList = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final WriteMessageRow item = writeMessageRowList.get(position);
        //viewHolder.title.setText(item.getTitle());
        //viewHolder.userId.setText(item.getUserId());


        viewHolder.itemView.setTag(item);

    }


    @Override
    public int getItemCount() {
        return writeMessageRowList.size();
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
        int size = this.writeMessageRowList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.writeMessageRowList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
