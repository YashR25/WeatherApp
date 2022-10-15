package com.example.weatherforcast.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit,
    onButtonActionClicked: () -> Unit
){
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
                     IconButton(onClick = { /*TODO*/ }) {
                         Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Button")
                     }

                 }else Box() {}
    },
    navigationIcon = {
        IconButton(onClick = { onButtonActionClicked() }) {
            if (icon != null){
                Icon(imageVector = icon, contentDescription = "Navigate Back Icon")
            }
        }
    },
    backgroundColor = Color.Transparent,
    elevation = elevation)

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit
){
    val searchQueryState = rememberSaveable() {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val validState = remember(searchQueryState) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column() {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "City",
            onAction = KeyboardActions {
                if (!validState) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            },

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
