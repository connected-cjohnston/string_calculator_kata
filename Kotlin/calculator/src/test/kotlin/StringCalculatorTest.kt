import org.junit.jupiter.api.assertThrows
import java.lang.Exception
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

// 1. An empty string returns zero `'' => 0`
// 2. A single number returns the value `'1' => 1` `'2' => 2`
// 3. Two numbers, comma delimited, returns the sum `'1,2' => 3` `'10,20' => 30`
// 4. Two numbers, newline delimited, returns the sum `'1\n2' => 3`
// 5. Three numbers, delimited either way, returns the sum `'1\n2,3\n4' => 10`
// 6. Negative numbers throw an exception with the message `'-1,2,-3' => 'negatives not allowed: -1,-3'`
// 7. Numbers greater than 1000 are ignored
// 8. A single char delimiter can be defined on the first line starting with `//` (e.g `//#\n1#2` for a ‘#’ as the delimiter)
// 9. A multi char delimiter can be defined on the first line starting with `//` (e.g. `//###\n1###2` for ‘###’ as the delimiter)

class StringCalculatorTest() {
    private val calculator = StringCalculator()

    @Test
    fun shouldReturnZero_whenGivenEmptyString() {
        assertEquals(0, calculator.add(""))
    }

    @Test
    fun shouldReturnStringAsInteger_whenInputIsSingleInteger() {
        assertEquals(4, calculator.add("4"))
    }

    @Test
    fun shouldAddTwoIntegersSeparatedByComma() {
        assertEquals(3, calculator.add("1,2"))
    }

    @Test
    fun shouldAddTwoIntegers_whenSeparatedByANewline() {
        assertEquals(3, calculator.add("1\n2"))
    }

    @Test
    fun shouldAddIntegers_whenUsingBothDelimiters() {
        assertEquals(7, calculator.add("1,2\n4"))
    }

    @Test
    fun shouldThrowException_whenNegativeNumbersAreFound() {
        val exception = assertFailsWith<Exception>(
            block = { calculator.add("-1,2,-3") }
        )
        assertEquals("negatives not allowed: -1,-3", exception.message)
    }

    @Test
    fun shouldIgnoreNumbersGreaterThanOneThousand() {
        assertEquals(14, calculator.add("10,4,1001"))
    }

    @Test
    fun shouldAllowCustomDelimiter() {
        assertEquals(3, calculator.add("//#\n1#2"))
    }

    @Test
    fun shouldAllowMultiCharacterCustomDelimiter() {
        assertEquals(5, calculator.add("//###\n3###2"))
    }
}