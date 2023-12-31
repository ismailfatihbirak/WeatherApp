package com.example.weather.di

import com.example.weather.data.datasource.WeatherDataSource
import com.example.weather.data.repo.WeatherRepository
import com.example.weather.retrofit.ApiUtils
import com.example.weather.retrofit.Service2

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherDataSource(srcv:Service2) : WeatherDataSource{
        return WeatherDataSource(srcv)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(wds:WeatherDataSource) : WeatherRepository{
        return WeatherRepository(wds)
    }

    @Provides
    @Singleton
    fun provideService2() : Service2{
        return ApiUtils.getService2()
    }






}