package com.example.doanchuyennganh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh.Activity.DetailAcitivity;
import com.example.doanchuyennganh.Fragment.CartFragment;
import com.example.doanchuyennganh.Model.Category;
import com.example.doanchuyennganh.Model.Order;
import com.example.doanchuyennganh.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Order> orderList;
    Context context;
    public CartAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        //this.context = context;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.itemcart1, parent, false);
        CartAdapter.CartViewHolder viewHolder = new CartAdapter.CartViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txtName.setText(order.getProductName());
        holder.txtPrice.setText(order.getPrice());
        holder.imgCart.setImageResource(R.drawable.icon_home);
    }

    @Override
    public int getItemCount() {
        if(orderList != null){
            return orderList.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtPrice;
        ImageView imgCart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imgCartC);
            txtName = itemView.findViewById(R.id.txtNameC);
            txtPrice = itemView.findViewById(R.id.txtPriceC);
        }
    }
    public void updateOrderList(List<Order> updatedOrderList) {
        this.orderList = updatedOrderList;
        notifyDataSetChanged(); // Thông báo cập nhật dữ liệu cho RecyclerView
    }
}
