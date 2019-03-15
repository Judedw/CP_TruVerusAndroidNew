package com.mobile.cptruverusandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.cptruverusandroid.Fragment.CollectionFragment;
import com.mobile.cptruverusandroid.R;
import com.mobile.cptruverusandroid.models.CollectionListModel;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ImageViewHolder> {

    private LayoutInflater inflater;
    private List<CollectionListModel> imageList;
    private Context context;
    private List<String> cardList;

    public CollectionAdapter(Context context, List<CollectionListModel> imageList){

        inflater=LayoutInflater.from(context);
        this.imageList = imageList;
        this.context = context;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.collection_row,parent,false);
        ImageViewHolder holder=new ImageViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String url ="http://www.brandedgirls.com/wp-content/uploads/2016/12/levis-jeans-for-women-1024x755.jpg";
        Glide.with(context).load(url).into(holder.colection_img);
        holder.collection_lbl.setText("Levi's");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView collection_lbl;
        public ImageView colection_img;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
