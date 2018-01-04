package com.project.roy.cobabelajar.ui.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.roy.cobabelajar.R;
import com.project.roy.cobabelajar.data.model.Car;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roy on 1/2/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CarViewHolder>{

    private Context context;
    private List<Car> carList;
    ListGalleryPresenter presenter;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Car> carList){
        this.carList = carList;
        notifyDataSetChanged();
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        final Car car = carList.get(position);
        presenter = new ListGalleryPresenter((ListGalleryView) context);
        holder.tvCarName.setText(car.getMake());
        Picasso.with(context).load(car.getImgUrl()).into(holder.imgAvatar);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteCar(car.getId());
                presenter.showListGallery();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (carList == null) return 0;
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        TextView tvCarName;
        ImageView imgAvatar;
        ImageView imgDelete;

        public CarViewHolder(View itemView) {
            super(itemView);
            tvCarName = itemView.findViewById(R.id.tv_carName);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            imgDelete = itemView.findViewById(R.id.delete_car);
        }
    }
}
