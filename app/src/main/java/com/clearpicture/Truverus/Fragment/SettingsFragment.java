package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    private TextView settingsHeadertxt;
    private TextView manageLocationTxt;
    public SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsHeadertxt = view.findViewById(R.id.settingsHeadertxt);
        manageLocationTxt = view.findViewById(R.id.manageLocationTxt);
        manageLocationTxt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manageLocationTxt: {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.settingsContainer, new MapViewFragment().newInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                settingsHeadertxt.setText("Manage Location");
            }
        }
    }
}
