package com.gentlekboy.openweatherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.cityresponse.Coord

@Dao
interface CoordinatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityResponse: CityResponse)

    @Query("SELECT coord FROM coordinates_table")
    fun fetchAllCoordinates(): List<Coord>
}