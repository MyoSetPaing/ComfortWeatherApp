package com.myosetpaing.comfortweatherapp.screen.component

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.myosetpaing.comfortweatherapp.screen.WeatherState
import com.myosetpaing.comfortweatherapp.utils.Dimens.ForecastCardHeight
import com.myosetpaing.comfortweatherapp.utils.Dimens.LargePadding
import com.myosetpaing.comfortweatherapp.utils.Dimens.MediumPadding
import kotlin.math.roundToInt
import com.myosetpaing.comfortweatherapp.R
import com.myosetpaing.comfortweatherapp.utils.Constants
import com.myosetpaing.comfortweatherapp.utils.Dimens
import com.myosetpaing.comfortweatherapp.utils.Dimens.textSizeLarge
import com.myosetpaing.comfortweatherapp.utils.Dimens.textSizeXXLarge

@Composable
fun WeatherCard(
    state: WeatherState
) {
    state.weatherCurrentInfo.let { data ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Constants.myLocation,
                fontSize = textSizeLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(MediumPadding))
            AsyncImage(
                model = "https://${data?.icon}",
                contentDescription = null,
                modifier = Modifier.width(ForecastCardHeight)
            )
            Spacer(modifier = Modifier.height(MediumPadding))
            Text(
                text = data?.name?:"",
                fontSize = textSizeXXLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(MediumPadding))
            Text(
                text = data?.description ?: "",
                fontSize = textSizeLarge,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(LargePadding))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    value = data?.pressure?.roundToInt() ?: 0,
                    unit = "hpa",
                    icon = ImageVector.vectorResource(id =R.drawable.ic_pressure),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                WeatherDataDisplay(
                    value = data?.humidity ?: 0,
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                WeatherDataDisplay(
                    value = data?.windSpeed?.roundToInt() ?: 0,
                    unit = "km/h",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
            }
        }

    }
}