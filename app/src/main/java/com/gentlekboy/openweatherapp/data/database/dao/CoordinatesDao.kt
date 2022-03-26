package com.gentlekboy.openweatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse

@Dao
interface CoordinatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityResponse: CityResponse)

    @Query("SELECT * FROM coordinates_table")
    fun fetchCityInfo(): LiveData<CityResponse>

    @Update
    suspend fun updateCity(cityResponse: CityResponse)
}