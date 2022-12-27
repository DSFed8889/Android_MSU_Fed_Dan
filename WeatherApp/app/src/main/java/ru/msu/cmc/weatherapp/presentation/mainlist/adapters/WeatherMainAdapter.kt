package ru.msu.cmc.weatherapp.presentation.mainlist.adapters

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
import ru.msu.cmc.weatherapp.presentation.mainlist.OnWeatherContainerClicked

class WeatherMainAdapter(
    private val dataSet: List<WeatherItem>,
    private val OnWeatherContainerClicked: OnWeatherContainerClicked
    ): RecyclerView.Adapter<WeatherMainAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mainItemCard: View
        val tvMainCond: TextView
        val tvMainTemp: TextView
        val tvMainDate: TextView
        val imageViewMainCond: ImageView
        val mainConstraintLayout: ConstraintLayout

        init {
            mainItemCard = view.findViewById(R.id.mainItemCard)
            tvMainCond = view.findViewById(R.id.tvMainCond)
            tvMainTemp = view.findViewById(R.id.tvMainTemp)
            tvMainDate = view.findViewById(R.id.tvMainDate)
            imageViewMainCond = view.findViewById(R.id.imageViewMainCond)
            mainConstraintLayout = view.findViewById(R.id.mainConstraintLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_main_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            dataSet[position].let {
                tvMainCond.text = it.condition
                tvMainCond.setTextColor(DataToAdapterParse(it).getFontColor())
                tvMainTemp.text = DataToAdapterParse(it).getTemp()
                tvMainTemp.setTextColor(DataToAdapterParse(it).getFontColor())
                tvMainDate.text = DataToAdapterParse(it).getDate()
                tvMainDate.setTextColor(DataToAdapterParse(it).getFontColor())
                imageViewMainCond.setImageResource(DataToAdapterParse(it).getSourceImage())
                mainConstraintLayout.setBackgroundResource(DataToAdapterParse(it).getBackground())
                val wItem = it
                mainItemCard.setOnClickListener {
                    OnWeatherContainerClicked.onWeatherContainerClick(wItem)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}