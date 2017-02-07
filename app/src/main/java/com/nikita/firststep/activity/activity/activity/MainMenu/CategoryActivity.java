package com.nikita.firststep.activity.activity.activity.MainMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nikita.firststep.activity.activity.activity.Objects.ConcreteObjectActivity;
import com.nikita.firststep.activity.activity.adapter.ShopsAdapter;
import com.nikita.firststep.activity.activity.other.GridSpacingItemDecoration;
import com.nikita.firststep.activity.activity.other.RecyclerTouchListener;
import com.nikita.firststep.activity.activity.other.YaroslavlObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nikita.myappfirststep.R;

public class CategoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ShopsAdapter mAdapter;
    private List<YaroslavlObject> mYaroslavlObjectList;

    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int layout = intent.getIntExtra("layout", 0);
        int toolbarId = intent.getIntExtra("toolbar", 0);
        int collapseId = intent.getIntExtra("collapse", 0);
        final String title = intent.getStringExtra("title");

        setContentView(layout);

        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);

        // click listener for toolbar
        // back arrow from CategoryActivity to HomeFragment
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(collapseId);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle(" ");
        }
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.globus_appbar);
        if (appBarLayout != null) {
            appBarLayout.setExpanded(true);

            // hiding & showing the title when toolbar expanded & collapsed
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbar.setTitle(title);
                        isShow = true;
                    } else if (isShow) {
                        collapsingToolbar.setTitle(" ");
                        isShow = false;
                    }
                }
            });
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mYaroslavlObjectList = new ArrayList<>();
        mAdapter = new ShopsAdapter(this, mYaroslavlObjectList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                    mRecyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    YaroslavlObject object = mYaroslavlObjectList.get(position);
                    Intent intent = new Intent(CategoryActivity.this, ConcreteObjectActivity.class);
                    intent.putExtra("title", object.getName())
                            .putExtra("lat", object.getLatitude())
                            .putExtra("lng", object.getLongitude())
                            .putExtra("time", object.getTimeToGo())
                            .putExtra("imageId", object.getImage())
                            .putExtra("tabs", object.getTabs())
                            .putExtra("theme", object.getTheme())
                            .putExtra("description", object.getDescription())
                            .putExtra("address", object.getAddress())
                            .putExtra("email", object.getEmail())
                            .putExtra("open_to", object.getOpenTo())
                            .putExtra("phone", object.getPhone())
                            .putExtra("fab", object.getFab())
                            .putExtra("fab1", object.getFab1())
                            .putExtra("fab2", object.getFab2())
                            .putExtra("fab3", object.getFab3())
                            .putExtra("mark_count", object.getMarkCount())
                            .putExtra("category", object.getCategory())
                            .putExtra("location", object.getLocation());
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position) {
                }
            }));
        }
        if (layout == R.layout.activity_shops) {
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Лента").setDistance(3.9)
                    .setLatitude(57.656384).setLongitude(39.943600)
                    .setTimeToGo("ехать примерно 15-20 минут").setImage(R.drawable.lenta)
                    .setTabs(R.color.lenta_tabs).setTheme(R.style.AppTheme_Lenta)
                    .setDescription(getString(R.string.lenta_description)).setAddress(getString(R.string.lenta_address))
                    .setEmail(getString(R.string.lenta_email)).setOpenTo(getString(R.string.lenta_open_to))
                    .setFab(R.drawable.ic_47).setPhone(getString(R.string.lenta_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.lenta_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Высшая Лига").setDistance(3.5)
                    .setLatitude(57.637615).setLongitude(39.879762)
                    .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.vyshaya_liga)
                    .setTabs(R.color.vliga_tabs).setTheme(R.style.AppTheme_VLiga)
                    .setDescription(getString(R.string.vliga_description)).setAddress(getString(R.string.vliga_address))
                    .setEmail(getString(R.string.vliga_email)).setOpenTo(getString(R.string.vliga_open_to))
                    .setFab(R.drawable.ic_47).setPhone(getString(R.string.vliga_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                    .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.vliga_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Глобус").setDistance(4.7)
                    .setLatitude(57.640264).setLongitude(39.966288)
                    .setTimeToGo("ехать примерно 20-25 минут").setImage(R.drawable.globus)
                    .setTabs(R.color.globus_tabs).setTheme(R.style.AppTheme_Globus)
                    .setDescription(getString(R.string.globus_description)).setAddress(getString(R.string.globus_address))
                    .setEmail(getString(R.string.globus_email)).setOpenTo(getString(R.string.globus_open_to))
                    .setFab(R.drawable.ic_47).setPhone(getString(R.string.vliga_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.globus_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Пятерочка").setDistance(3.4)
                    .setLatitude(57.637056).setLongitude(39.882716)
                    .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.pyaterochka)
                    .setTabs(R.color.pyaterochka_tabs).setTheme(R.style.AppTheme_Pyaterochka)
                    .setDescription(getString(R.string.pyaterochka_description)).setAddress(getString(R.string.pyaterochka_address))
                    .setEmail(getString(R.string.pyaterochka_email)).setOpenTo(getString(R.string.pyaterochka_open_to))
                    .setFab(R.drawable.ic_43).setPhone(getString(R.string.vliga_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.pyaterochka_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Дикси").setDistance(1.9)
                    .setLatitude(57.637056).setLongitude(39.948266)
                    .setTimeToGo("ехать примерно 10 минут").setImage(R.drawable.diksi)
                    .setTabs(R.color.dixy_tabs).setTheme(R.style.AppTheme_Dixy)
                    .setDescription(getString(R.string.dixy_description)).setAddress(getString(R.string.dixy_address))
                    .setEmail(getString(R.string.dixy_email)).setOpenTo(getString(R.string.dixy_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.dixy_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_3_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.dixy_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Ашан").setDistance(7.7)
                    .setLatitude(57.571633).setLongitude(39.842618)
                    .setTimeToGo("ехать примерно 1ч.15м.-1ч.30м.").setImage(R.drawable.auchan)
                    .setTabs(R.color.auchan_tabs).setTheme(R.style.AppTheme_Auchan)
                    .setDescription(getString(R.string.auchan_description)).setAddress(getString(R.string.auchan_address))
                    .setEmail(getString(R.string.auchan_email)).setOpenTo(getString(R.string.auchan_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.auchan_phone_number))
                    .setFab1(R.drawable.ic_2_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.vernisazh_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Лотос").setDistance(4.1)
                    .setLatitude(57.636035).setLongitude(39.867722)
                    .setTimeToGo("ехать примерно 25-30 минут").setImage(R.drawable.lotos)
                    .setTabs(R.color.lotos_tabs).setTheme(R.style.AppTheme_Lotos)
                    .setDescription(getString(R.string.lotos_description)).setAddress(getString(R.string.lotos_address))
                    .setEmail(getString(R.string.lotos_email)).setOpenTo(getString(R.string.lotos_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.lotos_phone_number))
                    .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_3_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.lotos_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Магнит").setDistance(4.3)
                    .setLatitude(57.659619).setLongitude(39.953052)
                    .setTimeToGo("ехать примерно 25-30 минут").setImage(R.drawable.magnit)
                    .setTabs(R.color.magnit_tabs).setTheme(R.style.AppTheme_Magnit)
                    .setDescription(getString(R.string.magnit_description)).setAddress(getString(R.string.magnit_address))
                    .setEmail(getString(R.string.magnit_email)).setOpenTo(getString(R.string.magnit_open_to))
                    .setFab(R.drawable.ic_37).setPhone(getString(R.string.magnit_phone_number))
                    .setFab1(R.drawable.ic_3_small).setFab2(R.drawable.ic_4_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shop").setLocation(getString(R.string.magnit_location_text))
                    .build());
        } else {
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Аура").setDistance(3.8)
                    .setLatitude(57.628080).setLongitude(39.870029)
                    .setTimeToGo("ехать примерно 35-40 минут").setImage(R.drawable.aura)
                    .setTabs(R.color.aura_tabs).setTheme(R.style.AppTheme_Aura)
                    .setDescription(getString(R.string.aura_description)).setAddress(getString(R.string.aura_address))
                    .setEmail(getString(R.string.aura_email)).setOpenTo(getString(R.string.aura_open_to))
                    .setFab(R.drawable.ic_47).setPhone(getString(R.string.auchan_phone_number))
                    .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.aura_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Флагман").setDistance(3.4)
                    .setLatitude(57.636912).setLongitude(39.882619)
                    .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.flagman)
                    .setTabs(R.color.flagman_tabs).setTheme(R.style.AppTheme_Flagman)
                    .setDescription(getString(R.string.flagman_description)).setAddress(getString(R.string.flagman_address))
                    .setEmail(getString(R.string.flagman_email)).setOpenTo(getString(R.string.flagman_open_to))
                    .setFab(R.drawable.ic_43).setPhone(getString(R.string.flagman_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.flagman_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Вернисаж").setDistance(7.7)
                    .setLatitude(57.571485).setLongitude(39.842767)
                    .setTimeToGo("ехать примерно 1ч.15м - 1ч.30м.").setImage(R.drawable.vernisazh)
                    .setTabs(R.color.magnit_tabs).setTheme(R.style.AppTheme_Vernisazh)
                    .setDescription(getString(R.string.vernisazh_description)).setAddress(getString(R.string.vernisazh_address))
                    .setEmail(getString(R.string.vernisazh_email)).setOpenTo(getString(R.string.vernisazh_open_to))
                    .setFab(R.drawable.ic_43).setPhone(getString(R.string.vernisazh_phone_number))
                    .setFab1(R.drawable.ic_3_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.vernisazh_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("РИО").setDistance(7.3)
                    .setLatitude(57.576959).setLongitude(39.843302)
                    .setTimeToGo("ехать примерно 1ч.5м - 1ч.15м.").setImage(R.drawable.rio)
                    .setTabs(R.color.rio_tabs).setTheme(R.style.AppTheme_Rio)
                    .setDescription(getString(R.string.rio_description)).setAddress(getString(R.string.rio_address))
                    .setEmail(getString(R.string.rio_email)).setOpenTo(getString(R.string.rio_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.rio_phone_number))
                    .setFab1(R.drawable.ic_3_small).setFab2(R.drawable.ic_4_small)
                    .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.rio_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Космос").setDistance(3.9)
                    .setLatitude(57.656264).setLongitude(39.943908)
                    .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.kosmos)
                    .setTabs(R.color.kosmos_tabs).setTheme(R.style.AppTheme_Kosmos)
                    .setDescription(getString(R.string.kosmos_description)).setAddress(getString(R.string.kosmos_address))
                    .setEmail(getString(R.string.kosmos_email)).setOpenTo(getString(R.string.kosmos_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.kosmos_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_3_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.kosmos_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Яркий").setDistance(4.3)
                    .setLatitude(57.645665).setLongitude(39.953042)
                    .setTimeToGo("ехать примерно 17-20 минут").setImage(R.drawable.yarkiy)
                    .setTabs(R.color.yarkiy_tabs).setTheme(R.style.AppTheme_Yarkiy)
                    .setDescription(getString(R.string.yarkiy_description)).setAddress(getString(R.string.yarkiy_address))
                    .setEmail(getString(R.string.kosmos_email)).setOpenTo(getString(R.string.yarkiy_open_to))
                    .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.yarkiy_phone_number))
                    .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_3_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.yarkiy_location_text))
                    .build());
            mYaroslavlObjectList.add(YaroslavlObject.newBuilder()
                    .setName("Альтаир").setDistance(13.4)
                    .setLatitude(57.697630).setLongitude(39.760264)
                    .setTimeToGo("ехать примерно 1ч.10м.").setImage(R.drawable.altair)
                    .setTabs(R.color.altair_tabs).setTheme(R.style.AppTheme_Altair)
                    .setDescription(getString(R.string.altair_description)).setAddress(getString(R.string.altair_address))
                    .setEmail(getString(R.string.altair_email)).setOpenTo(getString(R.string.altair_open_to))
                    .setFab(R.drawable.ic_37).setPhone(getString(R.string.altair_phone_number))
                    .setFab1(R.drawable.ic_2_small).setFab2(R.drawable.ic_5_small)
                    .setFab3(R.drawable.ic_4_small).setMarkCount(3)
                    .setCategory("shopc").setLocation(getString(R.string.altair_location_text))
                    .build());
        }
        mAdapter.notifyDataSetChanged();

        Glide.with(this)
                .load(R.drawable.cover_backdrop)
                .into((ImageView) findViewById(R.id.backdrop));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sort_action) {
            if (mYaroslavlObjectList != null) {
                sort(mYaroslavlObjectList, mAdapter);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_object_list, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Скоро будет");
        //searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        /*newText = newText.toLowerCase();
        final ArrayList<YaroslavlObject> newList = new ArrayList<>();

        for (YaroslavlObject object: mYaroslavlObjectList) {
            final String name = object.getName().toLowerCase();
            if (name.contains(newText))
                newList.add(object);
        }
        mAdapter.setFilter(newList);
        mAdapter.notifyDataSetChanged();*/
        return true;
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * This method sorts objects on distance from obshaga
     * @param list - list of objects
     * @param adapter - shops adapter that inflates one element of list
     */
    public void sort(final List<YaroslavlObject> list, ShopsAdapter adapter) {
        if (list != null) {
            Collections.sort(list);
            adapter.notifyDataSetChanged();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main_content), "Отсортировано" + "по удаленности",
                    Snackbar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
        }
    }
}
