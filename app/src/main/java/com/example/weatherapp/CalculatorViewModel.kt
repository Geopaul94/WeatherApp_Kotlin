package com.example.weatherapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

//class CalculatorViewModel : ViewModel() {
//    private val _equationText = MutableLiveData<String>("") // Corrected initialization
//    val equationText: LiveData<String> = _equationText
//
//    private val _resultText = MutableLiveData<String>("0") // Corrected initialization
//    val resultText: LiveData<String> = _resultText
//    fun onButtonClick(btn: String) {
//        Log.i(
//            "Clikced Buttonn", btn
//        )
//
//        _equationText.value?.let {
//            if (btn == "AC") {
//                _equationText.value = ""
//                _resultText.value = "0"
//                return
//            }
//            if (btn == "C") {
//                if (it.isNotEmpty()) {
//                    _equationText.value = it.substring(0, it.length - 1)
//
//                } else if (it.isEmpty()) {
//                    _equationText.value = ""
//                }
//                return
//            }
//
//            if (btn == "=") {
//                _equationText.value = _resultText.value
//                return
//            }
//            _equationText.value = it + btn
//
//            Log.i("Equation ", _equationText.value.toString())
//
//
//            try {
//                _resultText.value = calculateResult(_equationText.value.toString())
//
//            } catch (_: Exception) {
//            }
//        }
//
//    }
//}
//Exception
//
//fun calculateResult(equation: String): String {
//
//    val context: Context = Context.enter()
//    context.optimizationLevel = -1
//    val scriptable: Scriptable = context.initStandardObjects()
//    var finalresult = context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()
//    if (finalresult.endsWith(suffix = ".0")) {
//        finalresult = finalresult.replace(oldValue = ".0", newValue = "")
//    }
//    return finalresult
//
//}

class CalculatorViewModel : ViewModel() {
    private val _equationText = MutableLiveData<String>("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData<String>("0")
    val resultText: LiveData<String> = _resultText

    fun onButtonClick(btn: String) {
        if (_equationText.value.isNullOrEmpty()) {
            if (btn == "AC" || btn == "C" || btn == "=") return
        }

        when (btn) {
            "AC" -> resetCalculator()
            "C" -> backspace()
            "=" -> calculateFinalResult()
            else -> appendToEquation(btn)
        }
    }

    private fun resetCalculator() {
        _equationText.value = ""
        _resultText.value = "0"
    }

    private fun backspace() {
        _equationText.value = if (_equationText.value.isNullOrEmpty()) "" else _equationText.value?.dropLast(1)
    }

    private fun calculateFinalResult() {
        if (_equationText.value == _resultText.value) return
        _equationText.value = _resultText.value
    }

    private fun appendToEquation(btn: String) {
        if (isOperator(btn)) {
            if (_equationText.value.isNullOrEmpty() || isLastCharOperator(_equationText.value!!)) return
        }
        updateEquation(_equationText.value.orEmpty() + btn)
    }

    private fun isOperator(char: String): Boolean {
        return char in setOf("+", "-", "*", "/")
    }

    private fun isLastCharOperator(equation: String): Boolean {
        val operators = setOf('+', '-', '*', '/')
        return equation.isNotEmpty() && operators.contains(equation.last())
    }

    private fun updateEquation(newText: String) {
        _equationText.value = newText
        try {
            _resultText.value = calculateResult(newText)
        } catch (e: Exception) {
            _resultText.value = "Error"
        }
    }

    private fun calculateResult(equation: String): String {
        val context: Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()
        val result = context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()
        Context.exit()
        return if (result.endsWith(".0")) result.dropLast(2) else result
    }
}