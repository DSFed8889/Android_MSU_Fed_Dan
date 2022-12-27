package ru.msu.cmc.weatherapp.presentation.detailslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.data.datastores.WeatherNetworkDS
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel
import ru.msu.cmc.weatherapp.data.repositories.WeatherRepository
import ru.msu.cmc.weatherapp.data.repositories.WeatherRepositoryImpl
import ru.msu.cmc.weatherapp.presentation.detailslist.adapters.WeatherDetailsAdapter

class FragmentDetailsList: Fragment(R.layout.fmt_details_list) {

    private val model: WeatherItemsViewModel by activityViewModels()

    companion object {
        private const val ARG_DATE = "ARG_DATE"
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(date: String, town: String): FragmentDetailsList {
        val fragment: FragmentDetailsList = FragmentDetailsList()

        fragment.arguments = Bundle().apply {
            putString(ARG_DATE, date)
            putString(ARG_TOWN, town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewDetails)

        val repository: WeatherRepository = WeatherRepositoryImpl(
            WeatherNetworkDS(this.requireContext())
        )

        val args = requireArguments()

        val date: String = args.getString(ARG_DATE, "")
        val town: String = args.getString(ARG_TOWN, "")

        repository.getWeatherDetailsList(date, town, model)

        model.wItemDetailsList.observe(viewLifecycleOwner) {
            recycler.adapter = WeatherDetailsAdapter(it)
        }
    }
}