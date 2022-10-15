package com.example.weatherforcast.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val _data = MutableStateFlow<DataOrException<Weather,Boolean,Exception>>(DataOrException(loading = true))
    val data: StateFlow<DataOrException<Weather,Boolean,Exception>>
    get() = _data

    init {
        getWeatherInfo(cityName = "surat")
    }

    private fun getWeatherInfo(cityName: String){
        _data.value.loading = true
        viewModelScope.launch {
            with(Dispatchers.IO){
                _data.value = repository.getWeatherInfo(cityName)
                if (_data.value.data.toString().isNotEmpty()) _data.value.loading = false
             }
        }
    }
}