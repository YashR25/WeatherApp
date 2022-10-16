package com.example.weatherforcast.screens.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforcast.model.Favourite
import com.example.weatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavourites().distinctUntilChanged().collect{listOfFav->
                if (listOfFav.isEmpty()){
                    Log.d("TAG", "FavouriteViewModel: Empty Favourite List")
                }else{
                    _favList.value = listOfFav
                }
            }
        }

    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch {
        repository.insertFavourite(favourite)
    }

    fun updateFavourite(favourite: Favourite) = viewModelScope.launch {
        repository.updateFavourite(favourite)
    }

    fun deleterFavourite(favourite: Favourite) = viewModelScope.launch {
        repository.deleteFavourite(favourite)
    }

    fun deleterAll() = viewModelScope.launch {
        repository.deleterAll()
    }


}