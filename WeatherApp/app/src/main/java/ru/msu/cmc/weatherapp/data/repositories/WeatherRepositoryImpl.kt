package ru.msu.cmc.weatherapp.data.repositories

import ru.msu.cmc.weatherapp.data.datastores.WeatherNetworkDS
import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel

class WeatherRepositoryImpl(
    private val weatherNetworkDS: WeatherNetworkDS
): WeatherRepository {

    override fun getWeatherMainList(town: String, model: WeatherItemsViewModel) =
        weatherNetworkDS.getWeatherMainList(town, model)

    override fun getWeatherDetailsList(date: String, town: String, model: WeatherItemsViewModel) =
        weatherNetworkDS.getWeatherDetailsList(date, town, model)

    override fun getWeatherToday(town: String, model: WeatherItemsViewModel) =
        weatherNetworkDS.getWeatherToday(town, model)
}