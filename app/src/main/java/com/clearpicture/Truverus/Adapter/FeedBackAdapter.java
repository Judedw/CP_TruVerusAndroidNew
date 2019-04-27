package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;

import java.util.ArrayList;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {
    private ArrayList<FeedBackModel> feedbackList;
    private Context context;
    private GridItemClickListener gridItemClickListener;

    public FeedBackAdapter(Context context, ArrayList<FeedBackModel> feedbackList, GridItemClickListener gridItemClickListener) {
        this.feedbackList = feedbackList;
        this.context = context;
        this.gridItemClickListener = gridItemClickListener;
    }

    @NonNull
    @Override
    public FeedBackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_feedback, parent, false);
        FeedBackAdapter.ViewHolder holder = new FeedBackAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.brandImageLogo.setBackgroundResource(feedbackList.get(position).getBrandIcon());
        holder.itemNameText.setText(feedbackList.get(position).getItemName());
        holder.communityNameText.setText(feedbackList.get(position).getCommunityName());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView communityNameText;
        public ImageView brandImageLogo;
        public TextView itemNameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandImageLogo = (ImageView) itemView.findViewById(R.id.brandImageLogo);
            communityNameText = (TextView) itemView.findViewById(R.id.communityNameText);
            itemNameText = (TextView) itemView.findViewById(R.id.itemNameText);
            communityNameText.setPaintFlags(communityNameText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            itemView.setOnClickListener(this);
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
