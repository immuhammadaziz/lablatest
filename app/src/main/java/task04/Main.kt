package task04

inline fun command(crossinline action: () -> Unit): Runnable =
    Runnable { action() }

fun main() {
    val commands = mapOf(
        "1" to command { println("Hello!") },
        "2" to command { println("Goodbye!") },
        "3" to command { println("Time to rest.") },
        "4" to command { println("Settings opened") },
    )
    print("Choose 1, 2, 3 or 4: ")
    val chosen = commands[readln().trim()]
    if (chosen == null) println("Unknown command") else chosen.run()
}

/*
Task 4 Questions & Answers:
1. Compiler error message & parameter/modifier:
   "Can't inline 'action' here: it may contain non-local returns. Add 'crossinline' modifier to parameter declaration 'action'".
   Names parameter `action`, suggests modifier `crossinline`.

2. Running with 2, then 9:
   Input 2 -> "Goodbye!"
   Input 9 -> "Unknown command"

3. Changing first command to `command { println("Hello!"); return }`:
   Compiler error: "Return is not allowed here".
   Why: `crossinline` explicitly forbids non-local `return` statements in the passed lambda because the lambda is invoked inside an execution boundary (a `Runnable` instance) where a non-local return to the outer function (`main`) is impossible at runtime.

4. Added 4th command ("4" -> "Settings opened").
*/
