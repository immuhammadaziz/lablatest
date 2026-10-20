package task08

const val VAT_RATE = 0.12

inline val Double.withVat: Double
    get() = this * (1 + VAT_RATE)

val String.wordCount: Int
    inline get() = split(" ").count { it.isNotBlank() }

inline val Int.kilobytes: Int
    get() = this * 1024

fun main() {
    print("Price without VAT: ")
    val price = readln().trim().toDoubleOrNull()
    if (price == null) {
        println("Not a number")
        return
    }
    println("Price with VAT: ${"%.2f".format(price.withVat)}")
    print("Write a sentence: ")
    val sentence = readln()
    println("Words: ${sentence.wordCount}")

    print("Enter size in KB: ")
    val kb = readln().trim().toIntOrNull() ?: 1
    println("$kb KB = ${kb.kilobytes} bytes")
}

/*
Task 8 Questions & Answers:
1. Inputs 100 & "the quick  brown fox":
   - Price with VAT: 112.00
   - Words: 4

2. Property inline syntax comparison:
   - `Double.withVat` is marked `inline` on the property declaration as a whole.
   - `String.wordCount` is marked `inline` on its getter (`inline get()`).
   Both behave identically for getter inlining at the call sites.

3. Adding `inline val taxRate: Double = 0.12`:
   Error: "Inline property cannot have a backing field".
   Why: Inlining expands property accessors directly into calling code. Memory storage (backing fields) cannot be inlined or expanded into external call sites.

4. Added `Int.kilobytes` inline property returning `this * 1024`.
*/
