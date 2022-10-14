package com.example.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforcast.screens.main.HomeScreen
import com.example.weatherforcast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.WeatherSplashScreen.name){
        composable(WeatherScreens.WeatherSplashScreen.name){
            WeatherSplashScreen(navController)
        }
        composable(WeatherScreens.HomeScreen.name){
            HomeScreen()
        }
    }
}