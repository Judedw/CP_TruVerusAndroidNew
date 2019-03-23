package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ImageViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CollectionListModel> collectionList;
    private Context context;



    public CollectionAdapter(Context context, ArrayList<CollectionListModel> collectionList){

        this.collectionList = collectionList;
        this.context = context;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_collection,parent,false);
        ImageViewHolder holder=new ImageViewHolder(view);

        return holder;


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final CollectionListModel collectionListModel = collectionList.get(position);
        String url ="http://www.brandedgirls.com/wp-content/uploads/2016/12/levis-jeans-for-women-1024x755.jpg";
        holder.collectionLbl.setText(collectionList.get(position).getItemName());
        Glide.with(context)
                .load(collectionListModel.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.colectionImg);//        holder.collectionLbl.setText("Levi's");

    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView collectionLbl;
        public ImageView colectionImg;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            colectionImg = (ImageView) itemView.findViewById(R.id.colectionImg);
            collectionLbl = (TextView) itemView.findViewById(R.id.collectionLbl);
        }
    }
}
