package task15

import kotlin.system.exitProcess

fun fail(message: String): Nothing = throw IllegalStateException(message)

fun runCalculator(): Nothing {
    var total = 0
    while (true) {
        print("> ")
        val parts = readln().trim().split(" ")
        val number = parts.getOrNull(1)?.toIntOrNull()
        when (parts[0]) {
            "add" -> total += number ?: 0
            "sub" -> total -= number ?: 0
            "reset" -> total = 0
            "mul" -> total *= number ?: 1
            "show" -> println("Total = $total")
            "exit" -> {
                println("Bye, final total = $total")
                exitProcess(0)
            }
            else -> println("Unknown command '${parts[0]}'")
        }
    }
}

fun discountFor(code: String): Int = when (code.uppercase()) {
    "STUDENT" -> 20
    "FRIEND" -> 10
    else -> fail("Unknown coupon '$code'")
}

fun main() {
    val none: List<Nothing> = emptyList()
    val names: List<String> = none
    val ages: List<Int> = none
    println("${names.size} names, ${ages.size} ages")

    try {
        print("Coupon: ")
        val coupon = readln().trim()
        if (coupon.isNotEmpty()) {
            println("Discount: ${discountFor(coupon)}%")
        }
    } catch (e: Exception) {
        println("Coupon error: ${e.message}")
    }

    println("Commands: add N, sub N, reset, mul N, show, exit")
    runCalculator()
    @Suppress("UNREACHABLE_CODE")
    println("This line is never reached")
}

/*
Task 15 Questions & Answers:
1. Why `runCalculator()` return type is `Nothing`:
   It contains an endless loop (`while (true)`) whose only exit path is `exitProcess(0)`, so it never returns normally.

2. Unreachable line in `main()`:
   `println("This line is never reached")` is flagged as unreachable code because `runCalculator()` returns `Nothing`.

3. Type of `when` in `discountFor`:
   Branches return `Int` (20), `Int` (10), and `Nothing` (`fail`).
   Because `Nothing` is a subtype of `Int`, the overall return type of `when` is inferred as `Int`.

4. `List<Nothing>` covariance:
   Kotlin's `List<out E>` interface is covariant in `E`.
   Since `Nothing` is a subtype of all types (`String`, `Int`, etc.), `List<Nothing>` is a subtype of both `List<String>` and `List<Int>`.

5. Added new commands `reset` (sets total to 0) and `mul N` (multiplies total by N).
*/
