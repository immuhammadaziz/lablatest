package task03

val contacts = listOf("Ali Karimov", "Dilnoza Yusupova", "Aliyah Smith", "Bobur Aliev")

fun findFirst(query: String) {
    contacts.forEach {
        if (it.contains(query, ignoreCase = true)) {
            println("First match: $it")
            return
        }
    }
    println("No contact contains '$query'")
}

fun findAll(query: String) {
    contacts.forEach {
        if (!it.contains(query, ignoreCase = true)) return@forEach
        println("Match: $it")
    }
}

inline fun each(items: List<String>, action: (String) -> Unit) {
    for (item in items) action(item)
}

fun findWithEach(query: String) {
    each(contacts) {
        if (it.contains(query, ignoreCase = true)) {
            println("First match: $it")
            return
        }
    }
}

fun main() {
    print("Search: ")
    val query = readln().trim()
    findFirst(query)
    findAll(query)
    findWithEach(query)
    println("Search finished")
}

/*
Task 3 Questions & Answers:
1. Difference between findFirst and findAll output:
   - `findFirst` uses non-local `return`, which exits `findFirst()` entirely as soon as the first match is found.
   - `findAll` uses `return@forEach` (local return), which returns from the lambda for non-matching items and continues iterating over all elements in `contacts`.

2. `return@forEach` in `findAll`:
   It skips the remaining execution of the lambda for the current element and moves to the next element, functioning like `continue` in a standard `for` loop.

3. Unreachable line in `findFirst`:
   `println("No contact contains '$query'")` is never executed when a match is found because non-local `return` exits `findFirst` immediately.
   "Search finished" is still printed because `return` exits `findFirst` back to its caller `main()`.

4. Error in non-inline `each`:
   "Returns are not allowed here".
   Difference: standard `forEach` is an `inline` function, whereas non-inlined `each` receives a function object, so non-local returns are illegal.

5. Rule completion:
   A bare return is allowed in a lambda when the function accepting the lambda is marked `inline` (and the parameter is not `noinline` or `crossinline`).
*/
