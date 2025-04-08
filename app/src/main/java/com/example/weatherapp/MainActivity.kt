package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.* // Import Material 3 components
import androidx.compose.runtime.* // Import compose runtime functions like remember, State
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.* // Import Navigation Compose functions!
import bottomNavigationItems
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.screens.Calculator // Import Calculator composable
import com.example.weatherapp.ui.screens.WeatherPage // Import WeatherPage composable
import com.example.weatherapp.ui.viewmodel.CalculatorViewModel // Import CalculatorViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModel // Import WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class) // Opt-in for experimental Scaffold API if needed by your M3 version
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Instantiate BOTH ViewModels
        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        // Assuming you have a CalculatorViewModel, create it here too
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java] // Make sure you have this ViewModel class

        setContent {
            WeatherAppTheme {
                // 2. Create the NavController
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // 3. Add the Bottom Navigation Bar
                    bottomBar = {
                        NavigationBar {
                            // Get current route to highlight the correct item
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            // Loop through the items
                            bottomNavigationItems.forEach { screen ->
                                NavigationBarItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.title) },
                                    label = { Text(screen.title) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        // Navigate to the screen's route
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination to avoid building up stack
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination
                                            launchSingleTop = true
                                            // Restore state when reselecting
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding -> // This is the content area
                    // 4. Set up the NavHost for screen switching
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.Weather.route, // Start with Weather screen
                        modifier = Modifier.padding(innerPadding) // IMPORTANT: Apply padding
                    ) {
                        // Define the Weather screen route
                        composable(AppScreen.Weather.route) {
                            WeatherPage(
                                //modifier = Modifier, // Modifier is handled by NavHost padding now
                                viewModel = weatherViewModel
                            )
                        }
                        // Define the Calculator screen route
                        composable(AppScreen.Calculator.route) {
                            Calculator(
                                // modifier = Modifier, // Modifier is handled by NavHost padding now
                                viewModel = calculatorViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

