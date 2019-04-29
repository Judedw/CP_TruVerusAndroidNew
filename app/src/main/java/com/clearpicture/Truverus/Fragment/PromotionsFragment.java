package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.Adapter.FeedBackAdapter;
import com.clearpicture.Truverus.Adapter.promotionAdapter;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;
import com.clearpicture.Truverus.models.FeedBackModel;
import com.clearpicture.Truverus.models.InboxModel;
import com.clearpicture.Truverus.models.PromotionModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionsFragment extends Fragment implements GridItemClickListener {


    private promotionAdapter mAdapter;
    private RecyclerView promotionsrecyclerView;
    private ArrayList<PromotionModel> promotionModelArrayList;
    RecyclerView.LayoutManager layoutManager;
    int[] Images =  new int[]{R.drawable.addidas_red, R.drawable.nike};
    String[] Title = new String[]{"NIKE AIR MAX 270", "NIKE WOMEN'S REVERSIBLE"};

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
        initializeList();

        promotionsrecyclerView = view.findViewById(R.id.promotionsrecyclerView);

        layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        promotionsrecyclerView.setLayoutManager(layoutManager);
        mAdapter = new promotionAdapter(getActivity(), promotionModelArrayList, PromotionsFragment.this);
        promotionsrecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("message", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("message", "onKey Back listener is working!!!");
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onItemClick(Object object) {
        ViewPromotionsFragment newFragment = ViewPromotionsFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(ViewPromotionsFragment.TAG);
        ft.replace(R.id.promotionContainer, newFragment, ViewPromotionsFragment.TAG).commit();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemClick(String id, String msgId) {

    }
    public void initializeList(){
        int i =0;
        promotionModelArrayList = new ArrayList<>();

        for( i=0;i<Images.length;i++){
            PromotionModel info = new PromotionModel();
            info.setPromoImg(Images[i]);
            info.setPromotionName(Title[i]);

            promotionModelArrayList.add(info);

        }


    }
}
