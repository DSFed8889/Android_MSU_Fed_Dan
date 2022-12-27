package ru.msu.cmc.weatherapp.presentation.mainlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.FusedLocationProviderClient
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.data.DataToAdapterParse
import ru.msu.cmc.weatherapp.data.datastores.WeatherNetworkDS
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel
import ru.msu.cmc.weatherapp.data.repositories.WeatherRepository
import ru.msu.cmc.weatherapp.data.repositories.WeatherRepositoryImpl
import ru.msu.cmc.weatherapp.presentation.detailslist.FragmentDetailsList

class FragmentToday(private val onClickAction: OnWeatherContainerClicked): Fragment(R.layout.fmt_weather_today) {

    private val model: WeatherItemsViewModel by activityViewModels()

    companion object {
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(town: String): FragmentToday {
        val fragment: FragmentToday = FragmentToday(onClickAction)

        fragment.arguments = Bundle().apply {
            putString(ARG_TOWN, town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        val todayConstraintLayout = view.findViewById<ConstraintLayout>(R.id.todayConstraintLayout)
        val todayItemCard = view.findViewById<CardView>(R.id.todayItemCard)
        val tvTodayDate = view.findViewById<TextView>(R.id.tvTodayDate)
        val tvTodayTown = view.findViewById<TextView>(R.id.tvTodayTown)
        val tvTodayTemp = view.findViewById<TextView>(R.id.tvTodayTemp)
        val imageViewTodayCond = view.findViewById<ImageView>(R.id.imageViewTodayCond)

        val repository: WeatherRepository = WeatherRepositoryImpl(
            WeatherNetworkDS(requireContext())
        )

        val town = args.getString(ARG_TOWN)!!

        Log.d("MyLog", "Today is: $town")

        repository.getWeatherToday(town, model)

        model.wItemToday.observe(viewLifecycleOwner){
            Log.d("MyLog", model.wItemToday.value.toString())

            todayConstraintLayout.setBackgroundResource(DataToAdapterParse(it).getBackground())
            tvTodayDate.text = DataToAdapterParse(it).getFullDate()
            tvTodayDate.setTextColor(DataToAdapterParse(it).getFontColor())
            tvTodayTemp.text = DataToAdapterParse(it).getTemp()
            tvTodayTemp.setTextColor(DataToAdapterParse(it).getFontColor())
            tvTodayTown.text = it.town
            tvTodayTown.setTextColor(DataToAdapterParse(it).getFontColor())
            imageViewTodayCond.setImageResource(DataToAdapterParse(it).getSourceImage())

            val wItem = it

            todayItemCard.setOnClickListener {
                onClickAction.onWeatherContainerClick(wItem)
            }
        }
    }
}