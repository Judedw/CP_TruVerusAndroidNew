package com.clearpicture.Truverus.models;

public class FeedBackModel {
    private Integer brandIcon;
    private  Integer itemImg ;
    private  String CommunityName ;
    private String itemName;

    public Integer getBrandIcon() {
        return brandIcon;
    }

    public void setBrandIcon(Integer brandIcon) {
        this.brandIcon = brandIcon;
    }

    public Integer getItemImg() {
        return itemImg;
    }

    public void setItemImg(Integer itemImg) {
        this.itemImg = itemImg;
    }

    public String getCommunityName() {
        return CommunityName;
    }

    public void setCommunityName(String communityName) {
        CommunityName = communityName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
