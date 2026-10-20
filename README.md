# Laboratory Work — Kotlin Special Keywords

Software Mobile Development — Laboratory Work (Lecture 11: Special Keywords)

This repository contains implementations and solutions for 15 laboratory tasks on Kotlin Special Keywords:
`inline`, `noinline`, `crossinline`, non-local returns, non-local `break` and `continue`, `reified` type parameters, `inline` properties, `@PublishedApi`, `typealias`, `Any`, and `Nothing`.

---

## 📚 Tasks Overview

### Part A — Inline functions (Tasks 1–5)
* **Task 1 (`task01`)**: `inline` functions, `ask` prompt loop with attempt counting, and Kotlin bytecode decompilation analysis.
* **Task 2 (`task02`)**: `noinline` parameters for storing lambda callbacks in heap collections (`pending`).
* **Task 3 (`task03`)**: Non-local `return` in `forEach` vs local `return@forEach` and custom `inline fun each`.
* **Task 4 (`task04`)**: `crossinline` parameters for higher-order inline functions wrapping lambdas inside execution contexts (`Runnable`).
* **Task 5 (`task05`)**: Non-local `break` and `continue` inside lambdas of `inline` functions (`orSkip`, `let`, `run`).

### Part B — Reified types, inline properties, public API (Tasks 6–9)
* **Task 6 (`task06`)**: `reified` type parameters in generic type parsing (`readValue<T>`) supporting `Int`, `Double`, `Boolean`, `Long`, and `String`.
* **Task 7 (`task07`)**: `reified` list filtering using extension `List<Any>.onlyOfType<T>()` and token parsing.
* **Task 8 (`task08`)**: `inline` properties (`Double.withVat`, `String.wordCount`, `Int.kilobytes`).
* **Task 9 (`task09`)**: `@PublishedApi` internal variables for public-API inline functions and lazy logger evaluation.

### Part C — typealias, Any and Nothing (Tasks 10–15)
* **Task 10 (`task10`)**: `typealias Gradebook` & `typealias Grades = MutableList<Int>` with extension `Grades.best()`.
* **Task 11 (`task11`)**: Function `typealias` (`Validator`, `Predicate<T>`, `Rule`), value type aliases (`Meters`, `Feet`), and room measurement demo.
* **Task 12 (`task12`)**: Structural (`==`) vs referential (`===`) equality, overriding `equals`, `hashCode`, and `toString` in `Contact`.
* **Task 13 (`task13`)**: Type checking (`is`), safe casting (`as?`), unsafe casting (`as`), and `Any.typeName()` extension.
* **Task 14 (`task14`)**: `fail(message): Nothing` for non-null type inference (`Int? ?: Nothing` -> `Int`) and control flow analysis.
* **Task 15 (`task15`)**: Interactive loop `runCalculator(): Nothing`, coupon discount check `discountFor`, and covariant `List<Nothing>`.

---

## 🛠️ Requirements & Setup

* **Language**: Kotlin 2.2.10+
* **JDK**: 17+
* **Build System**: Gradle 9.8 (Android Application Module `:app`)

---

## 🧪 Running Tests

Run unit tests across all 15 tasks using Gradle:

```bash
./gradlew testDebugUnitTest
```

---

## 📁 Project Structure

```
app/src/main/java/
├── task01/Main.kt
├── task02/Main.kt
├── task03/Main.kt
├── task04/Main.kt
├── task05/Main.kt
├── task06/Main.kt
├── task07/Main.kt
├── task08/Main.kt
├── task09/Main.kt
├── task10/Main.kt
├── task11/Main.kt
├── task12/Main.kt
├── task13/Main.kt
├── task14/Main.kt
└── task15/Main.kt
```
