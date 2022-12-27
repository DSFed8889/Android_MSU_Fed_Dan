package ru.msu.cmc.weatherapp.data.network

import org.json.JSONObject
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel
import ru.msu.cmc.weatherapp.models.WeatherItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DataParser(private val model: WeatherItemsViewModel) {

    fun parseWeatherToday(result: String) {
        val mainObject = JSONObject(result)
        val wItem = WeatherItem(
            SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.UK).parse(
                mainObject.getJSONObject("current").getString("last_updated")),
            mainObject.getJSONObject("current").getString("temp_c").toFloat(),
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").
                    getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").
            getJSONObject("condition").getString("icon")
        )
        model.wItemToday.value = wItem
    }

    fun parseWeatherMainList(result: String) {
        val list = ArrayList<WeatherItem>()
        val mainObject = JSONObject(result)
            .getJSONObject("forecast")
            .getJSONArray("forecastday")
        for (i in 1 until mainObject.length()) {
            val objectInfo = (mainObject[i] as JSONObject)
                .getJSONArray("hour")[12] as JSONObject

            val wItem = WeatherItem(
                SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.UK).parse(
                    objectInfo.getString("time")
                ),
                (mainObject[i] as JSONObject)
                    .getJSONObject("day")
                    .getString("avgtemp_c").toFloat(),
                JSONObject(result).getJSONObject("location").getString("name"),
                (mainObject[i] as JSONObject).getJSONObject("day")
                    .getJSONObject("condition").getString("text"),
                (mainObject[i] as JSONObject).getJSONObject("day")
                    .getJSONObject("condition").getString("icon")
            )
            list.add(wItem)
        }
        model.wItemMainList.value = list
    }

    fun parseWeatherDetailsList(date: String, result: String) {
        val list = ArrayList<WeatherItem>()
        val mainObject = JSONObject(result)
            .getJSONObject("forecast")
            .getJSONArray("forecastday")
        var wItemNumber = 0
        for (i in 0 until mainObject.length())
            if (DateFormat.getDateInstance()
                    .format(SimpleDateFormat("yyyy-MM-dd", Locale.UK).parse(
                (mainObject[i] as JSONObject).getString("date"))) == date) {
                wItemNumber = i
                break
            }

        for (i in 1 until 24) {
            val objectDayInfo = (mainObject[wItemNumber] as JSONObject).getJSONArray("hour")[i] as JSONObject

            val wItem = WeatherItem(
                SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.UK).parse(
                    objectDayInfo.getString("time")
                ),
                objectDayInfo.getString("temp_c").toFloat(),
                JSONObject(result).getJSONObject("location").getString("name"),
                objectDayInfo.getJSONObject("condition").getString("text"),
                objectDayInfo.getJSONObject("condition").getString("icon")
            )
            list.add(wItem)
        }
        model.wItemDetailsList.value = list
    }
}