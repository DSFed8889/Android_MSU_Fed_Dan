package ru.msu.cmc.weatherapp.data.datastores

import ru.msu.cmc.weatherapp.models.WeatherItem
import java.util.*

class MockWeatherMainItemDS {

    fun getWeatherMainList(): List<WeatherItem> = listOf(
        WeatherItem(
            date = Calendar.getInstance().time,
            temperature = -28f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 31),
            temperature = 28f,
            town = "California",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 15),
            temperature = -2f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Calendar.getInstance().time,
            temperature = -28f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 31),
            temperature = 28f,
            town = "California",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 15),
            temperature = -2f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Calendar.getInstance().time,
            temperature = -28f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 31),
            temperature = 28f,
            town = "California",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 15),
            temperature = -2f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Calendar.getInstance().time,
            temperature = -28f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 31),
            temperature = 28f,
            town = "California",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 15),
            temperature = -2f,
            town = "Moscow",
            condition = "rainy"
        )
    )

    fun getWeatherDetailsList(date: String, town: String): List<WeatherItem> = listOf(
        WeatherItem(
            date = Calendar.getInstance().time,
            temperature = -28f,
            town = "Moscow",
            condition = "rainy"
        ),
        WeatherItem(
            date = Date(2022, 12, 31, 6, 0),
            temperature = -29f,
            town = "Moscow",
            condition = "rainy"
        )
    )
    fun getWeatherToday(): WeatherItem = WeatherItem(
        date = Calendar.getInstance().time,
        temperature = -49f,
        town = "Moscow",
        condition = "sunny"
    )
}