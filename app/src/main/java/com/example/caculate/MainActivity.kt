package com.example.caculate

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var currentExpression = ""
    var firstNumber: Double? = null
    var operator: String? = null
    lateinit var solution: TextView
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        solution = findViewById(R.id.solution_tv)
        result = findViewById(R.id.result_tv)

        initializeButtons()
    }

    private fun initializeButtons() {
        val buttonIds = arrayOf(
            R.id.button_0,
            R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6,
            R.id.button_7, R.id.button_8, R.id.button_9
        )

        for (i in 0..9) {
            val button = findViewById<Button>(buttonIds[i])
            button.setOnClickListener {
                onNumberButtonClick(button)
            }
        }

        val operatorButtonIds = arrayOf(
            R.id.button_plus,
            R.id.button_minus,
            R.id.button_multiply,
            R.id.button_divide
        )

        for (i in 0 until operatorButtonIds.size) {
            val button = findViewById<Button>(operatorButtonIds[i])
            button.setOnClickListener {
                onOperatorButtonClick(button)
            }
        }

        val equalButton = findViewById<Button>(R.id.button_equals)
        equalButton.setOnClickListener {
            evaluateExpression()
        }

        val ceButton = findViewById<Button>(R.id.button_ce)
        ceButton.setOnClickListener {
            onCEButtonClick(ceButton)
        }

        val cButton = findViewById<Button>(R.id.button_c)
        cButton.setOnClickListener {
            onCButtonClick(cButton)
        }

        val bsButton = findViewById<Button>(R.id.button_bs)
        bsButton.setOnClickListener {
            onBSButtonClick(bsButton)
        }
    }

    private fun onNumberButtonClick(button: Button) {
        val buttonText = button.text.toString()
        currentExpression += buttonText
        result.text = currentExpression
    }

    private fun onCEButtonClick(ceButton: Button) {
        currentExpression = ""
        result.text = currentExpression
        firstNumber = null
        operator = null
    }

    private fun onCButtonClick(cButton: Button) {
        currentExpression = ""
        result.text = currentExpression
    }

    private fun onBSButtonClick(bsButton: Button) {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length - 1)
            result.text = currentExpression
        }
    }

    private fun onOperatorButtonClick(button: Button) {
        val buttonText = button.text.toString()

        if (firstNumber == null) {
            firstNumber = currentExpression.toDoubleOrNull()
            operator = buttonText
            currentExpression = ""
        } else {
            val secondNumber = currentExpression.toDoubleOrNull()
            if (secondNumber != null && operator != null) {
                val result = when (operator) {
                    "+" -> firstNumber!! + secondNumber
                    "-" -> firstNumber!! - secondNumber
                    "*" -> firstNumber!! * secondNumber
                    "/" -> firstNumber!! / secondNumber
                    else -> firstNumber
                }
                firstNumber = result
                operator = buttonText
                currentExpression = ""

            }
        }
    }

    private fun evaluateExpression() {
        try {
            if (firstNumber != null && operator != null) {
                val secondNumber = currentExpression.toDoubleOrNull()
                if (secondNumber != null) {
                    val result = when (operator) {
                        "+" -> firstNumber!! + secondNumber
                        "-" -> firstNumber!! - secondNumber
                        "*" -> firstNumber!! * secondNumber
                        "/" -> firstNumber!! / secondNumber
                        else -> firstNumber
                    }
                    currentExpression = result.toString()
                    solution.text = currentExpression
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
