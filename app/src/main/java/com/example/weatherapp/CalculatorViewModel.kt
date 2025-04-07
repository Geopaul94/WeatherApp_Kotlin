package com.example.weatherapp


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

