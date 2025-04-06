package com.example.weatherapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object CalculatorScreen : Screen("calculator", "Calculator", Icons.Default.Menu)
    object WeatherScreen : Screen("weather", "Weather", Icons.Default.AddCircle)
}