package com.nikita.firststep.activity.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    private ImageView mImgNavHeaderBg, mImgProfile;
    private TextView mTxtName, mTxtSubName;
    private Toolbar mToolbar;
    private SharedPreferences mPrefs;

    private static final String URL_NAV_HEADER_BG =
            "https://pp.vk.me/c626117/v626117629/35c4f/xjMARiQExNM.jpg";
    private static final String URL_PROFILE_IMG = "";
    public static int sNavItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_FAQ = "faq";
    private static String sCurrentTag = TAG_HOME;

    private String[] mActivityTitles;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getSharedPreferences("com.nikita.firststep.activity.activity", MODE_PRIVATE);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mToolbar.setNavigationOnClickListener(view -> finish());

        mHandler = new Handler();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeader = mNavigationView.getHeaderView(0);

        mTxtSubName = (TextView) navHeader.findViewById(R.id.website);
        mTxtName = (TextView) navHeader.findViewById(R.id.name);
        mImgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        mImgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);


        mActivityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        loadNavHeader();
        loadHomeFragment();

        setUpNavigationView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPrefs.getBoolean("firstrun", true)) {
            checkInternetConnection();
            SharedPreferences.Editor edit = mPrefs.edit();
            edit.putBoolean("firstrun", false).apply();
        }
    }

    private void checkInternetConnection() {
        if (!isNetworkStatusAvailable(MainActivity.this)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Предупреждение")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Добро пожаловать!\nХотим предупредить, что у Вас нет " +
                            "интернет-соединения, поэтому некоторые картинки не загрузятся, а " +
                            "некоторые функции не будут работать корректно.")
                    .setNegativeButton("Хорошо!", (dialogInterface, i) -> {});
            builder.show();
        }
    }

    /**
     * Load navigation menu header information
     * like background, img, name, subname etc.
     */
    private void loadNavHeader() {
        // nav header text
        mTxtName.setText("");
        // nav header subtext
        mTxtSubName.setText("");

        // load nav header background into ImageView
        Glide.with(this)
                .load(URL_NAV_HEADER_BG).crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImgNavHeaderBg);

        // load profile image into ImageView
        Glide.with(this)
                .load(URL_PROFILE_IMG).crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImgProfile);

    }

    private void setUpNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    sNavItemIndex = 0;
                    sCurrentTag = TAG_HOME;
                    break;
                case R.id.nav_faq:
                    sNavItemIndex = 1;
                    sCurrentTag = TAG_FAQ;
                    break;
                case R.id.nav_settings:
                    sNavItemIndex = 2;
                    sCurrentTag = TAG_SETTINGS;
                    break;
                case R.id.nav_bus_schedule:
                    startActivity(new Intent(MainActivity.this, BusScheduleActivity.class));
                    mDrawer.closeDrawers();
                    return true;
                case R.id.nav_about_us:
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    mDrawer.closeDrawers();
                    return true;
                default: sNavItemIndex = 0;
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
        });

        // listener for fab
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
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

        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     * This method check if user press back
     * And if mDrawer is open it close
     * Also this method load home fragment when user is not on
     * home fragment when he pressed back
     */
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
        }
        if (sNavItemIndex != 0) { // if user isn't on home fragment
            sNavItemIndex = 0;
            sCurrentTag = TAG_HOME;
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
        // the navigation mDrawer
        if (getSupportFragmentManager().findFragmentByTag(sCurrentTag) != null) {
            mDrawer.closeDrawers();
        }

        // Sometimes when fragment has huge data screen can handing,
        // so it is better to use runnable to avoid it
        Runnable runnable = () -> {
            // update the main content by replacing fragments
            Fragment fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, sCurrentTag);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // add runnable to message queue
        mHandler.post(runnable);

        // close navigation mDrawer
        mDrawer.closeDrawers();

        // refresh mToolbar menu
        invalidateOptionsMenu();
    }

    /**
     * This method return respected Fragment depends on sNavItemIndex
     * @return new Fragment
     */
    private Fragment getHomeFragment() {
        switch (sNavItemIndex) {
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
     * This method set mToolbar title.
     * Title depends on sNavItemIndex from setUpNavigationView()
     */
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(mActivityTitles[sNavItemIndex]);
    }

    /**
     * This method set checked menu that was chosen by user
     */
    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(sNavItemIndex).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show map icon on mToolbar only when home fragment is active
        if (sNavItemIndex == 0) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // click on map item on mToolbar
        if (item.getItemId() == R.id.action_map) {
            startActivity(new Intent(this, MapActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager con = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (con != null) {
            NetworkInfo info = con.getActiveNetworkInfo();
            if (info != null) {
                if (info.isConnected())
                    return true;
            }
        }
        return false;
    }
}
