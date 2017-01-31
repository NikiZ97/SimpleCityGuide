package com.nikita.firststep.activity.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikita.firststep.activity.activity.activity.MainMenu.CategoryActivity;
import com.nikita.firststep.activity.activity.activity.MainMenu.CategorySportActivity;
import com.nikita.firststep.activity.activity.activity.MainMenu.CategoryWithViewPagerActivity;
import com.nikita.firststep.activity.activity.adapter.DataAdapter;
import com.nikita.firststep.activity.activity.other.PlaceItem;
import com.nikita.firststep.activity.activity.other.RecyclerItemClickListener;

import java.util.ArrayList;

import nikita.myappfirststep.R;

public class HomeFragment extends Fragment {

    private final String placeNames[] = {
            "Продуктовые магазины",
            "Торговые центры",
            "Развлечения",
            "Ночная жизнь",
            "Красота и здоровье",
            "Спорт",
    };

    private final String imageUrls[] = {
            "https://i.ytimg.com/vi/5Wq44KiEiyM/maxresdefault.jpg",
            "https://i.ytimg.com/vi/YnlYs_QwUY0/maxresdefault.jpg",
            "http://gorodskidok48.ru/upload/medialibrary/fe9/fe90accfc6d7d646efac41a298101177.jpg",
            "https://freelance.ru/img/portfolio/pics/00/0D/11/856491.jpg",
            "http://contental.ru/wp-content/uploads/2013/09/zdorovije-frilansera-kopirajtera-sovety1.jpg",
            "http://oboitut.com/uploads/posts/2015-04/thumbs/oboitut.com_1429089239.jpg"
    };

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // add a click listener to items of recycler view
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                                intent.putExtra("layout", R.layout.activity_shops);
                                intent.putExtra("toolbar", R.id.shops_toolbar);
                                intent.putExtra("collapse", R.id.shops_collapsing_toolbar);
                                intent.putExtra("title", "Магазины");
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), CategoryActivity.class);
                                intent2.putExtra("layout", R.layout.activity_shop_centers);
                                intent2.putExtra("toolbar", R.id.shops_centers_toolbar);
                                intent2.putExtra("collapse", R.id.shop_centers_collapsing_toolbar);
                                intent2.putExtra("title", "Торговые центры");
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent3.putExtra("layout", R.layout.activity_fun);
                                intent3.putExtra("toolbar", R.id.fun_toolbar);
                                intent3.putExtra("title", "Развлечения");
                                intent3.putExtra("title1", "Кинотеатры");
                                intent3.putExtra("title2", "Бильярд/боулинг");
                                intent3.putExtra("title3", "Антикафе");
                                startActivity(intent3);
                                break;
                            case 3:
                                Intent intent4 = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent4.putExtra("layout", R.layout.activity_night_life);
                                intent4.putExtra("toolbar", R.id.night_life_toolbar);
                                intent4.putExtra("title", "Ночная жизнь");
                                intent4.putExtra("title1", "Клубы");
                                intent4.putExtra("title2", "Бары");
                                intent4.putExtra("title3", "Кальянные");
                                startActivity(intent4);
                                break;
                            case 4:
                                Intent intent5 = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent5.putExtra("layout", R.layout.activity_beauty_and_health);
                                intent5.putExtra("toolbar", R.id.beauty_and_health_toolbar);
                                intent5.putExtra("title", "Красота и здоровье");
                                intent5.putExtra("title1", "Бани/сауны");
                                intent5.putExtra("title2", "Салоны красоты");
                                intent5.putExtra("title3", "Парикмахерские");
                                startActivity(intent5);
                                break;
                            case 5:
                                startActivity(new Intent(getActivity(), CategorySportActivity.class));
                                break;
                            default: break;
                        }
                    }
                })
        );

        ArrayList placeItems = prepareData();
        DataAdapter adapter = new DataAdapter(placeItems, getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private ArrayList prepareData() {
        ArrayList placeItem = new ArrayList<>();
        for (int i = 0; i < placeNames.length; i++) {
            PlaceItem itemPlace = new PlaceItem();
            itemPlace.setPlaceName(placeNames[i]);
            itemPlace.setImageUrl(imageUrls[i]);
            placeItem.add(itemPlace);
        }
        return placeItem;
    }
}
