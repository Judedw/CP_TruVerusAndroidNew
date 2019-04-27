package com.clearpicture.Truverus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.clearpicture.Truverus.main.AppControl;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBack;
    private Button btnGoogleSignIn;
    private Button btnCreateAcc;
    private LoginButton btnFacebookSignIn;
    private ImageView rememberPassword;

    private CallbackManager callbackManager;
    private static final String EMAIL = "email";

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;

    private static final int REQUEST_SiGN_IN = 1;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private   Uri profilePicURL;
    private String googleemail;
    private String  name;
    private String email;


    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_sign_up);

        callbackManager = CallbackManager.Factory.create();
        btnFacebookSignIn = findViewById(R.id.btnFacebookSignIn);
        btnGoogleSignIn = findViewById(R.id.sign_in_button);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        btnFacebookSignIn.setReadPermissions(Arrays.asList( EMAIL));
        btnFacebookSignIn.setReadPermissions("public_profile", "email", "user_friends");
        btnFacebookSignIn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        btnFacebookSignIn.setCompoundDrawablePadding(5);
        btnFacebookSignIn.setText("");

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(AppControl.clientID).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

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
                if (currentAccessToken != null){

                    loadUserProfile(currentAccessToken);

                }
            }
        };
        btnFacebookSignIn.setReadPermissions(Arrays.asList("email","public_profile"));
        btnFacebookSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                email=object.optString("email");
                            }

                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();
                Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
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
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                JSONObject json = response.getJSONObject();
                                try {
                                    if (json != null) {

                                        JSONObject data = json.getJSONObject("picture").getJSONObject("data");
                                        String name=json.getString("name");
                                        email = json.getString("email");
                                        String picUrl=data.getString("url");
                                    }

                                } catch (JSONException e) {

                                }
                            }
                        });

                        System.out.print("ljkghgcf"+loginResult.getAccessToken().getUserId());
                        System.out.print(loginResult.getAccessToken().getToken());
                        Profile profile = Profile.getCurrentProfile();
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
    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
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
            profilePicURL = account.getPhotoUrl();
            name = account.getDisplayName();
            googleemail = account.getEmail();
            String serverAuth = account.getServerAuthCode();

            System.out.println("Token is  : " + token);
            Log.d("Token is", token);
            System.out.println("ServerAuthToken == : " + serverAuth);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("loginSatus", "true");
            editor.apply();

            navigateToHome();
        } catch (ApiException e) {
            e.printStackTrace();

            Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void navigateToHome() {
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        intent.putExtra("profileImage",profilePicURL.toString());
        intent.putExtra("profileName",name.toString());
        intent.putExtra("profileEmail",googleemail.toString());

        startActivity(intent);

    }
    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    Intent main = new Intent(SignUpActivity.this, HomeActivity.class);
                    main.putExtra("name", first_name);
                    main.putExtra("surname", last_name);
                    main.putExtra("email",email);
                    main.putExtra("imageUrl", image_url);
                    startActivity(main);

                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("fbloginAcconutStatus", true);
                    editor.putString("fbname", first_name);
                    editor.putString("fnsurname", last_name);
                    editor.putString("fbimageUrl", image_url);
                    editor.putString("fbemail", email);

                    editor.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack: {
                this.finish();
                break;
            }


        }
    }
}
