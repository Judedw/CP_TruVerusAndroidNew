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
import com.clearpicture.Truverus.models.EventsModel;

import java.util.ArrayList;

public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.ViewHolder>{
    private GridItemClickListener gridItemClickListener;
    private ArrayList<EventsModel> eventsArrayList;
    private Context context;
    public EventAdapter(Context context, ArrayList<EventsModel> eventsArrayList, GridItemClickListener gridItemClickListener){
        this.eventsArrayList = eventsArrayList;
        this.context = context;
        this.gridItemClickListener = gridItemClickListener;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_events,parent,false);
        EventAdapter.ViewHolder holder=new EventAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.itemImage.setBackgroundResource(eventsArrayList.get(position).getLogo());
        holder.eventnameText.setText(eventsArrayList.get(position).getEventName());
    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView eventnameText;
        public ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            eventnameText = (TextView) itemView.findViewById(R.id.eventnameText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            EventsModel eventsModel = eventsArrayList.get(position);
            if (eventsModel != null) {

                gridItemClickListener.onItemClick(eventsModel);

            }

        }
    }
}
