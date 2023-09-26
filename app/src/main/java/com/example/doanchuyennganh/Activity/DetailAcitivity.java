package com.example.doanchuyennganh.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanchuyennganh.Model.Category;
import com.example.doanchuyennganh.R;
import com.squareup.picasso.Picasso;

public class DetailAcitivity extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Category category = (Category) bundle.get("obj_category");

        TextView txtNameF = findViewById(R.id.txtName);
        TextView txtPriceF = findViewById(R.id.txtPrice);
        TextView txtmota = findViewById(R.id.txtmotamonan);
        ImageView imgFood = findViewById(R.id.img_food);
        Spinner spinner = findViewById(R.id.SpinSl);

        txtNameF.setText(category.getName());
        txtPriceF.setText(category.getPrice());
        txtmota.setText(category.getDescription());
        Picasso.get().load(category.getImage()).into(imgFood);
        Integer[] sl = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sl);
        spinner.setAdapter(adapter);
    }
}
