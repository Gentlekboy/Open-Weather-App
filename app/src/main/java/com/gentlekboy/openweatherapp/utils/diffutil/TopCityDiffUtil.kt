package com.gentlekboy.openweatherapp.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse

class TopCityDiffUtil(
    private val oldList: List<CityResponse>,
    private val newList: List<CityResponse>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = when {
        oldList[oldItemPosition].id != newList[newItemPosition].id -> false
        oldList[oldItemPosition].coord != newList[newItemPosition].coord -> false
        oldList[oldItemPosition].dt != newList[newItemPosition].dt -> false
        oldList[oldItemPosition].main != newList[newItemPosition].main -> false
        oldList[oldItemPosition].name != newList[newItemPosition].name -> false
        oldList[oldItemPosition].sys != newList[newItemPosition].sys -> false
        oldList[oldItemPosition].weather != newList[newItemPosition].weather -> false
        oldList[oldItemPosition].isFavourite != newList[newItemPosition].isFavourite -> false
        else -> true
    }
}