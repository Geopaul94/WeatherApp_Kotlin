import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.weatherapp.ui.components.calculator.CalculatorButton
import org.junit.Rule
import org.junit.Test

class CalculatorButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testButtonDisplayAndClick() {
        var clicked = false

        composeTestRule.setContent {
            CalculatorButton(btn = "1") {
                clicked = true
            }
        }

        // Verify the button is displayed
        composeTestRule.onNodeWithText("1").assertIsDisplayed()

        // Perform a click and verify the callback is triggered
        composeTestRule.onNodeWithText("1").performClick()
        assert(clicked)
    }
}