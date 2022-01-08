package com.example.weatherapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.weatherapp.Adapter.CityAdapter;
import com.example.weatherapp.Model.CityModel;
import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cityRecyclerView;
    private List<CityModel> cityModelList;
    private CityAdapter cityAdapter;
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