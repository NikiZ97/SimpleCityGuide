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

public class CinemaFragment extends ObjectsFragment {


    private ArrayList<YaroslavlObject> mObjectList;

    public CinemaFragment() {
    }

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
        ShopsAdapter adapter = new ShopsAdapter(getActivity(), mObjectList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
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
                .setName("Родина").setDistance(3.4)
                .setLatitude(57.636703).setLongitude(39.882598)
                .setTimeToGo("ехать примерно 15-20 минут").setImage(R.drawable.rodina)
                .setTabs(R.color.rodina_tabs).setTheme(R.style.AppTheme_Rodina)
                .setDescription(getString(R.string.rodina_description)).setAddress(getString(R.string.rodina_address))
                .setEmail(getString(R.string.rodina_email)).setOpenTo(getString(R.string.rodina_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.rodina_phone_number))
                .setFab1(R.drawable.ic_3_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("cinema").setLocation(getString(R.string.rodina_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Киномакс").setDistance(3.8)
                .setLatitude(57.628080).setLongitude(39.870029)
                .setTimeToGo("ехать примерно 40 минут").setImage(R.drawable.kinomax)
                .setTabs(R.color.kinomax_tabs).setTheme(R.style.AppTheme_Kinomax)
                .setDescription(getString(R.string.kinomax_description)).setAddress(getString(R.string.kinomax_address))
                .setEmail(getString(R.string.kinomax_email)).setOpenTo(getString(R.string.kinomax_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.kinomax_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_3_small).setMarkCount(4)
                .setCategory("cinema").setLocation(getString(R.string.aura_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Киноформат").setDistance(4.3)
                .setLatitude(57.645474).setLongitude(39.953338)
                .setTimeToGo("ехать примерно 17-20 минут").setImage(R.drawable.kinoformat)
                .setTabs(R.color.kinoformat_tabs).setTheme(R.style.AppTheme_Kinoformat)
                .setDescription(getString(R.string.kinoformat_description)).setAddress(getString(R.string.kinoformat_address))
                .setEmail(getString(R.string.kinoformat_email)).setOpenTo(getString(R.string.kinoformat_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.kinoformat_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("cinema").setLocation(getString(R.string.yarkiy_location_text))
                .build());
        mObjectList.add(YaroslavlObject.newBuilder()
                .setName("Синема-Стар").setDistance(7.8)
                .setLatitude(57.670333).setLongitude(39.838551)
                .setTimeToGo("ехать примерно 40-45 минут").setImage(R.drawable.cinema_star)
                .setTabs(R.color.cinema_star_tabs).setTheme(R.style.AppTheme_CinemaStar)
                .setDescription(getString(R.string.cinema_star_description)).setAddress(getString(R.string.cinema_star_address))
                .setEmail(getString(R.string.cinema_star_email)).setOpenTo(getString(R.string.cinema_star_open_to))
                .setFab(R.drawable.ic_4_big).setPhone(getString(R.string.cinema_star_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_3_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("cinema").setLocation(getString(R.string.cinema_star_location_text))
                .build());
    }
}
