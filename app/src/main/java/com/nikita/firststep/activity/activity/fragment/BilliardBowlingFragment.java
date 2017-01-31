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

public class BilliardBowlingFragment extends ObjectsFragment {

    private ArrayList<YaroslavlObject> mBilbowList;

    public BilliardBowlingFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareObjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_list);
        ShopsAdapter adapter = new ShopsAdapter(getActivity(), mBilbowList);
        RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        YaroslavlObject object = mBilbowList.get(position);
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
        mBilbowList = new ArrayList<>();
        mBilbowList.add(YaroslavlObject.newBuilder()
                .setName("Во Флагмане").setDistance(3.4)
                .setLatitude(57.636912).setLongitude(39.882619)
                .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.vo_flagmane)
                .setTabs(R.color.vo_flagmane_tabs).setTheme(R.style.AppTheme_VoFlagmane)
                .setDescription(getString(R.string.vo_flagmane_description)).setAddress(getString(R.string.vo_flagmane_address))
                .setEmail(getString(R.string.vo_flagmane_email)).setOpenTo(getString(R.string.vo_flagmane_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.lenta_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_5_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.flagman_location_text))
                .build());
        mBilbowList.add(YaroslavlObject.newBuilder()
                .setName("Космик").setDistance(3.8)
                .setLatitude(57.628080).setLongitude(39.870029)
                .setTimeToGo("ехать примерно 40 минут").setImage(R.drawable.kosmik)
                .setTabs(R.color.kosmik_tabs).setTheme(R.style.AppTheme_Kosmik)
                .setDescription(getString(R.string.kosmik_description)).setAddress(getString(R.string.kosmik_address))
                .setEmail(getString(R.string.kosmik_email)).setOpenTo(getString(R.string.kosmik_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.lenta_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setMarkCount(0)
                .setCategory(null).setLocation(getString(R.string.aura_location_text))
                .build());
    }
}
