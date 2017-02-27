package com.beta.connected.connected.RecyclerView;

/**
 * Created by kirkee on 2017. 2. 27..
 */

public class WriteMessageRow {

    private boolean add;
    private String text;


    public WriteMessageRow(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
