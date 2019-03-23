package com.clearpicture.Truverus.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clearpicture.Truverus.Adapter.ImageSliderAdapter;
import com.clearpicture.Truverus.CustomViewPager;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.SignInActivity;
import com.clearpicture.Truverus.SignUpActivity;

import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment implements View.OnClickListener {

    private String vedioId = "QIetOk4hmiY";
    private static int currentPage = 0;
    private static final Integer[] HOTDEALS = {R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red, R.drawable.adidas, R.drawable.addidas_red};
    private ArrayList<Integer> HOTDEALSArray = new ArrayList<Integer>();
    private CircleIndicator indicator;
    public CustomViewPager mPager;
    private AppBarLayout mAppBarLayout;
    private NestedScrollView animateScrollView;
    private LinearLayout watchVedio;


    public ProductDetailsFragment newInstance() {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

//        mPager = (CustomViewPager) view.findViewById(R.id.pager);
//        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        mAppBarLayout = (AppBarLayout)view.findViewById(R.id.app_bar);
        animateScrollView = (NestedScrollView)view.findViewById(R.id.animateScrollView);
        watchVedio = (LinearLayout)view.findViewById(R.id.watchVedio);
        watchVedio.setOnClickListener(this);
//        init();
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

    }


    @Override
    public void onClick(View v) {
switch (v.getId()){
        case R.id.watchVedio: {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/QIetOk4hmiY" + vedioId));
            startActivity(intent);
            break;
        }
    }
    }
}