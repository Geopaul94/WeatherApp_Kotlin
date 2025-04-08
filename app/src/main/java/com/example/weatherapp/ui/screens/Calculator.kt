package com.example.weatherapp.ui.screens



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.viewmodel.CalculatorViewModel


val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*", "4", "5", "6", "+", "3", "2", "1", "-", "AC", "0", ".", "="
)

@Composable
fun Calculator(modifier: Modifier = Modifier,viewModel: CalculatorViewModel) {

    val equationText= viewModel.equationText.observeAsState()
    val resutlText= viewModel.resultText.observeAsState()

    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.End
        )
        {
            Text(
                text = equationText.value?:"", style = TextStyle(
                    fontSize = 30.sp, textAlign = TextAlign.End
                ), maxLines = 5, overflow = TextOverflow.Ellipsis
            )

            Text(
                text = resutlText.value?:"", style = TextStyle(
                    fontSize = 60.sp, textAlign = TextAlign.End
                ), maxLines = 5, overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier.height(
                    10.dp
                )
            )

            LazyVerticalGrid(columns = GridCells.Fixed(count = 4)) {
                items(buttonList) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(
                            it
                        )
                    })
                }
            }
        }
    }
}


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


fun getColor(btn: String): Color {
    return when (btn) {
        "C", "AC", "(", ")" -> Color(0xFFFF5722) // Orange for clear and parentheses
        "/", "*", "-", "=" -> Color(0xFFE53935) // Red for operators
        "+" -> Color(0xFF4CAF50) // Green for addition
        "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "." -> Color(0xFF64B5F6) // Light Blue for numbers
        else -> Color.Gray // Default color
    }
}