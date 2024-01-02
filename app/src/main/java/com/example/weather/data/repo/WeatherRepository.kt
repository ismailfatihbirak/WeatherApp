package com.example.weather.data.repo


import com.example.weather.data.datasource.WeatherDataSource
import com.example.weather.data.model.Konum

import com.example.weather.data.model.Root
import com.example.weather.data.modelanlik.CurrentWeatherr

class WeatherRepository (var wds : WeatherDataSource){
    suspend fun anasayfaRecyclerview(a:Konum) : List<Root> = wds.anasayfaRecyclerview(a)
    suspend fun anasayfaCurrent(a:Konum) : CurrentWeatherr = wds.anasayfaCurrent(a)

}