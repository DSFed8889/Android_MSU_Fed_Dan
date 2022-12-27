package ru.msu.cmc.weatherapp.presentation.mainlist

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.isPermissionGranted
import ru.msu.cmc.weatherapp.models.WeatherItem
import ru.msu.cmc.weatherapp.presentation.detailslist.FragmentDetails
import ru.msu.cmc.weatherapp.presentation.detailslist.FragmentDetailsList

class FragmentGeneral: Fragment(R.layout.fmt_general), OnWeatherContainerClicked {

    companion object {
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(town: String): FragmentGeneral {
        val fragment: FragmentGeneral = FragmentGeneral()

        fragment.arguments = Bundle().apply {
            putString(ARG_TOWN, town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fmt_today_container, FragmentToday(this)
                    .createFragment(args.getString(ARG_TOWN)!!))
                .replace(R.id.fmt_main_list_container, FragmentWeatherMainList(this)
                    .createFragment(args.getString(ARG_TOWN)!!))
                .commit()
        }
    }

    override fun onWeatherContainerClick(wItem: WeatherItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fmt_container, FragmentDetails().createFragment(wItem))
            .addToBackStack("general")
            .commit()
    }
}