package android.mobile.com.truverusandroid;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.mobile.com.truverusandroid.adapters.ViewPagerAdapter;
import android.mobile.com.truverusandroid.fragments.LogoutFragment;
import android.mobile.com.truverusandroid.fragments.MyCollectionFragment;
import android.mobile.com.truverusandroid.fragments.ProductDetailsFragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener  {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int count = 2;
    private AppBarLayout appbar;
 private ViewPagerAdapter viewPagerAdapter;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        appbar = findViewById(R.id.main_app_bar);
        tabLayout = findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        viewPager = findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
        setupViewPager(viewPager);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initNavigationDrawer();

    }




    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(ProductDetailsFragment.newInstance(), "Product Details");
        viewPagerAdapter.addFragment(MyCollectionFragment.newInstance(), "My Collection");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }





    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {

            getFragmentManager().popBackStack();
        } else {

            super.onBackPressed();
        }
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

//    @Override
//    public void onPageSelected(int position) {
//
//        Fragment fragment = (Fragment) adapter.instantiateItem(viewPager, position);
//        if (fragment instanceof EntertainmentFragment) {
//
//            ((EntertainmentFragment)fragment).onFragmentVisible();
//        }else if (fragment instanceof ViuAppsFragment){
//
//            ((ViuAppsFragment)fragment).onFragmentVisible();
//        }else {
//
//            ((OffersFragment)fragment).onFragmentVisible();
//        }
//    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }





    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        Menu menu = (Menu) navigationView.getMenu();

//        if (mobileNumber != null || !mobileNumber.equals("")) {
//            Validator validator = new Validator();
//            if (!validator.isDialogNumber(mobileNumber)) {
//                menu.findItem(R.id.nav_star_points).setVisible(false);
//            }
//        }

        //disable recharge viu
//        menu.findItem(R.id.nav_topup).setEnabled(false);

        SpannableString s = new SpannableString(menu.findItem(R.id.nav_topup).getTitle());
//        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.grey)), 0, s.length(), 0);
        menu.findItem(R.id.nav_topup).setTitle(s);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.nav_home:

                        // [START custom_event]
//                        mTracker.send(new HitBuilders.EventBuilder()
//                                .setCategory("Action")
//                                .setAction("Home")
//                                .setLabel("Navigate to Home Screen")
//                                .build());

                        Bundle params = new Bundle();
                        params.putString("screen", "Main Manu");
                        params.putString("navigate_to", "Home");


                        // [END custom_event]

                        viewPager.setCurrentItem(0, true);
                        drawerLayout.closeDrawers();
                        setNavigationItem(0);
                        break;

                    case R.id.nav_acc_setting:

                        // [START custom_event]
//                        mTracker.send(new HitBuilders.EventBuilder()
//                                .setCategory("Action")
//                                .setAction("Account Settings")
//                                .setLabel("Navigate to Account Settings Screen")
//                                .build());

                        Bundle params3 = new Bundle();
                        params3.putString("screen", "Main Manu");
                        params3.putString("navigate_to", "Account Settings");

                        // [END custom_event]


                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_logout:

                        // [START custom_event]
//                        mTracker.send(new HitBuilders.EventBuilder()
//                                .setCategory("Action")
//                                .setAction("Logout")
//                                .setLabel("Navigate to Logout Screen")
//                                .build());

                        Bundle params5 = new Bundle();
                        params5.putString("screen", "Main Manu");
                        params5.putString("navigate_to", "Logout");
                        openTopUpFragment();
                        drawerLayout.closeDrawers();
                        // [END custom_event]


                        break;


                }

                return true;
            }
        });
    }



    public void setNavigationItem(int position) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }


    public void openTopUpFragment() {

        Fragment topupFragment = new LogoutFragment().newInstance(drawerLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.drawer, topupFragment, LogoutFragment.TAG);
        fragmentTransaction.addToBackStack(LogoutFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }





    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    }

