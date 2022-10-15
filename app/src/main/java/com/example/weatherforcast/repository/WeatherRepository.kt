package com.example.weatherforcast.repository

import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeatherInfo(cityName: String): DataOrException<Weather,Boolean,Exception> {
        return try {
            val data = api.getWeatherInfo(q = cityName)
            DataOrException(data = data)
        }catch (e: Exception){
            DataOrException(e = e)
        }
    }
}