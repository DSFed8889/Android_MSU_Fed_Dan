package ru.msu.cmc.weatherapp.data.models.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.msu.cmc.weatherapp.models.WeatherItem

class WeatherItemsViewModel: ViewModel() {
    val wItemToday = MutableLiveData<WeatherItem>()
    val wItemMainList = MutableLiveData<List<WeatherItem>>()
    val wItemDetailsList = MutableLiveData<List<WeatherItem>>()
}