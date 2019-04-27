package com.clearpicture.Truverus.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.EventsModel;
import com.clearpicture.Truverus.models.SearchModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    GridItemClickListener gridItemClickListener;
    private Context context;
    private LayoutInflater layoutInflater;

    private SearchModel searchModel;
    private ArrayList<SearchModel> searchModels;

    public SearchAdapter(Context context, ArrayList<SearchModel> searchModels, GridItemClickListener gridItemClickListener) {
        this.gridItemClickListener = gridItemClickListener;
        this.context = context;
        this.searchModels = searchModels;
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.search_list_item,parent,false);
        SearchAdapter.ViewHolder holder=new SearchAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.txtItemName.setText(Html.fromHtml(searchModels.get(position).getItemName()));
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView txtItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);

        }
    }
}
