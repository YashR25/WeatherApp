package com.example.weatherforcast.navigation

enum class WeatherScreens {
    HomeScreen,
    WeatherSplashScreen;
    companion object{
        fun getScreen(route: String?) = when(route){
            HomeScreen.name -> HomeScreen
            WeatherSplashScreen.name -> WeatherSplashScreen
            null -> HomeScreen
            else -> throw java.lang.IllegalArgumentException()
        }
    }
}