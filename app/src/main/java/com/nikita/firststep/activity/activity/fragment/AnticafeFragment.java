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

import nikita.myappfirststep.R;

public class AnticafeFragment extends ObjectsFragment {


    private ArrayList<YaroslavlObject> mObjectList;

    public AnticafeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        ShopsAdapter adapter = new ShopsAdapter(getActivity(), mObjectList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
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
                .setName("Check In").setDistance(2.8)
                .setLatitude(57.625452).setLongitude(39.886547)
                .setTimeToGo("ехать примерно 20-25 минут").setImage(R.drawable.check_in)
                .setTabs(R.color.check_in_tabs).setTheme(R.style.AppTheme_CheckIn)
                .setDescription(getString(R.string.checkin_description)).setAddress(getString(R.string.checkin_address))
                .setEmail(getString(R.string.checkin_email)).setOpenTo(getString(R.string.checkin_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.checkin_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_5_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.checkin_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Самое время").setDistance(2.8)
                .setLatitude(57.626868).setLongitude(39.88626)
                .setTimeToGo("ехать примерно 25 минут").setImage(R.drawable.samoe_vremya)
                .setTabs(R.color.samoe_vremya_tabs).setTheme(R.style.AppTheme_SamoeVremya)
                .setDescription(getString(R.string.samoe_vremya_description)).setAddress(getString(R.string.samoe_vremya_address))
                .setEmail(getString(R.string.samoe_vremya_email)).setOpenTo(getString(R.string.samoe_vremya_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.samoe_vremya_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_4_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.samoe_vremya_location_text))
                .build());
    }
}
