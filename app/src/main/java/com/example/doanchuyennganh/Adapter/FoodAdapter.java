package com.example.doanchuyennganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh.Activity.DetailAcitivity;
import com.example.doanchuyennganh.Model.Category;
import com.example.doanchuyennganh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Category> categoryList;
    Context context;
    public FoodAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_food,parent,false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Category category = categoryList.get(position);
        Picasso.get().load(category.getImage()).into(holder.imgFood);
        holder.txtNfood.setText(category.getName());
        holder.txtPrice.setText(category.getPrice());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(category);
            }

            private void onClickGoToDetail(Category category) {
                Intent intent = new Intent(context, DetailAcitivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_category", category);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public void reLease(){
        context = null;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView txtNfood, txtPrice;
        ImageView imgFood;
        CardView layout_item;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = (CardView) itemView.findViewById(R.id.layout_item);
            imgFood = itemView.findViewById(R.id.img_Food);
            txtNfood = itemView.findViewById(R.id.txtNfood);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
