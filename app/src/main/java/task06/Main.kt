package task06

inline fun <reified T> readValue(prompt: String): T? {
    print(prompt)
    val text = readln().trim()
    return when (T::class) {
        Int::class -> text.toIntOrNull() as T?
        Double::class -> text.toDoubleOrNull() as T?
        Boolean::class -> text.toBooleanStrictOrNull() as T?
        Long::class -> text.toLongOrNull() as T?
        String::class -> text as T?
        else -> error("Unsupported type ${T::class.simpleName}")
    }
}

fun main() {
    val age = readValue<Int>("Age: ")
    val height = readValue<Double>("Height in meters: ")
    val subscribed = readValue<Boolean>("Newsletter (true/false): ")
    val phone = readValue<Long>("Phone: ")
    val city = readValue<String>("City: ")
    println("age=$age, height=$height, subscribed=$subscribed, phone=$phone, city=$city")
}

/*
Task 6 Questions & Answers:
1. Invalid inputs ("x", "1,75", "yes", ""):
   `age`, `height`, `subscribed` are all null because string parsing failed for Int, Double (comma instead of dot), and strict Boolean.
   `city` is NOT null ("") because empty string `""` is a valid String instance.

2. Removing `reified`:
   Two errors occur:
   - "Cannot use 'T' as reified type parameter" at `T::class`.
   - "Cannot type check/cast to erased type: T?" at `as T?`.
   The two places using `T` reflect runtime type reflection (`T::class`) and type casting (`as T?`).

3. Calling `readValue<Char>("City: ")`:
   It compiles successfully.
   The error occurs at RUN TIME when `else -> error("Unsupported type Char")` is reached during execution.

4. Added Long support (`text.toLongOrNull() as T?`) and called `readValue<Long>("Phone: ")` with "998901234567".
*/
