package com.example.doanchuyennganh.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh.Adapter.CartAdapter;
import com.example.doanchuyennganh.Adapter.FoodAdapter;
import com.example.doanchuyennganh.Database.Database;
import com.example.doanchuyennganh.Fragment.CartFragment;
import com.example.doanchuyennganh.Login.DangNhap;
import com.example.doanchuyennganh.Model.Category;
import com.example.doanchuyennganh.Model.Order;
import com.example.doanchuyennganh.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailAcitivity extends AppCompatActivity {
    Category currentFood;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        Spinner spinner = findViewById(R.id.SpinSl);
        TextView txtNameF = findViewById(R.id.txtName);
        TextView txtPriceF = findViewById(R.id.txtPrice);
        TextView txtmota = findViewById(R.id.txtmotamonan);
        ImageView imgFood = findViewById(R.id.img_food);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        } else {
            currentFood = (Category) bundle.get("obj_category");
            txtNameF.setText(currentFood.getName());
            txtPriceF.setText(currentFood.getPrice());
            txtmota.setText(currentFood.getDescription());
            Picasso.get().load(currentFood.getImage()).into(imgFood);
            Integer[] sl = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ArrayAdapter<Integer> spinnerAdapter  = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sl);
            spinner.setAdapter(spinnerAdapter );
        }
        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference productsRef = database.getReference("Category");
                final String productKey = productsRef.push().getKey();

                new Database(getBaseContext()).addToCart(new Order(
                        productKey,
                        currentFood.getName(),
                        spinner.getSelectedItem().toString(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));
                List<Order> orders = new Database(getBaseContext()).getCarts();
                for (Order order : orders) {
                    Log.d("CartDebug", "ProductID: " + order.getProductId() + ", ProductName: " + order.getProductName());
                }

                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                //note
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Toast.makeText(DetailAcitivity.this, "Added to Cart !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
