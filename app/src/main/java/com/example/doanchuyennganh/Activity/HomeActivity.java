package com.example.doanchuyennganh.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.doanchuyennganh.Adapter.ViewPager2Adapter;
import com.example.doanchuyennganh.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    public enum NavigationItem{
        HOME(R.id.nav_home),
        CART(R.id.nav_cart),
        INFO(R.id.nav_info);

        private final int id;

        NavigationItem(int id){
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ viewpager2
        ViewPager2 viewPager2 =findViewById(R.id.viewPage2);
        //set adapter cho viewpager2
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);

        //xử lý sự kiện khi vào bottomNav
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                NavigationItem selectItem = null;
                for (NavigationItem navigationItem : NavigationItem.values())
                    if (navigationItem.getId() == item.getItemId()) {
                        selectItem = navigationItem;
                        break;
                    }
                switch (selectItem){
                    case HOME:
                        viewPager2.setCurrentItem(0);
                        break;
                    case CART:
                        viewPager2.setCurrentItem(1);
                        break;
                    case INFO:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.nav_cart).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.nav_info).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
    }
}