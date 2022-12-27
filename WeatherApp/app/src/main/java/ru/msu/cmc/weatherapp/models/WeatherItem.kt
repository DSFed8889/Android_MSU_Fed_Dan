package ru.msu.cmc.weatherapp.models

import java.util.Date

data class WeatherItem(
    val date: Date = Date(0, 0, 0),
    val temperature: Float = 0.0f,
    val town: String = "",
    val condition: String = "",
    val iconUrl: String = ""
)
