package com.nikita.firststep.activity.activity.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nikita.firststep.activity.activity.other.YaroslavlObject;

import java.util.ArrayList;
import java.util.List;

import nikita.myappfirststep.R;

/**
 * Created by nikita on 14.11.16.
 */
public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.MyViewHolder> {

    private Context mContext;
    private List<YaroslavlObject> yaroslavlObjectList;

    public ShopsAdapter(Context mContext, List<YaroslavlObject> yaroslavlObjectList) {
        this.mContext = mContext;
        this.yaroslavlObjectList = yaroslavlObjectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YaroslavlObject yaroslavlObject = yaroslavlObjectList.get(position);
        holder.title.setText(yaroslavlObject.getName());
        holder.distance.setText(yaroslavlObject.getDistance() + " км");

        Glide.with(mContext).load(yaroslavlObject.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return yaroslavlObjectList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, distance;
        ImageView image;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            distance = (TextView) itemView.findViewById(R.id.distance);
            image = (ImageView) itemView.findViewById(R.id.thumbnail);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public void setFilter(ArrayList<YaroslavlObject> newList) {
        yaroslavlObjectList = new ArrayList<>();
        yaroslavlObjectList.addAll(newList);
        notifyDataSetChanged();
    }
}
