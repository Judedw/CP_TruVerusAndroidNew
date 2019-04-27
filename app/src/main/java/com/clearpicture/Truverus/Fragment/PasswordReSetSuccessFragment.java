package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordReSetSuccessFragment extends Fragment {
    public PasswordReSetSuccessFragment newInstance() {
        PasswordReSetSuccessFragment fragment = new PasswordReSetSuccessFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_re_set_success, container, false);
        return view;
    }

}
