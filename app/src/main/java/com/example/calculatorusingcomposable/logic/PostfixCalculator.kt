package com.example.calculatorusingcomposable.logic

import android.util.Log
import kotlin.math.E
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

object PostfixCalculator {
    private val stack = mutableListOf<Double>()

    fun calculatePostfix(expression: List<String>): Double {
        stack.clear() // Asegúrate de limpiar la pila antes de cada cálculo
        for (token in expression) {
            Log.d("PostfixCalculator", "Current: $token, Stack: $stack")
            when {
                token.toDoubleOrNull() != null -> {
                    stack.add(token.toDouble())
                    Log.d("PostfixCalculator", "Added to stack: $token, Stack: $stack")
                }
                Operators.isOperator(token) -> {
                    val result = when (token) {
                        "+" -> {
                            val b = stack.removeAt(stack.size - 1)
                            val a = stack.removeAt(stack.size - 1)
                            a + b
                        }
                        "-" -> {
                            val b = stack.removeAt(stack.size - 1)
                            val a = stack.removeAt(stack.size - 1)
                            a - b
                        }
                        "*" -> {
                            val b = stack.removeAt(stack.size - 1)
                            val a = stack.removeAt(stack.size - 1)
                            a * b
                        }
                        "/" -> {
                            val b = stack.removeAt(stack.size - 1)
                            val a = stack.removeAt(stack.size - 1)
                            a / b
                        }
                        "^" -> {
                            val b = stack.removeAt(stack.size - 1)
                            val a = stack.removeAt(stack.size - 1)
                            a.pow(b)
                        }
                        "√" -> {
                            val a = stack.removeAt(stack.size - 1)
                            sqrt(a)
                        }
                        else -> throw IllegalArgumentException("Invalid operator: $token")
                    }
                    stack.add(result)
                    Log.d("PostfixCalculator", "Computed with $token = $result, Stack: $stack")
                }
                token == "π" -> {
                    stack.add(PI)
                }
                token == "e" -> {
                    stack.add(E)
                }
                else -> throw IllegalArgumentException("Invalid token: $token")
            }
        }
        return stack.last()
    }
}