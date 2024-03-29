package com.example.weatherforcast.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforcast.R
import com.example.weatherforcast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController) {

    val defaultCity = "surat"

    val scaleState = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scaleState.animateTo(targetValue = 0.9f,
        animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            }
        )
        )
        delay(3000)
        navController.navigate(WeatherScreens.MainScreen.name + "/$defaultCity")
    })
    androidx.compose.material.Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scaleState.value)
        , shape = CircleShape,
    border = BorderStroke(width = 2.dp, color = Color.LightGray),
        color = MaterialTheme.colors.background
    ) {
        Column(verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.padding(1.dp)) {
            Image(painter = painterResource(id = R.drawable.sun) ,
                contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .padding(4.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground))
            Text(text = "Find the Sun ?", style = MaterialTheme.typography.h5, color = Color.LightGray)
        }


    }
}