package com.example.weatherforcast.widgets

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforcast.model.Favourite
import com.example.weatherforcast.navigation.WeatherScreens
import com.example.weatherforcast.screens.favourite.FavouriteViewModel

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit,
    onButtonActionClicked: () -> Unit
){
    val showDialog = remember {
        mutableStateOf(false)
    }

    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

        ShowSettingDropDownMenu(showDialog = showDialog,navController = navController)

    TopAppBar(title = {
        Text(text = title,
            color = MaterialTheme.colors.onBackground,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
    }, actions = {
                 if (isMainScreen){
                     IconButton(onClick = {
                         onAddActionClicked()
                     }) {
                         Icon(imageVector = Icons.Default.Search, contentDescription = "Search Button")
                     }
                     IconButton(onClick = {
                         showDialog.value = !showDialog.value
                     }) {
                         Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Button")
                     }

                 }else Box() {}
    },
    navigationIcon = {
            if (icon != null){
                Icon(imageVector = icon, contentDescription = "Navigate Back Icon", modifier = Modifier.clickable {
                    onButtonActionClicked()
                })
            }
        if (isMainScreen){
            val isAlreadyFavList = favouriteViewModel.favList.collectAsState().value.filter {
                (it.city == title.split(",")[0])
            }
            if(isAlreadyFavList.isEmpty()){
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favourite Icon",
            modifier = Modifier
                .scale(0.9f)
                .clickable {
                    favouriteViewModel
                        .insertFavourite(
                            Favourite(
                                city = title.split(",")[0],
                                country = title.split(",")[1]
                            )
                        )
                        .run {
                            showIt.value = true
                        }
                },
            tint = MaterialTheme.colors.onBackground
            )
            }else {
                showIt.value = false
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favourite",
                tint = Color.Red.copy(0.6f),
                modifier = Modifier.clickable {
                    favouriteViewModel.deleterFavourite(
                        Favourite(city = title.split(",")[0],
                        country = title.split(",")[1])
                    )
                })
            }

            showToast(showIt,context)
        }
    },
    backgroundColor = Color.Transparent,
    elevation = elevation)

}

fun showToast(showIt: MutableState<Boolean>, context: Context) {
    if (showIt.value){
        Toast.makeText(context,"City Added to Favourites",Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("About","Favourites","Settings")
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        DropdownMenu(expanded = showDialog.value, onDismissRequest = { showDialog.value = false },
        modifier = Modifier
            .width(140.dp)
            .background(MaterialTheme.colors.background)) {
            items.forEachIndexed{index, item ->
                DropdownMenuItem(onClick = {
//                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(imageVector = when(item){
                        "About" -> Icons.Default.Info
                        "Favourites" -> Icons.Default.FavoriteBorder
                        else -> Icons.Default.Settings
                    }, contentDescription = null, tint = Color.LightGray)
                    Text(text = item, modifier = Modifier.clickable {
                                                                    navController.navigate(when(item){
                                                                        "About" -> WeatherScreens.AboutScreen.name
                                                                        "Favourites" -> WeatherScreens.FavouriteScreen.name
                                                                        else -> WeatherScreens.SettingScreen.name
                                                                    })
                    }, fontWeight = FontWeight.W300)
                }


            }


        }


    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
){
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val validState = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column() {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "City",
            onAction = KeyboardActions(onNext = {
                if (!validState) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            }),
        )
    }
}

@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeHolder: String,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    imeAction: ImeAction = ImeAction.Next,
                    onAction: KeyboardActions = KeyboardActions.Default) {

    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        label = { Text(text = placeHolder)},
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth())

}
