package com.example.webapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {

    private Context context;
    private List<FlowerMainResponse> flowerResponseList;

    public FlowerAdapter(Context context, List<FlowerMainResponse> flowerResponseList) {
        this.context = context;
        this.flowerResponseList = flowerResponseList;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FlowerViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.flower_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int i) {
        FlowerMainResponse flowerResponse = flowerResponseList.get(i);
        holder.nameTV.setText(flowerResponse.getName());
        holder.priceTV.setText(String.valueOf(flowerResponse.getPrice()));
        Picasso
                .get()
                .load("http://services.hanselandpetal.com/photos/"+flowerResponse.getPhoto())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return flowerResponseList.size();
    }

    class FlowerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, priceTV;
        ImageView imageView;
        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.row_flowerName);
            priceTV = itemView.findViewById(R.id.row_flowerPrice);
            imageView = itemView.findViewById(R.id.row_flowerImage);
        }
    }
}
