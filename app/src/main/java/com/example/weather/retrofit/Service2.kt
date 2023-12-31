package com.example.weather.retrofit

import com.example.weather.data.model.CurrentWeather
import com.example.weather.data.modelanlik.CurrentWeatherr
import retrofit2.http.GET
import retrofit2.http.Query

interface Service2 {

    @GET("forecast?")
    suspend fun getCurrentWeather2(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("appid")
        appid:String,
        @Query("units")
        units: String
    ):CurrentWeather

    @GET("weather?")
    suspend fun getCurrentWeather3(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("appid")
        appid:String,
        @Query("units")
        units: String
    ):CurrentWeatherr
}