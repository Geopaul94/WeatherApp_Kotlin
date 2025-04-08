package com.example.weatherapp.ui.components.calculator

import androidx.compose.ui.graphics.Color

fun getColor(btn: String): Color {
    return when (btn) {
        "C", "AC", "(", ")" -> Color(0xFFFF5722) // Orange for clear and parentheses
        "/", "*", "-", "=" -> Color(0xFFE53935) // Red for operators
        "+" -> Color(0xFF4CAF50) // Green for addition
        "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "." -> Color(0xFF64B5F6) // Light Blue for numbers
        else -> Color.Gray // Default color
    }
}