package ru.msu.cmc.weatherapp.presentation.detailslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.data.DataToAdapterParse
import ru.msu.cmc.weatherapp.models.WeatherItem

class FragmentDetails: Fragment(R.layout.fmt_details) {

    companion object {
        private const val ARG_DATE = "ARG_DATE"
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(wItem: WeatherItem): FragmentDetails {
        val fragment: FragmentDetails = FragmentDetails()

        fragment.arguments = Bundle().apply {
            putString(ARG_DATE, DataToAdapterParse(wItem).getDate())
            putString(ARG_TOWN, wItem.town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()


        val date = args.getString(ARG_DATE, "")
        val town: String = args.getString(ARG_TOWN, "")

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.fmt_details_list, FragmentDetailsList().createFragment(date, town))
                .add(R.id.fmt_details_details, FragmentDetailsDetails().createFragment(date, town))
                .commit()
        }
    }
}
