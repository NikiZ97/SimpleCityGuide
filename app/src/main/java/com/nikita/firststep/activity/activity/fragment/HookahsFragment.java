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

public class HookahsFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();

    public HookahsFragment() {}

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
                .setName("Аладдин").setDistance(4.3)
                .setLatitude(57.622292).setLongitude(39.883566)
                .setTimeToGo("ехать примерно 30-40 минут").setImage(R.drawable.alladin)
                .setTabs(R.color.aladdin_tabs).setTheme(R.style.AppTheme_Aladdin)
                .setDescription(getString(R.string.aladdin_description)).setAddress(getString(R.string.aladdin_address))
                .setEmail(getString(R.string.aladdin_email)).setOpenTo(getString(R.string.aladdin_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.aladdin_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("hookah").setLocation(getString(R.string.aladdin_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("ДК ХЗ").setDistance(4.1)
                .setLatitude(57.626806).setLongitude(39.886006)
                .setTimeToGo("ехать примерно 25-35 минут").setImage(R.drawable.dk_hz)
                .setTabs(R.color.dkhz_tabs).setTheme(R.style.AppTheme_DKHZ)
                .setDescription(getString(R.string.dkhz_description)).setAddress(getString(R.string.dkhz_address))
                .setEmail(getString(R.string.dkhz_email)).setOpenTo(getString(R.string.dkhz_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.dkhz_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("hookah").setLocation(getString(R.string.dkhz_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Smoke Lab").setDistance(4.0)
                .setLatitude(57.625976).setLongitude(39.889557)
                .setTimeToGo("ехать примерно 25-35 минут").setImage(R.drawable.smoke_lab)
                .setTabs(R.color.smokelab_tabs).setTheme(R.style.AppTheme_SmokeLab)
                .setDescription(getString(R.string.smokelab_description)).setAddress(getString(R.string.smokelab_address))
                .setEmail(getString(R.string.smokelab_email)).setOpenTo(getString(R.string.smokelab_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.dkhz_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("hookah").setLocation(getString(R.string.smokelab_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Лес").setDistance(3.8)
                .setLatitude(57.626745).setLongitude(39.874376)
                .setTimeToGo("ехать примерно 25-30 минут").setImage(R.drawable.les)
                .setTabs(R.color.les_tabs).setTheme(R.style.AppTheme_Les)
                .setDescription(getString(R.string.les_description)).setAddress(getString(R.string.les_address))
                .setEmail(getString(R.string.les_email)).setOpenTo(getString(R.string.les_open_to))
                .setFab(R.drawable.ic_47).setPhone(getString(R.string.dkhz_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("hookah").setLocation(getString(R.string.les_location_text))
                .build());
    }
}
