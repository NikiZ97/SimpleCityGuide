package com.nikita.firststep.activity.activity.activity.MainMenu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nikita.firststep.activity.activity.adapter.ViewPagerAdapter;
import com.nikita.firststep.activity.activity.fragment.FitnessFragment;
import com.nikita.firststep.activity.activity.fragment.StadiumFragment;

import nikita.myappfirststep.R;

public class CategorySportActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.activity_sport_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Спорт");

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StadiumFragment(), "Стадионы");
        adapter.addFragment(new FitnessFragment(), "Фитнес/спортзалы");
        viewPager.setAdapter(adapter);
    }
}
