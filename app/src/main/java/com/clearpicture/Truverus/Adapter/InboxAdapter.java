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
import com.clearpicture.Truverus.models.InboxModel;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<InboxModel> messageList;
    private Context context;


    public InboxAdapter(Context context, ArrayList<InboxModel> messageList){

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public TextView bodyTxt;
        public TextView dateTxt;
        public TextView timeTxt;
        public ImageView notificationIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = (TextView) itemView.findViewById(R.id.titleTxt);
            bodyTxt = (TextView) itemView.findViewById(R.id.bodyTxt);
            dateTxt = (TextView) itemView.findViewById(R.id.dateTxt);
            timeTxt = (TextView) itemView.findViewById(R.id.timeTxt);
            notificationIcon = itemView.findViewById(R.id.notificationIcon);

        }
    }
}
