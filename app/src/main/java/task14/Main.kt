package task14

fun fail(message: String): Nothing = throw IllegalArgumentException(message)

fun readAge(): Int {
    print("Age: ")
    val age = readln().trim().toIntOrNull() ?: fail("Age must be a whole number")
    if (age < 0) fail("Age cannot be negative")
    return age
}

fun main() {
    try {
        val age = readAge()
        println("You will be ${age + 1} next year")
    } catch (e: IllegalArgumentException) {
        println("Input error: ${e.message}")
    }
}

/*
Task 14 Questions & Answers:
1. Inputs 20, abc, -5:
   - "20": prints "You will be 21 next year".
   - "abc": throws exception in `fail("Age must be a whole number")`, caught in `main` print "Input error: Age must be a whole number".
   - "-5": throws exception in `fail("Age cannot be negative")`, caught in `main` print "Input error: Age cannot be negative".

2. Type of `age`:
   Type is `Int` (not `Int?`).
   Because `fail(...)` returns `Nothing`, Kotlin's type system combines `Int? ?: Nothing` into non-null `Int` (since `Nothing` is a bottom type, a subtype of all Kotlin types).

3. Changing `fail` return type to `Unit`:
   Error: `toIntOrNull() ?: fail(...)` produces `Any` (union of `Int` and `Unit`), causing type mismatch error when assigning to `val age: Int`.

4. Adding `fail("always")` followed by `println("after fail")`:
   The IDE flags `println("after fail")` as "Unreachable code" because the compiler knows a function returning `Nothing` never returns normally.

5. Standard library `TODO()` and `error()` return types:
   Both return `Nothing`.
*/
