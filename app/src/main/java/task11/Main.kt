package task11

typealias Validator = (String) -> Boolean
typealias Predicate<T> = (T) -> Boolean
typealias Rule = Predicate<String>

typealias Meters = Double
typealias Feet = Double

val notBlank: Validator = { it.isNotBlank() }
val isEmail: Validator = { '@' in it && '.' in it.substringAfter('@') }
val isStrongPassword: Validator = { it.length >= 8 && it.any(Char::isDigit) }
val startsWithCode: Rule = { it.startsWith("+998") }

fun check(label: String, input: String, validator: Validator) {
    println("$label: ${if (validator(input)) "OK" else "INVALID"}")
}

fun <T> countMatching(items: List<T>, rule: Predicate<T>): Int = items.count(rule)

fun toFeet(length: Meters): Feet = length * 3.28084

fun describeRoom(width: Meters) {
    println("Room width: $width m")
}

fun roomDemo() {
    print("Room width in meters: ")
    val width: Meters = readln().trim().toDoubleOrNull() ?: 4.0
    val feet: Feet = toFeet(width)
    println("That is $feet feet")
    describeRoom(feet)
}

fun main() {
    print("E-mail: ")
    val email = readln().trim()
    print("Password: ")
    val password = readln()
    check("E-mail", email, isEmail)
    check("Password", password, isStrongPassword)
    check("Not blank", email, notBlank)
    val plain: (String) -> Boolean = isEmail
    println("Used as a plain function type: ${plain(email)}")
    println("Valid e-mails in the list: ${countMatching(listOf(email, "x@y.uz", "bad"), isEmail)}")

    println("--- Phone check using Rule alias ---")
    print("Enter 3 phone numbers separated by spaces: ")
    val phones = readln().trim().split(" ").filter { it.isNotEmpty() }
    println("Phones matching +998: ${countMatching(phones, startsWithCode)}")

    println("--- Room Demo ---")
    roomDemo()
}

/*
Task 11 Questions & Answers:
1. Interchanging plain lambdas and aliases:
   - Plain lambda passed to `Validator`: `check("Not blank", email) { it.isNotBlank() }`
   - `Validator` assigned to plain function variable: `val plain: (String) -> Boolean = isEmail`
   Both compile because `Validator` is a type alias for `(String) -> Boolean`.

2. Why `describeRoom(feet)` compiles when `describeRoom` expects `Meters`:
   Both `Meters` and `Feet` are type aliases for `Double`. The compiler sees both parameters as primitive `Double`s.
   Protection mechanism: Use Kotlin `value class` (inline value class, e.g. `value class Meters(val value: Double)`), which enforces strict compile-time type safety without runtime box overhead.

3. Added `typealias Rule = Predicate<String>` and `startsWithCode` (`startsWith("+998")`).
*/
