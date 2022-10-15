package com.example.weatherforcast.navigation

enum class WeatherScreens {
    MainScreen,
    WeatherSplashScreen,
    SearchScreen;
    companion object{
        fun getScreen(route: String?) = when(route){
            MainScreen.name -> MainScreen
            WeatherSplashScreen.name -> WeatherSplashScreen
            SearchScreen.name -> SearchScreen
            null -> MainScreen
            else -> throw java.lang.IllegalArgumentException()
        }
    }
}