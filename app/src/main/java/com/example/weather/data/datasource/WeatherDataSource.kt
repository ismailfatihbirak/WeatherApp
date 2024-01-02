package com.example.weather.data.datasource

import com.example.weather.MainActivity
import com.example.weather.data.model.Konum
import com.example.weather.data.model.Root
import com.example.weather.data.modelanlik.CurrentWeatherr
import com.example.weather.retrofit.Service2
import com.example.weather.ui.fragment.AnasayfaFragment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WeatherDataSource(var srcv:Service2) {

    suspend fun anasayfaRecyclerview(a:Konum) : List<Root> =
        withContext(Dispatchers.IO){
            return@withContext srcv.getCurrentWeather2(a.lat.toString(),a.lon.toString(),
                "c448d17baba4ee2e365095a7d9632f2a","metric").list
        }

    suspend fun anasayfaCurrent(a:Konum) : CurrentWeatherr =
        withContext(Dispatchers.IO){
            return@withContext srcv.getCurrentWeather3(a.lat.toString(),a.lon.toString(),
                "c448d17baba4ee2e365095a7d9632f2a","metric")
        }



}