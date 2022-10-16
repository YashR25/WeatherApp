package com.example.weatherforcast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.weatherforcast.R
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(navController = navController, onAddActionClicked = { },
        title = "About", isMainScreen = false, icon = Icons.Default.ArrowBack) {
            navController.popBackStack()
        }
    }) {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = CenterHorizontally, verticalArrangement = Center) {
                Text(text = stringResource(id = R.string.about_app), style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.abpout_api), style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground)
            }
        }
    }
}