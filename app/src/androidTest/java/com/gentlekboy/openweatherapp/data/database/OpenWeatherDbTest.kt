package com.gentlekboy.openweatherapp.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gentlekboy.openweatherapp.data.database.dao.CityResponseDao
import com.gentlekboy.openweatherapp.data.database.dao.CoordinatesResponseDao
import com.gentlekboy.openweatherapp.data.model.cityresponse.*
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OpenWeatherDbTest : TestCase() {
    private lateinit var db: OpenWeatherDb
    private lateinit var cityResponseDao: CityResponseDao
    private lateinit var coordinatesResponseDao: CoordinatesResponseDao
    val coord = Coord(48.8534, 2.3488)
    val main = Main(10.57)
    val sys = Sys("FR")
    val weather = listOf(Weather("clear sky", "01n", 800, "Clear"))
    val cityResponse = CityResponse(1, coord, 1648501063, main, "Ikeja", sys, weather, false)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUpVariables() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, OpenWeatherDb::class.java).build()
        cityResponseDao = db.coordinatesDao()
        coordinatesResponseDao = db.weatherDao()
        runBlocking {
            cityResponseDao.insertCityResponse(cityResponse)
        }
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testIfDbUpdates() = runBlocking {
        cityResponseDao.updateCityResponse(cityResponse.copy(isFavourite = true))

        val cityResponses = cityResponseDao.getCityResponseLiveData()
        val value = cityResponses.value

        if (value != null) {
            assertThat(value.contains(cityResponse.copy(isFavourite = true))).isTrue()
        }
    }
}