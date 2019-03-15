package com.mobile.cptruverusandroid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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
import com.mobile.cptruverusandroid.Utils.AppControl;

import org.json.JSONObject;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtEmail;
    private EditText txtPassword;
    private CheckBox cbRemember;
    private Button btnCreateAcc;
    private ImageView btnBack;
    private SignInButton btnGoogleSignIn;
    private LoginButton btnFacebookSignIn;
    private CallbackManager callbackManager;

    private static final String EMAIL = "email";
    private static final int REQUEST_SiGN_IN = 1;

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        cbRemember = findViewById(R.id.cbRemember);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);


        btnBack.setOnClickListener(this);
        btnCreateAcc.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(AppControl.clientID).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });


        btnFacebookSignIn = (LoginButton) findViewById(R.id.btnFacebookSignIn);
        btnFacebookSignIn.setReadPermissions("public_profile", "email", "user_friends");
        btnFacebookSignIn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        btnFacebookSignIn.setCompoundDrawablePadding(5);
        btnFacebookSignIn.setText("");

        // Callback registration
        btnFacebookSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        DisplayData(object);
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putString("fields", "email , id");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();

                Log.d("login result", String.valueOf(loginResult.getAccessToken()));
                Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(i);            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

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
            navigateToHome();
        }

        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack: {

                break;
            }
            case R.id.btnCreateAcc: {
                navigateToSignUp();

                break;
            }
            case R.id.btnGoogleSignIn: {
                googleSignIn();

                break;
            }
        }
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void googleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_SiGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
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
            System.out.println("Token : " + token);

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
    private void DisplayData(JSONObject object) {
        Log.d("facebook log in details", "data" + object);
    }
}
