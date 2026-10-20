package uz.ttpu.lablatest

import org.junit.Assert.*
import org.junit.Test
import task07.onlyOfType
import task08.kilobytes
import task08.withVat
import task08.wordCount
import task10.addGrade
import task10.best
import task10.Gradebook
import task10.Grades
import task13.typeName
import java.io.ByteArrayInputStream

class TasksUnitTest {

    @Test
    fun testTask01_ask() {
        val originalIn = System.`in`
        try {
            val input = "abc\n200\n35\n"
            System.setIn(ByteArrayInputStream(input.toByteArray()))

            var attempts = 0
            val age = task01.ask("Your age (0-120): ") { text ->
                attempts++
                text.toIntOrNull()?.takeIf { it in 0..120 }
            }
            assertEquals(35, age)
            assertEquals(3, attempts)
        } finally {
            System.setIn(originalIn)
        }
    }

    @Test
    fun testTask02_addTask() {
        task02.pending.clear()
        var savedCalled = false
        var reminderCalled = false

        task02.addTask(
            save = { savedCalled = true },
            reminder = { reminderCalled = true }
        )

        assertTrue(savedCalled)
        assertFalse(reminderCalled)
        assertEquals(1, task02.pending.size)

        task02.pending[0]()
        assertTrue(reminderCalled)
    }

    @Test
    fun testTask03_findFirstAndFindAll() {
        task03.findFirst("ali")
        task03.findAll("ali")
        task03.findWithEach("ali")
        
        var count = 0
        task03.each(listOf("a", "b", "c")) { count++ }
        assertEquals(3, count)
    }

    @Test
    fun testTask04_command() {
        var executed = false
        val cmd = task04.command { executed = true }
        assertNotNull(cmd)
        cmd.run()
        assertTrue(executed)
    }

    @Test
    fun testTask05_orSkip() {
        val valid = task05.orSkip("25.5") { 0.0 }
        assertEquals(25.5, valid, 0.001)

        val invalid = task05.orSkip("abc") { 99.0 }
        assertEquals(99.0, invalid, 0.001)
    }

    @Test
    fun testTask07_onlyOfTypeAndParseToken() {
        assertEquals(10, task07.parseToken("10"))
        assertEquals(2.5, task07.parseToken("2.5"))
        assertEquals(true, task07.parseToken("true"))
        assertEquals("apples", task07.parseToken("apples"))

        val mixed: List<Any> = listOf(10, "apples", 2.5, 7, "pears", true, false)
        assertEquals(listOf(10, 7), mixed.onlyOfType<Int>())
        assertEquals(listOf(2.5), mixed.onlyOfType<Double>())
        assertEquals(listOf(true, false), mixed.onlyOfType<Boolean>())
        assertEquals(listOf("apples", "pears"), mixed.onlyOfType<String>())
    }

    @Test
    fun testTask08_inlineProperties() {
        assertEquals(112.0, 100.0.withVat, 0.001)
        assertEquals(4, "the quick  brown fox".wordCount)
        assertEquals(5120, 5.kilobytes)
    }

    @Test
    fun testTask09_debugLogger() {
        task09.debugEnabled = false
        var built = false
        task09.debug {
            built = true
            "test"
        }
        assertFalse(built)

        task09.debugEnabled = true
        task09.debug {
            built = true
            "test"
        }
        assertTrue(built)
    }

    @Test
    fun testTask10_gradebook() {
        val book: Gradebook = mutableMapOf()
        book.addGrade("Ali", 85)
        book.addGrade("Ali", 70)

        assertEquals(listOf(85, 70), book["Ali"])
        val grades: Grades = book["Ali"]!!
        assertEquals(85, grades.best())
    }

    @Test
    fun testTask11_validatorsAndAliases() {
        assertTrue(task11.isEmail("a@b.uz"))
        assertFalse(task11.isEmail("bad"))

        assertTrue(task11.isStrongPassword("secret123"))
        assertFalse(task11.isStrongPassword("short"))

        assertTrue(task11.startsWithCode("+998901234567"))
        assertFalse(task11.startsWithCode("12345"))

        assertEquals(2, task11.countMatching(listOf("+998901", "+998902", "12345"), task11.startsWithCode))
        assertEquals(3.28084, task11.toFeet(1.0), 0.0001)
    }

    @Test
    fun testTask12_contactEquality() {
        val c1 = task12.Contact("Ali", "991234567")
        val c2 = task12.Contact("Ali K", "991234567")
        val c3 = task12.Contact("Bobur", "997654321")

        assertEquals(c1, c2)
        assertNotEquals(c1, c3)
        assertEquals("Ali (991234567)", c1.toString())
        assertEquals(c1.hashCode(), c2.hashCode())

        val set = hashSetOf(c1)
        assertTrue(c2 in set)
    }

    @Test
    fun testTask13_describeAndTypeName() {
        assertEquals("Int", 5.typeName())
        assertEquals("String", "x".typeName())

        assertEquals("a number, doubled it is 14", task13.describe(7))
        assertEquals("a decimal number, doubled it is 3.0", task13.describe(1.5))
        assertEquals("switched ON", task13.describe(true))
        assertEquals("a text of 7 characters", task13.describe("Dilnoza"))
        assertEquals("no value", task13.describe(null))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testTask14_failThrows() {
        task14.fail("Test failure")
    }

    @Test
    fun testTask15_discountFor() {
        assertEquals(20, task15.discountFor("student"))
        assertEquals(10, task15.discountFor("FRIEND"))

        try {
            task15.discountFor("invalid")
            fail("Should throw")
        } catch (e: IllegalStateException) {
            assertEquals("Unknown coupon 'invalid'", e.message)
        }
    }
}
