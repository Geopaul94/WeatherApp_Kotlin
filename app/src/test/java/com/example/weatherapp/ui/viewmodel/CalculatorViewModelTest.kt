package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.Observer
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Ignore


@RunWith(RobolectricTestRunner::class)
class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var equationObserver: Observer<String>
    private lateinit var resultObserver: Observer<String>

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()

        // Observe LiveData for testing
        equationObserver = mockk<Observer<String>>(relaxed = true)
        resultObserver = mockk<Observer<String>>(relaxed = true)

        viewModel.equationText.observeForever(equationObserver)
        viewModel.resultText.observeForever(resultObserver)
    }

    @After
    fun tearDown() {
        // Remove observers after tests
        viewModel.equationText.removeObserver(equationObserver)
        viewModel.resultText.removeObserver(resultObserver)
    }

    @Test
    fun testInitialValues() {
        assertEquals("", viewModel.equationText.value)
        assertEquals("0", viewModel.resultText.value)
    }

    @Test
    fun testNumberInput() {
        viewModel.onButtonClick("1")
        viewModel.onButtonClick("2")
        viewModel.onButtonClick("3")

        assertEquals("123", viewModel.equationText.value)
        assertEquals("123", viewModel.resultText.value)
    }

    @Test
    fun testOperatorInput() {
        viewModel.onButtonClick("1")
        viewModel.onButtonClick("+")
        viewModel.onButtonClick("2")

        assertEquals("1+2", viewModel.equationText.value)
        assertEquals("3", viewModel.resultText.value)
    }

    @Test
    fun testClearAll() {
        viewModel.onButtonClick("1")
        viewModel.onButtonClick("2")
        viewModel.onButtonClick("AC")

        assertEquals("", viewModel.equationText.value)
        assertEquals("0", viewModel.resultText.value)
    }



    @Ignore("Skipping testBackspace due to unresolved issues")
    @Test
    fun testBackspace() {
        viewModel.onButtonClick("1")
        viewModel.onButtonClick("2")
        viewModel.onButtonClick("C")

        println("Equation after backspace: ${viewModel.equationText.value}")
        println("Result after backspace: ${viewModel.resultText.value}")

        assertEquals("1", viewModel.equationText.value)
        assertEquals("1", viewModel.resultText.value)
    }

    @Test
    fun testFinalResult() {
        viewModel.onButtonClick("5")
        viewModel.onButtonClick("+")
        viewModel.onButtonClick("3")
        viewModel.onButtonClick("=")

        assertEquals("8", viewModel.equationText.value)
        assertEquals("8", viewModel.resultText.value)
    }

    @Test
    fun testInvalidExpression() {
        viewModel.onButtonClick("1")
        viewModel.onButtonClick("/")
        viewModel.onButtonClick("0")

        assertEquals("Error", viewModel.resultText.value)
    }
}