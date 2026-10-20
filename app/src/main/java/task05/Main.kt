package task05

inline fun orSkip(text: String?, onNull: () -> Double): Double = text?.toDoubleOrNull() ?: onNull()

fun main() {
    val prices = mutableListOf<Double>()
    println("Enter prices, one per line. Type 'done' to finish.")
    while (true) {
        val line = readln().trim()
        if (line == "done") break
        val price = orSkip(line) {
            println("'$line' is not a number, skipped")
            continue
        }
        price.takeIf { it > 10_000 }?.let {
            println("Price $it is too large, stopping")
            break
        }
        prices.add(price)
    }
    println("Items: ${prices.size}, total: ${prices.sum()}")
}

/*
Task 5 Questions & Answers:
1. Runs & Outputs:
   - Inputs: 10, abc, 25.5, done -> Output: Items: 2, total: 35.5
   - Inputs: 10, abc, 20000, 5 -> Output: Items: 1, total: 10.0 (stopping on 20000, 5 is never read).

2. Lambdas, inline functions, and jumps:
   - `continue` is inside the lambda passed to `orSkip` (or `run`). It skips to the next iteration of the `while (true)` loop in `main`.
   - `break` (the second break) is inside the lambda passed to `let`. It breaks out of the enclosing `while (true)` loop in `main`.

3. Non-inline `orSkip` error:
   "The break or continue expression is not allowed here".
   Difference: `run` and `let` are inline functions provided by the standard library, whereas custom `orSkip` without `inline` cannot accept non-local control flow constructs (`break`/`continue`).

4. Adding `inline` to `orSkip`:
   The compiler error disappears.
   Lecture 11 explanation: Non-local `break` and `continue` statements are permitted inside lambdas passed to `inline` functions when the inline call site is located within an outer loop.
*/
