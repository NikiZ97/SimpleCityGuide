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

    private final String mPlaceNames[] = {
            "Продуктовые магазины",
            "Торговые центры",
            "Развлечения",
            "Ночная жизнь",
            "Красота и здоровье",
            "Спорт",
    };

    private final String mImageUrls[] = {
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
                                intent.putExtra("layout", R.layout.activity_shops)
                                        .putExtra("toolbar", R.id.shops_toolbar)
                                        .putExtra("collapse", R.id.shops_collapsing_toolbar)
                                        .putExtra("title", "Магазины");
                                startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(getActivity(), CategoryActivity.class);
                                intent.putExtra("layout", R.layout.activity_shop_centers)
                                        .putExtra("toolbar", R.id.shops_centers_toolbar)
                                        .putExtra("collapse", R.id.shop_centers_collapsing_toolbar)
                                        .putExtra("title", "Торговые центры");
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent.putExtra("layout", R.layout.activity_fun)
                                        .putExtra("toolbar", R.id.fun_toolbar)
                                        .putExtra("title", "Развлечения")
                                        .putExtra("title1", "Кинотеатры")
                                        .putExtra("title2", "Бильярд/боулинг")
                                        .putExtra("title3", "Антикафе");
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent.putExtra("layout", R.layout.activity_night_life)
                                        .putExtra("toolbar", R.id.night_life_toolbar)
                                        .putExtra("title", "Ночная жизнь")
                                        .putExtra("title1", "Клубы")
                                        .putExtra("title2", "Бары")
                                        .putExtra("title3", "Кальянные");
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(getActivity(), CategoryWithViewPagerActivity.class);
                                intent.putExtra("layout", R.layout.activity_beauty_and_health)
                                        .putExtra("toolbar", R.id.beauty_and_health_toolbar)
                                        .putExtra("title", "Красота и здоровье")
                                        .putExtra("title1", "Бани/сауны")
                                        .putExtra("title2", "Салоны красоты")
                                        .putExtra("title3", "Парикмахерские");
                                startActivity(intent);
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
        for (int i = 0; i < mPlaceNames.length; i++) {
            PlaceItem itemPlace = new PlaceItem();
            itemPlace.setPlaceName(mPlaceNames[i]);
            itemPlace.setImageUrl(mImageUrls[i]);
            placeItem.add(itemPlace);
        }
        return placeItem;
    }
}
