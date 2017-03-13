package com.nikita.firststep.activity.activity.activity.MainMenu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nikita.firststep.activity.activity.adapter.ViewPagerAdapter;
import com.nikita.firststep.activity.activity.fragment.FitnessFragment;
import com.nikita.firststep.activity.activity.fragment.StadiumFragment;

import nikita.myappfirststep.R;

public class CategorySportActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.activity_sport_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Спорт");

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StadiumFragment(), "Стадионы");
        adapter.addFragment(new FitnessFragment(), "Фитнес/спортзалы");
        mViewPager.setAdapter(adapter);
    }
}
