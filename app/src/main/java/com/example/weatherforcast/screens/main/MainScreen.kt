package com.example.weatherforcast.screens.main

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.example.weatherforcast.components.MainScaffold
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController, city: String?) {

    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true)){
        value = viewModel.getWeatherData(cityName = city!!)
    }

    if (weatherData.value.loading == true){
        CircularProgressIndicator()
    }else{
        weatherData.value.data?.let {
            MainScaffold(data = it, navController = navController)
        }
    }

}

