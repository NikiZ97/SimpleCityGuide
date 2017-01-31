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

public class BeautySalonsFragment extends ObjectsFragment {

    private ArrayList<YaroslavlObject> objectList;

    public BeautySalonsFragment() {}

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
                .setName("Marmalade nail bar & hair").setDistance(3.2)
                .setLatitude(57.623043).setLongitude(39.890198)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.marmelad)
                .setTabs(R.color.marmelad_tabs).setTheme(R.style.AppTheme_Marmelad)
                .setDescription(getString(R.string.marmelad_description)).setAddress(getString(R.string.marmelad_address))
                .setEmail(getString(R.string.marmelad_email)).setOpenTo(getString(R.string.marmelad_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.marmelad_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("beauty").setLocation(getString(R.string.marmelad_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Ваниль").setDistance(3.0)
                .setLatitude(57.625531).setLongitude(39.882709)
                .setTimeToGo("ехать примерно 25-30 минут").setImage(R.drawable.vanil)
                .setTabs(R.color.vanil_tabs).setTheme(R.style.AppTheme_Vanil)
                .setDescription(getString(R.string.vanil_description)).setAddress(getString(R.string.vanil_address))
                .setEmail(getString(R.string.vanil_email)).setOpenTo(getString(R.string.vanil_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.vanil_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("beauty").setLocation(getString(R.string.vanil_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Ланжель").setDistance(3.2)
                .setLatitude(57.636912).setLongitude(39.882619)
                .setTimeToGo("ехать примерно 15 минут").setImage(R.drawable.langel)
                .setTabs(R.color.lanzhel_tabs).setTheme(R.style.AppTheme_Lanzhel)
                .setDescription(getString(R.string.lanzhel_description)).setAddress(getString(R.string.lanzhel_address))
                .setEmail(getString(R.string.lanzhel_email)).setOpenTo(getString(R.string.lanzhel_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.lanzhel_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("beauty").setLocation(getString(R.string.lanzhel_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Аквамарин").setDistance(3.4)
                .setLatitude(57.628022).setLongitude(39.879046)
                .setTimeToGo("ехать примерно 25-35 минут").setImage(R.drawable.akvamarin)
                .setTabs(R.color.akvamarin_tabs).setTheme(R.style.AppTheme_Akvamarin)
                .setDescription(getString(R.string.akvamarin_description)).setAddress(getString(R.string.akvamarin_address))
                .setEmail(getString(R.string.akvamarin_email)).setOpenTo(getString(R.string.akvamarin_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.akvamarin_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("beauty").setLocation(getString(R.string.akvamarin_location_text))
                .build());
    }
}
