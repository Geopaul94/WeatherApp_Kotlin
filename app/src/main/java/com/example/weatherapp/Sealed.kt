import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate // Example icon
import androidx.compose.material.icons.filled.WbSunny // Example icon
import androidx.compose.ui.graphics.vector.ImageVector

// Define your screens using a sealed class (good practice)
sealed class AppScreen(val route: String, val title: String, val icon: ImageVector) {
    object Weather : AppScreen("weather", "Weather", Icons.Filled.WbSunny)
    object Calculator : AppScreen("calculator", "Calculator", Icons.Filled.Calculate)
}

// List of items to show in the bottom bar
val bottomNavigationItems = listOf(
    AppScreen.Weather,
    AppScreen.Calculator
)