package com.example.weatherforcast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    private val _unitList = MutableStateFlow<List<com.example.weatherforcast.model.Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUnits().distinctUntilChanged().collect{listOfUnits->
                if (listOfUnits.isEmpty()){
                    Log.d("TAG", "SettingViewModel: List is Empty")
                }else{
                    _unitList.value = listOfUnits
                }

            }
        }
    }

    fun insertUnit(unit: com.example.weatherforcast.model.Unit) = viewModelScope.launch { repository.insertUnit(unit) }
    fun deleteUnit(unit: com.example.weatherforcast.model.Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun updateUnit(unit: com.example.weatherforcast.model.Unit) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteAllUnit() = viewModelScope.launch { repository.deleterAllUnits() }


}