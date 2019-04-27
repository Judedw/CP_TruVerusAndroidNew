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
public class SetNewPasswordFragment extends Fragment implements View.OnClickListener {
    private Button btnSaveAcc;

    public SetNewPasswordFragment newInstance() {
        SetNewPasswordFragment fragment = new SetNewPasswordFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_new_password, container, false);
        btnSaveAcc = view.findViewById(R.id.btnSaveAcc);
        btnSaveAcc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveAcc: {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.passwordResetContainer, new CheckEmailFragment().newInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }
}
