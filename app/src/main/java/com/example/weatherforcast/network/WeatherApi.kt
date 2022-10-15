package com.example.weatherforcast.network

import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherInfo(
        @Query(value = "appid") appid: String = API_KEY,
        @Query(value = "units") units: String = "imperial",
        @Query(value = "q") q: String): Weather

}