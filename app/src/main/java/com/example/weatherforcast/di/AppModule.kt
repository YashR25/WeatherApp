package com.example.weatherforcast.di

import android.content.Context
import androidx.room.Room
import com.example.weatherforcast.database.WeatherDao
import com.example.weatherforcast.database.WeatherDatabase
import com.example.weatherforcast.network.WeatherApi
import com.example.weatherforcast.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao{
        return weatherDatabase.weatherDao
    }

    @Provides
    @Singleton
    fun getWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase{
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun getWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


}