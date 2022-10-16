package com.example.weatherforcast.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherforcast.model.Unit
import com.example.weatherforcast.widgets.WeatherAppBar

@Composable
fun SettingScreen(navController: NavController,
viewModel: SettingsViewModel) {

    var unitToggleState by remember {
        mutableStateOf(false)
    }
    val measurements = listOf("Imperial (F)","Metric (C)")


    val choiceFromDb = viewModel.unitList.collectAsState()
    var choiceState by remember {
        mutableStateOf("")
    }
    if (choiceFromDb.value.isEmpty()){
        choiceState = measurements[0]
    }else{
        choiceState = choiceFromDb.value[0].unit
    }
    val defaultChoice = if(choiceFromDb.value.isEmpty()) measurements[0] else choiceFromDb.value[0].unit


    Scaffold(topBar = {
        WeatherAppBar(navController = navController, onAddActionClicked = { /*TODO*/ },
        isMainScreen = false,
        icon = Icons.Default.ArrowBack,
        title = "Settings") {
            navController.popBackStack()

        }
    }) {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Center, horizontalAlignment = CenterHorizontally,
            modifier = Modifier.padding(bottom = 15.dp)) {
                IconToggleButton(checked = !unitToggleState, onCheckedChange = {
                    unitToggleState = !it
                    if (unitToggleState){
                        choiceState = "Imperial (F)"
                    }else{
                        choiceState = "Metric (C)"

                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clip(RectangleShape)
                    .padding(5.dp)
                    .background(Color.Magenta.copy(alpha = 0.4f))) {
                    Text(text = when(choiceState){
                            "Imperial (F)" -> "Fahrenheit °F"
                            else -> "Celsius °C"
                        })
//                        if (unitToggleState) "Fahrenheit °F" else "Celsius °C")
                }
                
                Button(onClick = {
                                 viewModel.deleteAllUnit()
                    viewModel.insertUnit(Unit(choiceState))
                },
                modifier = Modifier
                    .padding(3.dp)
                    .align(CenterHorizontally),
                shape = CircleShape) {
                    Text(text = "Save", modifier = Modifier.padding(4.dp), color = Color.White, fontSize = 17.sp)
                }

            }
            
        }

    }
}