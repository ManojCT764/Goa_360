package com.example.goa360;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Place_RecyclerViewAdapter extends RecyclerView.Adapter<Place_RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<placeModel> placeModels;
    public Place_RecyclerViewAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<placeModel> placeModels){
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.placeModels = placeModels;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is ehere we inflate the Layout (Give look to the Rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent ,false);

        return new MyViewHolder(view , recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //assigning values to the views where we created in recycler_view_row layout file
        //based on the position of the recycler view
        holder.tvPlace.setText(placeModels.get(position).getPlaceName());
        holder.imageView.setImageResource(placeModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //here the recycler view just wants to know how many number of items we want to be displayed
        return placeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvPlace;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvPlace = itemView.findViewById(R.id.textView3);

            //seting onclick listner
            itemView.setOnClickListener(v -> {
                if(recyclerViewInterface != null ){
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            });
        }
    }
}
