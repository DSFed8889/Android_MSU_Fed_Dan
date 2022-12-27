package ru.msu.cmc.weatherapp.presentation.detailslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.data.DataToAdapterParse
import ru.msu.cmc.weatherapp.models.WeatherItem

class WeatherDetailsAdapter(private val dataSet: List<WeatherItem>): RecyclerView.Adapter<WeatherDetailsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvDetailsCond: TextView
        val tvDetailsTemp: TextView
        val tvDetailsTime: TextView
        val imageViewDetailsCond: ImageView
        val detailsConstraintLayout: ConstraintLayout

        init {
            tvDetailsCond = view.findViewById(R.id.tvMainCond)
            tvDetailsTemp = view.findViewById(R.id.tvMainTemp)
            tvDetailsTime = view.findViewById(R.id.tvMainDate)
            imageViewDetailsCond = view.findViewById(R.id.imageViewMainCond)
            detailsConstraintLayout = view.findViewById(R.id.detailsConstraintLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_details_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tvDetailsCond.text = dataSet[position].condition
            tvDetailsCond.setTextColor(DataToAdapterParse(dataSet[position]).getFontColor())
            tvDetailsTemp.text = DataToAdapterParse(dataSet[position]).getDetailedTemp()
            tvDetailsTemp.setTextColor(DataToAdapterParse(dataSet[position]).getFontColor())
            tvDetailsTime.text = DataToAdapterParse(dataSet[position]).getTime()
            tvDetailsTime.setTextColor(DataToAdapterParse(dataSet[position]).getFontColor())
            imageViewDetailsCond.setImageResource(DataToAdapterParse(dataSet[position]).getSourceImage())
            detailsConstraintLayout.setBackgroundResource(DataToAdapterParse(dataSet[position]).getBackground())
        }
    }

    override fun getItemCount(): Int = dataSet.size
}