package task12

class Contact(val name: String, val phone: String) {
    override fun toString(): String = "$name ($phone)"

    override fun equals(other: Any?): Boolean =
        other is Contact && other.phone == phone

    override fun hashCode(): Int = phone.hashCode()
}

fun readContact(): Contact {
    print("Name: ")
    val name = readln().trim()
    print("Phone: ")
    val phone = readln().trim()
    return Contact(name, phone)
}

fun main() {
    println("--- first contact ---")
    val a = readContact()
    println("--- second contact ---")
    val b = readContact()
    println("a toString: $a")
    println("a == b: ${a == b}")
    println("a === b: ${a === b}")
    val book = hashSetOf(a)
    println("book contains b: ${b in book}")
}

/*
Task 12 Questions & Answers:
1. Without custom `equals`/`hashCode`/`toString`:
   - `println(a)` prints object address string like `task12.Contact@3feba861` from `Any.toString()` / `java.lang.Object.toString()`.
   - `a == b` is false because default `equals` checks referential identity (`===`).
   - `==` calls `Any.equals(Any?)`; `===` checks memory reference equality.

2. With custom overrides (phone-based equality):
   - `println(a)` prints "Ali (991234567)".
   - `a == b` becomes true because phone numbers match.
   - `a === b` is still false (separate memory allocations).
   - `book contains b` becomes true.

3. Deleting `hashCode()` override:
   - `a == b` remains true, but `b in book` returns FALSE!
   Why: Hash-based collections (`HashSet`/`HashMap`) use `hashCode()` to find the storage bucket first. If `hashCode()` differs, the collection looks in the wrong bucket and never calls `equals()`.

4. Why `equals` accepts `Any?`:
   It overrides `Any.equals(other: Any?)`. `other is Contact` evaluates to `false` when `other` is null.
*/
