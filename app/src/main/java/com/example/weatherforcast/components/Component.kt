package com.example.weatherforcast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.weatherforcast.R
import com.example.weatherforcast.model.Weather
import com.example.weatherforcast.model.WeatherItem
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.util.formatDate
import com.example.weatherforcast.util.formatDateTime
import com.example.weatherforcast.util.formatDecimals
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun MainScaffold(data: Weather,navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(
            title = data.city.name + ", ${data.city.country}",
            navController = navController,
            onAddActionClicked = {
                                 navController.navigate(WeatherScreens.SearchScreen.name)
            },
        elevation = 5.dp){

        }
    }) {
        MainContent(data)
    }

}

@Composable
fun MainContent(data: Weather){
    

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formatDate(data.list[0].dt) ,
            modifier = Modifier.padding(6.dp),
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold)
        
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
        shape = CircleShape,
        color = Color(0xFFFFC107)
        ) {
            Column(verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally) {
                WeatherImage(data.list[0])
                Text(text = formatDecimals(data.list[0].temp.day) + "°",
                style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.onSecondary)
                Text(text = data.list[0].weather[0].main, fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.onSecondary)
            }

        }

        WeatherWindPressureRow(weather = data.list[0])
        Divider()
        SunsetAndSunRiseRow(weather = data.list[0])
        Text(text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(4.dp))
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(14.dp)) {
            LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)){
                items(data.list){item->
                    WeatherDetailRow(item)
                }

            }

            
        }

        
    }

}

@Composable
fun WeatherDetailRow(item: WeatherItem) {
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
    color = MaterialTheme.colors.background) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = SpaceBetween) {
            Text(text = formatDate(item.dt).split(",")[0],
            modifier = Modifier.padding(start = 5.dp))
            WeatherImage(data = item)
            Surface(shape = CircleShape, color = Color(0xFFFFC107)) {
                Text(text = item.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue.copy(alpha = 0.7f),
                fontWeight = FontWeight.SemiBold)){
                    append(formatDecimals(item.temp.max) + "° ")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)){
                    append(formatDecimals(item.temp.min) + "° ")
                }
            })

        }
    }

}

@Composable
fun SunsetAndSunRiseRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise), contentDescription = "SunRise",
                modifier = Modifier.size(30.dp))
            Text(text = formatDateTime(weather.sunrise), style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = formatDateTime(weather.sunset), style = MaterialTheme.typography.caption)
            Icon(painter = painterResource(id = R.drawable.sunset), contentDescription = "SunSet",
                modifier = Modifier.size(30.dp))
        }

    }

}

@Composable
fun WeatherWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity), contentDescription = "Humidity",
            modifier = Modifier.size(20.dp))
            Text(text = "${weather.humidity}%", style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure), contentDescription = "Pressure",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.pressure} psi", style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind), contentDescription = "Wind",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.speed} mph", style = MaterialTheme.typography.caption)
        }

    }

}

@Composable
private fun WeatherImage(data: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png"
    AsyncImage(
        model = imageUrl,
        contentDescription = "Weather Image",
        modifier = Modifier.size(50.dp)
    )
}