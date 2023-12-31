package com.example.weather.data.model

data class CurrentWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Root>,
    val message: Int
)