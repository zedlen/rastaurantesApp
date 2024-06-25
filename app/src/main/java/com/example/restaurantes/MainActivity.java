package com.example.restaurantes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.google.android.material.tabs.TabLayout;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.FakerConfig;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.restaurantsList);
                FakerConfig config = FakerConfig.builder()
                .withLocale("es-MX")
                .build();
        Faker faker = new Faker(config);
        String items[] = new String[10];

        for (int i = 0; i < items.length; i++) {
            items[i] = faker.getName().firstName() + ' ' + faker.getAddress().state();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(getApplicationContext(), RestaurantView.class);
                appInfo.putExtra("restaurant", items[position]);
                startActivity(appInfo);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
