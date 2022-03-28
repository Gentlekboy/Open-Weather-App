package com.gentlekboy.openweatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.cityresponse.Coord

@Dao
interface CityResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityResponse(cityResponse: CityResponse)

    @Query("SELECT coord FROM city_response_table")
    fun fetchAllCoordinates(): List<Coord>

    @Query("SELECT * FROM city_response_table ORDER BY isFavourite DESC")
    fun getCityResponseLiveData(): LiveData<List<CityResponse>>

    @Update
    suspend fun updateCityResponse(cityResponse: CityResponse)
}