package com.gentlekboy.openweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.databinding.TopCitiesViewHolderBinding
import com.gentlekboy.openweatherapp.utils.clickinterface.RecyclerviewClickInterface
import com.gentlekboy.openweatherapp.utils.convertTimeStampToDate
import com.gentlekboy.openweatherapp.utils.diffutil.TopCityDiffUtil

class TopCityAdapter(
    private val recyclerviewClickInterface: RecyclerviewClickInterface
) :
    RecyclerView.Adapter<TopCityAdapter.TopCityViewHolder>() {

    private var oldTopCityList = listOf<CityResponse>()

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
        val currentCity = oldTopCityList[position]
        with(holder) {
            with(oldTopCityList[position]) {
                with(binding) {
                    dateTextView.text = dt.convertTimeStampToDate()
                    temperatureTextView.text = main?.temp?.toString() + " \u2103"
                    locationTv.text = "$name, ${sys.country}"

                    when (isFavourite) {
                        true -> favouriteIcon.setBackgroundResource(R.drawable.favourite_clicked)
                        false -> favouriteIcon.setBackgroundResource(R.drawable.favourite_unclicked)
                    }

                    favouriteIcon.setOnClickListener {
                        recyclerviewClickInterface.setAsFavourite(currentCity)
                    }
                }
                itemView.setOnClickListener {
                    recyclerviewClickInterface.navigateToCityDetails(currentCity)
                }
            }
        }
    }

    override fun getItemCount() = oldTopCityList.size

    fun addTopCities(newTopCoordinatesList: List<CityResponse>) {
        val diffUtilLists = TopCityDiffUtil(oldTopCityList, newTopCoordinatesList)
        val diffResult = DiffUtil.calculateDiff(diffUtilLists)
        oldTopCityList = newTopCoordinatesList
        diffResult.dispatchUpdatesTo(this)
    }
}