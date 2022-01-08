package com.example.weatherapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.Adapter.ForecastAdapter;
import com.example.weatherapp.Model.ForecastModel;
import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailWeatherActivity extends AppCompatActivity {
    private TextView weatherCity, weatherDate, weatherTempreture;
    private ImageView weatherIcon;
    private String weatherCityText, weatherDateText,weatherTempretureText,weatherIconText;
    private RecyclerView forecastRecyclerView;
    private RequestQueue requestQueue;
    private String apiid = "2495c71008af233275487efb062176f8";
    private String url;
    private List<ForecastModel> forecastModelList;
    private ForecastAdapter forecastAdapter;
    private SimpleDateFormat input,outputDay,outputDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);
        weatherCity = (TextView) findViewById(R.id.weather_city_name);
        weatherTempreture = (TextView) findViewById(R.id.weather_temp);
        weatherDate = (TextView) findViewById(R.id.weather_date);
        weatherIcon = (ImageView) findViewById(R.id.weather_image);
        forecastRecyclerView = (RecyclerView) findViewById(R.id.forecast_recycler_view);
        weatherCityText = getIntent().getExtras().get("CITY_NAME").toString();
        weatherCity.setText(weatherCityText);
        requestQueue = Volley.newRequestQueue(this);
        url = "https://api.openweathermap.org/data/2.5/forecast?q=" + weatherCityText + "&appid=" + apiid;
        input = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        outputDate = new SimpleDateFormat("yyyy-MM-dd");
        outputDay = new SimpleDateFormat("EEEE");
        LinearLayoutManager layoutManager=new LinearLayoutManager(DetailWeatherActivity.this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        forecastRecyclerView.setLayoutManager(layoutManager);
        forecastModelList=new ArrayList<>();
        Fetch5DyasForcast(url);
    }

    private void Fetch5DyasForcast(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    forecastModelList.clear();
                    JSONArray fiveDaysForecastArray = response.getJSONArray("list");
                    JSONObject object = fiveDaysForecastArray.getJSONObject(0);

                    //getting current Tempreture
                    JSONObject mainObject=object.getJSONObject("main");
                    weatherTempretureText = String.valueOf(Double.parseDouble(mainObject.getString("temp")) - 273.15).substring(0,4);
                    weatherTempreture.setText(weatherTempretureText+"°C");
                    //getting current Tempreture

                    //getting date with day
                    String date_txt = object.getString("dt_txt");
                    String date=outputDate.format(input.parse(date_txt));
                    String day=outputDay.format(input.parse(date_txt));
                    weatherDateText=day+", "+date;
                    weatherDate.setText(weatherDateText);
                    //getting date with day

                    //getting icon
                    JSONObject iconObject=object.getJSONArray("weather").getJSONObject(0);
                    String iconCode=iconObject.getString("icon");
                    weatherIconText="http://openweathermap.org/img/w/"+iconCode+".png";
                    Picasso.get().load(weatherIconText).placeholder(R.drawable.weather).into(weatherIcon);
                    //getting icon

                    //getting 5 Days forecast
                    for(int i=0;i<fiveDaysForecastArray.length();i++){
                        JSONArray forecastArray=response.getJSONArray("list");
                        JSONObject forecastObject=forecastArray.getJSONObject(i);
                        String forecast_temp_min=forecastObject.getJSONObject("main").getString("temp_min");
                        String forecast_temp_max=forecastObject.getJSONObject("main").getString("temp_max");
                        String forecast_date_txt=forecastObject.getString("dt_txt");
                        String forecast_date=outputDate.format(input.parse(forecast_date_txt));
                        String forecast_day=outputDay.format(input.parse(forecast_date_txt));
                        String forecast_icon="http://openweathermap.org/img/w/"+forecastObject.getJSONArray("weather").getJSONObject(0).getString("icon")+".png";
                        Log.d("tushar",forecast_day+forecast_icon+forecast_temp_min+forecast_temp_max+forecast_date);
//                        ForecastModel model=new ForecastModel();
//                        model.setForecastDay(forecast_day);
//                        model.setForecastIcon(forecast_icon);
//                        model.setForecastMinTemp(forecast_temp_min);
//                        model.setForecastMaxTemp(forecast_temp_max);
//                        model.setForecastDate(forecast_date);
                        forecastModelList.add(new ForecastModel(forecast_day,forecast_icon,forecast_temp_min,forecast_temp_max,forecast_date));
                        forecastAdapter=new ForecastAdapter(DetailWeatherActivity.this,forecastModelList);
                        forecastRecyclerView.setAdapter(forecastAdapter);
                        forecastAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(DetailWeatherActivity.this, "Weather Fetch Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailWeatherActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}