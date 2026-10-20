package task13

fun Any.typeName(): String = this::class.simpleName ?: "anonymous"

fun describe(value: Any?): String = when (value) {
    null -> "no value"
    is Int -> "a number, doubled it is ${value * 2}"
    is Double -> "a decimal number, doubled it is ${value * 2}"
    is Boolean -> if (value) "switched ON" else "switched OFF"
    is String -> "a text of ${value.length} characters"
    else -> "an unknown type: ${value.typeName()}"
}

fun main() {
    val settings: Map<String, Any?> = mapOf(
        "volume" to 7,
        "darkMode" to true,
        "username" to "Dilnoza",
        "theme" to null,
        "ratio" to 1.5,
    )
    println("Settings: ${settings.keys}")
    print("Setting name: ")
    val key = readln().trim()
    if (key !in settings) {
        println("No such setting")
        return
    }
    val value = settings[key]
    println("$key is ${describe(value)}")

    val asNumber = value as? Int
    println("as? Int gives: $asNumber")
    val asText = value as? String
    println("as? String gives: $asText")

    println("--- Testing extension function typeName() ---")
    println(5.typeName())
    println("x".typeName())
}

/*
Task 13 Questions & Answers:
1. Handling `ratio`:
   Before adding `is Double`, `ratio` hit the `else` branch ("an unknown type: Double").

2. Behavior of unsafe cast (`value as String`):
   - `username` ("Dilnoza"): succeeds.
   - `volume` (7): throws `ClassCastException: java.lang.Integer cannot be cast to java.lang.String`.
   - `darkMode` (true): throws `ClassCastException`.
   - `theme` (null): throws `NullPointerException` / `TypeCastException` (null cannot be cast to non-null String).

3. `as? String` vs `as String?`:
   - `as? String` (safe cast): returns `null` for non-matching types or null values without throwing an exception.
   - `as String?` (unsafe cast to nullable String): permits `null`, but still throws `ClassCastException` for incompatible types (like Int).

4. Extension function on `Any`:
   `typeName()` can be called on any non-null expression because `Any` is the top type of Kotlin's type hierarchy.

5. Added `is Double` branch to `describe()`.
*/
