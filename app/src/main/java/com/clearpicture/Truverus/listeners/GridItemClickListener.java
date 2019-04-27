package com.clearpicture.Truverus.listeners;


public interface GridItemClickListener {

    void onItemClick(Object object);

    void onItemClick(String id);

    void onItemClick(String id, String msgId);

//    void onItemClick(String packageId, String subscriptionId, String gameName, float volume, String harvestId);


}
