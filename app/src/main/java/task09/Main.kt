package task09

@PublishedApi
internal var debugEnabled = false

inline fun debug(message: () -> String) {
    if (debugEnabled) println("[DEBUG] ${message()}")
}

fun buildReport(): String {
    println("(building the report...)")
    return "report with 1000 rows"
}

fun main() {
    print("Enable debug logging? (yes/no): ")
    debugEnabled = readln().trim().equals("yes", ignoreCase = true)
    debug { buildReport() }
    println("Program finished")
}

/*
Task 9 Questions & Answers:
1. Initial error on `private var debugEnabled`:
   "Public-API inline function cannot access non-public-API 'private var debugEnabled'".
   Changing `private` to `internal` yields: "Public-API inline function cannot access non-public-API 'internal var debugEnabled'".

2. Adding `@PublishedApi`:
   Program compiles cleanly.

3. Executing with "yes" vs "no":
   - "yes" prints "(building the report...)" followed by "[DEBUG] report with 1000 rows".
   - "no" skips "(building the report...)" completely.
   Why: The message parameter is a lambda (`() -> String`). When `debugEnabled` is false, `message()` is never invoked, enabling lazy evaluation.

4. Eager evaluation comparison (`debug(message: String)` with `debug(buildReport())`):
   When `debug` accepts a `String` parameter, `buildReport()` executes eagerly before `debug()` is invoked, running `(building the report...)` even if `debugEnabled` is false.
   The inline lambda version defers evaluation, executing `buildReport()` only when debug logging is active.
*/
