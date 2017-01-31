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

public class SaunaFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();
    public SaunaFragment() {}

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
                .setName("Алеша Попович").setDistance(2.5)
                .setLatitude(57.622816).setLongitude(39.889766)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.alesha_popovich)
                .setTabs(R.color.alesha_popovich_tabs).setTheme(R.style.AppTheme_Alesha)
                .setDescription(getString(R.string.alesha_description)).setAddress(getString(R.string.alesha_address))
                .setEmail(getString(R.string.alesha_email)).setOpenTo(getString(R.string.alesha_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.alesha_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.alesha_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Корона").setDistance(3.0)
                .setLatitude(57.623511).setLongitude(39.881343)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.korona)
                .setTabs(R.color.korona_tabs).setTheme(R.style.AppTheme_Korona)
                .setDescription(getString(R.string.korona_description)).setAddress(getString(R.string.korona_address))
                .setEmail(getString(R.string.korona_email)).setOpenTo(getString(R.string.korona_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.korona_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.korona_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Легкий пар").setDistance(2.4)
                .setLatitude(57.637209).setLongitude(39.904599)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.legky_par)
                .setTabs(R.color.legkiy_par_tabs).setTheme(R.style.AppTheme_LegkiyPar)
                .setDescription(getString(R.string.par_description)).setAddress(getString(R.string.par_address))
                .setEmail(getString(R.string.par_email)).setOpenTo(getString(R.string.par_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.par_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.par_location_text))
                .build());
    }
}
