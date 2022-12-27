package ru.msu.cmc.weatherapp.data

import android.graphics.Color
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.models.WeatherItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DataToAdapterParse(private val wItem: WeatherItem) {
    fun getSourceImage() =
        if (wItem.condition == "Rainy") R.drawable.rainy
        else  R.drawable.rainy

    fun getBackground() = when (wItem.condition) {
        "Rainy" -> R.drawable.rainy_grad
        else -> R.drawable.rainy_grad
    }

    fun getFontColor() =
        if (wItem.condition == "Rainy") Color.parseColor("#5B7795")
        else  Color.parseColor("#5B7795")

    fun getFullDate(): String = DateFormat.getDateInstance(DateFormat.FULL).format(wItem.date)

    fun getDate(): String = DateFormat.getDateInstance().format(wItem.date)

    fun getTime(): String = SimpleDateFormat("h:mm a", Locale.UK).format(wItem.date)

    fun getTemp(): String  = if (wItem.temperature.toInt() > 0) "+${wItem.temperature.toInt()}°C"
    else "${wItem.temperature.toInt()}°C"

    fun getDetailedTemp(): String = if (wItem.temperature > 0) "+${wItem.temperature}°C"
    else "${wItem.temperature}°C"
}