package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Constant
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.Weather
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<Weather>>()

    val weatherResult : LiveData<NetworkResponse<Weather>> = _weatherResult

    fun getData(city: String) {

        _weatherResult.value= NetworkResponse.Loading

        viewModelScope.launch {
            try {
                // Call the suspend function inside the coroutine
                val weatherResponse = weatherApi.getWeather(Constant.ApiKey, city)
                if (weatherResponse.isSuccessful){
                    weatherResponse.body()?.let{_weatherResult.value = NetworkResponse.Success(it)}




                    Log.d("WeatherViewModelResponse", "Weather Data: $weatherResponse")
                }else{

                   _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }


            } catch (e: Exception) {
                // Handle exceptions (e.g., network errors)
                Log.e("WeatherViewModel", "Error fetching weather data: ${e.message}")
            }
        }
    }
}