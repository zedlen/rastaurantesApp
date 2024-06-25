package com.example.restaurantes.ui.main;

import static androidx.lifecycle.Transformations.map;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantes.MenuItem;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.FakerConfig;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String> mIndex = new MutableLiveData<>();
    private LiveData<String> mMenuList = map(mIndex, input -> {
        switch (input) {
            case "1":
                return "Comida";
            case "2":
                return "Bebida";
            case "3":
                return "Complemento";
            default:
                return "";
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(String.valueOf(index));
    }

    public LiveData<String> getText() {
        return mMenuList;
    }
}
