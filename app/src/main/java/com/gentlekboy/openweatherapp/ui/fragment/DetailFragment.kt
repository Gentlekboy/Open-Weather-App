package com.gentlekboy.openweatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gentlekboy.openweatherapp.R
import com.gentlekboy.openweatherapp.databinding.FragmentDetailBinding
import com.gentlekboy.openweatherapp.ui.adapter.DailyWeatherAdapter
import com.gentlekboy.openweatherapp.utils.convertTimeStampToDate
import com.gentlekboy.openweatherapp.viewmodel.OpenWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val openWeatherViewModel: OpenWeatherViewModel by activityViewModels()
    private val dailyWeatherAdapter: DailyWeatherAdapter by lazy {
        DailyWeatherAdapter(
            requireContext()
        )
    }
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        populateViews()
        fetchDailyForecastIntoAdapter()
    }

    private fun fetchDailyForecastIntoAdapter() {
        val latitude = args.cityResponse.coord.lat
        val longitude = args.cityResponse.coord.lon
        openWeatherViewModel.getDailyWeatherLiveData(latitude, longitude)
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val listOfDaily = it.daily
                    dailyWeatherAdapter.addWeatherInfo(listOfDaily)
                }
            }

    }

    private fun setUpAdapter() {
        binding.dailyWeatherRecyclerView.adapter = dailyWeatherAdapter
    }

    private fun populateViews() {
        binding.apply {
            dateTv.text = args.cityResponse.dt.convertTimeStampToDate()
            temperatureTv.text = args.cityResponse.main?.temp.toString() + " \u2103"
            cityTv.text = "${args.cityResponse.name}, ${args.cityResponse.sys.country}"
            feelsLikeTv.text = args.cityResponse.weather?.get(0)?.description
            Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/${args.cityResponse.weather?.get(0)?.icon}@2x.png")
                .placeholder(R.drawable.cloud_white)
                .into(weatherImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}