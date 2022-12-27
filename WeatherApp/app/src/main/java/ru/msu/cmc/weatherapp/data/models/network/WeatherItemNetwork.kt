package ru.msu.cmc.weatherapp.data.models.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ru.msu.cmc.weatherapp.data.network.DataParser

const val API_KEY = "b9c31bc7c21744638c3143722222612"

class WeatherItemNetwork(
    private val dataParser: DataParser
) {

    fun requestWeatherToday(city: String, context: Context) {
        val url = "http://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "14" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result -> dataParser.parseWeatherToday(result)
            },
            {
                    error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    fun requestWeatherMainList(city: String, context: Context) {
        val url = "http://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "14" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> dataParser.parseWeatherMainList(result)
            },
            {
                    error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    fun requestWeatherDetailsList(date: String, city: String, context: Context) {
        val url = "http://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "14" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> dataParser.parseWeatherDetailsList(date, result)
            },
            {
                    error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }
}