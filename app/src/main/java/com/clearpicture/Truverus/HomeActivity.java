package com.clearpicture.Truverus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.clearpicture.Truverus.Adapter.TabAdapter;
import com.clearpicture.Truverus.Fragment.CommunityFragment;
import com.clearpicture.Truverus.Fragment.FeedBackFormFragment;
import com.clearpicture.Truverus.Fragment.FeedBackFragment;
import com.clearpicture.Truverus.Fragment.InboxFragment;
import com.clearpicture.Truverus.Fragment.MapViewFragment;
import com.clearpicture.Truverus.Fragment.MyAccountFragment;
import com.clearpicture.Truverus.Fragment.MyCollectionFragment;
import com.clearpicture.Truverus.Fragment.NFCFragment;
import com.clearpicture.Truverus.Fragment.ProductDetailsFragment;
import com.clearpicture.Truverus.Fragment.SettingsFragment;
import com.clearpicture.Truverus.Fragment.TransferOwnershipFragment;
import com.clearpicture.Truverus.main.AppControl;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.clearpicture.Truverus.SignInActivity.MY_PREFS_NAME;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tbTabs;
    private ViewPager vpFrags;
    private RelativeLayout rlMailContainer;

    private String userID;
    private String userName;
    private String userEmail;
    private String userToken;
    private String serverAuthCode;
    private Uri userProImage;
    private TabAdapter tabAdapter;
    private CircleImageView imgProPic;
    private TextView nameLbl;
    private TextView emailLbl;
    private NavigationView navigationView;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInAccount googleSignInAccount;
    private ShareDialog shareDialog;
    private ProfileTracker profileTracker;
    private String fbname;
    private String fbsurname;
    private String fbimageUrl;
    private boolean status;
    private boolean restoredText;
    private String fbEmailAdd;
    private boolean loginStatus;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FacebookSdk.sdkInitialize(this);
        Intent intent = getIntent();

        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        imgProPic = header.findViewById(R.id.imgProPic);
        nameLbl = header.findViewById(R.id.nameLbl);
        emailLbl = header.findViewById(R.id.emailLbl);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestServerAuthCode(AppControl.clientID).requestIdToken(AppControl.clientID).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        restoredText = prefs.getBoolean("fbloginAcconutStatus", false);
        if (restoredText == true) {
            status = prefs.getBoolean("fbloginAcconutStatus", true);
        }
        if (loginStatus == false) {
            System.out.print("log out user");
        }
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
            ShowAllItems();
        } else {
            hideItem();
        }

        tabAdapter = new TabAdapter(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        rlMailContainer = findViewById(R.id.rlMailContainer);
        tbTabs = findViewById(R.id.tbTabs);
        vpFrags = findViewById(R.id.vpFrags);

        tabAdapter = new TabAdapter(getSupportFragmentManager());

        String profileUrl = intent.getStringExtra("profileImage");
        String name = intent.getStringExtra("profileName");
        String email = intent.getStringExtra("profileEmail");

//        new DownloadImage((ImageView)header.findViewById(R.id.imgProPic)).execute(fbimageUrl);

        Glide.with(getApplicationContext())
                .load(userProImage)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProPic);//

        nameLbl.setText(userName);
        emailLbl.setText(userEmail);

        setUpFragments();

        if (status == true) {
            Bundle inBundle = getIntent().getExtras();
            fbname = prefs.getString("fbname", "");
            fbsurname = prefs.getString("fnsurname", "");
            fbimageUrl = prefs.getString("fbimageUrl", "");
            fbEmailAdd = prefs.getString("fbemail", "");
            Glide.with(getApplicationContext())
                    .load(fbimageUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProPic);//
            String fullname = fbname + fbsurname;
            nameLbl.setText(fullname);
            emailLbl.setText(fbEmailAdd);
            System.out.print(fbimageUrl);

        } else {
            System.out.print("log out user");

        }
    }

    public void setUpFragments() {
        tabAdapter.addFragment(new ProductDetailsFragment().newInstance(), "Product Details");
        tabAdapter.addFragment(new MyCollectionFragment().newInstance(), "My Collection");

        vpFrags.setAdapter(tabAdapter);
        tbTabs.setupWithViewPager(vpFrags);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            navigateToHome();
        } else if (id == R.id.nav_my_account) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new MyAccountFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_nfc_scan) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new NFCFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_inbox) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new InboxFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_community) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new CommunityFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_settings) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new SettingsFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_privacy) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new TransferOwnershipFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_logout) {
            logOut();

            LoginManager.getInstance().logOut();
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("fbloginAcconutStatus", false);
            editor.apply();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // LOGIN STATUS == FALSE
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("loginSatus", "false");

                // REFRESH ACTIVITY
                editor.apply();
                finish();
                startActivity(getIntent());

//                // CALL NFC SCREEN
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rlMailContainer, new NFCFragment().newInstance());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void navigateToHome() {
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rlMailContainer);
        if (fragment instanceof MapViewFragment)
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void hideItem() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_inbox).setVisible(false);
        nav_Menu.findItem(R.id.nav_community).setVisible(false);
        nav_Menu.findItem(R.id.nav_my_account).setVisible(false);
        nav_Menu.findItem(R.id.nav_logout).setVisible(false);

    }

    private void ShowAllItems() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_inbox).setVisible(true);
        nav_Menu.findItem(R.id.nav_community).setVisible(true);
        nav_Menu.findItem(R.id.nav_my_account).setVisible(true);
        nav_Menu.findItem(R.id.nav_logout).setVisible(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
