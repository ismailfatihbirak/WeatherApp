package com.example.weather.retrofit

class ApiUtils {
    companion object{
        var BASE_URL : String = "https://api.openweathermap.org/data/2.5/"
        var API_KEY : String = "c448d17baba4ee2e365095a7d9632f2a"

        fun getService2() :Service2 {
            return RetrofitClient.getClient(BASE_URL).create(Service2::class.java)
        }
    }
}