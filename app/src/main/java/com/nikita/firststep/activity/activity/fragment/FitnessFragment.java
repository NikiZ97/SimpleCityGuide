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

public class FitnessFragment extends ObjectsFragment {

    private List<YaroslavlObject> objectList = new ArrayList<>();

    public FitnessFragment() {}

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
                .setName("7 корпус").setDistance(0.3)
                .setLatitude(57.621666).setLongitude(39.926066)
                .setTimeToGo("идти примерно 3-4 минуты").setImage(R.drawable.seven)
                .setTabs(R.color.sevenk_tabs).setTheme(R.style.AppTheme_SevenK)
                .setDescription(getString(R.string.sevenk_description)).setAddress(getString(R.string.sevenk_address))
                .setEmail(getString(R.string.sevenk_email)).setOpenTo(getString(R.string.sevenk_open_to))
                .setFab(R.drawable.ic_45).setPhone(getString(R.string.sevenk_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_3_small)
                .setFab3(R.drawable.ic_5_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.sevenk_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Панацея").setDistance(5.0)
                .setLatitude(57.621737).setLongitude(39.879238)
                .setTimeToGo("ехать примерно 40-55 минут").setImage(R.drawable.panacea)
                .setTabs(R.color.panacea_tabs).setTheme(R.style.AppTheme_Panacea)
                .setDescription(getString(R.string.panacea_description)).setAddress(getString(R.string.panacea_address))
                .setEmail(getString(R.string.panacea_email)).setOpenTo(getString(R.string.panacea_open_to))
                .setFab(R.drawable.ic_45).setPhone(getString(R.string.garag_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_5_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.panacea_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Гараж").setDistance(3.4)
                .setLatitude(57.620261).setLongitude(39.852449)
                .setTimeToGo("ехать примерно 40-50 минут").setImage(R.drawable.garage)
                .setTabs(R.color.garag_tabs).setTheme(R.style.AppTheme_Garag)
                .setDescription(getString(R.string.garag_description)).setAddress(getString(R.string.garag_address))
                .setEmail(getString(R.string.garag_email)).setOpenTo(getString(R.string.garag_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.garag_phone_number))
                .setFab1(R.drawable.ic_4_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.garag_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("5 звезд").setDistance(5.1)
                .setLatitude(57.625489).setLongitude(39.850174)
                .setTimeToGo("ехать примерно 30-40 минут").setImage(R.drawable.five_star)
                .setTabs(R.color.fivestar_tabs).setTheme(R.style.AppTheme_FiveStar)
                .setDescription(getString(R.string.fivestar_description)).setAddress(getString(R.string.fivestar_address))
                .setEmail(getString(R.string.fivestar_email)).setOpenTo(getString(R.string.fivestar_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.fivestar_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_3_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.lifestyle_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Стиль Жизни").setDistance(3.1)
                .setLatitude(57.623879).setLongitude(39.883993)
                .setTimeToGo("ехать примерно 30-35 минут").setImage(R.drawable.stil_zhizni)
                .setTabs(R.color.lifestyle_tabs).setTheme(R.style.AppTheme_LifeStyle)
                .setDescription(getString(R.string.lifestyle_description)).setAddress(getString(R.string.lifestyle_address))
                .setEmail(getString(R.string.lifestyle_email)).setOpenTo(getString(R.string.lifestyle_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.lifestyle_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_5_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_3_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.lifestyle_location_text))
                .build());
        objectList.add(YaroslavlObject.newBuilder()
                .setName("Sport Line").setDistance(4.8)
                .setLatitude(57.631493).setLongitude(39.858442)
                .setTimeToGo("ехать примерно 30-40 минут").setImage(R.drawable.sport_line)
                .setTabs(R.color.sportline_tabs).setTheme(R.style.AppTheme_SportLine)
                .setDescription(getString(R.string.sportline_description)).setAddress(getString(R.string.sportline_address))
                .setEmail(getString(R.string.sportline_email)).setOpenTo(getString(R.string.sportline_open_to))
                .setFab(R.drawable.ic_43).setPhone(getString(R.string.sportline_phone_number))
                .setFab1(R.drawable.ic_5_small).setFab2(R.drawable.ic_4_small)
                .setFab3(R.drawable.ic_4_small).setFab4(R.drawable.ic_4_small).setMarkCount(4)
                .setCategory("fitness").setLocation(getString(R.string.sportline_location_text))
                .build());
    }
}
