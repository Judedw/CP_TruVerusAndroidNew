package android.mobile.com.truverusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.mobile.com.truverusandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectionFragment extends Fragment {


    public MyCollectionFragment() {
        // Required empty public constructor
    }

    public static MyCollectionFragment newInstance() {

        MyCollectionFragment fragment = new MyCollectionFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_collection, container, false);
    }

}
