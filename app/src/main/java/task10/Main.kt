package task10

typealias Grades = MutableList<Int>
typealias Gradebook = MutableMap<String, Grades>

fun Gradebook.addGrade(student: String, grade: Int) {
    getOrPut(student) { mutableListOf() }.add(grade)
}

fun Grades.best(): Int? = maxOrNull()

fun main() {
    val book: Gradebook = mutableMapOf()
    println("Enter lines like: Ali 85   (type 'end' to stop)")
    while (true) {
        val line = readln().trim()
        if (line == "end") break
        val parts = line.split(" ")
        val grade = parts.getOrNull(1)?.toIntOrNull()
        if (parts.size != 2 || grade == null) {
            println("Use: name grade")
            continue
        }
        book.addGrade(parts[0], grade)
    }
    for ((name, grades) in book) {
        println("$name: ${grades.joinToString()}  average = ${"%.1f".format(grades.average())}  best = ${grades.best()}")
    }
}

/*
Task 10 Questions & Answers:
1. Inputs: "Ali 85", "Dilnoza 90", "Ali 70", "bad", "Bobur x", "end":
   - "bad" rejected because `parts.size != 2`.
   - "Bobur x" rejected because `grade == null` ("x" is not an integer).

2. Type underlying `Gradebook`:
   `MutableMap<String, MutableList<Int>>`.

3. Changing extension target to `MutableMap<String, MutableList<Int>>`:
   Compiles and runs identically. This proves `typealias` does not create a new type at byte-level, but provides an alternative name for existing generic types.

4. Added `typealias Grades = MutableList<Int>` and extension `fun Grades.best(): Int?`.
*/
