package com.example.weatherforcast.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherforcast.model.Favourite
import com.example.weatherforcast.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_table")
    fun getFavourites(): Flow<List<Favourite>>

    @Query("SELECT * FROM fav_table WHERE city = :city")
    fun getFavouriteByCity(city: String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE FROM fav_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    //Unit Table
    @Query("SELECT * FROM setting_table")
    fun getAllUnits(): Flow<List<com.example.weatherforcast.model.Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: com.example.weatherforcast.model.Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: com.example.weatherforcast.model.Unit)

    @Query("DELETE FROM setting_table")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)




}