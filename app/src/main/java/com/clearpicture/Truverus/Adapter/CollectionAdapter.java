package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.CollectionListModel;
import com.clearpicture.Truverus.models.EventsModel;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ImageViewHolder> {
    private GridItemClickListener gridItemClickListener;
    private LayoutInflater inflater;
    private ArrayList<CollectionListModel> collectionList;
    private Context context;
    int selectedPosition=-1;

    public CollectionAdapter(Context context, ArrayList<CollectionListModel> collectionList, GridItemClickListener gridItemClickListener){

        this.collectionList = collectionList;
        this.context = context;
        this.gridItemClickListener = gridItemClickListener;
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
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        final CollectionListModel collectionListModel = collectionList.get(position);
        holder.collectionLbl.setText(collectionList.get(position).getItemName());
        Glide.with(context)
                .load(collectionListModel.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.colectionImg);//        holder.collectionLbl.setText("Levi's");

        if(selectedPosition==position)
            holder.itemView.setBackgroundColor(Color.parseColor("#3297999C"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView collectionLbl;
        public ImageView colectionImg;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            colectionImg = itemView.findViewById(R.id.colectionImg);
            collectionLbl =  itemView.findViewById(R.id.collectionLbl);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            selectedPosition=position;
            notifyDataSetChanged();

            CollectionListModel collectionListModel = collectionList.get(position);
            if (collectionListModel != null) {

                gridItemClickListener.onItemClick(collectionListModel);

            }
        }
    }
}
