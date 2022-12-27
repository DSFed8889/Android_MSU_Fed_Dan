package ru.msu.cmc.weatherapp.data.repositories

import ru.msu.cmc.weatherapp.data.models.network.WeatherItemsViewModel

interface WeatherRepository {

    fun getWeatherMainList(town: String, model: WeatherItemsViewModel)
    fun getWeatherDetailsList(date: String, town: String, model: WeatherItemsViewModel)
    fun getWeatherToday(town: String, model: WeatherItemsViewModel)
}