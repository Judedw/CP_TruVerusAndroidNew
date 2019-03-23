package com.clearpicture.Truverus;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.clearpicture.Truverus.Adapter.TabAdapter;
import com.clearpicture.Truverus.Fragment.MyCollectionFragment;
import com.clearpicture.Truverus.Fragment.NFCFragment;
import com.clearpicture.Truverus.Fragment.ProductDetailsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInAccount googleSignInAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


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

            tabAdapter = new TabAdapter(getSupportFragmentManager());
//            Picasso.get().load(userProImage).into(imgUserImage);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rlMailContainer = findViewById(R.id.rlMailContainer);
        tbTabs = findViewById(R.id.tbTabs);
        vpFrags = findViewById(R.id.vpFrags);

        tabAdapter = new TabAdapter(getSupportFragmentManager());

        setUpFragments();

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
            fragmentTransaction.replace(R.id.rlMailContainer, new NFCFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }  else if (id == R.id.nav_nfc_scan) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rlMailContainer, new NFCFragment().newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_logout) {
          Intent i = new Intent(HomeActivity.this,MainActivity.class);
          startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                navigateToLogIn();
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
}
