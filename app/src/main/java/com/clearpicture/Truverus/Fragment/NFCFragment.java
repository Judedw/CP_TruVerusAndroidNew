package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NFCFragment extends Fragment  {
    private AppBarLayout mAppBarLayout;


    public NFCFragment() {
        // Required empty public constructor
    }
    public NFCFragment newInstance() {
        NFCFragment fragment = new NFCFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_nfc, container, false);
        mAppBarLayout = (AppBarLayout)view.findViewById(R.id.app_bar);
        return  view;
    }


}
