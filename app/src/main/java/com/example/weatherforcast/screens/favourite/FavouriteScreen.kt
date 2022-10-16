package com.example.weatherforcast.screens.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherforcast.model.Favourite
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun FavouriteScreen(navController: NavController,
favouriteViewModel: FavouriteViewModel) {
    Scaffold(topBar = {
        WeatherAppBar(navController = navController, onAddActionClicked = { /*TODO*/ },
        icon = Icons.Default.ArrowBack,
        isMainScreen = false,
        title = "Favourite Cities") {
            navController.popBackStack()
        }
    }) {

        androidx.compose.material.Surface(modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()) {
            Column(verticalArrangement = Center, horizontalAlignment = CenterHorizontally) {
                val list = favouriteViewModel.favList.collectAsState().value
                LazyColumn(){
                    items(list){
                        CityRow(favourite = it, navController, favouriteViewModel)
                    }
                }

            }
        }

    }
}

@Composable
fun CityRow(favourite: Favourite,navController: NavController,favouriteViewModel: FavouriteViewModel) {
    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favourite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
    color = Color(0xFFB2DFDB)
    ){
        
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = SpaceEvenly) {
            Text(text = favourite.city, modifier = Modifier.padding(start = 4.dp))
            androidx.compose.material.Surface(shape = CircleShape, color = Color(0xFFD1E3E1)) {
                Text(text =favourite.country, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete",
            modifier = Modifier.clickable {
                                          favouriteViewModel.deleterFavourite(favourite)
            },
            tint = Color.Red.copy(alpha = 0.3f))
        }

    }


}
