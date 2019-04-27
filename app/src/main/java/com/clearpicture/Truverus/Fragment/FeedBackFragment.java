package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clearpicture.Truverus.Adapter.FeedBackAdapter;
import com.clearpicture.Truverus.Adapter.InboxAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;
import com.clearpicture.Truverus.models.InboxModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedBackFragment extends Fragment implements GridItemClickListener {
    private FeedBackAdapter mAdapter;
    private RecyclerView feedBackrecyclerView;
    private ArrayList<FeedBackModel> feedbackList;
    RecyclerView.LayoutManager layoutManager;

    public FeedBackFragment newInstance() {
        FeedBackFragment fragment = new FeedBackFragment();
        return fragment;
    }
    int[] brandImages =  new int[]{R.drawable.nikelogo, R.drawable.adidaslogo};
    String[] Communitynames = new String[]{"NIKE COMMUNITY", "UPCOMING EVENTS"};
    String[] ItemNames = new String[]{"Nike Air Max 270", "Nike Women's "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);
        initializeList();

        feedBackrecyclerView = (RecyclerView) view.findViewById(R.id.feedBackrecyclerView);
        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        feedBackrecyclerView.setLayoutManager(layoutManager);
        mAdapter = new FeedBackAdapter(getActivity(), feedbackList,FeedBackFragment.this);
        feedBackrecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    public void initializeList(){
        int i =0;
        feedbackList = new ArrayList<>();

        for( i=0;i<brandImages.length;i++){
            FeedBackModel info = new FeedBackModel();
            info.setBrandIcon(brandImages[i]);
            info.setCommunityName(Communitynames[i]);
            info.setItemName(ItemNames[i]);

            feedbackList.add(info);

        }

}

    @Override
    public void onItemClick(Object object) {
        ViewPromotionsFragment newFragment = ViewPromotionsFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(ViewPromotionsFragment.TAG);
        ft.replace(R.id.feedBackContainer, newFragment, ViewPromotionsFragment.TAG).commit();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }
}
