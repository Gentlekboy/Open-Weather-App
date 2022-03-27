package com.gentlekboy.openweatherapp.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse

class TopCityDiffUtil(
    private val oldList: MutableList<CoordinateResponse>,
    private val newList: MutableList<CoordinateResponse>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = when {
        oldList[oldItemPosition].id != newList[newItemPosition].id -> false
        oldList[oldItemPosition].alerts != newList[newItemPosition].alerts -> false
        oldList[oldItemPosition].current != newList[newItemPosition].current -> false
        oldList[oldItemPosition].hourly != newList[newItemPosition].hourly -> false
        oldList[oldItemPosition].lat != newList[newItemPosition].lat -> false
        oldList[oldItemPosition].lon != newList[newItemPosition].lon -> false
        oldList[oldItemPosition].timezone != newList[newItemPosition].timezone -> false
        oldList[oldItemPosition].isFavourite != newList[newItemPosition].isFavourite -> false
        else -> true
    }
}