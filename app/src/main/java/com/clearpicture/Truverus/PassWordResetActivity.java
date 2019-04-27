package com.clearpicture.Truverus;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.clearpicture.Truverus.Fragment.MyAccountFragment;
import com.clearpicture.Truverus.Fragment.SetNewPasswordFragment;

public class PassWordResetActivity extends AppCompatActivity  implements View.OnClickListener {
private Button btnBack;
private Button btnResetPwAcc;
private RelativeLayout passwordResetContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word_reset);

        btnBack = findViewById(R.id.btnBack);
        passwordResetContainer = findViewById(R.id.passwordResetContainer);
        btnResetPwAcc = findViewById(R.id.btnResetPwAcc);
        btnResetPwAcc.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack: {
                this.finish();
                break;
            }
            case R.id.btnResetPwAcc:{
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.passwordResetContainer, new SetNewPasswordFragment().newInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }
    }
}
