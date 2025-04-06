package com.example.weatherapp

import android.app.sdksandbox.SdkSandboxManager.SdkSandboxProcessDeathCallback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.Weather

import androidx.compose.material3.Card
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun WeatherPage(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {
    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,

                onValueChange = { newValue ->
                    city = newValue
                },
                label = {
                    Text(text = "Search for any location")
                }
            )

            IconButton(onClick = {

                viewModel.getData(
                    city
                )
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search for any location"
                )


            }
        }
        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data)
            }

            null -> {}
        }
    }
}




@Composable
fun WeatherKeyVal(key: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = key,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
    }
}

    @Composable
    fun WeatherDetails(data: Weather) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Location Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location icon",
                    modifier = Modifier.size(40.dp)
                )
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = data.location.name, fontSize = 30.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = data.location.country, fontSize = 18.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Temperature
            Text(
                text = "${data.current.temp_c}°C",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Weather Condition
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                modifier = Modifier.size(160.dp),
                model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
                contentDescription = "Condition icon",
                error = rememberVectorPainter(Icons.Default.Warning)
            )
            Text(
                text = data.current.condition.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Details Card
            Card {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherKeyVal(key = "Humidity", value = "${data.current.humidity}%")
                        WeatherKeyVal(key = "Wind Speed", value = "${data.current.wind_kph} km/h")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherKeyVal(key = "UV Index", value = "${data.current.uv}")
                        WeatherKeyVal(key = "Precipitation", value = "${data.current.precip_mm} mm")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherKeyVal(key = "Time", value = data.location.localtime.split(" ")[1])
                        WeatherKeyVal(key = "Date", value = data.location.localtime.split(" ")[0])
                    }
                }
            }
        }
    }