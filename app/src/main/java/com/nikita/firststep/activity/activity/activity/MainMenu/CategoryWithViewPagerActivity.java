package com.nikita.firststep.activity.activity.activity.MainMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nikita.firststep.activity.activity.adapter.ViewPagerAdapter;
import com.nikita.firststep.activity.activity.fragment.AnticafeFragment;
import com.nikita.firststep.activity.activity.fragment.BarbershopFragment;
import com.nikita.firststep.activity.activity.fragment.BarsFragment;
import com.nikita.firststep.activity.activity.fragment.BeautySalonsFragment;
import com.nikita.firststep.activity.activity.fragment.BilliardBowlingFragment;
import com.nikita.firststep.activity.activity.fragment.CinemaFragment;
import com.nikita.firststep.activity.activity.fragment.ClubsFragment;
import com.nikita.firststep.activity.activity.fragment.HookahsFragment;
import com.nikita.firststep.activity.activity.fragment.SaunaFragment;

import nikita.myappfirststep.R;

public class CategoryWithViewPagerActivity extends AppCompatActivity {

    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int layout = intent.getIntExtra("layout", 0);
        int toolbarId = intent.getIntExtra("toolbar", 0);
        String title = intent.getStringExtra("title");
        String viewPagerTitle1 = intent.getStringExtra("title1");
        String viewPagerTitle2 = intent.getStringExtra("title2");
        String viewPagerTitle3 = intent.getStringExtra("title3");

        setContentView(layout);

        Toolbar mToolbar = (Toolbar) findViewById(toolbarId);
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (layout == R.layout.activity_fun) {
            adapter.addFragment(new CinemaFragment(), viewPagerTitle1);
            adapter.addFragment(new BilliardBowlingFragment(), viewPagerTitle2);
            adapter.addFragment(new AnticafeFragment(), viewPagerTitle3);
        } else if (layout == R.layout.activity_beauty_and_health) {
            adapter.addFragment(new SaunaFragment(), viewPagerTitle1);
            adapter.addFragment(new BeautySalonsFragment(), viewPagerTitle2);
            adapter.addFragment(new BarbershopFragment(), viewPagerTitle3);
        } else {
            adapter.addFragment(new ClubsFragment(), viewPagerTitle1);
            adapter.addFragment(new BarsFragment(), viewPagerTitle2);
            adapter.addFragment(new HookahsFragment(), viewPagerTitle3);
        }
        mViewPager.setAdapter(adapter);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
