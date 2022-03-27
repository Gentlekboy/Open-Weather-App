package com.gentlekboy.openweatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gentlekboy.openweatherapp.data.model.coordinatesresponse.CoordinateResponse
import com.gentlekboy.openweatherapp.databinding.FragmentDashboardBinding
import com.gentlekboy.openweatherapp.ui.adapter.TopCityAdapter
import com.gentlekboy.openweatherapp.utils.clickinterface.RecyclerviewClickInterface
import com.gentlekboy.openweatherapp.viewmodel.OpenWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(), RecyclerviewClickInterface {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val openWeatherViewModel: OpenWeatherViewModel by activityViewModels()
    private val topCityAdapter: TopCityAdapter by lazy { TopCityAdapter(this, requireContext()) }
    private lateinit var list: MutableList<CoordinateResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = mutableListOf()

        setUpAdapter()
        populateAdapter()
    }

    private fun setUpAdapter() {
        binding.topCitiesRecyclerView.adapter = topCityAdapter
    }

    private fun populateAdapter() {
        openWeatherViewModel.getWeatherFromDb().observe(viewLifecycleOwner) {
            if (it != null) {
                list = it.toMutableList()
                topCityAdapter.addTopCities(it.toMutableList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToCityDetails(position: Int) {
        list[position].isFavourite = true

        val sortByFav = list.sortedBy { !it.isFavourite }
        topCityAdapter.addTopCities(sortByFav.toMutableList())
    }
}