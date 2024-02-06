package com.myosetpaing.comfortweatherapp.screen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myosetpaing.comfortweatherapp.screen.WeatherState
import com.myosetpaing.comfortweatherapp.utils.Constants
import com.myosetpaing.comfortweatherapp.utils.Dimens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherForecastInfo?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = Constants.weatherForecastTest,
                fontSize = Dimens.textSizeLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(Dimens.MediumPadding))
            LazyColumn(content = {
                items(data.list!!) {
                    ForecastCard(
                        weatherData = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens.ForecastCardHeight)
                            .padding(horizontal = Dimens.MediumPadding)
                    )

                }
            })
        }
    }
}