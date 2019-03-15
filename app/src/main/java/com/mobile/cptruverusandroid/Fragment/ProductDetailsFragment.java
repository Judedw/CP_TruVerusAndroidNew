package com.mobile.cptruverusandroid.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mobile.cptruverusandroid.Adapter.ImageSliderAdapter;
import com.mobile.cptruverusandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailsFragment extends Fragment  {


    private static int currentPage = 0;
    private static final Integer[] HOTDEALS = {R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red};
    private ArrayList<Integer> HOTDEALSArray = new ArrayList<Integer>();
    private CircleIndicator indicator;
    public ViewPager mPager;

    public ProductDetailsFragment newInstance() {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);

        init();

        return view;



    }
    private void init() {
        for(int i=0;i<HOTDEALS.length;i++)
            HOTDEALSArray.add(HOTDEALS[i]);

        mPager.setAdapter(new ImageSliderAdapter(getActivity(),HOTDEALSArray));

        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == HOTDEALS.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 4500, 4500);
    }

}
