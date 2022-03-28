package com.gentlekboy.openweatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gentlekboy.openweatherapp.data.model.cityresponse.CityResponse
import com.gentlekboy.openweatherapp.databinding.FragmentDashboardBinding
import com.gentlekboy.openweatherapp.ui.adapter.TopCityAdapter
import com.gentlekboy.openweatherapp.utils.clickinterface.RecyclerviewClickInterface
import com.gentlekboy.openweatherapp.utils.isInternetAvailable
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
    private lateinit var cityResponseListFromDb: List<CityResponse>
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

        cityResponseListFromDb = mutableListOf()

        binding.retryButton.setOnClickListener { fetchDataFromApi() }

        setUpAdapter()
        populateAdapter()
    }

    private fun setUpAdapter() {
        binding.topCitiesRecyclerView.adapter = topCityAdapter
    }

    private fun fetchDataFromApi() {
        openWeatherViewModel.saveDataToDb(getKeys(), requireContext())
    }

    private fun populateAdapter() {
        openWeatherViewModel.getCityResponseLiveData()
            .observe(viewLifecycleOwner) { listOfCityResponse ->
                cityResponseListFromDb = listOfCityResponse

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

    override fun navigateToCityDetails(position: Int) {
        val action = DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(
            cityResponseListFromDb[position]
        )
        findNavController().navigate(action)
    }

    override fun setAsFavourite(position: Int) {
        when (cityResponseListFromDb[position].isFavourite) {
            true -> {
                cityResponseListFromDb[position].isFavourite = false
                val updatedCityResponse = cityResponseListFromDb[position].copy(isFavourite = false)
                openWeatherViewModel.updateCityResponse(updatedCityResponse)

                val sortedListByFavourites = cityResponseListFromDb.sortedBy { !it.isFavourite }
                topCityAdapter.addTopCities(sortedListByFavourites)
            }
            false -> {
                cityResponseListFromDb[position].isFavourite = true
                val updatedCityResponse = cityResponseListFromDb[position].copy(isFavourite = true)
                openWeatherViewModel.updateCityResponse(updatedCityResponse)

                val sortedListByFavourites = cityResponseListFromDb.sortedBy { !it.isFavourite }
                topCityAdapter.addTopCities(sortedListByFavourites)
            }
        }
    }
}