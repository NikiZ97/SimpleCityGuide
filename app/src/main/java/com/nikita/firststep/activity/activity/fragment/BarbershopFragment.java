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

public class BarbershopFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();

    public BarbershopFragment() {}

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
        ShopsAdapter adapter = new ShopsAdapter(getActivity(), objectList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        YaroslavlObject object = objectList.get(position);
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
        objectList = new ArrayList<>();
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Big Bro").setDistance(2.3)
                .setLatitude(57.628782).setLongitude(39.887152)
                .setTimeToGo("ехать примерно 25-30 минут").setImage(R.drawable.bigbro)
                .setTabs(R.color.bigbro_tabs).setTheme(R.style.AppTheme_BigBro)
                .setDescription(getString(R.string.bigbro_description)).setAddress(getString(R.string.bigbro_address))
                .setEmail(getString(R.string.bigbro_email)).setOpenTo(getString(R.string.bigbro_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.bigbro_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("barber").setLocation(getString(R.string.bigbro_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Индиго").setDistance(2.3)
                .setLatitude(57.649324).setLongitude(39.966945)
                .setTimeToGo("ехать примерно 30-40 минут").setImage(R.drawable.indigo)
                .setTabs(R.color.indigo_tabs).setTheme(R.style.AppTheme_Indigo)
                .setDescription(getString(R.string.indigo_description)).setAddress(getString(R.string.indigo_address))
                .setEmail(getString(R.string.indigo_email)).setOpenTo(getString(R.string.indigo_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.bigbro_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_3_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("barber").setLocation(getString(R.string.bigbro_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Chop-Chop").setDistance(2.6)
                .setLatitude(57.624091).setLongitude(39.893786)
                .setTimeToGo("ехать примерно 45-50 минут").setImage(R.drawable.chop_chop)
                .setTabs(R.color.chop_tabs).setTheme(R.style.AppTheme_Chop)
                .setDescription(getString(R.string.chop_description)).setAddress(getString(R.string.chop_address))
                .setEmail(getString(R.string.chop_email)).setOpenTo(getString(R.string.chop_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.chop_phone_number))
                .setFab1(R.drawable.ic_3_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("barber").setLocation(getString(R.string.chop_location_text))
                .build());
    }
}
