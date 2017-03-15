package com.connected.beta.object.recycler;

/**
 * Created by kirkee on 2017. 2. 17..
 */

public class BlogRow {

    private String id;
    private String userId;
    private String thumbNailImage;
    private String title;

    public BlogRow(){

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
