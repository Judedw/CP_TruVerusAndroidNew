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
import com.clearpicture.Truverus.models.CollectionListModel;
import com.clearpicture.Truverus.models.FeedBackModel;
import com.clearpicture.Truverus.models.InboxModel;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<InboxModel> messageList;
    private Context context;
    private GridItemClickListener gridItemClickListener;

    public InboxAdapter(Context context, ArrayList<InboxModel> messageList,GridItemClickListener gridItemClickListener){
        this.gridItemClickListener = gridItemClickListener;
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_inbox,parent,false);
        InboxAdapter.ViewHolder holder=new InboxAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final InboxModel inboxModel = messageList.get(position);
        holder.titleTxt.setText(messageList.get(position).getTitle());
        holder.bodyTxt.setText(messageList.get(position).getBody());
        holder.notificationIcon.setBackgroundResource(messageList.get(position).getIcon());
//        holder.dateTxt.setText(messageList.get(position).getDate());
//        holder.timeTxt.setText(messageList.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTxt;
        public TextView bodyTxt;
        public TextView dateTxt;
        public TextView timeTxt;
        public ImageView notificationIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            bodyTxt = itemView.findViewById(R.id.bodyTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            notificationIcon = itemView.findViewById(R.id.notificationIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            InboxModel inboxModel = messageList.get(position);

            if (inboxModel != null) {

                gridItemClickListener.onItemClick(inboxModel);

            }
        }
    }
}
