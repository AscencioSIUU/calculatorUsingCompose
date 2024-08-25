package com.example.calculatorusingcomposable.logic

object Operators {
    private val precedenceMap = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2,
        "^" to 3,  // Añadir precedencia para el operador de potencia
        "√" to 3  // Añadir precedencia para el operador de raíz
    )

    fun isOperator(token: String): Boolean {
        return precedenceMap.containsKey(token)
    }

    fun precedence(operator: String): Int {
        return precedenceMap[operator] ?: throw IllegalArgumentException("Invalid operator: $operator")
    }
}