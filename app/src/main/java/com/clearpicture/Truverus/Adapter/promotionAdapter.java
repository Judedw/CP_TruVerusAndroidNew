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
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;
import com.clearpicture.Truverus.models.PromotionModel;

import java.util.ArrayList;

public class promotionAdapter extends RecyclerView.Adapter<promotionAdapter.ViewHolder>  {
    private LayoutInflater inflater;
    private ArrayList<PromotionModel> promotionModelArrayList;
    private Context context;
    private  GridItemClickListener gridItemClickListener;


    public promotionAdapter(Context context, ArrayList<PromotionModel> promotionModelArrayList,GridItemClickListener gridItemClickListener){

        this.promotionModelArrayList = promotionModelArrayList;
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
    public void onBindViewHolder(@NonNull promotionAdapter.ViewHolder holder, int position) {
        holder.promoItemNameLabel.setText(promotionModelArrayList.get(position).getPromotionName());
        holder.itemImage.setBackgroundResource(promotionModelArrayList.get(position).getPromoImg());

    }

    @Override
    public int getItemCount() {
        return promotionModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView itemImage;
        private TextView promoItemNameLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            promoItemNameLabel = itemView.findViewById(R.id.promoItemNameLabel);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            PromotionModel promotionModel = promotionModelArrayList.get(position);

            if (promotionModel != null) {

                gridItemClickListener.onItemClick(promotionModel);

            }
        }
    }
}
