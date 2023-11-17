package com.example.doanchuyennganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh.Activity.DetailAcitivity;
import com.example.doanchuyennganh.Activity.OrderDetail;
import com.example.doanchuyennganh.Model.CurrentUser;
import com.example.doanchuyennganh.Model.Request;
import com.example.doanchuyennganh.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    List<Request> requests;
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Request");

    public OrderAdapter(List<Request> requests) {
        this.requests = requests;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_orders, parent, false);
        OrderAdapter.OrderViewHolder viewHolder = new OrderViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Request request = requests.get(position);
        String requestKey = request.getKey();
        holder.txtKey.setText(requestKey);
        holder.txtStatus.setText(convertStatus(request.getStatus()));
        holder.txtPhone.setText(request.getPhone());
        holder.txtAddress.setText(request.getAddress());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailOrder(request);
            }

            private void goToDetailOrder(Request request) {
                Intent intent = new Intent(context, OrderDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_request", request);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (requests != null) {
            return requests.size();
        } else {
            return 0;
        }
    }
    private String convertStatus(String status){
        if (status.equals("0"))
            return "Đã nhận";
        else if (status.equals("1"))
            return "Đang giao";
        else
            return "Đã giao";
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder{
        public TextView txtKey, txtAddress, txtStatus, txtPhone;
        public CardView cardView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.layout_cart);
            txtKey = itemView.findViewById(R.id.txtKeyO);
            txtAddress = itemView.findViewById(R.id.txtAddressO);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtPhone = itemView.findViewById(R.id.txtPhoneO);
        }
    }
}
