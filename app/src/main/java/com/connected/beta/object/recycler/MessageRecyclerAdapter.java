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
 * Created by kirkee on 2017. 2. 12..
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {
    private List<MessageRow> messageRowList;
    private int itemLayout;


    public MessageRecyclerAdapter(List<MessageRow> items, int itemLayout) {
        this.messageRowList = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {


        final MessageRow item = messageRowList.get(position);
        viewHolder.message.setText(item.getMessage());
        viewHolder.watch.setText(item.getWatch()+"");
        viewHolder.comment.setText(item.getComment()+"");
        viewHolder.idLocation.setText(item.getUserId()+" ( " + item.getLocation() + " )");

        if(item.getIsNew()){
            viewHolder.isNew.setVisibility(View.VISIBLE);
        }else{
            viewHolder.isNew.setVisibility(View.INVISIBLE);
        }

        if(item.getIsCamera()){
            viewHolder.isCamera.setVisibility(View.VISIBLE);
        }else{
            viewHolder.isCamera.setVisibility(View.INVISIBLE);
        }

        viewHolder.itemView.setTag(item);

    }


    @Override
    public int getItemCount() {
        return messageRowList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbNailImage;
        public TextView message;
        public TextView watch;
        public TextView comment;
        public TextView idLocation;
        public TextView isNew;
        public ImageView isCamera;


        public ViewHolder(View itemView) {
            super(itemView);

            thumbNailImage = (ImageView) itemView.findViewById(R.id.thumbNailImage);
            message = (TextView) itemView.findViewById(R.id.message);
            watch = (TextView) itemView.findViewById(R.id.watch);
            comment = (TextView) itemView.findViewById(R.id.comment);
            idLocation = (TextView) itemView.findViewById(R.id.idLocation);
            isNew = (TextView) itemView.findViewById(R.id.isNew);
            isCamera = (ImageView) itemView.findViewById(R.id.isCamera);
        }

    }

    public void clearData() {
        int size = this.messageRowList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.messageRowList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
