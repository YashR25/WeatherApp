package com.example.weatherforcast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforcast.components.MainScaffold
import com.example.weatherforcast.data.DataOrException
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.screens.settings.SettingsViewModel

@Composable
fun MainScreen(viewModel: MainViewModel,
               settingsViewModel: SettingsViewModel = hiltViewModel(),
               navController: NavController, city: String?) {

    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit by remember{
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }
    if (unitFromDb.isNotEmpty()){
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial = unit == "imperial"
    }
        val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true)){
            value = viewModel.getWeatherData(cityName = city!!,
            units = unit)
        }

        if (weatherData.value.loading == true){
            CircularProgressIndicator()
        }else{
            weatherData.value.data?.let {
                MainScaffold(data = it, navController = navController,isImperial = isImperial)
            }
        }

}

