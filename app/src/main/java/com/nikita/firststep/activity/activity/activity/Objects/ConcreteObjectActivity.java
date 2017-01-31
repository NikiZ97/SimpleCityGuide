package com.nikita.firststep.activity.activity.activity.Objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nikita.firststep.activity.activity.other.StartConfig;

import nikita.myappfirststep.R;

public class ConcreteObjectActivity extends StartConfig {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int theme = intent.getIntExtra("theme", 0);
        setTheme(theme);

        setContentView(R.layout.activity_object);

        final String title = intent.getStringExtra("title");
        final double lat = intent.getDoubleExtra("lat", 0);
        final double lng = intent.getDoubleExtra("lng", 0);
        final String time = intent.getStringExtra("time");
        final int imageId = intent.getIntExtra("imageId", 0);
        final int tabsId = intent.getIntExtra("tabs", 0);
        final String description = intent.getStringExtra("description");
        final String address = intent.getStringExtra("address");
        final String email = intent.getStringExtra("email");
        final String phone = intent.getStringExtra("phone");
        final String open_to = intent.getStringExtra("open_to");
        final int fab = intent.getIntExtra("fab", 0);
        final int fab1 = intent.getIntExtra("fab1", 0);
        final int fab2 = intent.getIntExtra("fab2", 0);
        final int fab3 = intent.getIntExtra("fab3", 0);
        final int fab4 = intent.getIntExtra("fab4", 0);
        final int markCount = intent.getIntExtra("mark_count", 0);
        final String category = intent.getStringExtra("category");
        final String location = intent.getStringExtra("location");

        Toolbar toolbar = (Toolbar) findViewById(R.id.object_toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapse = (CollapsingToolbarLayout) findViewById(R.id.object_collapsing_toolbar);
        collapse.setTitle(title);
        collapse.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));

        ImageView imageView = (ImageView) findViewById(R.id.object_image);
        imageView.setImageResource(imageId);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.object_viewpager);
        TabLayout tab = (TabLayout) findViewById(R.id.object_sliding_tabs);
        tab.setBackgroundResource(tabsId);

        CustomPagerAdapter adapter = new CustomPagerAdapter(this) {
            @Override
            public Object instantiateItem(ViewGroup collection, int position) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = null;
                switch (position){
                    case 0:
                        view = inflater.inflate(R.layout.object_information, collection, false);
                        TextView objectDescription = (TextView) view.findViewById(R.id.object_info_description);
                        TextView objectAddress = (TextView) view.findViewById(R.id.object_info_address);
                        TextView objectEmail = (TextView) view.findViewById(R.id.object_info_email);
                        TextView objectPhone = (TextView) view.findViewById(R.id.object_info_phone);
                        TextView objectOpenTo = (TextView) view.findViewById(R.id.object_info_open_to);
                        FloatingActionMenu fam = (FloatingActionMenu) view.findViewById(R.id.menu_yellow);
                        FloatingActionButton fab11 =
                                (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab12);
                        FloatingActionButton fab22 =
                                (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab22);
                        FloatingActionButton fab33 =
                                (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab32);
                        FloatingActionButton fab44 =
                                (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab42);

                        objectDescription.setText(description);
                        objectEmail.setText(email);
                        objectAddress.setText(address);
                        objectPhone.setText(phone);
                        objectOpenTo.setText(open_to);

                        fam.getMenuIconView().setImageResource(fab);
                        fam.setMenuButtonColorNormalResId(tabsId);

                        // проверки необходимы для того, чтобы вывести на экран
                        // количество оценок для каждой категории, количество
                        // которых для каждой из категорий разное.
                        // знаю, что это костыль, но больше никаких мыслей в срочном
                        // порядке не приходит
                        if (markCount == 3 && (category.equals("shop") || category.equals("shopc"))) {

                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setBackgroundResource(fab1);
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setBackgroundResource(fab2);
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);
                        } else if (markCount == 3 && category.equals("club")) {
                            fab11.setLabelText("Вместимость");
                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setLabelText("Раположение");
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setLabelText("Атмосфера");
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);

                        } else if (markCount == 4 && (category.equals("bar") || category.equals("hookah"))) {
                            fab44.setVisibility(View.VISIBLE);
                            fab44.setLabelText("Обслуживание");
                            fab44.setImageResource(fab4);
                            fab44.setColorNormalResId(tabsId);

                            fab11.setLabelText("Расположение");
                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setLabelText("Цены");
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setLabelText("Атмосфера");
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);

                        } else if (markCount == 4 && (category.equals("cinema") || category.equals("stadium")
                                                            || category.equals("fitness"))) {
                            fab44.setVisibility(View.VISIBLE);
                            fab44.setLabelText("Цены");
                            fab44.setImageResource(fab4);
                            fab44.setColorNormalResId(tabsId);

                            fab11.setLabelText("Вместимость");
                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setLabelText("Комфортабельность");
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setLabelText("Расположение");
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);
                        } else if (markCount == 4 && (category.equals("beauty") || category.equals("barber"))) {
                            fab44.setVisibility(View.VISIBLE);
                            fab44.setLabelText("Качество");
                            fab44.setImageResource(fab4);
                            fab44.setColorNormalResId(tabsId);

                            fab11.setLabelText("Расположение");
                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setLabelText("Цены");
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setLabelText("Обслуживание");
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);
                        } else {
                            fab11.setLabelText("Комфортабельность");
                            fab11.setImageResource(fab1);
                            fab11.setColorNormalResId(tabsId);

                            fab22.setLabelText("Цены");
                            fab22.setImageResource(fab2);
                            fab22.setColorNormalResId(tabsId);

                            fab33.setLabelText("Расположение");
                            fab33.setImageResource(fab3);
                            fab33.setColorNormalResId(tabsId);
                        }
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.object_location, collection, false);
                        TextView locationText = (TextView) view.findViewById(R.id.location_description);
                        locationText.setText(location);
                        break;
                    case 2:
                        try {
                            view = inflater.inflate(R.layout.map_fragment, collection, false);
                            SupportMapFragment mapFragment = (SupportMapFragment)
                                    getSupportFragmentManager().findFragmentById(R.id.map);
                            mapFragment.getMapAsync(this);
                        } catch (InflateException e) {
                            return view;
                        }
                }

                collection.addView(view);
                return view;
            }

            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                LatLng object = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(object).title(title)
                        .snippet(time));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(object));
            }
        };
        mViewPager.setAdapter(adapter);
        tab.setupWithViewPager(mViewPager);
    }
}
