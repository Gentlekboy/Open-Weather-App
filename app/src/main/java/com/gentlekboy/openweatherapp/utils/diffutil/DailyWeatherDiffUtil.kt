package com.gentlekboy.openweatherapp.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.Daily

class DailyWeatherDiffUtil(
    private val oldList: List<Daily>,
    private val newList: List<Daily>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].dt == newList[newItemPosition].dt

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = when {
        oldList[oldItemPosition].dt != newList[newItemPosition].dt -> false
        oldList[oldItemPosition].temp != newList[newItemPosition].temp -> false
        oldList[oldItemPosition].weather != newList[newItemPosition].weather -> false
        else -> true
    }
}