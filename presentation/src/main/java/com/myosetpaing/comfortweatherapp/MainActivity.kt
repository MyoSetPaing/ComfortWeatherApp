package com.myosetpaing.comfortweatherapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.myosetpaing.comfortweatherapp.screen.HomeViewModel
import com.myosetpaing.comfortweatherapp.screen.component.AnimateDottedText
import com.myosetpaing.comfortweatherapp.screen.component.EmptyScreen
import com.myosetpaing.comfortweatherapp.screen.component.WeatherCard
import com.myosetpaing.comfortweatherapp.screen.component.WeatherForecast
import com.myosetpaing.comfortweatherapp.ui.theme.ComfortWeatherAppTheme
import com.myosetpaing.comfortweatherapp.utils.Colors
import com.myosetpaing.comfortweatherapp.utils.Constants
import com.myosetpaing.comfortweatherapp.utils.Dimens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.getCurrentLocation()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            ComfortWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Colors.defaultBackgroundColor)
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                        )
                        Spacer(modifier = Modifier.height(Dimens.MediumPadding))
                        WeatherForecast(state = viewModel.state)

                    }
                    if (viewModel.state.isLoading) {
                        AnimateDottedText(text = Constants.loading)

                    }
                    viewModel.state.error?.let { error ->
                        EmptyScreen(
                            error
                        )
                    }
                }
            }
        }
    }
}

