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

public class ClubsFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();;

    public ClubsFragment() {}

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
        RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mlayoutManager);
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
                .setName("Мёд").setDistance(1.9)
                .setLatitude(57.621084).setLongitude(39.899989)
                .setTimeToGo("ехать примерно 35-40 минут").setImage(R.drawable.med)
                .setTabs(R.color.med_tabs).setTheme(R.style.AppTheme_Med)
                .setDescription(getString(R.string.med_description)).setAddress(getString(R.string.med_address))
                .setEmail(getString(R.string.med_email)).setOpenTo(getString(R.string.med_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.med_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_5_small).setMarkCount(3)
                .setCategory("club").setLocation(getString(R.string.med_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Горка").setDistance(2.9)
                .setLatitude(57.630927).setLongitude(39.887097)
                .setTimeToGo("ехать примерно 20-25 минут").setImage(R.drawable.gorka)
                .setTabs(R.color.gorka_tabs).setTheme(R.style.AppTheme_Gorka)
                .setDescription(getString(R.string.gorka_description)).setAddress(getString(R.string.gorka_address))
                .setEmail(getString(R.string.gorka_email)).setOpenTo(getString(R.string.gorka_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.gorka_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_3_small).setMarkCount(3)
                .setCategory("club").setLocation(getString(R.string.gorka_location_text))
                .build());

        /*objectList.add(new YaroslavlObject("Мёд", 3.4, R.drawable.med));
        objectList.add(new YaroslavlObject("Горка", 4.1, R.drawable.gorka));*/
    }
}
