package task01

inline fun <T> ask(prompt: String, parse: (String) -> T?): T {
    while (true) {
        print(prompt)
        val value = parse(readln().trim())
        if (value != null) return value
        println("Invalid input, try again.")
    }
}

fun main() {
    var attempts = 0
    val age = ask("Your age (0-120): ") { text ->
        attempts++
        text.toIntOrNull()?.takeIf { it in 0..120 }
    }
    println("Age accepted: $age after $attempts attempt(s)")
}

/*
Task 1 Questions & Answers:
1. Inputs: "abc", "200", "35".
   Attempts reported: 3 attempts.
   Why lambda returns null:
   - "abc": `toIntOrNull()` returns null because "abc" is not a valid integer string.
   - "200": `toIntOrNull()` returns 200, but `.takeIf { it in 0..120 }` returns null because 200 is outside 0..120.

2. Bytecode comparison:
   - With `inline`: The loop body of `ask` and the body of the lambda are directly inlined (copied) into `main`.
     No call to `ask()` exists, and no `Ref.IntRef` or `Function1` object is generated because `attempts` is mutated in place in `main`.
   - Without `inline`: A call to `ask()` appears. A lambda object (Function1) is instantiated.
     Because the lambda captures and mutates `var attempts`, `attempts` is wrapped in a Kotlin `Ref.IntRef` object.

3. Explanation to friend marking a 200-line function `inline`:
   "Inlining pays off primarily for small higher-order functions taking lambdas, where it eliminates lambda object allocations and virtual call overhead.
    Inlining a massive 200-line function will duplicate its entire bytecode at every call site, inflating binary/DEX size significantly with no practical performance benefit."
*/
