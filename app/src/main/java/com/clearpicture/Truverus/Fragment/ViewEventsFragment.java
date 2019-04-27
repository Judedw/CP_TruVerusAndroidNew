package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.listeners.GridItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewEventsFragment extends Fragment{


    public static final String TAG = ViewEventsFragment.class.getSimpleName();

    public static ViewEventsFragment newInstance() {
        ViewEventsFragment fragment = new ViewEventsFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_events, container, false);

        return view;
    }

}
