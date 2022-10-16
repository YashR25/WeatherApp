package com.example.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforcast.screens.main.MainScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherforcast.screens.about.AboutScreen
import com.example.weatherforcast.screens.favourite.FavouriteScreen
import com.example.weatherforcast.screens.main.MainViewModel
import com.example.weatherforcast.screens.search.SearchScreen
import com.example.weatherforcast.screens.settings.SettingScreen
import com.example.weatherforcast.screens.splash.WeatherSplashScreen
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.WeatherSplashScreen.name){
        composable(WeatherScreens.WeatherSplashScreen.name){
            WeatherSplashScreen(navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
        arguments = listOf(
            navArgument(name = "city"){
                type = NavType.StringType
            }
        )
        ){navBack->
            navBack.arguments?.getString("city")?.let {city->
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(viewModel,navController,city)
            }

        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController)
        }

        composable(WeatherScreens.FavouriteScreen.name){
            FavouriteScreen(navController, favouriteViewModel = hiltViewModel())
        }

        composable(WeatherScreens.SettingScreen.name){
            SettingScreen(navController)
        }

    }
}