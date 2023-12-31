package com.example.weather.data.repo


import com.example.weather.data.datasource.WeatherDataSource

import com.example.weather.data.model.Root
import com.example.weather.data.modelanlik.CurrentWeatherr

class WeatherRepository (var wds : WeatherDataSource){
    suspend fun anasayfaRecyclerview() : List<Root> = wds.anasayfaRecyclerview()
    suspend fun anasayfaCurrent() : CurrentWeatherr = wds.anasayfaCurrent()

}