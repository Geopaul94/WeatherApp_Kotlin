package com.example.weatherapp.ui.components.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) { // Added onClick parameter
    Box(modifier = Modifier.padding(8.dp)) { // Reduced padding for FABs
        FloatingActionButton(
            onClick = onClick, // Use the passed onClick
            containerColor = getColor(btn),
            modifier = Modifier.size(80.dp) // Added padding to the FAB itself
        ) {
            Text(text = btn, fontSize = 20.sp)
        }
    }
}
