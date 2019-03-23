package com.clearpicture.Truverus;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.clearpicture.Truverus.main.AppControl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private Button btnLogOut;
    private TextView txtUserID;
    private TextView txtUserName;
    private TextView txtUserEmail;
    private ImageView imgUserImage;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInAccount googleSignInAccount;

    private String userID;
    private String userName;
    private String userEmail;
    private String userToken;
    private String serverAuthCode;
    private Uri userProImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut = findViewById(R.id.btnLogOut);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserID = findViewById(R.id.txtUserID);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        imgUserImage = findViewById(R.id.imgUserImage);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestServerAuthCode(AppControl.clientID).requestIdToken(AppControl.clientID).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount != null) {
            userID = googleSignInAccount.getId();
            userName = googleSignInAccount.getDisplayName();
            userEmail = googleSignInAccount.getEmail();
            userToken = googleSignInAccount.getIdToken();
            serverAuthCode = googleSignInAccount.getServerAuthCode();
            userProImage = googleSignInAccount.getPhotoUrl();

            System.out.println("Auth Token : " + serverAuthCode);
            System.out.println("User Token : " + userToken);

            txtUserID.setText(userID);
            txtUserName.setText(userName);
            txtUserEmail.setText(userEmail);
//            Picasso.get().load(userProImage).into(imgUserImage);
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
    }

    private void logOut() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                navigateToLogIn();
            }
        });
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}