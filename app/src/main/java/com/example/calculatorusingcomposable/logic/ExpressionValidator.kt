package com.example.calculatorusingcomposable.logic

object ExpressionValidator {
    // Valida que los caracteres en la expresión sean válidos
    fun validateCharacters(expression: String): Boolean {
        val regex = Regex("[^\\d +\\-/*^er()\\[\\]π√]")
        return !regex.containsMatchIn(expression)
    }

    // Valida que los paréntesis en la expresión estén balanceados
    fun validateParenthesis(expression: String): Boolean {
        val stack = mutableListOf<Char>()
        for (ch in expression) {
            when (ch) {
                '(' -> stack.add(ch)
                ')' -> {
                    if (stack.isNotEmpty()) {
                        stack.removeLast()
                    } else {
                        return false
                    }
                }
            }
        }
        return stack.isEmpty()
    }

    // Valida toda la expresión
    fun isValid(expression: String): Boolean {
        return validateCharacters(expression) && validateParenthesis(expression)
    }
}