package com.example.weatherapp.data.repository

sealed class NetworkResponse <out T> {

    data class  Success<out T>(val data : T) : NetworkResponse<T>()


        object  Loading : NetworkResponse<Nothing>()


    data class Error (val message : String ) : NetworkResponse<Nothing>()
}