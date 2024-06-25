package com.example.restaurantes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.restaurantes.ui.main.SectionsPagerAdapter;
import com.example.restaurantes.databinding.ActivityRestaurantViewBinding;

public class RestaurantView extends AppCompatActivity {

    private ActivityRestaurantViewBinding binding;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRestaurantViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();
        String title = "Restaurante";
        Bundle b = i.getExtras();
        if(b != null){
            title = b.getString("restaurant");
        }
        textView = (TextView) findViewById(R.id.restaurantViewTitle);
        textView.setText(title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    public boolean onOptionsItemSelected(MenuItem item){
       finish();
       return true;
    }
}