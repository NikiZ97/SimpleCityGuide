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


public class StadiumFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();

    public StadiumFragment() {}

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
                .setName("Шинник").setDistance(4.2)
                .setLatitude(57.628856).setLongitude(39.866541)
                .setTimeToGo("ехать примерно 35-40 минут").setImage(R.drawable.shinnik)
                .setTabs(R.color.shinnik_tabs).setTheme(R.style.AppTheme_Shinnik)
                .setDescription(getString(R.string.shinnik_description)).setAddress(getString(R.string.shinnik_address))
                .setEmail(getString(R.string.shinnik_email)).setOpenTo(getString(R.string.shinnik_open_to))
                .setFab(R.drawable.ic_45).setPhone(getString(R.string.shinnik_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("stadium").setLocation(getString(R.string.arena_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Арена").setDistance(6.3)
                .setLatitude(57.588692).setLongitude(39.847660)
                .setTimeToGo("ехать примерно 50 минут - 1ч.5м.").setImage(R.drawable.arena)
                .setTabs(R.color.arena_tabs).setTheme(R.style.AppTheme_Arena)
                .setDescription(getString(R.string.arena_description)).setAddress(getString(R.string.arena_address))
                .setEmail(getString(R.string.arena_email)).setOpenTo(getString(R.string.arena_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.arena_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_3_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("stadium").setLocation(getString(R.string.arena_location_text))
                .build());
    }
}
