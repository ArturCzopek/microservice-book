package pl.simplecoding.calculator

enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, EXIT, WRONG_OPERATOR;

    fun isMathOperation() = this != EXIT && this != WRONG_OPERATOR

    companion object {
        fun fromString(value: String?) = when (value) {
            "+" -> ADD
            "-" -> SUBTRACT
            "*" -> MULTIPLY
            "/" -> DIVIDE
            "w" -> EXIT
            else -> WRONG_OPERATOR
        }
    }
}

fun main(args: Array<String>) {
    fun displayOptions() = println("""
        ================================================
        Możliwe działania:
        + - dodawanie
        - - odejmowanie
        * - mnożenie
        / - dzielenie
        w - wyjście z programu
    """.trimIndent())

    fun readArguments() = readLine()!!
            .split(" ")
            .mapNotNull {
                try {
                    it.toLong()
                } catch (nfe: NumberFormatException) {
                    null
                }
            }

    fun List<Long>.areArgumentsValid() = this.size == 2

    fun doMath(operation: Operation, arguments: List<Long>) {
        val (a, b) = arguments
        val result: Number = when (operation) {
            Operation.ADD -> a + b
            Operation.SUBTRACT -> a - b
            Operation.MULTIPLY -> a * b
            Operation.DIVIDE -> a / b.toDouble()
            else -> 0
        }

        println("Wynik: ${result.toDouble()}")
    }

    println("Witaj!")

    while (true) {
        displayOptions()
        val operation = Operation.fromString(readLine())

        when {
            operation.isMathOperation() -> {
                println("Podaj dwie liczby całkowite oddzielone spacją")
                val arguments = readArguments()
                if (arguments.areArgumentsValid()) doMath(operation, arguments) else println("Złe parametry! Obie liczby muszą być całkowite")
            }
            operation == Operation.EXIT -> System.exit(0)
            else -> println("Zły typ operacji")
        }
    }
}
