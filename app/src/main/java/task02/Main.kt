package task02

val pending = mutableListOf<() -> Unit>()

inline fun addTask(save: () -> Unit, noinline reminder: () -> Unit) {
    save()
    pending.add(reminder)
}

fun main() {
    print("Task name: ")
    val name = readln().trim()
    addTask(
        save = { println("Saved: $name") },
        reminder = { println("Reminder: finish '$name'") },
    )
    println("Pending reminders: ${pending.size}")
    pending.forEach { it() }
}

/*
Task 2 Questions & Answers:
1. Initial error message:
   "Illegal usage of inline-parameter 'reminder' ... Add 'noinline' modifier to the parameter declaration".
   Parameter named: `reminder`. Suggestion: add `noinline`.

2. After adding `noinline` to `reminder` and running with "Write report":
   Output order:
   Task name: Write report
   Saved: Write report
   Pending reminders: 1
   Reminder: finish 'Write report'

3. Adding `pending.add(save)`:
   Compiler says: "Illegal usage of inline-parameter 'save' ... Add 'noinline' modifier to the parameter declaration".
   Why: `save` can be invoked in place when inlined, but cannot be stored in a collection because inlined lambdas do not exist as object references at runtime unless marked `noinline`.

4. Where `reminder` lives during `pending.forEach { it() }`:
   It lives inside an object (a lambda instance implementing Function0) stored in the `pending` heap list.
   If it were inlined, no heap object would exist to reference later after `addTask` returns.
*/
