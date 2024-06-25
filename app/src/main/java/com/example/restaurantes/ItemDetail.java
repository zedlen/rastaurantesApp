package com.example.restaurantes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restaurantes.databinding.ActivityItemDetailBinding;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.FakerConfig;

public class ItemDetail extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityItemDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        FakerConfig config = FakerConfig.builder()
                .withLocale("es-MX")
                .build();
        Faker faker = new Faker(config);

        TextView price = (TextView) findViewById(R.id.itemPrice);
        price.setText(faker.getMoney().amount());

        String title = "";
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null){
            title = b.getString("restaurant");
        }
        TextView name = (TextView) findViewById(R.id.itemPrice);
        name.setText(title);
        TextView header = (TextView) findViewById(R.id.itemDetailTitle);
        header.setText(title);

        TextView description = (TextView) findViewById(R.id.itemDetailTitle);
        description.setText(faker.getString().toString());


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_item_detail);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}