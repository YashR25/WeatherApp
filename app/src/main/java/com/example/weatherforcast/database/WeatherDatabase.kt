package com.example.weatherforcast.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherforcast.model.Favourite
import com.example.weatherforcast.model.Unit

@Database(entities = [Favourite::class,Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase(){
    abstract val weatherDao: WeatherDao
}