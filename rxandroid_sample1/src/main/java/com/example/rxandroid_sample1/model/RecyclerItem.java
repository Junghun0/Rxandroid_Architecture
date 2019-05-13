package com.example.rxandroid_sample1.model;

import android.graphics.drawable.Drawable;

public class RecyclerItem {
    Drawable image;
    String title;

    public static RecyclerItem of(Drawable image, String title){
        return new RecyclerItem(image,title);
    }

    public RecyclerItem(Drawable image, String title) {
        this.image = image;
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
