package com.gentlekboy.openweatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinatesResponse

@Dao
interface CoordinatesResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinatesResponse(coordinatesResponse: CoordinatesResponse)

    @Query("SELECT * FROM coordinates_response_table")
    fun getCoordinatesResponseLiveData(): LiveData<List<CoordinatesResponse>>

    @Query("SELECT * FROM coordinates_response_table")
    suspend fun fetchCoordinatesResponseList(): List<CoordinatesResponse>

    @Update
    suspend fun updateCoordinatesResponse(coordinatesResponse: CoordinatesResponse)

    @Query("DELETE FROM coordinates_response_table")
    suspend fun deleteAllCoordinatesResponse()
}