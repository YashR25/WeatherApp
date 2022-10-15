package com.example.weatherforcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherforcast.navigation.WeatherNavigation
import com.example.weatherforcast.screens.main.MainViewModel
import com.example.weatherforcast.ui.theme.WeatherForcastTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp {
                val viewModel: MainViewModel by viewModels()
                WeatherNavigation(viewModel)
            }
        }
    }
}

@Composable
fun WeatherApp(content: @Composable () -> Unit){
    WeatherForcastTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                content()
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherApp {

    }
}