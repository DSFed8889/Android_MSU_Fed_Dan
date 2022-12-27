package ru.msu.cmc.weatherapp.presentation.mainlist

import ru.msu.cmc.weatherapp.models.WeatherItem

interface OnWeatherContainerClicked {
    fun onWeatherContainerClick(wItem: WeatherItem)
}