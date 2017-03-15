package com.connected.beta.object.recycler;

/**
 * Created by kirkee on 2017. 2. 12..
 */

public class MessageRow {
    private String id;
    private String userId;
    private String location;
    private String title;
    private String thumbNailImage;
    private String message;
    private int watch;
    private int comment;
    private boolean isNew;
    private boolean isCamera;

    public MessageRow(){

    }

    public int getWatch(){
        return watch;
    }
    public void setWatch(int watch){
        this.watch =watch;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id =id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getThumbNailImage() {
        return thumbNailImage;
    }
    public void setThumbNailImage(String thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }

    public int getComment() {
        return comment;
    }
    public void setComment(int comment) {
        this.comment = comment;
    }

    public boolean getIsCamera() {
        return isCamera;
    }
    public void setIsCamera(boolean isCamera) {
        this.isCamera = isCamera;
    }

    public boolean getIsNew() {
        return isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
