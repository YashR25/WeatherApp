package com.example.weatherforcast.repository

import com.example.weatherforcast.database.WeatherDao
import com.example.weatherforcast.model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getAllFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()
    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)
    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite)
    suspend fun deleterAll() = weatherDao.deleteAll()
    suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite)
    suspend fun getFavouriteByCity(city: String) = weatherDao.getFavouriteByCity(city)

    //Unit Table
    fun getAllUnits(): Flow<List<com.example.weatherforcast.model.Unit>> = weatherDao.getAllUnits()
    suspend fun insertUnit(unit: com.example.weatherforcast.model.Unit) = weatherDao.insertUnit(unit)
    suspend fun deleteUnit(unit: com.example.weatherforcast.model.Unit) = weatherDao.deleteUnit(unit)
    suspend fun deleterAllUnits() = weatherDao.deleteAllUnits()
    suspend fun updateUnit(unit: com.example.weatherforcast.model.Unit) = weatherDao.updateUnit(unit)
}