package com.example.weatherapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Model.ForecastModel;
import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.Viewholder> {
    private Context context;
    private List<ForecastModel> forecastModelList;

    public ForecastAdapter(Context context, List<ForecastModel> forecastModelList) {
        this.context = context;
        this.forecastModelList = forecastModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.weather_forecast_layout,parent,false);
        return new ForecastAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String day=forecastModelList.get(position).getForecastDay();
        String minTemp=forecastModelList.get(position).getForecastMinTemp();
        String maxTemp=forecastModelList.get(position).getForecastMaxTemp();
        String date=forecastModelList.get(position).getForecastDate();
        String icon=forecastModelList.get(position).getForecastIcon();
        holder.setData(day,minTemp,maxTemp,date,icon);
    }

    @Override
    public int getItemCount() {
        return forecastModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView forecastDay;
        private TextView forecastMinTemp;
        private TextView forecastMaxTemp;
        private TextView forecastDate;
        private ImageView forecastIcon;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            forecastDay=(TextView) itemView.findViewById(R.id.weather_forecast_day);
            forecastMinTemp=(TextView) itemView.findViewById(R.id.weather_forecast_min_temp);
            forecastMaxTemp=(TextView) itemView.findViewById(R.id.weather_forecast_max_temp);
            forecastDate=(TextView) itemView.findViewById(R.id.weather_forecast_date);
            forecastIcon=(ImageView) itemView.findViewById(R.id.weather_forecast_icon);
        }
        public void setData(String day,String minTemp,String maxTemp,String date,String icon){
            forecastDay.setText(day);
            forecastMinTemp.setText(minTemp+"°C");
            forecastMaxTemp.setText(maxTemp+"°C");
            forecastDate.setText(date);
            Picasso.get().load(icon).placeholder(R.drawable.weather).into(forecastIcon);
        }
    }
}
