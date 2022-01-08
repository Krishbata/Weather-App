package com.example.weatherapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Activity.DetailWeatherActivity;
import com.example.weatherapp.Model.CityModel;
import com.example.weatherapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.Viewholder> {
    private Context context;
    private List<CityModel> cityModelList;

    public CityAdapter(Context context, List<CityModel> cityModelList) {
        this.context = context;
        this.cityModelList = cityModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.city_name_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        String cityNameText=cityModelList.get(position).getCityName();
        holder.setData(cityNameText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName=cityModelList.get(position).getCityName();
                GoToDetailWeatherActivity(context,cityName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView cityName;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cityName=(TextView)itemView.findViewById(R.id.city_name);
        }
        private void setData(String cityNameText){
            cityName.setText(cityNameText);
        }
    }

    private void GoToDetailWeatherActivity(Context context, String cityName) {
        Intent detailIntent=new Intent(context, DetailWeatherActivity.class);
        detailIntent.putExtra("CITY_NAME",cityName);
        context.startActivity(detailIntent);
    }

}
