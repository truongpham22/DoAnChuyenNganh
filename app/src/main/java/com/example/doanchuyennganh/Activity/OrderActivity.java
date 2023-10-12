package com.example.doanchuyennganh.Activity;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanchuyennganh.Adapter.OrderAdapter;
import com.example.doanchuyennganh.Model.CurrentUser;
import com.example.doanchuyennganh.Model.Request;
import com.example.doanchuyennganh.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    RecyclerView rcvOrder;
    OrderAdapter orderAdapter;
    List<Request> requestList;
    Request rq;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference requests = database.getReference("Request");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_food);

        rcvOrder= findViewById(R.id.rcvOrder);
        rcvOrder.setHasFixedSize(true);
        rcvOrder.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        requestList = new ArrayList<>();
        orderAdapter = new OrderAdapter(requestList);
        rcvOrder.setAdapter(orderAdapter);
        loadOrder(CurrentUser.currentUser.getPhone());
    }

    private void loadOrder(String phone) {

        // Sử dụng ValueEventListener để lắng nghe dữ liệu từ Firebase
        requests.orderByChild("phone").equalTo(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                requestList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Request request = dataSnapshot.getValue(Request.class);
                    requestList.add(request);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });
    }
}
