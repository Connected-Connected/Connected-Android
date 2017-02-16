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
 * Created by kirkee on 2017. 2. 12..
 */

public class ChattingRecyclerAdapter extends RecyclerView.Adapter<ChattingRecyclerAdapter.ViewHolder> {
    private List<ChattingRow> chattingRowList;
    private int itemLayout;


    public ChattingRecyclerAdapter(List<ChattingRow> items, int itemLayout) {
        this.chattingRowList = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {


        final ChattingRow item = chattingRowList.get(position);
        viewHolder.message.setText(item.getMessage());
        viewHolder.userId.setText(item.getUserId());
        viewHolder.time.setText(item.getTime());
        //viewHolder.incommingMessage.setText(item.getIncommingMessage());




        viewHolder.itemView.setTag(item);

    }


    @Override
    public int getItemCount() {
        return chattingRowList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbNailImage;
        public TextView message;
        public TextView userId;
        public TextView time;
        public TextView incommingMessage;


        public ViewHolder(View itemView) {
            super(itemView);

            thumbNailImage = (ImageView) itemView.findViewById(R.id.thumbNailImage);
            message = (TextView) itemView.findViewById(R.id.message);
            userId = (TextView) itemView.findViewById(R.id.userId);
            time = (TextView) itemView.findViewById(R.id.time);
            //time = (TextView) itemView.findViewById(R.id.time);
            //incommingMessage = (TextView) itemView.findViewById(R.id.incommingMessage);

        }

    }

    public void clearData() {
        int size = this.chattingRowList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.chattingRowList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
