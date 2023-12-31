package com.example.weather.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.model.Root
import com.example.weather.data.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var wrepo : WeatherRepository): ViewModel() {

    var gunListesi = MutableLiveData<List<Root>>()

    init {
        detayRecyclerview()
    }

    fun detayRecyclerview() {
        CoroutineScope(Dispatchers.Main).launch {
            gunListesi.value = wrepo.anasayfaRecyclerview()
        }
    }
}