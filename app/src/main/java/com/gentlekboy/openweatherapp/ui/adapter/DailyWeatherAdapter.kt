package com.gentlekboy.openweatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.Daily
import com.gentlekboy.openweatherapp.databinding.DailyWeatherViewHolderBinding
import com.gentlekboy.openweatherapp.utils.convertTimeStampToDate
import com.gentlekboy.openweatherapp.utils.convertTimeStampToTime
import com.gentlekboy.openweatherapp.utils.diffutil.DailyWeatherDiffUtil

class DailyWeatherAdapter(private val context: Context) :
    RecyclerView.Adapter<DailyWeatherAdapter.WeatherInfoViewHolder>() {

    private var oldDailyWeatherList = listOf<Daily>()

    inner class WeatherInfoViewHolder(val binding: DailyWeatherViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherInfoViewHolder(
        DailyWeatherViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        with(holder) {
            with(oldDailyWeatherList[position]) {
                with(binding) {
                    date.text = dt.convertTimeStampToDate()
                    timeTv.text = dt.convertTimeStampToTime()
                    temperatureTextView.text = "${temp.day} \u2103"
                    Glide.with(context)
                        .load("https://openweathermap.org/img/wn/${weather[0].icon}@2x.png")
                        .placeholder(R.drawable.cloud)
                        .into(weatherIcon)
                }
            }
        }
    }

    override fun getItemCount() = oldDailyWeatherList.size

    fun addWeatherInfo(newDailyWeatherList: List<Daily>) {
        val diffUtilLists = DailyWeatherDiffUtil(oldDailyWeatherList, newDailyWeatherList)
        val diffResult = DiffUtil.calculateDiff(diffUtilLists)
        oldDailyWeatherList = newDailyWeatherList
        diffResult.dispatchUpdatesTo(this)
    }
}