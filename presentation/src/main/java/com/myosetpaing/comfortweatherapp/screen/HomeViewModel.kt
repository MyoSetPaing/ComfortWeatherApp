package com.myosetpaing.comfortweatherapp.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myosetpaing.domain.location.LocationTracker
import com.myosetpaing.domain.model.Result
import com.myosetpaing.domain.usecase.GetCurrentWeatherUseCase
import com.myosetpaing.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val locationTracker: LocationTracker
) :
    ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set


    fun getCurrentLocation() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            var lat = 0.0
            var lon = 0.0
            locationTracker.getCurrentLocation()?.let { location ->
                lat = location.latitude
                lon = location.latitude
            }
            getCurrentData(lat, lon)
            getForecastData(lat, lon)


        }
    }

    private fun getCurrentData(lat: Double, lon: Double) {
        viewModelScope.launch {

            getCurrentWeatherUseCase.invoke(
                GetCurrentWeatherUseCase.Input(
                    lat,
                    lon
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        state = state.copy(
                            weatherCurrentInfo = it.successData,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        state = state.copy(
                            weatherCurrentInfo = null,
                            isLoading = false,
                            error = it.message
                        )
                    }
                }
            }
        }
    }

   private fun getForecastData(lat: Double, lon: Double) {
        viewModelScope.launch {
            getWeatherForecastUseCase.invoke(
                GetWeatherForecastUseCase.Input(
                    lat,
                    lon
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        state = state.copy(
                            weatherForecastInfo = it.successData,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        state = state.copy(
                            weatherForecastInfo = null,
                            isLoading = false,
                            error = it.message
                        )
                    }
                }
            }
        }
    }


}