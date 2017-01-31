package com.nikita.firststep.activity.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikita.firststep.activity.activity.other.PlaceItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nikita.myappfirststep.R;

/**
 * Created by nikita on 09.11.16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private ArrayList<PlaceItem> placeItems;
    private Context context;

    public DataAdapter(ArrayList<PlaceItem> placeItems, Context context) {
        this.placeItems = placeItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvPlace.setText(placeItems.get(position).getPlaceName());
        Picasso.with(context).load(placeItems.get(position).getImageUrl()).resize(120, 60)
                .into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {
        return placeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlace;
        private ImageView imageUrl;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvPlace = (TextView) itemView.findViewById(R.id.tv_android);
            imageUrl = (ImageView) itemView.findViewById(R.id.img_android);
        }
    }
}
