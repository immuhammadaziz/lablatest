package task07

inline fun <reified T> List<Any>.onlyOfType(): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (item is T) result.add(item)
    }
    return result
}

fun parseToken(token: String): Any =
    token.toIntOrNull() ?: token.toDoubleOrNull() ?: token.toBooleanStrictOrNull() ?: token

fun main() {
    print("Items separated by spaces (for example: 10 apples 2.5 7): ")
    val items: List<Any> = readln().trim().split(" ")
        .filter { it.isNotEmpty() }
        .map(::parseToken)
    println("All:      $items")
    println("Ints:     ${items.onlyOfType<Int>()}")
    println("Doubles:  ${items.onlyOfType<Double>()}")
    println("Booleans: ${items.onlyOfType<Boolean>()}")
    println("Words:    ${items.onlyOfType<String>()}")
}

/*
Task 7 Questions & Answers:
1. Input: "10 apples 2.5 7 pears 0.75":
   - Ints: [10, 7]
   - Doubles: [2.5, 0.75]
   - Words: [apples, pears]
   Why 7 is Int: `token.toIntOrNull()` is evaluated first in `parseToken()`, successfully converting "7" to Int before Double is attempted.

2. Removing `reified`:
   Error: "Cannot check for instance of erased type: T".
   'Erased type' means generic type arguments are stripped during JVM compilation, leaving type checks (`is T`) impossible at runtime without reification.

3. Comparing with `filterIsInstance`:
   Output is identical. `filterIsInstance` has modifiers `inline` and `reified`.

4. Added Boolean support (`toBooleanStrictOrNull()`) and tested with "true 5 false x":
   Booleans list: [true, false].
*/
