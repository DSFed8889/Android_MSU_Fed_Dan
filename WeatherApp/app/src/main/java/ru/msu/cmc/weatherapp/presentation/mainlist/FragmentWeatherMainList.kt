package ru.msu.cmc.weatherapp.presentation.mainlist

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
import ru.msu.cmc.weatherapp.presentation.mainlist.adapters.WeatherMainAdapter

class FragmentWeatherMainList(
    private val onClickAction: OnWeatherContainerClicked
): Fragment(R.layout.fmt_main_list) {

    private val model: WeatherItemsViewModel by activityViewModels()

    companion object {
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(town: String): FragmentWeatherMainList {
        val fragment: FragmentWeatherMainList = FragmentWeatherMainList(onClickAction)

        fragment.arguments = Bundle().apply {
            putString(ARG_TOWN, town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewMain)

        val repository: WeatherRepository = WeatherRepositoryImpl(
            WeatherNetworkDS(requireContext())
        )

        val args = requireArguments()

        repository.getWeatherMainList(args.getString(ARG_TOWN)!!, model)

        model.wItemMainList.observe(viewLifecycleOwner) {
            recycler.adapter = WeatherMainAdapter(it, onClickAction)
        }
    }
}