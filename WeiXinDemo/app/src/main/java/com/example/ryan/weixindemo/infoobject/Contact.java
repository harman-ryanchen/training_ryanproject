package com.example.ryan.weixindemo.infoobject;

import android.graphics.Bitmap;

/**
 * Created by ryan on 1/1/16.
 */
public class Contact {
    private Bitmap bitmap;
    private String name;
    private boolean isTitle = false;

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
