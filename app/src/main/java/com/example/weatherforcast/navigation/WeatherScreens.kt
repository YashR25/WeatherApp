package com.example.weatherforcast.navigation

enum class WeatherScreens {
    MainScreen,
    WeatherSplashScreen,
    SearchScreen,
    AboutScreen,
    FavouriteScreen,
    SettingScreen;
    companion object{
        fun getScreen(route: String?) = when(route){
            MainScreen.name -> MainScreen
            WeatherSplashScreen.name -> WeatherSplashScreen
            SearchScreen.name -> SearchScreen
            AboutScreen.name -> AboutScreen
            FavouriteScreen.name -> FavouriteScreen
            SettingScreen.name -> SettingScreen
            null -> MainScreen
            else -> throw java.lang.IllegalArgumentException()
        }
    }
}