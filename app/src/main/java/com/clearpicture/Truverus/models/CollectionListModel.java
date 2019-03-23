package com.clearpicture.Truverus.models;

import android.graphics.drawable.Drawable;

public class CollectionListModel {
    private String image;
    private  Integer id ;
   private String itemName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
