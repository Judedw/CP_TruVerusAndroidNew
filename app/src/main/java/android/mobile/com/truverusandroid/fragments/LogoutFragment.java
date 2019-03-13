package android.mobile.com.truverusandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.mobile.com.truverusandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {

    public static final String TAG = LogoutFragment.class.getSimpleName();
    public static LogoutFragment newInstance(DrawerLayout drawer) {

        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }
    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

}
