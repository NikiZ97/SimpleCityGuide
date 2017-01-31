package com.nikita.firststep.activity.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nikita.firststep.activity.activity.activity.MainMenu.MapActivity;
import com.nikita.firststep.activity.activity.fragment.FaqFragment;
import com.nikita.firststep.activity.activity.fragment.HomeFragment;
import com.nikita.firststep.activity.activity.fragment.SettingsFragment;
import com.nikita.firststep.activity.activity.other.CircleTransform;

import nikita.myappfirststep.R;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtSubName;
    private Toolbar toolbar;
    private FloatingActionButton fab;


    private static final String urlNavHeaderBg =
            "https://pp.vk.me/c626117/v626117629/35c4f/xjMARiQExNM.jpg";
    private static final String urlProfileImg = "";
    public static int navItemIndex = 0; // to identify current item from nav menu

    private static final String TAG_HOME = "home";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_FAQ = "faq";
    private static String CURRENT_TAG = TAG_HOME;

    private String[] activityTitles;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        handler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        View navHeader = navigationView.getHeaderView(0);

        txtSubName = (TextView) navHeader.findViewById(R.id.website);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);


        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Вы написали письмо", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        loadNavHeader();
        loadHomeFragment();

        setUpNavigationView();
    }

    /**
     * Load navigation menu header information
     * like background, img, name, subname etc.
     */
    private void loadNavHeader() {
        // nav header text
        txtName.setText("");
        // nav header subtext
        txtSubName.setText("");

        // load nav header background into ImageView
        Glide.with(this)
                .load(urlNavHeaderBg).crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // load profile image into ImageView
        Glide.with(this)
                .load(urlProfileImg).crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_faq:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_FAQ;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_bus_schedule:
                        startActivity(new Intent(MainActivity.this, BusScheduleActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_about_us:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default: navItemIndex = 0;
                }

                // check if item is checked or not. If not, set it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        // listener for fab
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     * This method check if user press back
     * And if drawer is open it close
     * Also this method load home fragment when user is not on
     * home fragment when he pressed back
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }
        if (navItemIndex != 0) { // if user isn't on home fragment
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
            return;
        }
        super.onBackPressed();
    }

    /**
     * This method load respected fragment selected by user with Toolbar
     */
    private void loadHomeFragment() {
        selectNavMenu();

        setToolbarTitle();

        // if user select the current menu item, don't do anything, just close
        // the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            toggleFab();
        }

        // Sometimes when fragment has huge data screen can handing,
        // so it is better to use runnable to avoid it
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // add runnable to message queue
        handler.post(runnable);

        toggleFab();

        // close navigation drawer
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    /**
     * This method return respected Fragment depends on navItemIndex
     * @return new Fragment
     */
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FaqFragment();
            case 2:
                return new SettingsFragment();
            default:
                return new HomeFragment();
        }
    }

    /**
     * This method shows or hides toggle FAB
     */
    private void toggleFab() {
        if (navItemIndex == 0) {
            fab.show();
        } else {
            fab.hide();
        }
    }

    private void checkGit() {}

    /**
     * This method set toolbar title.
     * Title depends on navItemIndex from setUpNavigationView()
     */
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    /**
     * This method set checked menu that was chosen by user
     */
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show map icon on toolbar only when home fragment is active
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // click on map item on toolbar
        if (item.getItemId() == R.id.action_map) {
            startActivity(new Intent(this, MapActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
