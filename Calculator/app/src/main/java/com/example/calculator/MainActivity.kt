package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private var value1: Float? = null
    private var value2: Float? = null
    private var operator: String? = null
    private var isOperatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply,
            R.id.btnDivide, R.id.btnEquals, R.id.btnClear
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { view -> onButtonClick(view) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "=" -> calculate()
            "+", "-", "*", "/" -> setOperator(buttonText)
            else -> addNumber(buttonText)
        }
    }

    private fun addNumber(number: String) {
        if (isOperatorPressed) {
            result.setText("")
            isOperatorPressed = false
        }
        result.append(number)
    }

    private fun setOperator(op: String) {
        value1 = result.text.toString().toFloatOrNull()
        operator = op
        isOperatorPressed = true
    }

    private fun calculate() {
        value2 = result.text.toString().toFloatOrNull()
        if (value1 != null && value2 != null) {
            val res = when (operator) {
                "+" -> value1!! + value2!!
                "-" -> value1!! - value2!!
                "*" -> value1!! * value2!!
                "/" -> value1!! / value2!!
                else -> 0f
            }
            result.setText(res.toString())
        }
    }

    private fun clear() {
        result.setText("")
        value1 = null
        value2 = null
        operator = null
        isOperatorPressed = false
    }
}
