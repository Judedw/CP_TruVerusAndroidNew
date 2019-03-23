package com.clearpicture.Truverus;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clearpicture.Truverus.main.AppControl;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private SignInButton btnGoogleSignIn;
    private Button btnCreateAcc;
    private LoginButton btnFacebookSignIn;

    private CallbackManager callbackManager;
    private static final String EMAIL = "email";

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;

    private static final int REQUEST_SiGN_IN = 1;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_sign_in);

        callbackManager = CallbackManager.Factory.create();


        btnFacebookSignIn = (LoginButton) findViewById(R.id.btnFacebookSignIn);
        btnGoogleSignIn = findViewById(R.id.sign_in_button);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        btnFacebookSignIn.setReadPermissions(Arrays.asList(EMAIL));
        btnFacebookSignIn.setReadPermissions("public_profile", "email", "user_friends");
        btnFacebookSignIn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        btnFacebookSignIn.setCompoundDrawablePadding(5);
        btnFacebookSignIn.setText("");

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(AppControl.clientID).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        btnCreateAcc.setOnClickListener(this) ;
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        callbackManager = CallbackManager.Factory.create();

         accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {


            }
        };
        System.out.print(accessTokenTracker);
        // If the access token is available already assign it.
         accessToken = AccessToken.getCurrentAccessToken();
        System.out.print(accessToken);
        btnFacebookSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplication(), "log in succes!",
                        Toast.LENGTH_LONG).show();

                System.out.print("ljkghgcf"+loginResult.getAccessToken().getUserId());
                System.out.print(loginResult.getAccessToken().getToken());

                Log.d("login result", String.valueOf(loginResult.getAccessToken()));
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(i);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getApplication(), "success!!",
                                Toast.LENGTH_LONG).show();
                        System.out.print("ljkghgcf"+loginResult.getAccessToken().getUserId());
                        System.out.print(loginResult.getAccessToken().getToken());

                        Log.d("login result", String.valueOf(loginResult.getAccessToken()));
                        Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplication(), "log in cancel!",
                                Toast.LENGTH_LONG).show();                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }


    @Override
    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {

        }

        super.onStart();
    }
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_SiGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SiGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }



    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);


            String token = account.getIdToken();
            String serverAuth = account.getServerAuthCode();

            System.out.println("Token : " + token);
            System.out.println("ServerAuthToken : " + serverAuth);

            navigateToHome();
        } catch (ApiException e) {
            e.printStackTrace();

            Toast.makeText(SignInActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack: {
                this.finish();
                break;
            }
            case R.id.btnCreateAcc: {
               Intent i = new Intent(SignInActivity.this,SignUpActivity.class);
               startActivity(i);
                break;
            }

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}
