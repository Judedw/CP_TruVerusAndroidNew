package com.clearpicture.Truverus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.clearpicture.Truverus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckEmailFragment extends Fragment implements View.OnClickListener {
private Button btnSubmitAcc;
    public CheckEmailFragment newInstance() {
        CheckEmailFragment fragment = new CheckEmailFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_email, container, false);
        btnSubmitAcc  = view.findViewById(R.id.btnSubmitAcc);
        btnSubmitAcc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.btnSubmitAcc:{
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.passwordResetContainer, new PasswordReSetSuccessFragment().newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
    }
}
