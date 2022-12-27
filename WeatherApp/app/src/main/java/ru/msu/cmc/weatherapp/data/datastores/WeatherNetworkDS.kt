package ru.msu.cmc.weatherapp.data.datastores

import android.content.Context
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemNetwork
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel
import ru.msu.cmc.weatherapp.data.network.DataParser

class WeatherNetworkDS(
    private val context: Context
) {

    fun getWeatherToday(town: String, model: WeatherItemsViewModel){
        val parser = DataParser(model)
        WeatherItemNetwork(parser).requestWeatherToday(town, context)
    }

    fun getWeatherMainList(town: String, model: WeatherItemsViewModel){
        val parser = DataParser(model)
        WeatherItemNetwork(parser).requestWeatherMainList(town, context)
    }

    fun getWeatherDetailsList(date: String, town: String, model: WeatherItemsViewModel){
        val parser = DataParser(model)
        WeatherItemNetwork(parser).requestWeatherDetailsList(date, town, context)
    }
}