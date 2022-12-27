package ru.msu.cmc.weatherapp.presentation.detailslist

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.msu.cmc.weatherapp.R

class FragmentDetailsDetails: Fragment(R.layout.fmt_details_details) {

    companion object {
        private const val ARG_DATE = "ARG_DATE"
        private const val ARG_TOWN = "ARG_TOWN"
    }

    fun createFragment(date: String, town: String): FragmentDetailsDetails {
        val fragment: FragmentDetailsDetails = FragmentDetailsDetails()

        fragment.arguments = Bundle().apply {
            putString(ARG_DATE, date)
            putString(ARG_TOWN, town)
        }

        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvDetailsDate = view.findViewById<TextView>(R.id.tvDetailsDate)
        val tvDetailsTown = view.findViewById<TextView>(R.id.tvDetailsTown)

        val args = requireArguments()

        val date = args.getString(FragmentDetailsDetails.ARG_DATE, "")
        val town: String = args.getString(FragmentDetailsDetails.ARG_TOWN, "")

        tvDetailsDate.setTextColor(Color.parseColor("#5B7795"))
        tvDetailsTown.setTextColor(Color.parseColor("#5B7795"))

        tvDetailsDate.text = date
        tvDetailsTown.text = town
    }
}