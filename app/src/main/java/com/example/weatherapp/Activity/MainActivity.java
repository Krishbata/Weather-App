package com.example.weatherapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.Adapter.CityAdapter;
import com.example.weatherapp.Model.CityModel;
import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cityRecyclerView;
    private List<CityModel> cityModelList;
    private CityAdapter cityAdapter;
    private int PERMISSION_CODE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityRecyclerView=(RecyclerView)findViewById(R.id.city_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cityRecyclerView.setLayoutManager(layoutManager);
        cityModelList=new ArrayList<>();
        cityModelList.add(new CityModel("New York City"));
        cityModelList.add(new CityModel("London"));
        cityModelList.add(new CityModel("New Delhi"));
        cityModelList.add(new CityModel("Tokyo"));
        cityModelList.add(new CityModel("Mexico City"));
        cityAdapter=new CityAdapter(MainActivity.this,cityModelList);
        cityRecyclerView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();
    }
}