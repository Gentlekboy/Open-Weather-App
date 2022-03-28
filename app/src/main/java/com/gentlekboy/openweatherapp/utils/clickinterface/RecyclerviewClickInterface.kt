package com.gentlekboy.openweatherapp.utils.clickinterface

import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse

interface RecyclerviewClickInterface {
    fun navigateToCityDetails(currentCity: CityResponse)
    fun setAsFavourite(currentCity: CityResponse)
}