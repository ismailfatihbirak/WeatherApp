package com.example.weather.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.model.Root
import com.example.weather.data.modelanlik.CurrentWeatherr

import com.example.weather.data.repo.WeatherRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var wrepo : WeatherRepository) : ViewModel() {

    var saatListesi = MutableLiveData<List<Root>>()
    var anlıkListesi = MutableLiveData<CurrentWeatherr>()

    init {
        anasayfaRecyclerview()
        anasayfaCurrent()
    }

    fun anasayfaRecyclerview() {
        CoroutineScope(Dispatchers.Main).launch {
            saatListesi.value = wrepo.anasayfaRecyclerview()
            anlıkListesi.value = wrepo.anasayfaCurrent()
        }
    }
    fun anasayfaCurrent() {
        CoroutineScope(Dispatchers.Main).launch {
            anlıkListesi.value = wrepo.anasayfaCurrent()
        }
    }

}