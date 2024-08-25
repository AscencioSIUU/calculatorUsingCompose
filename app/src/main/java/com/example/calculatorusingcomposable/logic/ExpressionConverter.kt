package com.example.calculatorusingcomposable.logic

import android.util.Log

object ExpressionConverter {
    fun convertToPostfix(expression: String): List<String> {
        val stack = mutableListOf<String>()
        val output = mutableListOf<String>()
        val tokens = expression.split(Regex("(?<=[-+*/()^√πe])|(?=[-+*/()^√πe])")).filter { it.isNotBlank() }

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null || token == "π" || token == "e" -> {
                    output.add(token)
                }
                Operators.isOperator(token) -> {
                    while (stack.isNotEmpty() && stack.last() != "(" && Operators.precedence(stack.last()) >= Operators.precedence(token)) {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                    stack.add(token)
                }
                token == "(" -> {
                    stack.add(token)
                }
                token == ")" -> {
                    while (stack.isNotEmpty() && stack.last() != "(") {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                    if (stack.isNotEmpty() && stack.last() == "(") {
                        stack.removeAt(stack.size - 1)
                    }
                    if (stack.isNotEmpty() && stack.last() == "√") {
                        output.add(stack.removeAt(stack.size - 1))
                    }
                }
                token == "√" -> {
                    stack.add(token)
                }
                else -> throw IllegalArgumentException("Invalid token: $token")
            }
        }
        while (stack.isNotEmpty()) {
            output.add(stack.removeAt(stack.size - 1))
        }

        Log.d("ExpressionConverter", "Postfix List: $output")
        return output
    }
}