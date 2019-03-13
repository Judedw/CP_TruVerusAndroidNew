package android.mobile.com.truverusandroid;


import android.content.Intent;
import android.mobile.com.truverusandroid.adapters.ViewPagerAdapter;
import android.mobile.com.truverusandroid.fragments.LogoutFragment;
import android.mobile.com.truverusandroid.fragments.MyCollectionFragment;
import android.mobile.com.truverusandroid.fragments.ProductDetailsFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.main_app_bar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");




        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
        setupViewPager(viewPager);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initNavigationDrawer();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(ProductDetailsFragment.newInstance(), "Product Details");
        viewPagerAdapter.addFragment(MyCollectionFragment.newInstance(), "My Collection");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                });
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



                        break;


                    case R.id.nav_logout:

                        Log.d("outout", "onNavigationItemSelected: ");
                        signOut();
                        openLogoutFragment();

                        drawerLayout.closeDrawers();
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
    public void openLogoutFragment() {

        Fragment topupFragment = new LogoutFragment().newInstance(drawerLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.drawer, topupFragment, LogoutFragment.TAG);
        fragmentTransaction.addToBackStack(LogoutFragment.TAG);



    }
}
