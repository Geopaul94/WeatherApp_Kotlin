package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
 //   https://api.weatherapi.com/?key=beb1a541e5c1407e8f860431250604&q=London&aqi=no

    @GET("v1/current.json")
    suspend fun getWeather(@Query("key") apikey : String, @Query("q")city : String) : Response<Weather>

}