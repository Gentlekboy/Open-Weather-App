package com.gentlekboy.openweatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse
import com.gentlekboy.openweatherapp.databinding.TopCitiesViewHolderBinding
import com.gentlekboy.openweatherapp.utils.clickinterface.RecyclerviewClickInterface
import com.gentlekboy.openweatherapp.utils.diffutil.TopCityDiffUtil

class TopCityAdapter(
    private val recyclerviewClickInterface: RecyclerviewClickInterface,
    private val context: Context
) :
    RecyclerView.Adapter<TopCityAdapter.TopCityViewHolder>() {

    private var oldTopCityList = mutableListOf<CoordinateResponse>()

    inner class TopCityViewHolder(val binding: TopCitiesViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopCityViewHolder(
        TopCitiesViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TopCityViewHolder, position: Int) {
        with(holder) {
            with(oldTopCityList[position]) {
                with(binding) {
                    feelsLike.text = current.weather[0].description
                    temperatureTextView.text = current.temp.toString()
                    locationTv.text = timezone
                    Glide.with(context)
                        .load("https://openweathermap.org/img/wn/${current.weather[0].icon}@2x.png")
                        .into(weatherIcon)
                }

                itemView.setOnClickListener {
                    recyclerviewClickInterface.navigateToCityDetails(position)
                }
            }
        }
    }

    override fun getItemCount() = oldTopCityList.size

    fun addTopCities(newTopCityList: MutableList<CoordinateResponse>) {
        val diffUtilLists = TopCityDiffUtil(oldTopCityList, newTopCityList)
        val diffResult = DiffUtil.calculateDiff(diffUtilLists)
        oldTopCityList = newTopCityList
        diffResult.dispatchUpdatesTo(this)
    }
}