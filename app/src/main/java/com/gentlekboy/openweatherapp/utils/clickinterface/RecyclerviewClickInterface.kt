package com.gentlekboy.openweatherapp.utils.clickinterface

interface RecyclerviewClickInterface {
    fun navigateToCityDetails(position: Int)
    fun setAsFavourite(position: Int)
}