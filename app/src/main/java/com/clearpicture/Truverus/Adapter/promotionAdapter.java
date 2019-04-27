package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;

import java.util.ArrayList;

public class promotionAdapter extends RecyclerView.Adapter<promotionAdapter.ViewHolder>  {
    private LayoutInflater inflater;
    private ArrayList<FeedBackModel> feedbackList;
    private Context context;
    private  GridItemClickListener gridItemClickListener;


    public promotionAdapter(Context context, ArrayList<FeedBackModel> feedbackList,GridItemClickListener gridItemClickListener){

        this.feedbackList = feedbackList;
        this.context = context;
        this.gridItemClickListener = gridItemClickListener;
    }

    @NonNull
    @Override
    public promotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_promotions,parent,false);
        promotionAdapter.ViewHolder holder=new promotionAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull promotionAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            FeedBackModel feedBackModel = feedbackList.get(position);

            if (feedBackModel != null) {

                gridItemClickListener.onItemClick(feedBackModel);

            }
        }
    }
}
