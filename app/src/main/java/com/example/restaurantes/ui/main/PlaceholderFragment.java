package com.example.restaurantes.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurantes.ItemDetail;
import com.example.restaurantes.MenuItem;
import com.example.restaurantes.R;
import com.example.restaurantes.RestaurantView;
import com.example.restaurantes.databinding.FragmentRestaurantViewBinding;

import java.util.ArrayList;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.FakerConfig;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentRestaurantViewBinding binding;

    private ArrayList<String> arrayList;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentRestaurantViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.tabList;
        arrayList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        pageViewModel.getText().observe(getViewLifecycleOwner(),
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String menuList) {
                        int nItems = 10;
                        FakerConfig config = FakerConfig.builder()
                                .withLocale("es-MX")
                                .build();
                        Faker faker = new Faker(config);
                        arrayList.clear();
                        for (int i = 0; i < nItems; i++) {

                            arrayList.add(menuList + " " + faker.getAddress().community());
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(requireContext(), ItemDetail.class);
                appInfo.putExtra("itemName", arrayList.get(position));
                startActivity(appInfo);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}