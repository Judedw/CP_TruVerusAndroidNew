package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.Adapter.FeedBackAdapter;
import com.clearpicture.Truverus.Adapter.promotionAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionsFragment extends Fragment implements GridItemClickListener {


    private promotionAdapter mAdapter;
    private RecyclerView promotionsrecyclerView;
    private ArrayList<FeedBackModel> feedbackList;
    RecyclerView.LayoutManager layoutManager;
    public PromotionsFragment() {
        // Required empty public constructor
    }

    public PromotionsFragment newInstance() {
        PromotionsFragment fragment = new PromotionsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_promotions, container, false);

        promotionsrecyclerView = (RecyclerView) view.findViewById(R.id.promotionsrecyclerView);

        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        promotionsrecyclerView.setLayoutManager(layoutManager);
        mAdapter = new promotionAdapter(getActivity(), feedbackList, PromotionsFragment.this);
        promotionsrecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onItemClick(Object object) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rlMailContainer, new ViewPromotionsFragment().newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }
}
