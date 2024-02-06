package com.myosetpaing.comfortweatherapp.screen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myosetpaing.comfortweatherapp.utils.Colors.forecastCardBackgroundColor
import com.myosetpaing.comfortweatherapp.utils.DateFormatter
import com.myosetpaing.comfortweatherapp.utils.Dimens
import com.myosetpaing.comfortweatherapp.utils.Dimens.SmallPadding
import com.myosetpaing.comfortweatherapp.utils.Dimens.XLargePadding
import com.myosetpaing.domain.model.ForecastDataDomain

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastCard(
    weatherData: ForecastDataDomain,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = Modifier
            .padding(vertical = Dimens.XXSmallPadding)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = forecastCardBackgroundColor
        ),
        elevation = CardDefaults.cardElevation(),
        border = null,
    ) {

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = DateFormatter.StringToDate(date = weatherData.date),
                color = Color.White
            )
            AsyncImage(
                model = "https://${weatherData.icon}",
                contentDescription = null,
                modifier = Modifier.width(XLargePadding)
            )

            Text(
                text = "${weatherData.maxtemp_c}°C",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}