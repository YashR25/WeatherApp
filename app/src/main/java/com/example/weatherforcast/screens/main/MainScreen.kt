package com.example.weatherforcast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.weatherforcast.components.MainScaffold

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {

    val data = viewModel.data.collectAsState()

    if (data.value.loading == true){
        CircularProgressIndicator()
    }else{
        data.value.data?.let {
            MainScaffold(data = it, navController = navController)
        }
    }


}

