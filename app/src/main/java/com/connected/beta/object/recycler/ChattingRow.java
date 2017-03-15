package com.connected.beta.object.recycler;

/**
 * Created by kirkee on 2017. 2. 12..
 */

public class ChattingRow {
    private String id;
    private String userId;
    private String thumbNailImage;
    private String message;
    private int incommingMessage;
    private String time;

    public ChattingRow(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThumbNailImage() {
        return thumbNailImage;
    }

    public void setThumbNailImage(String thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIncommingMessage() {
        return incommingMessage;
    }

    public void setIncommingMessage(int incommingMessage) {
        this.incommingMessage = incommingMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
