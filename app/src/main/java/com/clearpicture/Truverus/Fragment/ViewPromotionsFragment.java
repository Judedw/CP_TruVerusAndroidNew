package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPromotionsFragment extends Fragment {

    public static final String TAG = ViewPromotionsFragment.class.getSimpleName();
    public static ViewPromotionsFragment newInstance() {
        ViewPromotionsFragment fragment = new ViewPromotionsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_promotions, container, false);

        return view;
    }

}
