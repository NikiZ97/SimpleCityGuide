package com.nikita.firststep.activity.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikita.firststep.activity.activity.activity.Objects.ConcreteObjectActivity;
import com.nikita.firststep.activity.activity.adapter.ShopsAdapter;
import com.nikita.firststep.activity.activity.other.GridSpacingItemDecoration;
import com.nikita.firststep.activity.activity.other.RecyclerTouchListener;
import com.nikita.firststep.activity.activity.other.YaroslavlObject;

import java.util.ArrayList;
import java.util.List;

import nikita.myappfirststep.R;

public class BarsFragment extends ObjectsFragment {

    private List<YaroslavlObject> mObjectList = new ArrayList<>();

    public BarsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        ShopsAdapter adapter = new ShopsAdapter(getActivity(), mObjectList);
        RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        YaroslavlObject object = mObjectList.get(position);
                        Intent intent = new Intent(getActivity(), ConcreteObjectActivity.class);
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
                                .putExtra("fab4", object.getFab4())
                                .putExtra("mark_count", object.getMarkCount())
                                .putExtra("category", object.getCategory())
                                .putExtra("location", object.getLocation());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {}
                }));
        return view;
    }

    @Override
    protected void prepareObjects() {
        mObjectList = new ArrayList<>();
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Папин Гараж").setDistance(3.9)
                .setLatitude(57.630183).setLongitude(39.869155)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.papin_garage)
                .setTabs(R.color.paping_garage_tabs).setTheme(R.style.AppTheme_PapinGarage)
                .setDescription(getString(R.string.pg_description)).setAddress(getString(R.string.pg_address))
                .setEmail(getString(R.string.pg_email)).setOpenTo(getString(R.string.pg_open_to))
                .setFab(R.drawable.ic_45).setPhone(getString(R.string.pg_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("bar").setLocation(getString(R.string.pg_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Дудки Бар").setDistance(3.0)
                .setLatitude(57.626054).setLongitude(39.881874)
                .setTimeToGo("ехать примерно 20-25 минут").setImage(R.drawable.dudki_bar)
                .setTabs(R.color.dudki_bar_tabs).setTheme(R.style.AppTheme_DudkiBar)
                .setDescription(getString(R.string.dudki_bar_description)).setAddress(getString(R.string.dudki_bar_address))
                .setEmail(getString(R.string.dudki_bar_email)).setOpenTo(getString(R.string.dudki_bar_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.dudki_bar_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("bar").setLocation(getString(R.string.dudki_bar_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("KillFish").setDistance(4.0)
                .setLatitude(57.626861).setLongitude(39.874466)
                .setTimeToGo("ехать примерно 30-40 минут").setImage(R.drawable.killfish)
                .setTabs(R.color.killfish_tabs).setTheme(R.style.AppTheme_Killfish)
                .setDescription(getString(R.string.killfish_description)).setAddress(getString(R.string.killfish_address))
                .setEmail(getString(R.string.killfish_email)).setOpenTo(getString(R.string.killfish_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.kuba_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("bar").setLocation(getString(R.string.killfish_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Куба-Либре").setDistance(2.6)
                .setLatitude(57.625841).setLongitude(39.888833)
                .setTimeToGo("ехать примерно 25-35 минут").setImage(R.drawable.kuba_libre)
                .setTabs(R.color.kuba_libre_tabs).setTheme(R.style.AppTheme_KubaLibre)
                .setDescription(getString(R.string.kuba_description)).setAddress(getString(R.string.kuba_address))
                .setEmail(getString(R.string.kuba_email)).setOpenTo(getString(R.string.kuba_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.kuba_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("bar").setLocation(getString(R.string.kuba_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Шкаф").setDistance(3.0)
                .setLatitude(57.625576).setLongitude(39.882116)
                .setTimeToGo("ехать примерно 25-35 минут").setImage(R.drawable.shkaf)
                .setTabs(R.color.shkaf_tabs).setTheme(R.style.AppTheme_Shkaf)
                .setDescription(getString(R.string.shkaf_description)).setAddress(getString(R.string.shkaf_address))
                .setEmail(getString(R.string.shkaf_email)).setOpenTo(getString(R.string.shkaf_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.shkaf_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("bar").setLocation(getString(R.string.shkaf_location_text))
                .build());
    }
}
