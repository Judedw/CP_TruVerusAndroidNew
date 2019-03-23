package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.models.CollectionListModel;

import java.util.ArrayList;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.ImageViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<CollectionListModel> collectionList;
    private Context context;

    public PendingListAdapter(Context context, ArrayList<CollectionListModel> collectionList){

        inflater=LayoutInflater.from(context);
        this.collectionList = collectionList;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingListAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=inflater.inflate(R.layout.row_pending_list,parent,false);
        PendingListAdapter.ImageViewHolder holder=new ImageViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PendingListAdapter.ImageViewHolder imageViewHolder, int i) {

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
