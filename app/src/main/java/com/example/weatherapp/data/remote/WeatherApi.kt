package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/current.json")
    suspend fun getWeather(@Query("key") apikey : String, @Query("q")city : String) : Response<Weather>

}