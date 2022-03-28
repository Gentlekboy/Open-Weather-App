package com.gentlekboy.openweatherapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.data.model.service.WeatherServiceModel
import com.gentlekboy.openweatherapp.databinding.FragmentDashboardBinding
import com.gentlekboy.openweatherapp.ui.adapter.TopCityAdapter
import com.gentlekboy.openweatherapp.utils.FAVOURITE_CITY
import com.gentlekboy.openweatherapp.utils.clickinterface.RecyclerviewClickInterface
import com.gentlekboy.openweatherapp.utils.isInternetAvailable
import com.gentlekboy.openweatherapp.utils.service.OpenWeatherService
import com.gentlekboy.openweatherapp.viewmodel.OpenWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(), RecyclerviewClickInterface {
    init {
        System.loadLibrary("api-keys")
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val openWeatherViewModel: OpenWeatherViewModel by activityViewModels()
    private val topCityAdapter: TopCityAdapter by lazy { TopCityAdapter(this) }
    private external fun getKeys(): String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryButton.setOnClickListener { fetchDataFromApi() }

        setUpAdapter()
        populateAdapter()
        filterCity()
        startServiceForFavouriteCity()
    }

    private fun setUpAdapter() {
        binding.topCitiesRecyclerView.adapter = topCityAdapter
    }

    private fun fetchDataFromApi() {
        openWeatherViewModel.saveDataToDb(getKeys(), requireContext())
    }

    private fun startServiceForFavouriteCity() {
        openWeatherViewModel.getCityResponseLiveData()
            .observe(viewLifecycleOwner) { listOfCityResponse ->
                val intent = Intent(activity, OpenWeatherService::class.java)
                val cityResponse = listOfCityResponse.filter { it.isFavourite }

                when (cityResponse.isNotEmpty()) {
                    true -> {
                        requireActivity().startService(
                            intent.putExtra(
                                FAVOURITE_CITY,
                                WeatherServiceModel(
                                    cityResponse.first().main.temp,
                                    "${cityResponse.first().name}, ${cityResponse.first().sys.country}",
                                    cityResponse.first().weather.first().description
                                )
                            )
                        )
                    }
                    false -> {
                        requireActivity().stopService(intent)
                    }
                }
            }
    }

    private fun filterCity() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) searchDatabaseForCity(newText)
                return true
            }
        })
    }

    fun searchDatabaseForCity(cityName: String) {
        val searchQuery = "%$cityName%"
        openWeatherViewModel.searchDatabaseForCity(searchQuery).observe(viewLifecycleOwner) {
            topCityAdapter.addTopCities(it)
        }
    }

    private fun populateAdapter() {
        openWeatherViewModel.getCityResponseLiveData()
            .observe(viewLifecycleOwner) { listOfCityResponse ->

                if (listOfCityResponse.isEmpty() && !requireContext().isInternetAvailable()) {
                    binding.apply {
                        topCitiesRecyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        checkInternetTv.visibility = View.VISIBLE
                        retryButton.visibility = View.VISIBLE
                    }
                } else if (listOfCityResponse.isEmpty() && requireContext().isInternetAvailable()) {
                    binding.apply {
                        topCitiesRecyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        checkInternetTv.visibility = View.GONE
                        retryButton.visibility = View.GONE
                    }
                } else if (listOfCityResponse.isNotEmpty()) {
                    topCityAdapter.addTopCities(listOfCityResponse)

                    binding.apply {
                        topCitiesRecyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        checkInternetTv.visibility = View.GONE
                        retryButton.visibility = View.GONE
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToCityDetails(currentCity: CityResponse) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(currentCity)
        findNavController().navigate(action)
    }

    override fun setAsFavourite(currentCity: CityResponse) {
        when (currentCity.isFavourite) {
            true -> {
                val updatedCityResponse = currentCity.copy(isFavourite = false)
                openWeatherViewModel.updateCityResponse(updatedCityResponse)

                openWeatherViewModel.getCityResponseLiveData()
                    .observe(viewLifecycleOwner) { topCityAdapter.addTopCities(it) }
            }
            false -> {
                val updatedCityResponse = currentCity.copy(isFavourite = true)
                openWeatherViewModel.updateCityResponse(updatedCityResponse)

                openWeatherViewModel.getCityResponseLiveData()
                    .observe(viewLifecycleOwner) { topCityAdapter.addTopCities(it) }
            }
        }
    }
}